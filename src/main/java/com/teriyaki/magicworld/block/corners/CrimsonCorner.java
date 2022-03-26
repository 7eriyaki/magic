package com.teriyaki.magicworld.block.corners;

import com.teriyaki.magicworld.block.CornerBlock;

public class CrimsonCorner extends CornerBlock {
    private static final String CORNER_MODEL = "crimson_corner";

    public CrimsonCorner(Properties properties) {
        super(properties);
    }

    @Override
    public String getCornerModel() {
        return CORNER_MODEL;
    }
}