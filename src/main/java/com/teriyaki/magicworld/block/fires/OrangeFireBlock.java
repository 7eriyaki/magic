package com.teriyaki.magicworld.block.fires;

import com.teriyaki.magicworld.block.ColoredFireBlock;
import net.minecraft.item.DyeColor;

public class OrangeFireBlock extends ColoredFireBlock {
    public static final DyeColor dyeColor = DyeColor.ORANGE;
    public static final int lightLevel = 10;

    public OrangeFireBlock() {
        super(dyeColor, lightLevel, dyeColor.getColorValue());
    }
}
