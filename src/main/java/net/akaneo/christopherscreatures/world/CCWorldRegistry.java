package net.akaneo.christopherscreatures.world;

import net.akaneo.christopherscreatures.ChristophersCreatures;
import net.akaneo.christopherscreatures.config.BiomeConfig;
import net.akaneo.christopherscreatures.config.CCConfig;
import net.akaneo.christopherscreatures.config.biome.SpawnBiomeData;
import net.akaneo.christopherscreatures.entity.CCEntityRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.tuple.Pair;

@Mod.EventBusSubscriber(modid = ChristophersCreatures.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CCWorldRegistry {
    public static boolean initBiomes = false;

    public CCWorldRegistry() {
    }

    public static void onBiomesLoad(BiomeLoadingEvent event) {
        initBiomes = true;
        if (testBiome(BiomeConfig.giraffe, event.getCategory(), event.getName()) && CCConfig.giraffeSpawnWeight > 0) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(CCEntityRegistry.GIRAFFE.get(), CCConfig.giraffeSpawnWeight, 3, 5));
        }

        if (testBiome(BiomeConfig.lioness, event.getCategory(), event.getName()) && CCConfig.lionessSpawnWeight > 0) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(CCEntityRegistry.LIONESS.get(), CCConfig.lionessSpawnWeight, 3, 5));
        }
    }

    public static boolean testBiome(Pair<String, SpawnBiomeData> entry, Biome.BiomeCategory category, ResourceLocation registryName) {
        boolean result = false;
        try {
            result = BiomeConfig.test(entry, category, registryName);
        } catch (Exception e) {
            ChristophersCreatures.LOGGER.warn("could not test biome config for " + entry.getLeft() + ", defaulting to no spawns for mob");
            result = false;
        }
        return result;
    }
}
