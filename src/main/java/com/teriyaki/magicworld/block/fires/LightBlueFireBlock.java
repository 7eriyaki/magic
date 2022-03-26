package com.teriyaki.magicworld.block.fires;

import com.teriyaki.magicworld.block.ColoredFireBlock;
import net.minecraft.item.DyeColor;

public class LightBlueFireBlock extends ColoredFireBlock {
    public static final DyeColor dyeColor = DyeColor.LIGHT_BLUE;
    private static final int colorValue = 8317951;
    public static final int lightLevel = 12;

    public LightBlueFireBlock() {
        super(dyeColor, lightLevel, colorValue);
    }
}
