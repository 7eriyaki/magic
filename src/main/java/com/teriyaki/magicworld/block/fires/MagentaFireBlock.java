package com.teriyaki.magicworld.block.fires;

import com.teriyaki.magicworld.block.ColoredFireBlock;
import net.minecraft.item.DyeColor;

public class MagentaFireBlock extends ColoredFireBlock {
    public static final DyeColor dyeColor = DyeColor.MAGENTA;
    private static final int colorValue = 13041847;
    public static final int lightLevel = 10;

    public MagentaFireBlock() {
        super(dyeColor, lightLevel, colorValue);
    }
}
