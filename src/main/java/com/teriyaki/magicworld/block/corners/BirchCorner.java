package com.teriyaki.magicworld.block.corners;

import com.teriyaki.magicworld.block.CornerBlock;

public class BirchCorner extends CornerBlock {
    private static final String CORNER_MODEL = "birch_corner";

    public BirchCorner(Properties properties) {
        super(properties);
    }

    @Override
    public String getCornerModel() {
        return CORNER_MODEL;
    }
}
