package com.teriyaki.magicworld.block.corners;

import com.teriyaki.magicworld.block.CornerBlock;

public class SpruceCorner extends CornerBlock {
    private static final String CORNER_MODEL = "spruce_corner";

    public SpruceCorner(Properties properties) {
        super(properties);
    }

    @Override
    public String getCornerModel() {
        return CORNER_MODEL;
    }
}