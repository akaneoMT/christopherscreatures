package net.akaneo.christopherscreatures.entity.lioness;

import net.akaneo.christopherscreatures.ChristophersCreatures;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class LionessModel extends AnimatedGeoModel<LionessEntity> {
    @Override
    public ResourceLocation getModelLocation(LionessEntity object) {
        return new ResourceLocation(ChristophersCreatures.MOD_ID, "geo/lioness.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(LionessEntity object) {
        return new ResourceLocation(ChristophersCreatures.MOD_ID, "textures/entity/lion/lioness.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(LionessEntity animatable) {
        return new ResourceLocation(ChristophersCreatures.MOD_ID, "animations/lioness.animation.json");
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void setLivingAnimations(LionessEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("Head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }
}
