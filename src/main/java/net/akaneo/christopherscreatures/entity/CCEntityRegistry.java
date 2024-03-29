package net.akaneo.christopherscreatures.entity;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import net.akaneo.christopherscreatures.ChristophersCreatures;
import net.akaneo.christopherscreatures.entity.antelope.sable.SableEntity;
import net.akaneo.christopherscreatures.entity.giraffe.GiraffeEntity;
import net.akaneo.christopherscreatures.entity.lioness.LionessEntity;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Random;

@Mod.EventBusSubscriber(modid = ChristophersCreatures.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CCEntityRegistry {
    public static final DeferredRegister<EntityType<?>> DEF_REG = DeferredRegister.create(ForgeRegistries.ENTITIES, ChristophersCreatures.MOD_ID);

    public static final RegistryObject<EntityType<GiraffeEntity>> GIRAFFE = DEF_REG.register("giraffe", () -> registerEntity(EntityType.Builder.of(GiraffeEntity::new, MobCategory.CREATURE).sized(1.5F, 3.7F), "giraffe"));
    public static final RegistryObject<EntityType<LionessEntity>> LIONESS = DEF_REG.register("lioness", () -> registerEntity(EntityType.Builder.of(LionessEntity::new, MobCategory.CREATURE).sized(1.0F, 1.0F), "lioness"));
    public static final RegistryObject<EntityType<SableEntity>> SABLE = DEF_REG.register("sable", () -> registerEntity(EntityType.Builder.of(SableEntity::new, MobCategory.CREATURE).sized(1.0F, 1.0F), "sable"));


    private static final EntityType registerEntity(EntityType.Builder builder, String entityName) {
        return (EntityType) builder.build(entityName);
    }

    @SubscribeEvent
    public static void initializeAttributes(EntityAttributeCreationEvent event) {
        SpawnPlacements.register(GIRAFFE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        event.put(GIRAFFE.get(), GiraffeEntity.bakeAttributes().build());
        SpawnPlacements.register(LIONESS.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        event.put(LIONESS.get(), LionessEntity.bakeAttributes().build());
        SpawnPlacements.register(SABLE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        event.put(SABLE.get(), LionessEntity.bakeAttributes().build());
    }

    public static Predicate<LivingEntity> buildPredicateFromTag(TagKey<EntityType<?>> entityTag){
        if(entityTag == null){
            return Predicates.alwaysFalse();
        }else{
            return (com.google.common.base.Predicate<LivingEntity>) e -> e.isAlive() && e.getType().is(entityTag);
        }
    }

    public static Predicate<LivingEntity> buildPredicateFromTagTameable(TagKey<EntityType<?>> entityTag, LivingEntity owner){
        if(entityTag == null){
            return Predicates.alwaysFalse();
        }else{
            return (com.google.common.base.Predicate<LivingEntity>) e -> e.isAlive() && e.getType().is(entityTag) && !owner.isAlliedTo(e);
        }
    }

    public static boolean rollSpawn(int rolls, Random random, MobSpawnType reason){
        if(reason == MobSpawnType.SPAWNER){
            return true;
        }else{
            return rolls <= 0 || random.nextInt(rolls) == 0;
        }
    }
}
