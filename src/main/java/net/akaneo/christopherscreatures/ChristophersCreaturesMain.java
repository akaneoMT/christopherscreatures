package net.akaneo.christopherscreatures;

import net.akaneo.christopherscreatures.entity.EntityMain;
import net.akaneo.christopherscreatures.entity.giraffe.GiraffeRenderer;
import net.akaneo.christopherscreatures.item.ItemMain;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

@Mod(ChristophersCreaturesMain.MOD_ID)
public class ChristophersCreaturesMain {
    public static final String MOD_ID = "christopherscreatures";

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    // Add a comment
    public ChristophersCreaturesMain() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemMain.register(eventBus);
        EntityMain.register(eventBus);

        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);

        GeckoLib.initialize();

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

    }

    private void clientSetup(final FMLClientSetupEvent event) {
        EntityRenderers.register(EntityMain.GIRAFFE.get(), GiraffeRenderer::new);
    }
    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
}
