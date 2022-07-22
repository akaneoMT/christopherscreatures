package net.akaneo.christopherscreatures.misc;

import net.akaneo.christopherscreatures.ChristophersCreatures;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.lang.reflect.Field;

@Mod.EventBusSubscriber(modid = ChristophersCreatures.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CCSoundRegistry {
    public static final SoundEvent ROAR = createSoundEvent("roar");

    public CCSoundRegistry() {
    }

    private static SoundEvent createSoundEvent(String soundName) {
        ResourceLocation soundID = new ResourceLocation("christopherscreatures", soundName);
        return (SoundEvent)(new SoundEvent(soundID)).setRegistryName(soundID);
    }
    @SubscribeEvent
    public static void registerSoundEvents(RegistryEvent.Register<SoundEvent> event) {
        try {
            Field[] var1 = CCSoundRegistry.class.getDeclaredFields();
            int var2 = var1.length;

            for (int var3 = 0; var3 < var2; ++var3) {
                Field f = var1[var3];
                Object obj = f.get((Object) null);
                if (obj instanceof SoundEvent) {
                    event.getRegistry().register((SoundEvent) obj);
                } else if (obj instanceof SoundEvent[]) {
                    SoundEvent[] var6 = (SoundEvent[]) obj;
                    int var7 = var6.length;

                    for (int var8 = 0; var8 < var7; ++var8) {
                        SoundEvent soundEvent = var6[var8];
                        event.getRegistry().register(soundEvent);
                    }
                }
            }

        } catch (IllegalAccessException var10) {
            throw new RuntimeException(var10);
        }
    }
}
