package net.akaneo.christopherscreatures.config;

import net.akaneo.christopherscreatures.ChristophersCreatures;
import net.akaneo.christopherscreatures.config.biome.SpawnBiomeConfig;
import net.akaneo.christopherscreatures.config.biome.SpawnBiomeData;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import org.apache.commons.lang3.tuple.Pair;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class BiomeConfig {
    public static Pair<String, SpawnBiomeData> giraffe = Pair.of("christopherscreatures:giraffe_spawns", DefaultBiomes.GIRAFFE);
    public static Pair<String, SpawnBiomeData> lioness = Pair.of("christopherscreatures:lioness_spawns", DefaultBiomes.LIONESS);

    private static boolean init = false;
    private static Map<String, SpawnBiomeData> biomeConfigValues = new HashMap<>();

    public static void init() {
        try {
            for (Field f : BiomeConfig.class.getDeclaredFields()) {
                Object obj = f.get(null);
                if(obj instanceof Pair){
                    String id = (String)((Pair) obj).getLeft();
                    SpawnBiomeData data = (SpawnBiomeData)((Pair) obj).getRight();
                    biomeConfigValues.put(id, SpawnBiomeConfig.create(new ResourceLocation(id), data));
                }
            }
        }catch (Exception e){
            ChristophersCreatures.LOGGER.warn("Encountered error building Christopher's Creatures biome config .json files");
            e.printStackTrace();
        }
        init = true;
    }

    public static boolean test(Pair<String, SpawnBiomeData> entry, Biome.BiomeCategory category, ResourceLocation name){
        if(!init){
            return false;
        }
        return biomeConfigValues.get(entry.getKey()).matches(category, name);
    }

    public static boolean test(Pair<String, SpawnBiomeData> spawns, Holder<Biome> biome) {
        return test(spawns, Biome.getBiomeCategory(biome), biome.value().getRegistryName());
    }
}
