package net.akaneo.christopherscreatures.entity.antelope.sable;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.akaneo.christopherscreatures.ChristophersCreatures;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SableRenderer extends GeoEntityRenderer<SableEntity> {
    public SableRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new SableModel());
        this.shadowRadius = 0.7f;
    }

    @Override
    public ResourceLocation getTextureLocation(SableEntity instance) {
        return new ResourceLocation(ChristophersCreatures.MOD_ID, "textures/entity/antelope/sable.png");
    }

    @Override
    public RenderType getRenderType(SableEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        if(animatable.isBaby()) {
            stack.scale(0.6F, 0.6F, 0.6F);
        } else {
            stack.scale(1.0F, 1.0F, 1.0F);
        }

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
