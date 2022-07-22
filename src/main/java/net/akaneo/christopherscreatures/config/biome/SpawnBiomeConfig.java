package net.akaneo.christopherscreatures.config.biome;

import net.akaneo.christopherscreatures.ChristophersCreatures;
import net.akaneo.christopherscreatures.config.biome.SpawnBiomeData;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.commons.io.FileUtils;

public class SpawnBiomeConfig {
    public static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().registerTypeAdapter(net.akaneo.christopherscreatures.config.biome.SpawnBiomeData.class, new net.akaneo.christopherscreatures.config.biome.SpawnBiomeData.Deserializer()).create();
    private final ResourceLocation fileName;

    private SpawnBiomeConfig(ResourceLocation fileName) {
        if (!fileName.getNamespace().endsWith(".json")) {
            this.fileName = new ResourceLocation(fileName.getNamespace(), fileName.getPath() + ".json");
        } else {
            this.fileName = fileName;
        }

    }

    public static net.akaneo.christopherscreatures.config.biome.SpawnBiomeData create(ResourceLocation fileName, net.akaneo.christopherscreatures.config.biome.SpawnBiomeData dataDefault) {
        net.akaneo.christopherscreatures.config.biome.SpawnBiomeConfig config = new net.akaneo.christopherscreatures.config.biome.SpawnBiomeConfig(fileName);
        net.akaneo.christopherscreatures.config.biome.SpawnBiomeData data = config.getConfigData(dataDefault);
        return data;
    }

    public static <T> T getOrCreateConfigFile(File configDir, String configName, T defaults, Type type) {
        File configFile = new File(configDir, configName);
        if (!configFile.exists()) {
            try {
                FileUtils.write(configFile, GSON.toJson(defaults));
            } catch (IOException var7) {
                ChristophersCreatures.LOGGER.error("Spawn Biome Config: Could not write " + configFile, var7);
            }
        }

        try {
            return GSON.fromJson(FileUtils.readFileToString(configFile), type);
        } catch (Exception var6) {
            ChristophersCreatures.LOGGER.error("Spawn Biome Config: Could not load " + configFile, var6);
            return defaults;
        }
    }

    private File getConfigDirFile() {
        Path configPath = FMLPaths.CONFIGDIR.get();
        Path jsonPath = Paths.get(configPath.toAbsolutePath().toString(), this.fileName.getNamespace());
        return jsonPath.toFile();
    }

    private net.akaneo.christopherscreatures.config.biome.SpawnBiomeData getConfigData(net.akaneo.christopherscreatures.config.biome.SpawnBiomeData defaultConfigData) {
        net.akaneo.christopherscreatures.config.biome.SpawnBiomeData configData = (net.akaneo.christopherscreatures.config.biome.SpawnBiomeData)getOrCreateConfigFile(this.getConfigDirFile(), this.fileName.getPath(), defaultConfigData, (new TypeToken<SpawnBiomeData>() {
        }).getType());
        return configData;
    }
}