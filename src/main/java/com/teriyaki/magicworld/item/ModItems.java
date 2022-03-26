package com.teriyaki.magicworld.item;

import com.teriyaki.magicworld.MagicWorld;
import com.teriyaki.magicworld.block.ModBlocks;
import com.teriyaki.magicworld.block.ModFluids;
import com.teriyaki.magicworld.util.Registration;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;

public class ModItems {

    /*  ===== ITEMS =====  */

    // Copper Ingot
    public static final RegistryObject<Item> COPPER_INGOT =
            Registration.ITEMS.register("copper_ingot",
                    () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS)));
    // Copper Wire
    public static final RegistryObject<Item> COPPER_WIRE =
            Registration.ITEMS.register("copper_wire",
                    () -> new Item(new Item.Properties().group(MagicWorld.MAGIC_WORLD_TAB)));
    // Copper Apple
    public static final RegistryObject<Item> COPPERED_APPLE =
            Registration.ITEMS.register("coppered_apple", CopperedApple::new);
    // Fire Powder
    public static final RegistryObject<Item> FIRE_POWDER =
            Registration.ITEMS.register("fire_powder", FirePowder::new);
    // Black Fire Powder
    public static final RegistryObject<Item> BLACK_FIRE_POWDER =
            Registration.ITEMS.register("black_fire_powder", () -> new ColoredFirePowder(DyeColor.BLACK));
    // Blue Fire Powder
    public static final RegistryObject<Item> BLUE_FIRE_POWDER =
            Registration.ITEMS.register("blue_fire_powder", () -> new ColoredFirePowder(DyeColor.BLUE));
    // Brown Fire Powder
    public static final RegistryObject<Item> BROWN_FIRE_POWDER =
            Registration.ITEMS.register("brown_fire_powder", () -> new ColoredFirePowder(DyeColor.BROWN));
    // Cyan Fire Powder
    public static final RegistryObject<Item> CYAN_FIRE_POWDER =
            Registration.ITEMS.register("cyan_fire_powder", () -> new ColoredFirePowder(DyeColor.CYAN));
    // Gray Fire Powder
    public static final RegistryObject<Item> GRAY_FIRE_POWDER =
            Registration.ITEMS.register("gray_fire_powder", () -> new ColoredFirePowder(DyeColor.GRAY));
    // Green Fire Powder
    public static final RegistryObject<Item> GREEN_FIRE_POWDER =
            Registration.ITEMS.register("green_fire_powder", () -> new ColoredFirePowder(DyeColor.GREEN));
    // Light Blue Fire Powder
    public static final RegistryObject<Item> LIGHT_BLUE_FIRE_POWDER =
            Registration.ITEMS.register("light_blue_fire_powder", () -> new ColoredFirePowder(DyeColor.LIGHT_BLUE));
    // Light Gray Fire Powder
    public static final RegistryObject<Item> LIGHT_GRAY_FIRE_POWDER =
            Registration.ITEMS.register("light_gray_fire_powder", () -> new ColoredFirePowder(DyeColor.LIGHT_GRAY));
    // Lime Fire Powder
    public static final RegistryObject<Item> LIME_FIRE_POWDER =
            Registration.ITEMS.register("lime_fire_powder", () -> new ColoredFirePowder(DyeColor.LIME));
    // Magenta Fire Powder
    public static final RegistryObject<Item> MAGENTA_FIRE_POWDER =
            Registration.ITEMS.register("magenta_fire_powder", () -> new ColoredFirePowder(DyeColor.MAGENTA));
    // Orange Fire Powder
    public static final RegistryObject<Item> ORANGE_FIRE_POWDER =
            Registration.ITEMS.register("orange_fire_powder", () -> new ColoredFirePowder(DyeColor.ORANGE));
    // Pink Fire Powder
    public static final RegistryObject<Item> PINK_FIRE_POWDER =
            Registration.ITEMS.register("pink_fire_powder", () -> new ColoredFirePowder(DyeColor.PINK));
    // Purple Fire Powder
    public static final RegistryObject<Item> PURPLE_FIRE_POWDER =
            Registration.ITEMS.register("purple_fire_powder", () -> new ColoredFirePowder(DyeColor.PURPLE));
    // Red Fire Powder
    public static final RegistryObject<Item> RED_FIRE_POWDER =
            Registration.ITEMS.register("red_fire_powder", () -> new ColoredFirePowder(DyeColor.RED));
    // White Fire Powder
    public static final RegistryObject<Item> WHITE_FIRE_POWDER =
            Registration.ITEMS.register("white_fire_powder", () -> new ColoredFirePowder(DyeColor.WHITE));
    // Yellow Fire Powder
    public static final RegistryObject<Item> YELLOW_FIRE_POWDER =
            Registration.ITEMS.register("yellow_fire_powder", () -> new ColoredFirePowder(DyeColor.YELLOW));


    /*  ===== TOOLS =====  */

    // Copper Shovel
    public static final RegistryObject<Item> COPPER_SHOVEL =
            Registration.ITEMS.register("copper_shovel",
                    () -> new ShovelItem(ModItemTier.COPPER, 0f,0f,
                            new Item.Properties()
                                .defaultMaxDamage(150)
                                .addToolType(ToolType.SHOVEL,2)
                                .group(MagicWorld.MAGIC_WORLD_TAB)));
    // Copper Sword
    public static final RegistryObject<Item> COPPER_SWORD =
            Registration.ITEMS.register("copper_sword",
                    () -> new SwordItem(ModItemTier.COPPER, 2,0f,
                            new Item.Properties()
                                    .defaultMaxDamage(150)
                                    .group(MagicWorld.MAGIC_WORLD_TAB)));
    // Copper Pickaxe
    public static final RegistryObject<Item> COPPER_PICKAXE =
            Registration.ITEMS.register("copper_pickaxe",
                    () -> new PickaxeItem(ModItemTier.COPPER, 0,0f,
                            new Item.Properties()
                                    .defaultMaxDamage(150)
                                    .addToolType(ToolType.PICKAXE,2)
                                    .group(MagicWorld.MAGIC_WORLD_TAB)));
    // Copper Hoe
    public static final RegistryObject<Item> COPPER_HOE =
            Registration.ITEMS.register("copper_hoe",
                    () -> new HoeItem(ModItemTier.COPPER, 0,0f,
                            new Item.Properties()
                                    .defaultMaxDamage(150)
                                    .addToolType(ToolType.HOE,2)
                                    .group(MagicWorld.MAGIC_WORLD_TAB)));
    // Copper axe
    public static final RegistryObject<Item> COPPER_AXE =
            Registration.ITEMS.register("copper_axe",
                    () -> new AxeItem(ModItemTier.COPPER, 2.5f,-3f,
                            new Item.Properties()
                                    .defaultMaxDamage(150)
                                    .addToolType(ToolType.SHOVEL,2)
                                    .group(MagicWorld.MAGIC_WORLD_TAB)));


    /*  ===== ARMOUR =====  */

    // Copper Helmet
    public static final RegistryObject<Item> COPPER_HELMET =
            Registration.ITEMS.register("copper_helmet",
                    () -> new ArmorItem(ModArmourMaterial.COPPER, EquipmentSlotType.HEAD,
                            new Item.Properties().group(MagicWorld.MAGIC_WORLD_TAB)));
    // Copper Chestplate
    public static final RegistryObject<Item> COPPER_CHESTPLATE =
            Registration.ITEMS.register("copper_chestplate",
                    () -> new ArmorItem(ModArmourMaterial.COPPER, EquipmentSlotType.CHEST,
                            new Item.Properties().group(MagicWorld.MAGIC_WORLD_TAB)));
    // Copper Leggings
    public static final RegistryObject<Item> COPPER_LEGGINGS =
            Registration.ITEMS.register("copper_leggings",
                    () -> new ArmorItem(ModArmourMaterial.COPPER, EquipmentSlotType.LEGS,
                            new Item.Properties().group(MagicWorld.MAGIC_WORLD_TAB)));
    // Copper Boots
    public static final RegistryObject<Item> COPPER_BOOTS =
            Registration.ITEMS.register("copper_boots",
                    () -> new ArmorItem(ModArmourMaterial.COPPER, EquipmentSlotType.FEET,
                            new Item.Properties().group(MagicWorld.MAGIC_WORLD_TAB)));


    /*  ===== CROPS =====  */

    public static final RegistryObject<Item> ZUCCHINI_SEED =
            Registration.ITEMS.register("zucchini_seed",
                    () -> new BlockItem(ModBlocks.ZUCCHINI_CROP.get(), new Item.Properties().group(MagicWorld.MAGIC_WORLD_TAB)));


    /*  ===== FLUIDS =====  */

    public static final RegistryObject<Item> OIL_BUCKET =
            Registration.ITEMS.register("oil_bucket",
                    () -> new BucketItem(ModFluids.OIL_FLUID::get,
                            new Item.Properties().group(MagicWorld.MAGIC_WORLD_TAB).maxStackSize(1)));




    public static void register() {}

    public enum ModArmourMaterial implements IArmorMaterial {
        ////   MATERIALS   ////
        COPPER(50, new int[] {2, 7, 5, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_IRON,
                Ingredient.fromStacks(new ItemStack(ModItems.COPPER_INGOT.get())), MagicWorld.MOD_ID + ":copper",
                0, 1.2f);

        ////   PROPERTIES   ////
        private final int durability;
        private final int[] damageReductionAmountArray;
        private final int enchantability;
        private final SoundEvent sountEvent;
        private final Ingredient repairMaterial;
        private final String name;
        private final float toughness;
        private final float knockbackResistance;

        ////   CONSTRUCTOR   ////
        ModArmourMaterial(int durability, int[] damageReductionAmountArray, int enchantability, SoundEvent sountEvent,
                          Ingredient repairMaterial, String name, float toughness, float knockbackResistance)
        {
            this.durability = durability;
            this.damageReductionAmountArray = damageReductionAmountArray;
            this.enchantability = enchantability;
            this.sountEvent = sountEvent;
            this.repairMaterial = repairMaterial;
            this.name = name;
            this.toughness = toughness;
            this.knockbackResistance = knockbackResistance;
        }

        ////   FUNCTIONS   ////
        @Override
        public int getDurability(EquipmentSlotType slotIn) {
            return durability;
        }

        @Override
        public int getDamageReductionAmount(EquipmentSlotType slotIn) {
            return damageReductionAmountArray[slotIn.getIndex()];
        }

        @Override
        public int getEnchantability() {
            return enchantability;
        }

        @Override
        public SoundEvent getSoundEvent() {
            return sountEvent;
        }

        @Override
        public Ingredient getRepairMaterial() {
            return repairMaterial;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public float getToughness() {
            return toughness;
        }

        @Override
        public float getKnockbackResistance() {
            return knockbackResistance;
        }
    }

    public enum ModItemTier implements IItemTier {
        
        COPPER(2, 150, 2.5f, 0f, 15,
                Ingredient.fromStacks(new ItemStack(ModItems.COPPER_INGOT.get())));

        private final int harvestLevel; 
        private final int maxUses;
        private final float efficiency;
        private final float attackDamage; 
        private final int enchantability;
        private final Ingredient repairMaterial;

        ModItemTier(int harvestLevel, int maxUsees, float efficiency, float attackDamage, int enchantability, Ingredient repairMaterial) {
            this.harvestLevel = harvestLevel;
            this.maxUses = maxUsees;
            this.efficiency = efficiency;
            this.attackDamage = attackDamage;
            this.enchantability = enchantability;
            this.repairMaterial = repairMaterial;
        }

        @Override
        public int getMaxUses() {
            return maxUses;
        }

        @Override
        public float getEfficiency() {
            return efficiency;
        }

        @Override
        public float getAttackDamage() {
            return attackDamage;
        }

        @Override
        public int getHarvestLevel() {
            return harvestLevel;
        }

        @Override
        public int getEnchantability() {
            return enchantability;
        }

        @Override
        public Ingredient getRepairMaterial() {
            return repairMaterial;
        }
    }
}
