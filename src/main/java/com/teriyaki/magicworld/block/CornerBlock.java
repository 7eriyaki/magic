package com.teriyaki.magicworld.block;

import com.teriyaki.magicworld.util.cornerblocktools.CornerBlockModels;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class CornerBlock extends Block {

    private static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    private static final String CORNER_MODEL = "";

    public CornerBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH));
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return Block.makeCuboidShape(0, 0, 0, 8, 8, 8);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        // Check if the block is being placed on a corner container
        World world = context.getWorld();
        BlockPos placedOnPos = context.getPos().offset(context.getFace().getOpposite());
        Block placedOnBlock = world.getBlockState(placedOnPos).getBlock();
        // If player placed on corner block, block may need to be added to clicked on block instead
        if (placedOnBlock instanceof CornerContainerBlock) {
            BlockState cornerContainerBlockState = world.getBlockState(placedOnPos);
            // Check if container corner block matches corner block to be placed
            if (!( this.getCornerModel().equals(((CornerBlock)cornerContainerBlockState.getBlock()).getCornerModel()) )) {
                // Check if the block is being placed on the outer surface of the block, or an inner surface of the block

                // DO NOT PLACE NEW CONTAINER
                return world.getBlockState(context.getPos());
            }


        }
        // New container is placed as normal
        return ((CornerContainerBlock) ModBlocks.CORNER_CONTAINER_BLOCK.get()).getStateForPlacement(context, CornerBlockModels.getValueByName(this.getCornerModel()));
    }

    // OVERRIDDEN IN CHILD CLASSES
    public String getCornerModel() {return CORNER_MODEL;}

    @SuppressWarnings("deprecation")
    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
