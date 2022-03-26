package com.teriyaki.magicworld.block.fires;

import com.teriyaki.magicworld.block.ColoredFireBlock;
import net.minecraft.item.DyeColor;

public class YellowFireBlock extends ColoredFireBlock {
    public static final DyeColor dyeColor = DyeColor.YELLOW;
    public static final int colorInt = 16773416;
    public static final int lightLevel = 14;

    public YellowFireBlock() {
        super(dyeColor, lightLevel, colorInt);
    }
}
