package net.akaneo.christopherscreatures.entity.giraffe;

import net.akaneo.christopherscreatures.config.CCConfig;
import net.akaneo.christopherscreatures.entity.CCEntityRegistry;
import net.akaneo.christopherscreatures.entity.IHerdPanic;
import net.akaneo.christopherscreatures.entity.ai.AnimalAIHerdPanic;
import net.akaneo.christopherscreatures.entity.ai.AnimalAIWanderRanged;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;

import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.List;


public class GiraffeEntity extends Animal implements IAnimatable, IHerdPanic {
    private AnimationFactory factory = new AnimationFactory(this);
    private boolean hasSpedUp = false;
    private int revengeCooldown = 0;
    private static final EntityDataAccessor<Boolean> RUNNING = SynchedEntityData.defineId(GiraffeEntity.class, EntityDataSerializers.BOOLEAN);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Blocks.ACACIA_LEAVES.asItem(), Blocks.JUNGLE_LEAVES.asItem());

    public GiraffeEntity(EntityType type, Level worldIn) {
        super(type, worldIn);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new AnimalAIHerdPanic(this, 2.0D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(5, new AnimalAIWanderRanged(this, 100, 1.0D, 25, 7));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.GENERIC_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.GENERIC_DEATH;
    }
    public boolean checkSpawnRules(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
        return CCEntityRegistry.rollSpawn(CCConfig.giraffeSpawnRolls, this.getRandom(), spawnReasonIn) && super.checkSpawnRules(worldIn, spawnReasonIn);
    }

    public int getMaxSpawnClusterSize() {
        return 6;
    }

    public boolean isMaxGroupSizeReached(int sizeIn) {
        return false;
    }

    public boolean hurt(DamageSource source, float amount) {
        boolean prev = super.hurt(source, amount);
        if(prev){
            double range = 15;
            int fleeTime = 100 + getRandom().nextInt(150);
            this.revengeCooldown = fleeTime;
            List<? extends GiraffeEntity> list = this.level.getEntitiesOfClass(this.getClass(), this.getBoundingBox().inflate(range, range/2, range));
            for(GiraffeEntity gaz : list){
                gaz.revengeCooldown = fleeTime;

            }
        }
        return prev;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(RUNNING, Boolean.valueOf(false));
    }

    public boolean isRunning() {
        return this.entityData.get(RUNNING).booleanValue();
    }

    public void setRunning(boolean running) {
        this.entityData.set(RUNNING, Boolean.valueOf(running));
    }

    public boolean isFood(ItemStack stack) {
        return FOOD_ITEMS.test(stack);
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 50.0D).add(Attributes.ATTACK_DAMAGE, 3.0F).add(Attributes.MOVEMENT_SPEED, 0.3F);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("GiraffeRunning", this.isRunning());
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {

        if (event.isMoving() && !(this.isRunning())) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.giraffe.walk", true));
            return PlayState.CONTINUE;
        }
        if (event.isMoving() && this.isRunning()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.giraffe.run", true));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.giraffe.idle", true));
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
    public void tick() {
        super.tick();
        if(!this.level.isClientSide){
            if(revengeCooldown >= 0){
                revengeCooldown--;
            }
            if(revengeCooldown == 0 && this.getLastHurtByMob() != null){
                this.setLastHurtByMob(null);
            }
            this.setRunning(revengeCooldown > 0);
            if(isRunning() && !hasSpedUp){
                hasSpedUp = true;
                this.setSprinting(true);
                this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.6F);
            }
            if(!isRunning() && hasSpedUp){
                hasSpedUp = false;
                this.setSprinting(false);
                this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.3F);
            }
        }
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setRunning(compound.getBoolean("GiraffeRunning"));
    }
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob mob) {
        return CCEntityRegistry.GIRAFFE.get().create(serverLevel);
    }
    @Override
    public void onPanic() {
    }

    @Override
    public boolean canPanic() {
        return true;
    }
}