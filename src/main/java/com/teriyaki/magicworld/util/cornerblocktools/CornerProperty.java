package com.teriyaki.magicworld.util.cornerblocktools;

import net.minecraft.state.EnumProperty;

public class CornerProperty extends EnumProperty<CornerBlockModels> {
    private CornerProperty(String name) {
        super(name, CornerBlockModels.class, CornerBlockModels.getCornerBlockModels());
    }
    public static CornerProperty create(String name) {
        return new CornerProperty(name);
    }
}
