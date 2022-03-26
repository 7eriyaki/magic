package com.teriyaki.magicworld.block.fires;

import com.teriyaki.magicworld.block.ColoredFireBlock;
import net.minecraft.item.DyeColor;

public class PurpleFireBlock extends ColoredFireBlock {
    public static final DyeColor dyeColor = DyeColor.PURPLE;
    private static final int colorValue = 7148189;
    public static final int lightLevel = 8;

    public PurpleFireBlock() {
        super(dyeColor, lightLevel, colorValue);
    }
}
