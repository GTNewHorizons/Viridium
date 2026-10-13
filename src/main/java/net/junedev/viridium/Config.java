package net.junedev.viridium;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    public static String greeting = "Hello World";
    public static int firstBiomeId = 100;

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        greeting = configuration.getString("greeting", Configuration.CATEGORY_GENERAL, greeting, "How shall I greet?");
        firstBiomeId = configuration.getInt(
            "firstBiomeId",
            Configuration.CATEGORY_GENERAL,
            firstBiomeId,
            0,
            255,
            "First Id value for the biome definitions");

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
