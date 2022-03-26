package com.teriyaki.magicworld.block.corners;

import com.teriyaki.magicworld.block.CornerBlock;

public class JungleCorner extends CornerBlock {
    private static final String CORNER_MODEL = "jungle_corner";

    public JungleCorner(Properties properties) {
        super(properties);
    }

    @Override
    public String getCornerModel() {
        return CORNER_MODEL;
    }
}