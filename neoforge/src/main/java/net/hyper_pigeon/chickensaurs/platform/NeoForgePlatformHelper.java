package net.hyper_pigeon.chickensaurs.platform;

import com.mojang.serialization.MapCodec;
import net.hyper_pigeon.chickensaurs.ChickensaursNeoForge;
import net.hyper_pigeon.chickensaurs.platform.services.IPlatformHelper;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;

import java.util.function.Supplier;

public class NeoForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {

        return "NeoForge";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return !FMLLoader.isProduction();
    }

    @Override
    public <T extends BlockEntity> Supplier<BlockEntityType<T>> registerBlockEntity(String id, Supplier<BlockEntityType<T>> blockEntityType) {
        return ChickensaursNeoForge.BLOCK_ENTITIES.register(id, blockEntityType);
    }

    @Override
    public <T extends Block> Supplier<T> registerBlock(String id, Supplier<T> block) {
        return ChickensaursNeoForge.BLOCKS.register(id, block);
    }

    @Override
    public <T extends Entity> Supplier<EntityType<T>> registerEntity(String id, Supplier<EntityType<T>> entity) {
        return ChickensaursNeoForge.ENTITIES.register(id, entity);
    }

    @Override
    public <T extends Item> Supplier<T> registerItem(String id, Supplier<T> item) {
        return ChickensaursNeoForge.ITEMS.register(id, item);
    }

    @Override
    public Holder<MobEffect> registerMobEffect(String name, MobEffect mobEffect) {
        return ChickensaursNeoForge.MOB_EFFECTS.register(name,() -> mobEffect);
    }


    @Override
    public <T extends SoundEvent> Supplier<T> registerSound(String id, Supplier<T> sound) {
        return ChickensaursNeoForge.SOUND_EVENTS.register(id, sound);
    }

    @Override
    public <T extends CreativeModeTab> Supplier<T> registerCreativeModeTab(String id, Supplier<T> tab) {
        return ChickensaursNeoForge.CREATIVE_TABS.register(id, tab);
    }

    @Override
    public <S extends Structure, T extends StructureType<S>> Supplier<T> registerStructureType(String id, Supplier<MapCodec<S>> mapCodec) {
        return (Supplier<T>) ChickensaursNeoForge.STRUCTURE_TYPE.register(id,() -> explicitStructureTypeTyping(mapCodec.get()));
    }

    @Override
    public <T extends StructurePieceType> Supplier<T> registerStructurePieceType(String id, Supplier<T> structurePiece) {
        return ChickensaursNeoForge.STRUCTURE_PIECE_TYPE.register(id,structurePiece);
    }

    private static <T extends Structure> StructureType<T> explicitStructureTypeTyping(MapCodec<T> structureCodec) {
        return () -> structureCodec;
    }


    @Override
    public <E extends Mob> Supplier<SpawnEggItem> makeSpawnEggFor(Supplier<EntityType<E>> entityType, int primaryEggColour, int secondaryEggColour, Item.Properties itemProperties) {
        return () -> new DeferredSpawnEggItem(entityType, primaryEggColour, secondaryEggColour, itemProperties);
    }

    @Override
    public CreativeModeTab.Builder newCreativeTabBuilder() {
        return CreativeModeTab.builder();
    }
}