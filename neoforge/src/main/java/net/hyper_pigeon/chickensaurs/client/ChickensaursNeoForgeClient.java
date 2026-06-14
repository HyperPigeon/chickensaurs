package net.hyper_pigeon.chickensaurs.client;

import net.hyper_pigeon.chickensaurs.Constants;
import net.hyper_pigeon.chickensaurs.client.model.ChickensaurModel;
import net.hyper_pigeon.chickensaurs.register.ItemRegistry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ChickensaursNeoForgeClient {
    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        ChickensaursClient.registerRenderers(event::registerEntityRenderer);
    }

    @SubscribeEvent
    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ChickensaurModel.LAYER_LOCATION, ChickensaurModel::createBodyLayer);
        event.registerLayerDefinition(ChickensaurModel.SADDLE_LAYER_LOCATION, ChickensaurModel::createBodyLayer);
        event.registerLayerDefinition(ChickensaurModel.SCALE_LEVEL_0_LOCATION, ChickensaurModel::createBodyLayer);
        event.registerLayerDefinition(ChickensaurModel.SCALE_LEVEL_1_LOCATION, ChickensaurModel::createBodyLayer);
        event.registerLayerDefinition(ChickensaurModel.SCALE_LEVEL_2_LOCATION, ChickensaurModel::createBodyLayer);
        event.registerLayerDefinition(ChickensaurModel.BAND_LAYER_LOCATION, ChickensaurModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void buildContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.insertAfter(new ItemStack(Items.COOKED_CHICKEN), new ItemStack(ItemRegistry.CHICKENSAUR.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ItemRegistry.CHICKENSAUR.get()), new ItemStack(ItemRegistry.COOKED_CHICKENSAUR.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ItemRegistry.COOKED_CHICKENSAUR.get()), new ItemStack(ItemRegistry.CHICKENSAUR_NUGGETS.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        } else if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.insertAfter(new ItemStack(Items.SNIFFER_EGG), new ItemStack(ItemRegistry.CHICKENSAUR_EGG.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        } else if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(ItemRegistry.CHICKENSAUR_SPAWN_EGG.get());
        }
    }
}
