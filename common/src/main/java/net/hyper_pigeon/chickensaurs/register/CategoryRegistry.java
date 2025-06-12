package net.hyper_pigeon.chickensaurs.register;

import net.minecraft.world.entity.MobCategory;

public record CategoryRegistry() {
    static {
        MobCategory.values();
    }
    public static MobCategory CHICKENSAUR;
}
