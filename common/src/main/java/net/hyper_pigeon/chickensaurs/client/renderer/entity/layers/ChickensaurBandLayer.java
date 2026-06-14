package net.hyper_pigeon.chickensaurs.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.hyper_pigeon.chickensaurs.Constants;
import net.hyper_pigeon.chickensaurs.client.model.ChickensaurModel;
import net.hyper_pigeon.chickensaurs.entity.Chickensaur;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class ChickensaurBandLayer extends RenderLayer<Chickensaur, ChickensaurModel> {

    private static final ResourceLocation BAND_LOCATION = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "textures/entity/chickensaur/chickensaur_band.png");

    public ChickensaurBandLayer(RenderLayerParent<Chickensaur, ChickensaurModel> pRenderer) {
        super(pRenderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, Chickensaur chickensaur, float v, float v1, float v2, float v3, float v4, float v5) {
        if (chickensaur.hasOwner() && !chickensaur.isInvisible()) {
            int color = chickensaur.getCollarColor();
            VertexConsumer vertexconsumer = multiBufferSource.getBuffer(RenderType.entityCutoutNoCull(BAND_LOCATION));
            this.getParentModel().renderToBuffer(poseStack, vertexconsumer, i, OverlayTexture.NO_OVERLAY, color);
        }
    }
}
