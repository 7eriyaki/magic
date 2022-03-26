package com.teriyaki.magicworld;

import com.teriyaki.magicworld.block.ModBlocks;
import com.teriyaki.magicworld.block.ModFluids;
import com.teriyaki.magicworld.events.ModEvents;
import com.teriyaki.magicworld.item.ModItems;
import com.teriyaki.magicworld.setup.ClientProxy;
import com.teriyaki.magicworld.setup.IProxy;
import com.teriyaki.magicworld.setup.ServerProxy;
import com.teriyaki.magicworld.util.Config;
import com.teriyaki.magicworld.util.FlammablesRegistry;
import com.teriyaki.magicworld.util.Registration;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MagicWorld.MOD_ID)
public class MagicWorld {

    public static final String MOD_ID = "magicworld";

    public static final ItemGroup MAGIC_WORLD_TAB = new ItemGroup("magicWorldTab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.COPPER_WIRE.get());
        }
    };

    public static IProxy proxy;

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public MagicWorld() {
        proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);

        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        registerModAdditions();

        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        registerConfigs();
        proxy.init();
        loadConfigs();
    }

    private void registerConfigs() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_CONFIG);
    }

    private void loadConfigs() {
        Config.loadConfigFile(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve("magicworld-client.toml").toString());
        Config.loadConfigFile(Config.SERVER_CONFIG, FMLPaths.CONFIGDIR.get().resolve("magicworld-server.toml").toString());
    }

    private void registerModAdditions() {
        // Initialize registration of mod additions
        Registration.init();

        // Empty Methods: Initializing Mod Classes
        ModItems.register();
        ModBlocks.register();
        ModFluids.register();

        // Register Mod events
        MinecraftForge.EVENT_BUS.register(new ModEvents());
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event) {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        FlammablesRegistry.register();
        LOGGER.info("HELLO from server starting");
    }
}
