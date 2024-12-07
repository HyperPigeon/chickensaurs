package net.hyper_pigeon.chickensaurs;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.hyper_pigeon.chickensaurs.entity.Chickensaur;
import net.hyper_pigeon.chickensaurs.register.EntityRegistry;
import net.hyper_pigeon.chickensaurs.register.ItemRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.levelgen.Heightmap;

public class ChickensaursFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        
        // This method is invoked by the Fabric mod loader when it is ready
        // to load your mod. You can access Fabric and Common code in this
        // project.

        Chickensaurs.init();

        EntityRegistry.registerEntityAttributes(FabricDefaultAttributeRegistry::register);
        SpawnPlacements.register(EntityRegistry.CHICKENSAUR.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Chickensaur::checkChickensaurSpawnRules);
        BiomeModifications.addSpawn(BiomeSelectors.tag(Constants.CHICKENSAUR_SPAWN_BIOMES), MobCategory.CREATURE, EntityRegistry.CHICKENSAUR.get(), 300, 3,6);
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(content -> {
            content.addAfter(Items.COOKED_CHICKEN, ItemRegistry.CHICKENSAUR.get());
            content.addAfter(ItemRegistry.CHICKENSAUR.get(), ItemRegistry.COOKED_CHICKENSAUR.get());
            content.addAfter(ItemRegistry.COOKED_CHICKENSAUR.get(), ItemRegistry.CHICKENSAUR_NUGGETS.get());
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(content -> {
            content.addAfter(Items.SNIFFER_EGG, ItemRegistry.CHICKENSAUR_EGG.get());
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.SPAWN_EGGS).register(content -> {
            content.accept(ItemRegistry.CHICKENSAUR_SPAWN_EGG.get());
        });
    }
}

