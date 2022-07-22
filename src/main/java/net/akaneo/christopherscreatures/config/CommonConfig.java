package net.akaneo.christopherscreatures.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig {
    public final ForgeConfigSpec.IntValue giraffeSpawnWeight;
    public final ForgeConfigSpec.IntValue giraffeSpawnRolls;
    public final ForgeConfigSpec.IntValue lionessSpawnWeight;
    public final ForgeConfigSpec.IntValue lionessSpawnRolls;

    public CommonConfig(final ForgeConfigSpec.Builder builder) {
        builder.push("spawning");
        giraffeSpawnWeight = buildInt(builder, "giraffeSpawnWeight", "spawns", CCConfig.giraffeSpawnWeight, 0, 1000, "Spawn Weight, added to a pool of other mobs for each biome. Higher number = higher chance of spawning. 0 = disable spawn");
        giraffeSpawnRolls = buildInt(builder, "giraffeSpawnRolls", "spawns", CCConfig.giraffeSpawnRolls, 0, Integer.MAX_VALUE, "Random roll chance to enable mob spawning. Higher number = lower chance of spawning");
        lionessSpawnWeight = buildInt(builder, "lionessSpawnWeight", "spawns", CCConfig.lionessSpawnWeight, 0, 1000, "Spawn Weight, added to a pool of other mobs for each biome. Higher number = higher chance of spawning. 0 = disable spawn");
        lionessSpawnRolls = buildInt(builder, "lionessSpawnRolls", "spawns", CCConfig.lionessSpawnRolls, 0, Integer.MAX_VALUE, "Random roll chance to enable mob spawning. Higher number = lower chance of spawning");
    }
    private static ForgeConfigSpec.IntValue buildInt(ForgeConfigSpec.Builder builder, String name, String category, int defaultValue, int min, int max, String comment) {
        return builder.comment(comment).translation(name).defineInRange(name, defaultValue, min, max);
    }
}