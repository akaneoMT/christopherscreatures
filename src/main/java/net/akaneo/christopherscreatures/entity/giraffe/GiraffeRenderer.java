package net.akaneo.christopherscreatures.entity.giraffe;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.akaneo.christopherscreatures.ChristophersCreatures;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GiraffeRenderer extends GeoEntityRenderer<GiraffeEntity> {
    public GiraffeRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new GiraffeModel());
        this.shadowRadius = 0.9f;
    }

    @Override
    public ResourceLocation getTextureLocation(GiraffeEntity instance) {
        return new ResourceLocation(ChristophersCreatures.MOD_ID, "textures/entity/giraffe.png");
    }

    @Override
    public RenderType getRenderType(GiraffeEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        if(animatable.isBaby()) {
            stack.scale(0.6F, 0.6F, 0.6F);
        } else {
            stack.scale(1.1F, 1.1F, 1.1F);
        }

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
