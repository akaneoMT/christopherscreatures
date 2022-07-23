package net.akaneo.christopherscreatures.config;


import net.akaneo.christopherscreatures.config.biome.BiomeEntryType;
import net.akaneo.christopherscreatures.config.biome.SpawnBiomeData;

public class DefaultBiomes {

    public static final SpawnBiomeData EMPTY = new SpawnBiomeData();
    public static final SpawnBiomeData GIRAFFE = new SpawnBiomeData()
            .addBiomeEntry(BiomeEntryType.BIOME_DICT, false, "overworld", 0)
            .addBiomeEntry(BiomeEntryType.BIOME_CATEGORY, false, "savanna", 0)
            .addBiomeEntry(BiomeEntryType.BIOME_DICT, false, "overworld", 1)
            .addBiomeEntry(BiomeEntryType.BIOME_DICT, false, "savanna", 1)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "terralith:arid_highlands", 2)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "terralith:brushland", 3)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "terralith:fractured_savanna", 4)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "terralith:savanna_badlands", 5)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "terralith:savanna_slopes", 6)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "terralith:shrubland", 7)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "terralith:red_oasis", 8)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "minecraft:sparse_jungle", 9);
    public static final SpawnBiomeData LIONESS = new SpawnBiomeData()
            .addBiomeEntry(BiomeEntryType.BIOME_DICT, false, "overworld", 0)
            .addBiomeEntry(BiomeEntryType.BIOME_CATEGORY, false, "savanna", 0)
            .addBiomeEntry(BiomeEntryType.BIOME_DICT, false, "overworld", 1)
            .addBiomeEntry(BiomeEntryType.BIOME_DICT, false, "savanna", 1)
            .addBiomeEntry(BiomeEntryType.BIOME_DICT, false, "overworld", 2)
            .addBiomeEntry(BiomeEntryType.BIOME_CATEGORY, false, "desert", 2)
            .addBiomeEntry(BiomeEntryType.BIOME_DICT, false, "overworld", 3)
            .addBiomeEntry(BiomeEntryType.BIOME_DICT, false, "hot", 3)
            .addBiomeEntry(BiomeEntryType.BIOME_DICT, false, "dry", 3)
            .addBiomeEntry(BiomeEntryType.BIOME_DICT, false, "sandy", 3)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "terralith:brushland", 4)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "terralith:desert_canyon", 5)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "terralith:fractured_savanna", 6)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "terralith:desert_oasis", 7)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "terralith:savanna_badlands", 8)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "terralith:desert_spires", 9)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "terralith:savanna_slopes", 10)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "terralith:sandstone_valley", 11)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "terralith:shrubland", 12)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "terralith:red_oasis", 13)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "terralith:arid_highlands", 14)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "terralith:ancient_sands", 15);

    public static final SpawnBiomeData SABLE = new SpawnBiomeData()
            .addBiomeEntry(BiomeEntryType.BIOME_DICT, false, "overworld", 0)
            .addBiomeEntry(BiomeEntryType.BIOME_CATEGORY, false, "savanna", 0)
            .addBiomeEntry(BiomeEntryType.BIOME_DICT, false, "overworld", 1)
            .addBiomeEntry(BiomeEntryType.BIOME_DICT, false, "savanna", 1)
            .addBiomeEntry(BiomeEntryType.BIOME_DICT, false, "overworld", 2)
            .addBiomeEntry(BiomeEntryType.BIOME_CATEGORY, false, "desert", 2)
            .addBiomeEntry(BiomeEntryType.BIOME_DICT, false, "overworld", 3)
            .addBiomeEntry(BiomeEntryType.BIOME_DICT, false, "hot", 3)
            .addBiomeEntry(BiomeEntryType.BIOME_DICT, false, "dry", 3)
            .addBiomeEntry(BiomeEntryType.BIOME_DICT, false, "sandy", 3)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "terralith:brushland", 4)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "terralith:desert_canyon", 5)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "terralith:fractured_savanna", 6)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "terralith:desert_oasis", 7)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "terralith:savanna_badlands", 8)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "terralith:desert_spires", 9)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "terralith:savanna_slopes", 10)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "terralith:sandstone_valley", 11)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "terralith:shrubland", 12)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "terralith:red_oasis", 13)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "terralith:arid_highlands", 14)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "terralith:ancient_sands", 15);

}
