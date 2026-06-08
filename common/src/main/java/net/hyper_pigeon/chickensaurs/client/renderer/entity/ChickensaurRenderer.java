package net.hyper_pigeon.chickensaurs.client.renderer.entity;

import net.hyper_pigeon.chickensaurs.Constants;
import net.hyper_pigeon.chickensaurs.client.model.ChickensaurModel;
import net.hyper_pigeon.chickensaurs.client.renderer.entity.layers.ChickensaurBandLayer;
import net.hyper_pigeon.chickensaurs.entity.Chickensaur;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.resources.ResourceLocation;

public class ChickensaurRenderer extends MobRenderer<Chickensaur, ChickensaurModel>{

    private static final ResourceLocation DEFAULT = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID,"textures/entity/chickensaur/chickensaur.png");
    private static final ResourceLocation SADDLE = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID,"textures/entity/chickensaur/chickensaur_saddle.png");
    private static final ResourceLocation SCALES_LEVEL_0 = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "textures/entity/chickensaur/chickensaur_0.png");
    private static final ResourceLocation SCALES_LEVEL_1 = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "textures/entity/chickensaur/chickensaur_1.png");
    private static final ResourceLocation SCALES_LEVEL_2 = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "textures/entity/chickensaur/chickensaur_2.png");


    public ChickensaurRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new ChickensaurModel(pContext.bakeLayer(ChickensaurModel.LAYER_LOCATION)), 0.5f);
        this.addLayer(
                new SaddleLayer<>(
                        this, new ChickensaurModel(pContext.bakeLayer(ChickensaurModel.SADDLE_LAYER_LOCATION)), SADDLE
                )
        );
        this.addLayer(new ChickensaurBandLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(Chickensaur chickensaur) {
        if(chickensaur.getBrushAmount() <= 0) {
            return SCALES_LEVEL_0;
        } else if (chickensaur.getBrushAmount() == 1) {
            return SCALES_LEVEL_1;
        } else if (chickensaur.getBrushAmount() == 2) {
            return SCALES_LEVEL_2;
        }
        return DEFAULT;
    }
}
