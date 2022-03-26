package com.teriyaki.magicworld.util;

import com.teriyaki.magicworld.block.ModBlocks;
import com.teriyaki.magicworld.block.fires.*;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class FlammablesRegistry {
    private static final BlackFireBlock blackFireBlock = (BlackFireBlock) ModBlocks.BLACK_FIRE.get();
    private static final BlueFireBlock blueFireBlock = (BlueFireBlock) ModBlocks.BLUE_FIRE.get();
    private static final BrownFireBlock brownFireBlock = (BrownFireBlock) ModBlocks.BROWN_FIRE.get();
    private static final CyanFireBlock cyanFireBlock = (CyanFireBlock) ModBlocks.CYAN_FIRE.get();
    private static final GrayFireBlock grayFireBlock = (GrayFireBlock) ModBlocks.GRAY_FIRE.get();
    private static final GreenFireBlock greenFireBlock = (GreenFireBlock) ModBlocks.GREEN_FIRE.get();
    private static final LightBlueFireBlock lightBlueFireBlock = (LightBlueFireBlock) ModBlocks.LIGHT_BLUE_FIRE.get();
    private static final LightGrayFireBlock lightGrayFireBlock = (LightGrayFireBlock) ModBlocks.LIGHT_GRAY_FIRE.get();
    private static final LimeFireBlock limeFireBlock = (LimeFireBlock) ModBlocks.LIME_FIRE.get();
    private static final MagentaFireBlock magentaFireBlock = (MagentaFireBlock) ModBlocks.MAGENTA_FIRE.get();
    private static final OrangeFireBlock orangeFireBlock = (OrangeFireBlock) ModBlocks.ORANGE_FIRE.get();
    private static final PinkFireBlock pinkFireBlock = (PinkFireBlock) ModBlocks.PINK_FIRE.get();
    private static final PurpleFireBlock purpleFireBlock = (PurpleFireBlock) ModBlocks.PURPLE_FIRE.get();
    private static final RedFireBlock redFireBlock = (RedFireBlock) ModBlocks.RED_FIRE.get();
    private static final WhiteFireBlock whiteFireBlock = (WhiteFireBlock) ModBlocks.WHITE_FIRE.get();
    private static final YellowFireBlock yellowFireBlock = (YellowFireBlock) ModBlocks.YELLOW_FIRE.get();

    private static final Object[][] FLAMMABLES;

    @SubscribeEvent
    public static void register() {
        setFlammableProperties(FLAMMABLES);

        // ADDITIONAL MOD COMPATIBILITY
        // REGISTER MOD FLAMMABLES HERE
        // setFlammableProperties(MOD_FLAMMABLES);
    }

    private static void setFlammableProperties(Object[][] listOfFlammables) {
        for (Object[] block : listOfFlammables) {
            blackFireBlock.setFireInfo((Block) block[0], (int) block[1], (int) block[2]);
            blueFireBlock.setFireInfo((Block) block[0], (int) block[1], (int) block[2]);
            brownFireBlock.setFireInfo((Block) block[0], (int) block[1], (int) block[2]);
            cyanFireBlock.setFireInfo((Block) block[0], (int) block[1], (int) block[2]);
            grayFireBlock.setFireInfo((Block) block[0], (int) block[1], (int) block[2]);
            greenFireBlock.setFireInfo((Block) block[0], (int) block[1], (int) block[2]);
            lightBlueFireBlock.setFireInfo((Block) block[0], (int) block[1], (int) block[2]);
            lightGrayFireBlock.setFireInfo((Block) block[0], (int) block[1], (int) block[2]);
            limeFireBlock.setFireInfo((Block) block[0], (int) block[1], (int) block[2]);
            magentaFireBlock.setFireInfo((Block) block[0], (int) block[1], (int) block[2]);
            orangeFireBlock.setFireInfo((Block) block[0], (int) block[1], (int) block[2]);
            pinkFireBlock.setFireInfo((Block) block[0], (int) block[1], (int) block[2]);
            purpleFireBlock.setFireInfo((Block) block[0], (int) block[1], (int) block[2]);
            redFireBlock.setFireInfo((Block) block[0], (int) block[1], (int) block[2]);
            whiteFireBlock.setFireInfo((Block) block[0], (int) block[1], (int) block[2]);
            yellowFireBlock.setFireInfo((Block) block[0], (int) block[1], (int) block[2]);
        }
    }


    static {
        FLAMMABLES = new Object[][] {
            {Blocks.OAK_PLANKS, 5, 20},
            {Blocks.SPRUCE_PLANKS, 5, 20},
            {Blocks.BIRCH_PLANKS, 5, 20},
            {Blocks.JUNGLE_PLANKS, 5, 20},
            {Blocks.ACACIA_PLANKS, 5, 20},
            {Blocks.DARK_OAK_PLANKS, 5, 20},
            {Blocks.OAK_SLAB, 5, 20},
            {Blocks.SPRUCE_SLAB, 5, 20},
            {Blocks.BIRCH_SLAB, 5, 20},
            {Blocks.JUNGLE_SLAB, 5, 20},
            {Blocks.ACACIA_SLAB, 5, 20},
            {Blocks.DARK_OAK_SLAB, 5, 20},
            {Blocks.OAK_FENCE_GATE, 5, 20},
            {Blocks.SPRUCE_FENCE_GATE, 5, 20},
            {Blocks.BIRCH_FENCE_GATE, 5, 20},
            {Blocks.JUNGLE_FENCE_GATE, 5, 20},
            {Blocks.DARK_OAK_FENCE_GATE, 5, 20},
            {Blocks.ACACIA_FENCE_GATE, 5, 20},
            {Blocks.OAK_FENCE, 5, 20},
            {Blocks.SPRUCE_FENCE, 5, 20},
            {Blocks.BIRCH_FENCE, 5, 20},
            {Blocks.JUNGLE_FENCE, 5, 20},
            {Blocks.DARK_OAK_FENCE, 5, 20},
            {Blocks.ACACIA_FENCE, 5, 20},
            {Blocks.OAK_STAIRS, 5, 20},
            {Blocks.BIRCH_STAIRS, 5, 20},
            {Blocks.SPRUCE_STAIRS, 5, 20},
            {Blocks.JUNGLE_STAIRS, 5, 20},
            {Blocks.ACACIA_STAIRS, 5, 20},
            {Blocks.DARK_OAK_STAIRS, 5, 20},
            {Blocks.OAK_LOG, 5, 5},
            {Blocks.SPRUCE_LOG, 5, 5},
            {Blocks.BIRCH_LOG, 5, 5},
            {Blocks.JUNGLE_LOG, 5, 5},
            {Blocks.ACACIA_LOG, 5, 5},
            {Blocks.DARK_OAK_LOG, 5, 5},
            {Blocks.STRIPPED_OAK_LOG, 5, 5},
            {Blocks.STRIPPED_SPRUCE_LOG, 5, 5},
            {Blocks.STRIPPED_BIRCH_LOG, 5, 5},
            {Blocks.STRIPPED_JUNGLE_LOG, 5, 5},
            {Blocks.STRIPPED_ACACIA_LOG, 5, 5},
            {Blocks.STRIPPED_DARK_OAK_LOG, 5, 5},
            {Blocks.STRIPPED_OAK_WOOD, 5, 5},
            {Blocks.STRIPPED_SPRUCE_WOOD, 5, 5},
            {Blocks.STRIPPED_BIRCH_WOOD, 5, 5},
            {Blocks.STRIPPED_JUNGLE_WOOD, 5, 5},
            {Blocks.STRIPPED_ACACIA_WOOD, 5, 5},
            {Blocks.STRIPPED_DARK_OAK_WOOD, 5, 5},
            {Blocks.OAK_WOOD, 5, 5},
            {Blocks.SPRUCE_WOOD, 5, 5},
            {Blocks.BIRCH_WOOD, 5, 5},
            {Blocks.JUNGLE_WOOD, 5, 5},
            {Blocks.ACACIA_WOOD, 5, 5},
            {Blocks.DARK_OAK_WOOD, 5, 5},
            {Blocks.OAK_LEAVES, 30, 60},
            {Blocks.SPRUCE_LEAVES, 30, 60},
            {Blocks.BIRCH_LEAVES, 30, 60},
            {Blocks.JUNGLE_LEAVES, 30, 60},
            {Blocks.ACACIA_LEAVES, 30, 60},
            {Blocks.DARK_OAK_LEAVES, 30, 60},
            {Blocks.BOOKSHELF, 30, 20},
            {Blocks.TNT, 15, 100},
            {Blocks.GRASS, 60, 100},
            {Blocks.FERN, 60, 100},
            {Blocks.DEAD_BUSH, 60, 100},
            {Blocks.SUNFLOWER, 60, 100},
            {Blocks.LILAC, 60, 100},
            {Blocks.ROSE_BUSH, 60, 100},
            {Blocks.PEONY, 60, 100},
            {Blocks.TALL_GRASS, 60, 100},
            {Blocks.LARGE_FERN, 60, 100},
            {Blocks.DANDELION, 60, 100},
            {Blocks.POPPY, 60, 100},
            {Blocks.BLUE_ORCHID, 60, 100},
            {Blocks.ALLIUM, 60, 100},
            {Blocks.AZURE_BLUET, 60, 100},
            {Blocks.RED_TULIP, 60, 100},
            {Blocks.ORANGE_TULIP, 60, 100},
            {Blocks.WHITE_TULIP, 60, 100},
            {Blocks.PINK_TULIP, 60, 100},
            {Blocks.OXEYE_DAISY, 60, 100},
            {Blocks.CORNFLOWER, 60, 100},
            {Blocks.LILY_OF_THE_VALLEY, 60, 100},
            {Blocks.WITHER_ROSE, 60, 100},
            {Blocks.WHITE_WOOL, 30, 60},
            {Blocks.ORANGE_WOOL, 30, 60},
            {Blocks.MAGENTA_WOOL, 30, 60},
            {Blocks.LIGHT_BLUE_WOOL, 30, 60},
            {Blocks.YELLOW_WOOL, 30, 60},
            {Blocks.LIME_WOOL, 30, 60},
            {Blocks.PINK_WOOL, 30, 60},
            {Blocks.GRAY_WOOL, 30, 60},
            {Blocks.LIGHT_GRAY_WOOL, 30, 60},
            {Blocks.CYAN_WOOL, 30, 60},
            {Blocks.PURPLE_WOOL, 30, 60},
            {Blocks.BLUE_WOOL, 30, 60},
            {Blocks.BROWN_WOOL, 30, 60},
            {Blocks.GREEN_WOOL, 30, 60},
            {Blocks.RED_WOOL, 30, 60},
            {Blocks.BLACK_WOOL, 30, 60},
            {Blocks.VINE, 15, 100},
            {Blocks.COAL_BLOCK, 5, 5},
            {Blocks.HAY_BLOCK, 60, 20},
            {Blocks.TARGET, 15, 20},
            {Blocks.WHITE_CARPET, 60, 20},
            {Blocks.ORANGE_CARPET, 60, 20},
            {Blocks.MAGENTA_CARPET, 60, 20},
            {Blocks.LIGHT_BLUE_CARPET, 60, 20},
            {Blocks.YELLOW_CARPET, 60, 20},
            {Blocks.LIME_CARPET, 60, 20},
            {Blocks.PINK_CARPET, 60, 20},
            {Blocks.GRAY_CARPET, 60, 20},
            {Blocks.LIGHT_GRAY_CARPET, 60, 20},
            {Blocks.CYAN_CARPET, 60, 20},
            {Blocks.PURPLE_CARPET, 60, 20},
            {Blocks.BLUE_CARPET, 60, 20},
            {Blocks.BROWN_CARPET, 60, 20},
            {Blocks.GREEN_CARPET, 60, 20},
            {Blocks.RED_CARPET, 60, 20},
            {Blocks.BLACK_CARPET, 60, 20},
            {Blocks.DRIED_KELP_BLOCK, 30, 60},
            {Blocks.BAMBOO, 60, 60},
            {Blocks.SCAFFOLDING, 60, 60},
            {Blocks.LECTERN, 30, 20},
            {Blocks.COMPOSTER, 5, 20},
            {Blocks.SWEET_BERRY_BUSH, 60, 100},
            {Blocks.BEEHIVE, 5, 20},
            {Blocks.BEE_NEST, 30, 20},
        };
    }


}
