package com.teriyaki.magicworld.block.fires;

import com.teriyaki.magicworld.block.ColoredFireBlock;
import net.minecraft.item.DyeColor;

public class BrownFireBlock extends ColoredFireBlock {
    public static final DyeColor dyeColor = DyeColor.BROWN;
    public static final int lightLevel = 8;

    public BrownFireBlock() {
        super(dyeColor, lightLevel, dyeColor.getColorValue());
    }
}
