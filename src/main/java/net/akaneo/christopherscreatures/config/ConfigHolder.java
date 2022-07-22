package net.akaneo.christopherscreatures.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public final class ConfigHolder {
    public static final ForgeConfigSpec COMMON_SPEC;
    public static final net.akaneo.christopherscreatures.config.CommonConfig COMMON;

    public ConfigHolder() {
    }

    static {
        Pair<net.akaneo.christopherscreatures.config.CommonConfig, ForgeConfigSpec> specPair = (new ForgeConfigSpec.Builder()).configure(net.akaneo.christopherscreatures.config.CommonConfig::new);
        COMMON = (CommonConfig)specPair.getLeft();
        COMMON_SPEC = (ForgeConfigSpec)specPair.getRight();
    }
}
