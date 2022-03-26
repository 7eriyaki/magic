package com.teriyaki.magicworld.block.corners;

import com.teriyaki.magicworld.block.CornerBlock;

public class DarkOakCorner extends CornerBlock {
    private static final String CORNER_MODEL = "dark_oak_corner";

    public DarkOakCorner(Properties properties) {
        super(properties);
    }

    @Override
    public String getCornerModel() {
        return CORNER_MODEL;
    }
}
