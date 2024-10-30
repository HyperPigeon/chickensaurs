package net.hyper_pigeon.chickensaurs.client.renderer.entity;

import net.hyper_pigeon.chickensaurs.Constants;
import net.hyper_pigeon.chickensaurs.client.model.ChickensaurModel;
import net.hyper_pigeon.chickensaurs.entity.Chickensaur;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.resources.ResourceLocation;

public class ChickensaurRenderer extends MobRenderer<Chickensaur, ChickensaurModel>{

    private static final ResourceLocation DEFAULT = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID,"textures/entity/chickensaur/chickensaur.png");
    private static final ResourceLocation SADDLE = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID,"textures/entity/chickensaur/chickensaur_saddle.png");
    public ChickensaurRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new ChickensaurModel(pContext.bakeLayer(ChickensaurModel.LAYER_LOCATION)), 0.5f);
        this.addLayer(
                new SaddleLayer<>(
                        this, new ChickensaurModel(pContext.bakeLayer(ChickensaurModel.SADDLE_LAYER_LOCATION)), SADDLE
                )
        );
    }

    @Override
    public ResourceLocation getTextureLocation(Chickensaur chickensaur) {
        return DEFAULT;
    }
}
