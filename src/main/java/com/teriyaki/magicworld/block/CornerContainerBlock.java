package com.teriyaki.magicworld.block;

import com.teriyaki.magicworld.util.cornerblocktools.CornerBlockModels;
import com.teriyaki.magicworld.util.cornerblocktools.CornerPosition;
import com.teriyaki.magicworld.util.cornerblocktools.CornerProperty;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Objects;

public class CornerContainerBlock extends Block {

    private static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    private static final BooleanProperty CORNER_POS0 = BooleanProperty.create("corner_pos0");
    private static final BooleanProperty CORNER_POS1 = BooleanProperty.create("corner_pos1");
    private static final BooleanProperty CORNER_POS2 = BooleanProperty.create("corner_pos2");
    private static final BooleanProperty CORNER_POS3 = BooleanProperty.create("corner_pos3");
    private static final BooleanProperty CORNER_POS4 = BooleanProperty.create("corner_pos4");
    private static final BooleanProperty CORNER_POS5 = BooleanProperty.create("corner_pos5");
    private static final BooleanProperty CORNER_POS6 = BooleanProperty.create("corner_pos6");
    private static final BooleanProperty CORNER_POS7 = BooleanProperty.create("corner_pos7");
    private static final CornerProperty CORNER_BLOCK_MODEL = CornerProperty.create("corner_block_model");

    public CornerContainerBlock(Material blockMaterial) {
        super(AbstractBlock.Properties.create(blockMaterial));
        this.setDefaultState(this.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(CORNER_POS0, false)
                .with(CORNER_POS1, false)
                .with(CORNER_POS2, false)
                .with(CORNER_POS3, false)
                .with(CORNER_POS4, false)
                .with(CORNER_POS5, false)
                .with(CORNER_POS6, false)
                .with(CORNER_POS7, false)
        );
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        VoxelShape shape = VoxelShapes.create(0,0,0,0,0,0);
        if (state.get(CORNER_POS0)) {
            shape = VoxelShapes.or(shape, CornerPosition.CORNER_POS0.getShape());
        }
        else if (state.get(CORNER_POS1)) {
            shape = VoxelShapes.or(shape, CornerPosition.CORNER_POS1.getShape());
        }
        else if (state.get(CORNER_POS2)) {
            shape = VoxelShapes.or(shape, CornerPosition.CORNER_POS2.getShape());
        }
        else if (state.get(CORNER_POS3)) {
            shape = VoxelShapes.or(shape, CornerPosition.CORNER_POS3.getShape());
        }
        else if (state.get(CORNER_POS4)) {
            shape = VoxelShapes.or(shape, CornerPosition.CORNER_POS4.getShape());
        }
        else if (state.get(CORNER_POS5)) {
            shape = VoxelShapes.or(shape, CornerPosition.CORNER_POS5.getShape());
        }
        else if (state.get(CORNER_POS6)) {
            shape = VoxelShapes.or(shape, CornerPosition.CORNER_POS6.getShape());
        }
        else if (state.get(CORNER_POS7)) {
            shape = VoxelShapes.or(shape, CornerPosition.CORNER_POS7.getShape());
        }

        return shape;
    }

    public BlockState getStateForPlacement(BlockItemUseContext context, CornerBlockModels model) {
        Vector3d lookVector = Objects.requireNonNull(context.getPlayer()).getLookVec();
        int cornerPosition;
        String cornerName;

        boolean isDown = lookVector.y <= 0;  // DOWN  -> y = -1  // UP    -> y = 1
        boolean isWest = lookVector.x <= 0;  // WEST  -> x = -1  // EAST  -> x = 1
        boolean isNorth = lookVector.z <= 0; // NORTH -> z = -1  // SOUTH -> z = 1

        if (isDown) {
            if (isWest) {
                cornerName = isNorth ? "corner_pos0" : "corner_pos3";
            }
            else {
                cornerName = isNorth ? "corner_pos1" : "corner_pos2";
            }
        }
        else {
            if (isWest) {
                cornerName = isNorth ? "corner_pos4" : "corner_pos7";
            }
            else {
                cornerName = isNorth ? "corner_pos5" : "corner_pos6";
            }
        }
        cornerPosition = CornerPosition.getPosByName(cornerName);

        // First placement will always have only one corner
        return getStateWithUpdatedCornerPos(cornerPosition).with(CORNER_BLOCK_MODEL, model);
    }

    private BlockState getStateWithUpdatedCornerPos(int cornerPosInt) {
        switch(cornerPosInt) {
            case 0:
                return this.getDefaultState().with(CORNER_POS0, true);
            case 1:
                return this.getDefaultState().with(CORNER_POS1, true);
            case 2:
                return this.getDefaultState().with(CORNER_POS2, true);
            case 3:
                return this.getDefaultState().with(CORNER_POS3, true);
            case 4:
                return this.getDefaultState().with(CORNER_POS4, true);
            case 5:
                return this.getDefaultState().with(CORNER_POS5, true);
            case 6:
                return this.getDefaultState().with(CORNER_POS6, true);
            case 7:
            default:
                return this.getDefaultState().with(CORNER_POS7, true);
        }
    }

    public String getCornerModel() {
        return "";
    }
    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(CORNER_POS0);
        builder.add(CORNER_POS1);
        builder.add(CORNER_POS2);
        builder.add(CORNER_POS3);
        builder.add(CORNER_POS4);
        builder.add(CORNER_POS5);
        builder.add(CORNER_POS6);
        builder.add(CORNER_POS7);
        builder.add(CORNER_BLOCK_MODEL);
    }
}
