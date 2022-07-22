package net.akaneo.christopherscreatures.entity.lioness;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.akaneo.christopherscreatures.ChristophersCreatures;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class LionessRenderer extends GeoEntityRenderer<LionessEntity> {
    public LionessRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new LionessModel());
        this.shadowRadius = 0.5f;
    }

    @Override
    public ResourceLocation getTextureLocation(LionessEntity instance) {
        return new ResourceLocation(ChristophersCreatures.MOD_ID, "textures/entity/lion/lioness.png");
    }

    @Override
    public RenderType getRenderType(LionessEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        if(animatable.isBaby()) {
            stack.scale(0.4F, 0.4F, 0.4F);
        } else {
            stack.scale(1.0F, 1.0F, 1.0F);
        }

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
