package net.hyper_pigeon.chickensaurs.client.renderer.entity;

import net.hyper_pigeon.chickensaurs.Constants;
import net.hyper_pigeon.chickensaurs.client.model.ChickensaurModel;
import net.hyper_pigeon.chickensaurs.entity.Chickensaur;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ChickensaurRenderer extends MobRenderer<Chickensaur, ChickensaurModel>{

    private static final ResourceLocation DEFAULT = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID,"textures/entity/chickensaur/chickensaur.png");
    public ChickensaurRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new ChickensaurModel(pContext.bakeLayer(ChickensaurModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(Chickensaur chickensaur) {
        return DEFAULT;
    }
}
