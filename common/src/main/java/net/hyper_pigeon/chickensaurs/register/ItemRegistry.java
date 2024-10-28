package net.hyper_pigeon.chickensaurs.register;

import net.hyper_pigeon.chickensaurs.Constants;
import net.hyper_pigeon.chickensaurs.platform.Services;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;

import java.util.function.Supplier;

public final class ItemRegistry {
    public static final Supplier<BlockItem> CHICKENSAUR_EGG = registerItem("chickensaur_egg", () -> new BlockItem(BlockRegistry.CHICKENSAUR_EGG.get(), new Item.Properties()));
    public static final Supplier<SpawnEggItem> CHICKENSAUR_SPAWN_EGG = registerItem("chickensaur_spawn_egg", () -> new SpawnEggItem(EntityRegistry.CHICKENSAUR.get(), 0xd4a66a, 0xbd7519, new Item.Properties()));
    public static final Supplier<Item> CHICKENSAUR = registerItem("chickensaur", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.3F)
            .effect(new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F)
            .build())));
    public static final Supplier<Item> COOKED_CHICKENSAUR = registerItem("cooked_chickensaur", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .nutrition(7)
            .saturationModifier(0.7F)
            .build())));
    public static final Supplier<Item> CHICKENSAUR_NUGGETS = registerItem("chickensaur_nuggets", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.4F)
            .fast()
            .build())));

    public static final Supplier<CreativeModeTab> CHICKENSAURS_TAB = Services.PLATFORM.registerCreativeModeTab("chickensaurs_items", () -> Services.PLATFORM.newCreativeTabBuilder()
            .title(Component.translatable("itemGroup." + Constants.MOD_ID + ".chickensaurs_items"))
            .icon(() -> new ItemStack(CHICKENSAUR_EGG.get()))
            .displayItems((enabledFeatures, entries) -> {
                entries.accept(CHICKENSAUR_EGG.get());
                entries.accept(CHICKENSAUR_SPAWN_EGG.get());
                entries.accept(CHICKENSAUR.get());
                entries.accept(COOKED_CHICKENSAUR.get());
                entries.accept(CHICKENSAUR_NUGGETS.get());
            })
            .build());

    public static void init(){

    }

    private static <T extends Item> Supplier<T> registerItem(String id, Supplier<T> item) {
        return Services.PLATFORM.registerItem(id, item);
    }
}
