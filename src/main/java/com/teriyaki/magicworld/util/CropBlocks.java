package com.teriyaki.magicworld.util;

import com.teriyaki.magicworld.block.ModBlocks;
import com.teriyaki.magicworld.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.properties.BlockStateProperties;

import java.util.ArrayList;

public enum CropBlocks {
    POTATO(Blocks.POTATOES, Items.POTATO, 7, BlockStateProperties.AGE_0_7),
    WHEAT(Blocks.WHEAT, Items.WHEAT_SEEDS, 7, BlockStateProperties.AGE_0_7),
    CARROT(Blocks.CARROTS, Items.CARROT, 7, BlockStateProperties.AGE_0_7),
    BEETROOT(Blocks.BEETROOTS, Items.BEETROOT_SEEDS, 3, BlockStateProperties.AGE_0_3),
    NETHER_WART(Blocks.NETHER_WART, Items.NETHER_WART, 3, BlockStateProperties.AGE_0_3),
    COCO_BEANS(Blocks.COCOA, Items.COCOA_BEANS, 2, BlockStateProperties.AGE_0_2),
    ZUCCHINI(ModBlocks.ZUCCHINI_CROP.get(), ModItems.ZUCCHINI_SEED.get(), 7, BlockStateProperties.AGE_0_7);

    private final Block cropBlock;
    private final Item seedsItem;
    private final int maxAge;
    private final IntegerProperty ageProperty;

    private static final ArrayList<Block> cropBlocksList = new ArrayList<>();

    static {
        cropBlocksList.add(Blocks.POTATOES);
        cropBlocksList.add(Blocks.WHEAT);
        cropBlocksList.add(Blocks.CARROTS);
        cropBlocksList.add(Blocks.BEETROOTS);
        cropBlocksList.add(Blocks.NETHER_WART);
        cropBlocksList.add(Blocks.COCOA);
        cropBlocksList.add(ModBlocks.ZUCCHINI_CROP.get());
    }


    CropBlocks(Block cropBlock, Item seedsItem, int maxAge, IntegerProperty ageProperty) {
        this.cropBlock = cropBlock;
        this.seedsItem = seedsItem;
        this.maxAge = maxAge;
        this.ageProperty = ageProperty;
    }

    public Block getCropBlock() {
        return this.cropBlock;
    }

    public Item getSeedsItem() {
        return this.seedsItem;
    }

    public int getMaxAge() {
        return this.maxAge;
    }

    public IntegerProperty getAgeProperty() {
        return this.ageProperty;
    }

    public static boolean isCropsBlock(Block block) {
        for (Block cropBlock : CropBlocks.cropBlocksList) {
            if (cropBlock == block) return true;
        }
        return false;
    }

    public static CropBlocks getBlockEnum(Block block) {
        if (block == POTATO.cropBlock) return POTATO;
        if (block == WHEAT.cropBlock) return WHEAT;
        if (block == CARROT.cropBlock) return CARROT;
        if (block == BEETROOT.cropBlock) return BEETROOT;
        if (block == NETHER_WART.cropBlock) return NETHER_WART;
        if (block == COCO_BEANS.cropBlock) return COCO_BEANS;
        if (block == ZUCCHINI.cropBlock) return ZUCCHINI;
        return WHEAT;
    }
}
