package net.hyper_pigeon.chickensaurs;

import com.google.common.base.Preconditions;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.hyper_pigeon.chickensaurs.entity.Chickensaur;
import net.hyper_pigeon.chickensaurs.register.CategoryRegistry;
import net.hyper_pigeon.chickensaurs.register.EntityRegistry;
import net.hyper_pigeon.chickensaurs.register.ItemRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.function.Predicate;

public class ChickensaursFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        
        // This method is invoked by the Fabric mod loader when it is ready
        // to load your mod. You can access Fabric and Common code in this
        // project.

        Chickensaurs.init();

        EntityRegistry.registerEntityAttributes(FabricDefaultAttributeRegistry::register);
        SpawnPlacements.register(EntityRegistry.CHICKENSAUR.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Chickensaur::checkChickensaurSpawnRules);
        addSpawnWithCosts(BiomeSelectors.tag(Constants.CHICKENSAUR_SPAWN_BIOMES), EntityRegistry.CHICKENSAUR_CATEGORY, EntityRegistry.CHICKENSAUR.get(), 2, 2,2,0.7F,0.15F);
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

    public static void addSpawnWithCosts(Predicate<BiomeSelectionContext> biomeSelector,
                                MobCategory spawnGroup, EntityType<?> entityType,
                                int weight, int minGroupSize, int maxGroupSize, float mass, float gravityLimit) {
        // See constructor of SpawnSettings.SpawnEntry for context
        Preconditions.checkArgument(entityType.getCategory() != MobCategory.MISC,
                "Cannot add spawns for entities with spawnGroup=MISC since they'd be replaced by pigs.");

        // We need the entity type to be registered, or we cannot deduce an ID otherwise
        ResourceLocation id = BuiltInRegistries.ENTITY_TYPE.getKey(entityType);
        Preconditions.checkState(BuiltInRegistries.ENTITY_TYPE.getResourceKey(entityType).isPresent(), "Unregistered entity type: %s", entityType);

        BiomeModifications.create(id).add(ModificationPhase.ADDITIONS, biomeSelector, context -> {
            context.getSpawnSettings().addSpawn(spawnGroup, new MobSpawnSettings.SpawnerData(entityType, weight, minGroupSize, maxGroupSize));
            context.getSpawnSettings().setSpawnCost(EntityRegistry.CHICKENSAUR.get(), mass,gravityLimit);
        });
    }
}

