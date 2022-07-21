package net.akaneo.christopherscreatures.entity;

import net.akaneo.christopherscreatures.ChristophersCreaturesMain;
import net.akaneo.christopherscreatures.entity.giraffe.GiraffeEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityMain {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, ChristophersCreaturesMain.MOD_ID);

    public static final RegistryObject<EntityType<GiraffeEntity>> GIRAFFE =
            ENTITY_TYPES.register("giraffe",
                    () -> EntityType.Builder.of(GiraffeEntity::new, MobCategory.CREATURE)
                            .sized(1.5f, 2.7f)
                            .build(new ResourceLocation(ChristophersCreaturesMain.MOD_ID, "giraffe").toString()));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
