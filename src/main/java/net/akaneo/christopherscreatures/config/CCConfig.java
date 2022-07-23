package net.akaneo.christopherscreatures.config;

import net.akaneo.christopherscreatures.ChristophersCreatures;
import net.minecraftforge.fml.config.ModConfig;

public class CCConfig {
    public static int giraffeSpawnWeight = 30;
    public static int giraffeSpawnRolls = 0;

    public static int lionessSpawnWeight = 30;
    public static int lionessSpawnRolls = 0;

    public static int sableSpawnWeight = 30;
    public static int sableSpawnRolls = 0;

    public CCConfig() {
    }

    public static void bake(ModConfig config) {
        try {
            giraffeSpawnWeight = (Integer) net.akaneo.christopherscreatures.config.ConfigHolder.COMMON.giraffeSpawnWeight.get();
            giraffeSpawnRolls = (Integer) net.akaneo.christopherscreatures.config.ConfigHolder.COMMON.giraffeSpawnRolls.get();

            lionessSpawnWeight = (Integer) net.akaneo.christopherscreatures.config.ConfigHolder.COMMON.lionessSpawnWeight.get();
            lionessSpawnRolls = (Integer) net.akaneo.christopherscreatures.config.ConfigHolder.COMMON.lionessSpawnRolls.get();

            sableSpawnWeight = (Integer) net.akaneo.christopherscreatures.config.ConfigHolder.COMMON.sableSpawnWeight.get();
            sableSpawnRolls = (Integer) net.akaneo.christopherscreatures.config.ConfigHolder.COMMON.sableSpawnRolls.get();

        } catch (Exception e) {
            ChristophersCreatures.LOGGER.warn("An exception was caused trying to load the config for Christopher's Creatures.");
            e.printStackTrace();
        }
    }
}
