package net.akaneo.christopherscreatures;

import net.akaneo.christopherscreatures.config.BiomeConfig;
import net.akaneo.christopherscreatures.config.CCConfig;
import net.akaneo.christopherscreatures.config.ConfigHolder;
import net.akaneo.christopherscreatures.entity.CCEntityRegistry;
import net.akaneo.christopherscreatures.entity.giraffe.GiraffeRenderer;
import net.akaneo.christopherscreatures.item.CCItemRegistry;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

@Mod(ChristophersCreatures.MOD_ID)
public class ChristophersCreatures {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "christopherscreatures";

    // Add a comment
    public ChristophersCreatures() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

        CCItemRegistry.DEF_REG.register(FMLJavaModLoadingContext.get().getModEventBus());
        CCEntityRegistry.DEF_REG.register(FMLJavaModLoadingContext.get().getModEventBus());

        GeckoLib.initialize();

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

    }

    @SubscribeEvent
    public void onModConfigEvent(final ModConfigEvent event) {
        final ModConfig config = event.getConfig();
        // Rebake the configs when they change
        if (config.getSpec() == ConfigHolder.COMMON_SPEC) {
            CCConfig.bake(config);
        }
        BiomeConfig.init();
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        EntityRenderers.register(CCEntityRegistry.GIRAFFE.get(), GiraffeRenderer::new);
    }
    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
}
