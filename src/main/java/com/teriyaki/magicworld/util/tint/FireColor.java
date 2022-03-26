package com.teriyaki.magicworld.util.tint;

import com.teriyaki.magicworld.block.ColoredFireBlock;
import com.teriyaki.magicworld.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class FireColor implements IBlockColor {
    public static final IBlockColor INSTANCE = new FireColor();

    /**
     * This method determines the color of the fire tint based on COLOR property of
     * the ColoredFireBlock.
     * @param state BlockState of the block being tinted
     * @param reader IBlockDisplayReader
     * @param pos position of block being tinted
     * @param tintIndex int representation of the color of tint
     * @return the tint color corresponding to the tint index provided
     */
    @Override
    public int getColor(BlockState state, @Nullable IBlockDisplayReader reader, @Nullable BlockPos pos, int tintIndex) {
        return ((ColoredFireBlock)state.getBlock()).COLOR;
    }

    /**
     * Registration of Fire block tints. Used to colorize the generic fire texture with the correct
     * fire color of the block.
     *
     * Called from within the client initialization.
     */
    public static void registerFireColors() {
        Minecraft.getInstance().getBlockColors().register(INSTANCE, ModBlocks.BLACK_FIRE.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, ModBlocks.BLUE_FIRE.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, ModBlocks.BROWN_FIRE.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, ModBlocks.CYAN_FIRE.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, ModBlocks.GRAY_FIRE.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, ModBlocks.GREEN_FIRE.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, ModBlocks.LIGHT_BLUE_FIRE.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, ModBlocks.LIGHT_GRAY_FIRE.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, ModBlocks.LIME_FIRE.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, ModBlocks.MAGENTA_FIRE.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, ModBlocks.ORANGE_FIRE.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, ModBlocks.PINK_FIRE.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, ModBlocks.PURPLE_FIRE.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, ModBlocks.RED_FIRE.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, ModBlocks.WHITE_FIRE.get());
        Minecraft.getInstance().getBlockColors().register(INSTANCE, ModBlocks.YELLOW_FIRE.get());
    }
}
