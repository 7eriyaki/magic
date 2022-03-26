package com.teriyaki.magicworld.util.cornerblocktools;

import net.minecraft.util.IStringSerializable;

import java.util.ArrayList;
import java.util.Arrays;

public enum CornerBlockModels implements IStringSerializable {
    AIR_CORNER("air_corner"),
    ACACIA_CORNER("acacia_corner"),
    BIRCH_CORNER("birch_corner"),
    CRIMSON_CORNER("crimson_corner"),
    DARK_OAK_CORNER("dark_oak_corner"),
    JUNGLE_CORNER("jungle_corner"),
    OAK_CORNER("oak_corner"),
    SPRUCE_CORNER("spruce_corner"),
    WARPED_CORNER("warped_corner"),
    ANDESITE_CORNER("andesite_corner"),
    BLACKSTONE_CORNER("blackstone_corner"),
    BRICK_CORNER("brick_corner"),
    COBBLESTONE_CORNER("cobblestone_corner"),
    CUT_SANDSTONE_CORNER("cut_sandstone_corner"),
    CUT_RED_SANDSTONE_CORNER("cut_red_sandstone_corner"),
    DARK_PRISMARINE_CORNER("dark_prismarine_corner"),
    DIORITE_CORNER("diorite_corner"),
    END_STONE_BRICK_CORNER("end_stone_brick_corner"),
    GRANITE_CORNER("granite_corner"),
    MOSSY_COBBLESTONE_CORNER("mossy_cobblestone_corner"),
    MOSSY_STONE_BRICK_CORNER("mossy_stone_brick_corner"),
    NETHER_BRICK_CORNER("nether_brick_corner"),
    POLISHED_ANDESITE_CORNER("polished_andesite_corner"),
    POLISHED_BLACKSTONE_BRICK_CORNER("polished_blackstone_brick_corner"),
    POLISHED_BLACKSTONE_CORNER("polished_blackstone_corner"),
    POLISHED_DIORITE_CORNER("polished_diorite_corner"),
    POLISHED_GRANITE_CORNER("polished_granite_corner"),
    PRISMARINE_BRICK_CORNER("prismarine_brick_corner"),
    PRISMARINE_CORNER("prismarine_corner"),
    PURPUR_CORNER("purpur_corner"),
    QUARTZ_CORNER("quartz_corner"),
    RED_NETHER_BRICK_CORNER("red_nether_brick_corner"),
    RED_SANDSTONE_CORNER("red_sandstone_corner"),
    SANDSTONE_CORNER("sandstone_corner"),
    SMOOTH_QUARTZ_CORNER("smooth_quartz_corner"),
    SMOOTH_RED_SANDSTONE_CORNER("smooth_red_sandstone_corner"),
    SMOOTH_STONE_CORNER("smooth_stone_corner"),
    STONE_BRICK_CORNER("stone_brick_corner"),
    STONE_CORNER("stone_corner");

    private final String name;

    CornerBlockModels(String name) {
        this.name = name;
    }

    @Override
    public String getString() {
        return name;
    }

    public static ArrayList<CornerBlockModels> getCornerBlockModels() {
        return new ArrayList<>(Arrays.asList(CornerBlockModels.values()));
    }

    public static CornerBlockModels getValueByName(String name) {
        for (CornerBlockModels model : CornerBlockModels.getCornerBlockModels()) {
            if (model.name.equals(name)) return model;
        }
        return null;
    }
}
