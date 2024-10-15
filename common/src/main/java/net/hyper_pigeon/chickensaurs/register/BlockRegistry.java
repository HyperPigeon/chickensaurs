package net.hyper_pigeon.chickensaurs.register;

import net.hyper_pigeon.chickensaurs.block.ChickensaurEggBlock;
import net.hyper_pigeon.chickensaurs.platform.Services;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public final class BlockRegistry {

    public static final Supplier<ChickensaurEggBlock> CHICKENSAUR_EGG = registerBlock("chickensaur_egg", ChickensaurEggBlock::new);

    public static void init() {}

    private static <T extends Block> Supplier<T> registerBlock(String id, Supplier<T> block) {
        return Services.PLATFORM.registerBlock(id, block);
    }
}