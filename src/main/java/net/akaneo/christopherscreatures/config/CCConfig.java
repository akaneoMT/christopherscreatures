package net.akaneo.christopherscreatures.config;

import net.akaneo.christopherscreatures.ChristophersCreatures;
import net.minecraftforge.fml.config.ModConfig;

public class CCConfig {
    public static int giraffeSpawnWeight = 30;
    public static int giraffeSpawnRolls = 0;

    public static int lionessSpawnWeight = 50;
    public static int lionessSpawnRolls = 0;

    public static void bake(ModConfig config) {
        try {
            giraffeSpawnWeight = ConfigHolder.COMMON.giraffeSpawnWeight.get();
            giraffeSpawnRolls = ConfigHolder.COMMON.giraffeSpawnRolls.get();

            lionessSpawnWeight = ConfigHolder.COMMON.lionessSpawnWeight.get();
            lionessSpawnRolls = ConfigHolder.COMMON.lionessSpawnRolls.get();
        } catch (Exception e) {
            ChristophersCreatures.LOGGER.warn("An exception was caused trying to load the config for Christopher's Creatures.");
            e.printStackTrace();
        }
    }
}
