package net.akaneo.christopherscreatures.entity.giraffe;

import net.akaneo.christopherscreatures.ChristophersCreatures;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class GiraffeModel extends AnimatedGeoModel<GiraffeEntity> {
    @Override
    public ResourceLocation getModelLocation(GiraffeEntity object) {
        return new ResourceLocation(ChristophersCreatures.MOD_ID, "geo/giraffe.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GiraffeEntity object) {
        return new ResourceLocation(ChristophersCreatures.MOD_ID, "textures/entity/giraffe.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GiraffeEntity animatable) {
        return new ResourceLocation(ChristophersCreatures.MOD_ID, "animations/giraffe.animation.json");
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void setLivingAnimations(GiraffeEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("Head2");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 165F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 165F));
        }
    }
}
