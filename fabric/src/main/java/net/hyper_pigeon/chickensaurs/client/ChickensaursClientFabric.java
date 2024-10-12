package net.hyper_pigeon.chickensaurs.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.hyper_pigeon.chickensaurs.client.model.ChickensaurModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.entity.animal.Chicken;

public class ChickensaursClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ChickensaursClient.registerRenderers(EntityRendererRegistry::register, BlockEntityRenderers::register);
        EntityModelLayerRegistry.registerModelLayer(ChickensaurModel.LAYER_LOCATION, ChickensaurModel::createBodyLayer);
    }
}