package com.teriyaki.magicworld.block.fires;

import com.teriyaki.magicworld.block.ColoredFireBlock;
import net.minecraft.item.DyeColor;

public class PinkFireBlock extends ColoredFireBlock {
    public static final DyeColor dyeColor = DyeColor.PINK;
    private static final int colorValue = 15954841;
    public static final int lightLevel = 12;

    public PinkFireBlock() {
        super(dyeColor, lightLevel, colorValue);
    }
}
