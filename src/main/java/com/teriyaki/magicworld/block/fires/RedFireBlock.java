package com.teriyaki.magicworld.block.fires;

import com.teriyaki.magicworld.block.ColoredFireBlock;
import net.minecraft.item.DyeColor;

public class RedFireBlock extends ColoredFireBlock {
    public static final DyeColor dyeColor = DyeColor.RED;
    private static final int colorValue = 11537410;
    public static final int lightLevel = 10;

    public RedFireBlock() {
        super(dyeColor, lightLevel, colorValue);
    }
}
