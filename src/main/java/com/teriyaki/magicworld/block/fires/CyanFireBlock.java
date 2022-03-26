package com.teriyaki.magicworld.block.fires;

import com.teriyaki.magicworld.block.ColoredFireBlock;
import net.minecraft.item.DyeColor;

public class CyanFireBlock extends ColoredFireBlock {
    public static final DyeColor dyeColor = DyeColor.CYAN;
    private static final int colorValue = 174513;
    public static final int lightLevel = 12;

    public CyanFireBlock() {
        super(dyeColor, lightLevel, colorValue);
    }
}
