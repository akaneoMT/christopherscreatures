package net.akaneo.christopherscreatures.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig {
    public final ForgeConfigSpec.IntValue giraffeSpawnWeight;
    public final ForgeConfigSpec.IntValue giraffeSpawnRolls;

    public CommonConfig(final ForgeConfigSpec.Builder builder) {
        builder.push("spawning");
        giraffeSpawnWeight = buildInt(builder, "grizzlyBearSpawnWeight", "spawns", CCConfig.giraffeSpawnWeight, 0, 1000, "Spawn Weight, added to a pool of other mobs for each biome. Higher number = higher chance of spawning. 0 = disable spawn");
        giraffeSpawnRolls = buildInt(builder, "grizzlyBearSpawnRolls", "spawns", CCConfig.giraffeSpawnRolls, 0, Integer.MAX_VALUE, "Random roll chance to enable mob spawning. Higher number = lower chance of spawning");
    }
    private static ForgeConfigSpec.IntValue buildInt(ForgeConfigSpec.Builder builder, String name, String catagory, int defaultValue, int min, int max, String comment) {
        return builder.comment(comment).translation(name).defineInRange(name, defaultValue, min, max);
    }
}