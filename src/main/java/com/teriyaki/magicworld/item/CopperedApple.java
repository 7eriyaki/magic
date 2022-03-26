package com.teriyaki.magicworld.item;

import com.teriyaki.magicworld.MagicWorld;
import com.teriyaki.magicworld.util.Config;
import com.teriyaki.magicworld.util.KeyboardHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class CopperedApple extends Item {
    public CopperedApple() {
        super(new Properties().group(MagicWorld.MAGIC_WORLD_TAB)
            .food(new Food.Builder()
                .hunger(7)
                .saturation(1.5f)
                .setAlwaysEdible()
                .effect(() -> new EffectInstance(Effects.GLOWING, Config.COPPERED_GLOW_DURATION.get()), 0.5f)
                .build()));
    }

    @Override
    public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        if (KeyboardHelper.isHoldingShift()) {
            tooltip.add(new StringTextComponent("Sheep aren't sure if it tastes good"));
        }
        else {
            tooltip.add(new StringTextComponent("HOLD" + "\u00A7e" + " SHIFT " + "\u00A77" + "for more information!"));
        }
        super.addInformation(stack, world, tooltip, flag);
    }
}
