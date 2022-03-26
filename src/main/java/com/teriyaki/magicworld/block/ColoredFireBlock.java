package com.teriyaki.magicworld.block;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.*;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ColoredFireBlock extends FireBlock {
    public final int COLOR;
    public static final HashMap<MaterialColor, Integer> ColorLightLevelMap = new HashMap<>();
    private static final Map<Direction, BooleanProperty> FACING_TO_PROPERTY_MAP =
            SixWayBlock.FACING_TO_PROPERTY_MAP.entrySet().stream().filter(
                    (facingProperty) -> facingProperty.getKey() != Direction.DOWN).collect(Util.toMapCollector());
    private static final VoxelShape FIRE_SHAPE_UP = Block.makeCuboidShape(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape FIRE_SHAPE_WEST = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D);
    private static final VoxelShape FIRE_SHAPE_EAST = Block.makeCuboidShape(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape FIRE_SHAPE_NORTH = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D);
    private static final VoxelShape FIRE_SHAPE_SOUTH = Block.makeCuboidShape(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D);
    private final Map<BlockState, VoxelShape> stateToShapeMap;
    private final Object2IntMap<Block> encouragements = new Object2IntOpenHashMap<>();
    private final Object2IntMap<Block> flammabilities = new Object2IntOpenHashMap<>();

    /**
     * Constructor for all colored fire blocks.
     *
     * @param dyeColor color of flame
     * @param lightLevel    light level emitted by block
     */
    public ColoredFireBlock(DyeColor dyeColor, int lightLevel, int colorInt) {
        super(AbstractBlock.Properties.create(Material.FIRE, dyeColor)
                .noDrops()
                .notSolid()
                .zeroHardnessAndResistance()
                .setLightLevel((state) -> lightLevel)
                .sound(SoundType.CLOTH));
        this.COLOR = colorInt;
        this.stateToShapeMap = ImmutableMap.copyOf(this.stateContainer.getValidStates().stream().filter((state) -> state.get(AGE) == 0)
                .collect(Collectors.toMap(Function.identity(), ColoredFireBlock::getShapeForState)));
    }
    private static VoxelShape getShapeForState(BlockState state) {
        VoxelShape voxelshape = VoxelShapes.empty();
        if (state.get(UP)) {
            voxelshape = FIRE_SHAPE_UP;
        }

        if (state.get(NORTH)) {
            voxelshape = VoxelShapes.or(voxelshape, FIRE_SHAPE_NORTH);
        }

        if (state.get(SOUTH)) {
            voxelshape = VoxelShapes.or(voxelshape, FIRE_SHAPE_SOUTH);
        }

        if (state.get(EAST)) {
            voxelshape = VoxelShapes.or(voxelshape, FIRE_SHAPE_EAST);
        }

        if (state.get(WEST)) {
            voxelshape = VoxelShapes.or(voxelshape, FIRE_SHAPE_WEST);
        }

        return voxelshape.isEmpty() ? shapeDown : voxelshape;
    }

    /**
     * This is the primary function for determining the fire lifecycle.
     *
     * @param state   blockstate of block
     * @param worldIn the world the block is in
     * @param pos     the position of the block in the world
     * @param rand    Random() object
     */
    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        worldIn.getPendingBlockTicks().scheduleTick(pos, this, getTickCooldown(worldIn.rand));
        if (worldIn.getGameRules().getBoolean(GameRules.DO_FIRE_TICK)) {
            if (!state.isValidPosition(worldIn, pos)) {
                worldIn.removeBlock(pos, false);
            }

            BlockState blockstate = worldIn.getBlockState(pos.down());
            boolean flag = blockstate.isFireSource(worldIn, pos, Direction.UP);
            int i = state.get(AGE);
            if (!flag && worldIn.isRaining() && this.canDie(worldIn, pos) && rand.nextFloat() < 0.2F + (float) i * 0.03F) {
                worldIn.removeBlock(pos, false);
            } else {
                int j = Math.min(15, i + rand.nextInt(3) / 2);
                if (i != j) {
                    state = state.with(AGE, j);
                    worldIn.setBlockState(pos, state, 4);
                }

                if (!flag) {
                    if (!this.areNeighborsFlammable(worldIn, pos)) {
                        BlockPos blockpos = pos.down();
                        if (!worldIn.getBlockState(blockpos).isSolidSide(worldIn, blockpos, Direction.UP) || i > 3) {
                            worldIn.removeBlock(pos, false);
                        }
                        return;
                    }

                    if (i == 15 && rand.nextInt(4) == 0 && !this.canCatchFire(worldIn, pos.down(), Direction.UP)) {
                        worldIn.removeBlock(pos, false);
                        return;
                    }
                }

                boolean flag1 = worldIn.isBlockinHighHumidity(pos);
                int k = flag1 ? -50 : 0;
                this.tryCatchFire(worldIn, pos.east(), 300 + k, rand, i, Direction.WEST);
                this.tryCatchFire(worldIn, pos.west(), 300 + k, rand, i, Direction.EAST);
                this.tryCatchFire(worldIn, pos.down(), 250 + k, rand, i, Direction.UP);
                this.tryCatchFire(worldIn, pos.up(), 250 + k, rand, i, Direction.DOWN);
                this.tryCatchFire(worldIn, pos.north(), 300 + k, rand, i, Direction.SOUTH);
                this.tryCatchFire(worldIn, pos.south(), 300 + k, rand, i, Direction.NORTH);
                BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

                for (int l = -1; l <= 1; ++l) {
                    for (int i1 = -1; i1 <= 1; ++i1) {
                        for (int j1 = -1; j1 <= 4; ++j1) {
                            if (l != 0 || j1 != 0 || i1 != 0) {
                                int k1 = 100;
                                if (j1 > 1) {
                                    k1 += (j1 - 1) * 100;
                                }

                                blockpos$mutable.setAndOffset(pos, l, j1, i1);
                                int l1 = this.getNeighborEncouragement(worldIn, blockpos$mutable);
                                if (l1 > 0) {
                                    int i2 = (l1 + 40 + worldIn.getDifficulty().getId() * 7) / (i + 30);
                                    if (flag1) {
                                        i2 /= 2;
                                    }

                                    if (i2 > 0 && rand.nextInt(k1) <= i2 && (!worldIn.isRaining() || !this.canDie(worldIn, blockpos$mutable))) {
                                        int j2 = Math.min(15, i + rand.nextInt(5) / 4);
                                        worldIn.setBlockState(blockpos$mutable, this.getFireWithAge(worldIn, blockpos$mutable, j2), 3);
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
    }

    /**
     * Gets the delay before this block ticks again (without counting random ticks)
     */
    private static int getTickCooldown(Random rand) {
        return 30 + rand.nextInt(10);
    }


    private void tryCatchFire(World worldIn, BlockPos pos, int chance, Random random, int age, Direction face) {
        BlockState state = worldIn.getBlockState(pos);
        int i = this.getFlammability1(state);
        if (random.nextInt(chance) < i) {
            if (random.nextInt(age + 10) < 5 && !worldIn.isRainingAt(pos)) {
                int j = Math.min(age + random.nextInt(5) / 4, 15);
                worldIn.setBlockState(pos, this.getFireWithAge(worldIn, pos, j), 3);
            } else {
                worldIn.removeBlock(pos, false);
            }

            this.catchFire1(state, worldIn, pos, face, null);
        }
    }
    private BlockState getFireWithAge(IWorld world, BlockPos pos, int age) {
        BlockState blockstate = this.getStateForPlacement(world, pos);
        if (blockstate.getBlock() instanceof ColoredFireBlock) {
            return blockstate.with(AGE, age);
        }
        return blockstate;
    }

    @Override
    protected BlockState getStateForPlacement(IBlockReader blockReader, BlockPos pos) {
        BlockPos blockpos = pos.down();
        BlockState blockstate = blockReader.getBlockState(blockpos);
        if (!this.canCatchFire(blockReader, pos, Direction.UP) && !blockstate.isSolidSide(blockReader, blockpos, Direction.UP)) {
            BlockState blockstate1 = this.getDefaultState();

            for(Direction direction : Direction.values()) {
                BooleanProperty booleanproperty = FACING_TO_PROPERTY_MAP.get(direction);
                if (booleanproperty != null) {
                    blockstate1 = blockstate1.with(booleanproperty, this.canCatchFire(blockReader, pos.offset(direction), direction.getOpposite()));
                }
            }

            return blockstate1;
        } else {
            return this.getDefaultState();
        }
    }

    /**
     * This function gets the fire block to be placed based on the given fire powder color.
     *
     * @param blockReader   current world for getting blocks
     * @param pos           position for fire to be placed
     * @param powderColor   Color of powder, returned fire should match
     * @return              the default BlockState of the fire block matching the given powder color
     */
    public BlockState getStateForFirePowderUse(IBlockReader blockReader, BlockPos pos, DyeColor powderColor) {
        BlockPos blockpos = pos.down();
        BlockState blockstate = blockReader.getBlockState(blockpos);
        if (!this.canCatchFire(blockReader, pos, Direction.UP) && !blockstate.isSolidSide(blockReader, blockpos, Direction.UP)) {
            BlockState blockstate1 = this.getDefaultState();

            for(Direction direction : Direction.values()) {
                BooleanProperty booleanproperty = FACING_TO_PROPERTY_MAP.get(direction);
                if (booleanproperty != null) {
                    blockstate1 = blockstate1.with(booleanproperty, this.canCatchFire(blockReader, pos.offset(direction), direction.getOpposite()));
                }
            }

            return blockstate1;
        } else {
            return this.getDefaultState();
        }
    }
    public int getFlammability1(BlockState state) {
        return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.get(BlockStateProperties.WATERLOGGED) ? 0 : this.flammabilities.getInt(state.getBlock());
    }

    // catchFire()
    private void catchFire1(BlockState state, World world, BlockPos pos, @Nullable Direction face, @Nullable LivingEntity igniter)
    {
        state.getBlock().catchFire(state, world, pos, face, igniter);
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.down();
        return worldIn.getBlockState(blockpos).isSolidSide(worldIn, blockpos, Direction.UP) || this.areNeighborsFlammable(worldIn, pos);
    }
    protected boolean canDie(World worldIn, BlockPos pos) {
        return worldIn.isRainingAt(pos) || worldIn.isRainingAt(pos.west()) || worldIn.isRainingAt(pos.east()) || worldIn.isRainingAt(pos.north()) || worldIn.isRainingAt(pos.south());
    }

    private boolean areNeighborsFlammable(IBlockReader worldIn, BlockPos pos) {
        for (Direction direction : Direction.values()) {
            if (this.canCatchFire(worldIn, pos.offset(direction), direction.getOpposite())) {
                return true;
            }
        }
        return false;
    }

    private int getNeighborEncouragement(IWorldReader worldIn, BlockPos pos) {
        if (!worldIn.isAirBlock(pos)) {
            return 0;
        } else {
            int i = 0;

            for (Direction direction : Direction.values()) {
                BlockState blockstate = worldIn.getBlockState(pos.offset(direction));
                i = Math.max(this.getFireSpreadSpeed1(blockstate), i);
            }
            return i;
        }
    }
    private int getFireSpreadSpeed1(BlockState state) {
        return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.get(BlockStateProperties.WATERLOGGED) ? 0 : this.encouragements.getInt(state.getBlock());
    }

    /**
     * Used to register the list of flammable blocks for each ColoredFireBlock.
     * @param blockIn       Flammable block to add to fire block
     * @param encouragement int value representing how quickly it catches on fire
     * @param flammability  int value representing how flammable a block is
     */
    public void setFireInfo(Block blockIn, int encouragement, int flammability) {
        if (blockIn == Blocks.AIR) throw new IllegalArgumentException("Tried to set air on fire... This is bad.");
        this.encouragements.put(blockIn, encouragement);
        this.flammabilities.put(blockIn, flammability);
    }

    @Override
    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onBlockAdded(state, worldIn, pos, oldState, isMoving);
        worldIn.getPendingBlockTicks().scheduleTick(pos, this, getTickCooldown(worldIn.rand));
    }

    /**
     * Update the provided state given the provided neighbor facing and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
     * returns its solidified counterpart.
     * Note that this method should ideally consider only the specific face passed in.
     */
    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        return this.isValidPosition(stateIn, worldIn, currentPos) ? this.getFireWithAge(worldIn, currentPos, stateIn.get(AGE)) : Blocks.AIR.getDefaultState();
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.stateToShapeMap.get(state.with(AGE, 0));
    }

    /**
     * Gets the Colored fire block that corresponds to the given dye color
     * @param color dye color of desired fire
     * @return      the ColoredFireBlock with the provided color
     */
    public static ColoredFireBlock getFireByColor(DyeColor color) {

        switch (color.getColorValue()) {
            case 1908001: // BLACK
                return (ColoredFireBlock) ModBlocks.BLACK_FIRE.get();
            case 3949738: // BLUE
                return (ColoredFireBlock) ModBlocks.BLUE_FIRE.get();
            case 8606770: // BROWN
                return (ColoredFireBlock) ModBlocks.BROWN_FIRE.get();
            case 1481884: // CYAN
                return (ColoredFireBlock) ModBlocks.CYAN_FIRE.get();
            case 4673362: // GRAY
                return (ColoredFireBlock) ModBlocks.GRAY_FIRE.get();
            case 6192150: // GREEN
                return (ColoredFireBlock) ModBlocks.GREEN_FIRE.get();
            case 3847130: // LIGHT_BLUE
                return (ColoredFireBlock) ModBlocks.LIGHT_BLUE_FIRE.get();
            case 10329495: // LIGHT_GRAY
                return (ColoredFireBlock) ModBlocks.LIGHT_GRAY_FIRE.get();
            case 8439583: // LIME
                return (ColoredFireBlock) ModBlocks.LIME_FIRE.get();
            case 13061821: // MAGENTA
                return (ColoredFireBlock) ModBlocks.MAGENTA_FIRE.get();
            case 16351261: // ADOBE (ORANGE)
                return (ColoredFireBlock) ModBlocks.ORANGE_FIRE.get();
            case 15961002: // PINK
                return (ColoredFireBlock) ModBlocks.PINK_FIRE.get();
            case 8991416: // Purple
                return (ColoredFireBlock) ModBlocks.PURPLE_FIRE.get();
            case 11546150: // RED
                return (ColoredFireBlock) ModBlocks.RED_FIRE.get();
            case 16383998: // SNOW (WHITE)
                return (ColoredFireBlock) ModBlocks.WHITE_FIRE.get();
            case 16701501: // YELLOW
                return (ColoredFireBlock) ModBlocks.YELLOW_FIRE.get();
            default:
                return new ColoredFireBlock(DyeColor.ORANGE, 15, DyeColor.ORANGE.getColorValue());
        }
    }
}
