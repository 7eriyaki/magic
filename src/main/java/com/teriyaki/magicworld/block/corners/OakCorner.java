package com.teriyaki.magicworld.block.corners;

import com.teriyaki.magicworld.block.CornerBlock;

public class OakCorner extends CornerBlock {
    private static final String CORNER_MODEL = "oak_corner";

    public OakCorner(Properties properties) {
        super(properties);
    }

    @Override
    public String getCornerModel() {
        return CORNER_MODEL;
    }
}