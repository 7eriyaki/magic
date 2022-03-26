package com.teriyaki.magicworld.block.fires;

import com.teriyaki.magicworld.block.ColoredFireBlock;
import net.minecraft.item.DyeColor;

public class BlackFireBlock extends ColoredFireBlock {
    public static final DyeColor dyeColor = DyeColor.BLACK;
    public static final int lightLevel = 8;

    public BlackFireBlock() {
        super(dyeColor, lightLevel, dyeColor.getColorValue());
    }
}
