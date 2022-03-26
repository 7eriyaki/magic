package com.teriyaki.magicworld.util;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;

import java.io.File;

public class Config {
    // Config Objects
    public static ForgeConfigSpec SERVER_CONFIG;
    public static ForgeConfigSpec CLIENT_CONFIG;

    // Config Options:
    public static ForgeConfigSpec.IntValue COPPERED_GLOW_DURATION;

    static {
        // Config builders
        ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

        // Config Options
        glowingDuration(SERVER_BUILDER, CLIENT_BUILDER);

        // Build configs
        SERVER_CONFIG = SERVER_BUILDER.build();
        CLIENT_CONFIG = CLIENT_BUILDER.build();

    }

    private static void glowingDuration(ForgeConfigSpec.Builder SERVER_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        COPPERED_GLOW_DURATION = CLIENT_BUILDER.comment("How long the glowing effect lasts for Coppered Apples (in ticks)")
                .defineInRange("glow_duration", 300, 100, Integer.MAX_VALUE);
    }

    public static void loadConfigFile(ForgeConfigSpec config, String path) {
        final CommentedFileConfig file = CommentedFileConfig.builder(new File(path))
                .sync().autosave().writingMode(WritingMode.REPLACE).build();
        file.load();
        config.setConfig(file);
    }

}
