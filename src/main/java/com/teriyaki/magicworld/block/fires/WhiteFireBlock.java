package com.teriyaki.magicworld.block.fires;

import com.teriyaki.magicworld.block.ColoredFireBlock;
import net.minecraft.item.DyeColor;

public class WhiteFireBlock extends ColoredFireBlock {
    public static final DyeColor dyeColor = DyeColor.WHITE;
    public static final int colorInt = DyeColor.WHITE.getColorValue();
    public static final int lightLevel = 15;

    public WhiteFireBlock() {
        super(dyeColor, lightLevel, colorInt);
    }
}
