package net.akaneo.christopherscreatures.entity.antelope.sable;

import net.akaneo.christopherscreatures.ChristophersCreatures;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class SableModel extends AnimatedGeoModel<SableEntity> {

    private static final ResourceLocation TEXTURE_FEMALE = new ResourceLocation("christopherscreatures:textures/entity/antelope/sable/sable_female.png");
    private static final ResourceLocation TEXTURE_MALE = new ResourceLocation("christopherscreatures:textures/entity/antelope/sable/sable_male.png");

    @Override
    public ResourceLocation getModelLocation(SableEntity object) {
        return new ResourceLocation(ChristophersCreatures.MOD_ID, "geo/sable.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SableEntity entity) {
        return entity.isMale() ? TEXTURE_MALE : TEXTURE_FEMALE;
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SableEntity animatable) {
        return new ResourceLocation(ChristophersCreatures.MOD_ID, "animations/sable.animation.json");
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void setLivingAnimations(SableEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("Head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }
}
