package com.teriyaki.magicworld.block.corners;

import com.teriyaki.magicworld.block.CornerBlock;

public class WarpedCorner extends CornerBlock {
    private static final String CORNER_MODEL = "warped_corner";

    public WarpedCorner(Properties properties) {
        super(properties);
    }

    @Override
    public String getCornerModel() {
        return CORNER_MODEL;
    }
}