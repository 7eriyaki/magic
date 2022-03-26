package com.teriyaki.magicworld.block.fires;

import com.teriyaki.magicworld.block.ColoredFireBlock;
import net.minecraft.item.DyeColor;

public class LimeFireBlock extends ColoredFireBlock {
    public static final DyeColor dyeColor = DyeColor.LIME;
    public static final int lightLevel = 12;

    public LimeFireBlock() {
        super(dyeColor, lightLevel, dyeColor.getColorValue());
    }
}