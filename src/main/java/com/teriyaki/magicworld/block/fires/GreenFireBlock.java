package com.teriyaki.magicworld.block.fires;

import com.teriyaki.magicworld.block.ColoredFireBlock;
import net.minecraft.item.DyeColor;

public class GreenFireBlock extends ColoredFireBlock {
    public static final DyeColor dyeColor = DyeColor.GREEN;
    public static final int lightLevel = 10;

    public GreenFireBlock() {
        super(dyeColor, lightLevel, dyeColor.getColorValue());
    }
}
