package com.teriyaki.magicworld.setup;

import net.minecraft.world.World;

public interface IProxy {
    void init();

    World getClientWorld();
}
