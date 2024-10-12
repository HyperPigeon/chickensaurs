package net.hyper_pigeon.chickensaurs.register;

import net.hyper_pigeon.chickensaurs.Constants;
import net.hyper_pigeon.chickensaurs.platform.Services;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.function.Supplier;

public final class ItemRegistry {

    public static final Supplier<CreativeModeTab> MORETOTEMS_TAB = Services.PLATFORM.registerCreativeModeTab("chickensaurs_items", () -> Services.PLATFORM.newCreativeTabBuilder()
            .title(Component.translatable("itemGroup." + Constants.MOD_ID + ".chickensaurs_items"))
            .icon(() -> new ItemStack(Items.EGG))
            .displayItems((enabledFeatures, entries) -> {

            })
            .build());

    public static void init(){

    }

    private static <T extends Item> Supplier<T> registerItem(String id, Supplier<T> item) {
        return Services.PLATFORM.registerItem(id, item);
    }
}
