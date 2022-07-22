package net.akaneo.christopherscreatures.config.biome;

import net.akaneo.christopherscreatures.config.biome.BiomeEntryType;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraftforge.common.BiomeDictionary;

public class SpawnBiomeData {
    private List<List<net.akaneo.christopherscreatures.config.biome.SpawnBiomeData.SpawnBiomeEntry>> biomes = new ArrayList();

    public SpawnBiomeData() {
    }

    private SpawnBiomeData(net.akaneo.christopherscreatures.config.biome.SpawnBiomeData.SpawnBiomeEntry[][] biomesRead) {
        this.biomes = new ArrayList();
        net.akaneo.christopherscreatures.config.biome.SpawnBiomeData.SpawnBiomeEntry[][] var2 = biomesRead;
        int var3 = biomesRead.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            net.akaneo.christopherscreatures.config.biome.SpawnBiomeData.SpawnBiomeEntry[] innerArray = var2[var4];
            this.biomes.add(Arrays.asList(innerArray));
        }

    }

    public net.akaneo.christopherscreatures.config.biome.SpawnBiomeData addBiomeEntry(net.akaneo.christopherscreatures.config.biome.BiomeEntryType type, boolean negate, String value, int pool) {
        if (this.biomes.isEmpty() || this.biomes.size() < pool + 1) {
            this.biomes.add(new ArrayList());
        }

        ((List)this.biomes.get(pool)).add(new net.akaneo.christopherscreatures.config.biome.SpawnBiomeData.SpawnBiomeEntry(type, negate, value));
        return this;
    }

    /** @deprecated */
    @Deprecated
    public boolean matches(Biome biomeIn) {
        return this.matches(BiomeCategory.NONE, biomeIn.getRegistryName());
    }

    public boolean matches(Biome.BiomeCategory category, ResourceLocation registryName) {
        Iterator var3 = this.biomes.iterator();

        boolean overall;
        do {
            if (!var3.hasNext()) {
                return false;
            }

            List<net.akaneo.christopherscreatures.config.biome.SpawnBiomeData.SpawnBiomeEntry> all = (List)var3.next();
            overall = true;
            Iterator var6 = all.iterator();

            while(var6.hasNext()) {
                net.akaneo.christopherscreatures.config.biome.SpawnBiomeData.SpawnBiomeEntry cond = (net.akaneo.christopherscreatures.config.biome.SpawnBiomeData.SpawnBiomeEntry)var6.next();
                if (!cond.matches(category, registryName)) {
                    overall = false;
                }
            }
        } while(!overall);

        return true;
    }

    private class SpawnBiomeEntry {
        net.akaneo.christopherscreatures.config.biome.BiomeEntryType type;
        boolean negate;
        String value;

        public SpawnBiomeEntry(net.akaneo.christopherscreatures.config.biome.BiomeEntryType type, boolean remove, String value) {
            this.type = type;
            this.negate = remove;
            this.value = value;
        }

        public boolean matches(Biome.BiomeCategory category, ResourceLocation registryName) {
            if (this.type == net.akaneo.christopherscreatures.config.biome.BiomeEntryType.BIOME_DICT) {
                ResourceKey<Biome> biomeKey = ResourceKey.create(Registry.BIOME_REGISTRY, registryName);
                List<? extends String> biomeTypes = (List)BiomeDictionary.getTypes(biomeKey).stream().map((t) -> {
                    return t.toString().toLowerCase(Locale.ROOT);
                }).collect(Collectors.toList());
                if (biomeTypes.contains(this.value)) {
                    return !this.negate;
                } else {
                    return this.negate;
                }
            } else if (this.type == BiomeEntryType.BIOME_CATEGORY) {
                if (category.getName().toLowerCase(Locale.ROOT).equals(this.value)) {
                    return !this.negate;
                } else {
                    return this.negate;
                }
            } else if (registryName.toString().equals(this.value)) {
                return !this.negate;
            } else {
                return this.negate;
            }
        }
    }

    public static class Deserializer implements JsonDeserializer<net.akaneo.christopherscreatures.config.biome.SpawnBiomeData>, JsonSerializer<net.akaneo.christopherscreatures.config.biome.SpawnBiomeData> {
        public Deserializer() {
        }

        public net.akaneo.christopherscreatures.config.biome.SpawnBiomeData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonobject = json.getAsJsonObject();
            net.akaneo.christopherscreatures.config.biome.SpawnBiomeData.SpawnBiomeEntry[][] biomesRead = (net.akaneo.christopherscreatures.config.biome.SpawnBiomeData.SpawnBiomeEntry[][])GsonHelper.getAsObject(jsonobject, "biomes", new net.akaneo.christopherscreatures.config.biome.SpawnBiomeData.SpawnBiomeEntry[0][0], context, net.akaneo.christopherscreatures.config.biome.SpawnBiomeData.SpawnBiomeEntry[][].class);
            return new net.akaneo.christopherscreatures.config.biome.SpawnBiomeData(biomesRead);
        }

        public JsonElement serialize(net.akaneo.christopherscreatures.config.biome.SpawnBiomeData src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject jsonobject = new JsonObject();
            jsonobject.add("biomes", context.serialize(src.biomes));
            return jsonobject;
        }
    }
}
