package com.teriyaki.magicworld.block.fires;

import com.teriyaki.magicworld.block.ColoredFireBlock;
import net.minecraft.item.DyeColor;

public class GrayFireBlock extends ColoredFireBlock {
    public static final DyeColor dyeColor = DyeColor.GRAY;
    public static final int lightLevel = 10;

    public GrayFireBlock() {
        super(dyeColor, lightLevel, dyeColor.getColorValue());
    }
}
