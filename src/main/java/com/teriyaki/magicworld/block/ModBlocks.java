package com.teriyaki.magicworld.block;

import com.teriyaki.magicworld.MagicWorld;
import com.teriyaki.magicworld.block.corners.*;
import com.teriyaki.magicworld.block.crops.ZucchiniCrop;
import com.teriyaki.magicworld.block.fires.*;
import com.teriyaki.magicworld.util.Registration;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    /*   ======== BLOCKS ========   */

    // Copper Block
    public static final RegistryObject<Block> COPPER_BLOCK = register("copper_block",
            () -> new CopperBlock(AbstractBlock.Properties.create(Material.IRON)
                .hardnessAndResistance(3f, 10f)
                .sound(SoundType.METAL)));
    // Copper Ore
    public static final RegistryObject<Block> COPPER_ORE = register("copper_ore",
            () -> new Block(AbstractBlock.Properties.create(Material.ROCK)
                .hardnessAndResistance(3f, 10f)
                .harvestLevel(2)
                .harvestTool(ToolType.PICKAXE)
                .sound(SoundType.STONE)));
    // Copper Stairs
    public static final RegistryObject<Block> COPPER_STAIRS =
            register("copper_stairs", () -> new StairsBlock( () -> ModBlocks.COPPER_BLOCK.get().getDefaultState(),
                    AbstractBlock.Properties.create(Material.IRON)));
    // Copper Fence
    public static final RegistryObject<Block> COPPER_FENCE =
            register("copper_fence", () -> new FenceBlock(AbstractBlock.Properties.create(Material.IRON)));
    // Copper Fence Gate
    public static final RegistryObject<Block> COPPER_FENCE_GATE =
            register("copper_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.create(Material.IRON)));
    // Copper Pressure Plate
    public static final RegistryObject<Block> COPPER_PRESSURE_PLATE =
            register("copper_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    AbstractBlock.Properties.create(Material.IRON)));
    // Copper Slab
    public static final RegistryObject<Block> COPPER_SLAB =
            register("copper_slab", () -> new SlabBlock(AbstractBlock.Properties.create(Material.IRON)));
    // Copper Button
    public static final RegistryObject<Block> COPPER_BUTTON =
            register("copper_button", () -> new StoneButtonBlock(AbstractBlock.Properties.create(Material.IRON)));


    /*   ======== CROP BLOCKS ========   */

    public static final RegistryObject<Block> ZUCCHINI_CROP =
            Registration.BLOCKS.register("zucchini_crop",
                    () -> new ZucchiniCrop(AbstractBlock.Properties.from(Blocks.WHEAT)));


    /*   ======== FIRE BLOCKS ========   */

    public static final RegistryObject<Block> BLACK_FIRE = registerBlockOnly("black_fire", BlackFireBlock::new);
    public static final RegistryObject<Block> BLUE_FIRE = registerBlockOnly("blue_fire", BlueFireBlock::new);
    public static final RegistryObject<Block> BROWN_FIRE = registerBlockOnly("brown_fire", BrownFireBlock::new);
    public static final RegistryObject<Block> CYAN_FIRE = registerBlockOnly("cyan_fire", CyanFireBlock::new);
    public static final RegistryObject<Block> GRAY_FIRE = registerBlockOnly("gray_fire", GrayFireBlock::new);
    public static final RegistryObject<Block> GREEN_FIRE = registerBlockOnly("green_fire", GreenFireBlock::new);
    public static final RegistryObject<Block> LIGHT_BLUE_FIRE = registerBlockOnly("light_blue_fire", LightBlueFireBlock::new);
    public static final RegistryObject<Block> LIGHT_GRAY_FIRE = registerBlockOnly("light_gray_fire", LightGrayFireBlock::new);
    public static final RegistryObject<Block> LIME_FIRE = registerBlockOnly("lime_fire", LimeFireBlock::new);
    public static final RegistryObject<Block> MAGENTA_FIRE = registerBlockOnly("magenta_fire", MagentaFireBlock::new);
    public static final RegistryObject<Block> ORANGE_FIRE = registerBlockOnly("orange_fire", OrangeFireBlock::new);
    public static final RegistryObject<Block> PINK_FIRE = registerBlockOnly("pink_fire", PinkFireBlock::new);
    public static final RegistryObject<Block> PURPLE_FIRE = registerBlockOnly("purple_fire", PurpleFireBlock::new);
    public static final RegistryObject<Block> RED_FIRE = registerBlockOnly("red_fire", RedFireBlock::new);
    public static final RegistryObject<Block> WHITE_FIRE = registerBlockOnly("white_fire", WhiteFireBlock::new);
    public static final RegistryObject<Block> YELLOW_FIRE = registerBlockOnly("yellow_fire", YellowFireBlock::new);


    /*   ======== CORNER BLOCKS ========   */

    public static final RegistryObject<Block> CORNER_CONTAINER_BLOCK =
            registerBlockOnly("corner_container_block", () -> new CornerContainerBlock(Material.WOOD));

    public static final RegistryObject<Block> ACACIA_CORNER =
            register("acacia_corner", () -> new AcaciaCorner(AbstractBlock.Properties.create(Material.WOOD)));
    public static final RegistryObject<Block> BIRCH_CORNER =
            register("birch_corner", () -> new BirchCorner(AbstractBlock.Properties.create(Material.WOOD)));
    public static final RegistryObject<Block> CRIMSON_CORNER =
            register("crimson_corner", () -> new CrimsonCorner(AbstractBlock.Properties.create(Material.WOOD)));
    public static final RegistryObject<Block> DARK_OAK_CORNER =
            register("dark_oak_corner", () -> new DarkOakCorner(AbstractBlock.Properties.create(Material.WOOD)));
    public static final RegistryObject<Block> JUNGLE_CORNER =
            register("jungle_corner", () -> new JungleCorner(AbstractBlock.Properties.create(Material.WOOD)));
    public static final RegistryObject<Block> OAK_CORNER =
            register("oak_corner", () -> new OakCorner(AbstractBlock.Properties.create(Material.WOOD)));
    public static final RegistryObject<Block> SPRUCE_CORNER =
            register("spruce_corner", () -> new SpruceCorner(AbstractBlock.Properties.create(Material.WOOD)));
    public static final RegistryObject<Block> WARPED_CORNER =
            register("warped_corner", () -> new WarpedCorner(AbstractBlock.Properties.create(Material.WOOD)));


    public static void register() {}

    private static <T extends Block>RegistryObject<T> register(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = Registration.BLOCKS.register(name, block);
        Registration.ITEMS.register(name, () -> new BlockItem(toReturn.get(),
                new Item.Properties().group(MagicWorld.MAGIC_WORLD_TAB)));
        return toReturn;
    }

    private static <T extends Block>RegistryObject<T> registerBlockOnly(String name, Supplier<T> block) {
        return Registration.BLOCKS.register(name, block);
    }
}
