package net.hyper_pigeon.chickensaurs;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.hyper_pigeon.chickensaurs.entity.Chickensaur;
import net.hyper_pigeon.chickensaurs.register.EntityRegistry;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;

public class ChickensaursFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        
        // This method is invoked by the Fabric mod loader when it is ready
        // to load your mod. You can access Fabric and Common code in this
        // project.

        // Use Fabric to bootstrap the Common mod.
        Constants.LOG.info("Hello Fabric world!");
        Chickensaurs.init();

        EntityRegistry.registerEntityAttributes(FabricDefaultAttributeRegistry::register);
        SpawnPlacements.register(EntityRegistry.CHICKENSAUR.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Chickensaur::checkChickensaurSpawnRules);
        BiomeModifications.addSpawn(BiomeSelectors.tag(Constants.CHICKENSAUR_SPAWN_BIOMES), MobCategory.CREATURE, EntityRegistry.CHICKENSAUR.get(), 150, 3,4);

    }
}
