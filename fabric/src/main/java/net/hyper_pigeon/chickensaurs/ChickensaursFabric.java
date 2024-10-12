package net.hyper_pigeon.chickensaurs;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.hyper_pigeon.chickensaurs.register.EntityRegistry;

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
    }
}
