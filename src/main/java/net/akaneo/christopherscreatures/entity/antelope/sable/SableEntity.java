package net.akaneo.christopherscreatures.entity.antelope.sable;

import net.akaneo.christopherscreatures.config.CCConfig;
import net.akaneo.christopherscreatures.entity.CCEntityRegistry;
import net.akaneo.christopherscreatures.entity.antelope.AbstactAntelope;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.animal.horse.Donkey;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class SableEntity extends AbstactAntelope implements IAnimatable {
    private static final EntityDataAccessor<Boolean> MALE;
    private AnimationFactory factory = new AnimationFactory(this);

    public SableEntity(EntityType type, Level worldIn) {
        super(type, worldIn);
    }

    public boolean checkSpawnRules(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return CCEntityRegistry.rollSpawn(CCConfig.sableSpawnRolls, this.getRandom(), spawnReasonIn) && super.checkSpawnRules(worldIn, spawnReasonIn);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("Male", this.isMale());
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setMale(compound.getBoolean("Male"));
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(MALE, false);
    }

    public boolean isMale() {
        return (Boolean)this.entityData.get(MALE);
    }

    public void setMale(boolean male) {
        this.entityData.set(MALE, male);
    }


    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {

        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.sable.walk", true));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.sable.idle", true));
        return PlayState.CONTINUE;

    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
    }
    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    public boolean canMate(Animal animal) {
        boolean maleOther = animal instanceof SableEntity && ((SableEntity)animal).isMale();
        if (this.isMale() && maleOther) {
            return false;
        } else if (!this.isMale() && !maleOther) {
            return false;
        } else {
            return this.canParent() && ((SableEntity)animal).canParent();
        }
    }
    protected boolean canParent() {
        return !this.isBaby() && this.isInLove();
    }
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        SableEntity baby = (SableEntity) ((EntityType) CCEntityRegistry.SABLE.get()).create(p_241840_1_);
        double maleChance = 0.4;
        baby.setMale(this.random.nextDouble() < maleChance);
        return baby;
    }

    static {
        MALE = SynchedEntityData.defineId(SableEntity.class, EntityDataSerializers.BOOLEAN);
    }
}
