package net.hyper_pigeon.chickensaurs.register;


import net.hyper_pigeon.chickensaurs.Constants;
import net.hyper_pigeon.chickensaurs.platform.Services;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

public final class SoundRegistry {

    public static final Supplier<SoundEvent> BRUSH = registerSound("chickensaur_brush",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "chickensaur_brush")));
    public static final Supplier<SoundEvent> STEP_ONE = registerSound("chickensaur_step_one",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "chickensaur_step_one")));
    public static final Supplier<SoundEvent> STEP_TWO = registerSound("chickensaur_step_two",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "chickensaur_step_two")));
    public static final Supplier<SoundEvent> HURT_ONE = registerSound("chickensaur_hurt_one",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "chickensaur_hurt_one")));
    public static final Supplier<SoundEvent> HURT_TWO = registerSound("chickensaur_hurt_two",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "chickensaur_hurt_two")));
    public static final Supplier<SoundEvent> IDLE_ONE = registerSound("chickensaur_idle_one",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "chickensaur_idle_one")));
    public static final Supplier<SoundEvent> IDLE_TWO = registerSound("chickensaur_idle_two",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "chickensaur_idle_two")));
    public static final Supplier<SoundEvent> THREATEN = registerSound("chickensaur_threaten",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "chickensaur_threaten")));


    public static void init() {}

    private static <T extends SoundEvent> Supplier<T> registerSound(String id, Supplier<T> sound) {
        return Services.PLATFORM.registerSound(id, sound);
    }
}
