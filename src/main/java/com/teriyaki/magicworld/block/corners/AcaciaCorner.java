package com.teriyaki.magicworld.block.corners;

import com.teriyaki.magicworld.block.CornerBlock;

public class AcaciaCorner extends CornerBlock {
    private static final String CORNER_MODEL = "acacia_corner";

    public AcaciaCorner(Properties properties) {
        super(properties);
    }

    @Override
    public String getCornerModel() {
        return CORNER_MODEL;
    }
}
