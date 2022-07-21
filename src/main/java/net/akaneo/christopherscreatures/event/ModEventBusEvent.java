package net.akaneo.christopherscreatures.event;

import net.akaneo.christopherscreatures.ChristophersCreaturesMain;
import net.akaneo.christopherscreatures.entity.EntityMain;
import net.akaneo.christopherscreatures.entity.giraffe.GiraffeEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ChristophersCreaturesMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvent {
    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(EntityMain.GIRAFFE.get(), GiraffeEntity.setAttributes());
    }
}
