package com.teriyaki.magicworld.block.fires;

import com.teriyaki.magicworld.block.ColoredFireBlock;
import net.minecraft.item.DyeColor;

public class LightGrayFireBlock extends ColoredFireBlock {
    public static final DyeColor dyeColor = DyeColor.LIGHT_GRAY;
    public static final int lightLevel = 12;

    public LightGrayFireBlock() {
        super(dyeColor, lightLevel, dyeColor.getColorValue());
    }
}
