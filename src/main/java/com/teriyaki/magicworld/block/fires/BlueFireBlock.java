package com.teriyaki.magicworld.block.fires;

import com.teriyaki.magicworld.block.ColoredFireBlock;
import net.minecraft.item.DyeColor;

public class BlueFireBlock extends ColoredFireBlock {
    public static final DyeColor dyeColor = DyeColor.BLUE;
    private static final int colorValue = 1713578;
    public static final int lightLevel = 10;

    public BlueFireBlock() {
        super(dyeColor, lightLevel, colorValue);
    }
}
