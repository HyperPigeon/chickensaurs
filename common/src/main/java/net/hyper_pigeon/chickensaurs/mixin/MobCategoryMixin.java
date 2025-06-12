package net.hyper_pigeon.chickensaurs.mixin;

import net.hyper_pigeon.chickensaurs.register.CategoryRegistry;
import net.minecraft.world.entity.MobCategory;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(MobCategory.class)
public class MobCategoryMixin {
    @Unique
    private static final int OPCODE_PUTSTATIC = 179;

    @SuppressWarnings("InvokerTarget")
    @Invoker("<init>")
    private static MobCategory newMobCategory(
            String internalName,
            int internalId,
            String name,
            final int j,
            final boolean bl,
            final boolean bl2,
            final int k
    ) {
        throw new AssertionError();
    }

    @Shadow
    private static @Final
    @Mutable MobCategory[] $VALUES;

    @Inject(
            method = "<clinit>", at = @At(
            value = "FIELD", opcode = OPCODE_PUTSTATIC,
            target = "net/minecraft/world/entity/MobCategory.$VALUES:[Lnet/minecraft/world/entity/MobCategory;", shift = At.Shift.AFTER
    )
    )
    private static void addCustomMobCategory(CallbackInfo ci) {
        var categories = new ArrayList<>(Arrays.asList($VALUES));
        var last = categories.get(categories.size() - 1);
        var chickensaur = newMobCategory("CHICKENSAUR", last.ordinal() + 1, "chickensaur", 75, false, false, 128);
        CategoryRegistry.CHICKENSAUR = chickensaur;
        categories.add(chickensaur);
        $VALUES = categories.toArray(new MobCategory[0]);
    }
}
