package com.teriyaki.magicworld.setup;

import com.teriyaki.magicworld.MagicWorld;
import com.teriyaki.magicworld.block.ModBlocks;
import com.teriyaki.magicworld.util.tint.FireColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MagicWorld.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientProxy implements IProxy {
    @Override
    public void init() {
        registerRenderCutouts();
        registerColorTints();
    }

    @Override
    public World getClientWorld() {
        return Minecraft.getInstance().world;
    }

    private void registerRenderCutouts() {
        // Colored Fire Cutout
        RenderTypeLookup.setRenderLayer(ModBlocks.BLACK_FIRE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.BLUE_FIRE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.BROWN_FIRE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.CYAN_FIRE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.GRAY_FIRE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.GREEN_FIRE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.LIGHT_BLUE_FIRE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.LIGHT_GRAY_FIRE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.LIME_FIRE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.MAGENTA_FIRE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.ORANGE_FIRE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.PINK_FIRE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.PURPLE_FIRE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.RED_FIRE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.WHITE_FIRE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.YELLOW_FIRE.get(), RenderType.getCutout());

        // Crop Cutout
        RenderTypeLookup.setRenderLayer(ModBlocks.ZUCCHINI_CROP.get(), RenderType.getCutout());
    }

    private void registerColorTints() {
        FireColor.registerFireColors();
    }
}
