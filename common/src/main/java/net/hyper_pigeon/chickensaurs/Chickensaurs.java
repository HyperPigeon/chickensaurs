package net.hyper_pigeon.chickensaurs;

import net.hyper_pigeon.chickensaurs.entity.ai.memory_types.ChickensaurMemoryTypes;
import net.hyper_pigeon.chickensaurs.platform.Services;
import net.hyper_pigeon.chickensaurs.register.*;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.DispenserBlock;

// This class is part of the common project meaning it is shared between all supported loaders. Code written here can only
// import and access the vanilla codebase, libraries used by vanilla, and optionally third party libraries that provide
// common compatible binaries. This means common code can not directly use loader specific concepts such as Forge events
// however it will be compatible with all supported mod loaders.
public class Chickensaurs {

    // The loader specific projects are able to import and use any code from the common project. This allows you to
    // write the majority of your code here and load it from your loader specific projects. This example has some
    // code that gets invoked by the entry point of the loader specific projects.
    public static void init() {

        Constants.LOG.info("Chickensaurs loaded on platform {}, environment {}!", Services.PLATFORM.getPlatformName(), Services.PLATFORM.getEnvironmentName());

        BlockEntityRegistry.init();
        BlockRegistry.init();
        EntityRegistry.init();
        ItemRegistry.init();
        SoundRegistry.init();
        ChickensaurMemoryTypes.init();
        StructureRegistry.init();
    }
}