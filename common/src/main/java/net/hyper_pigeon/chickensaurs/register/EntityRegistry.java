package net.hyper_pigeon.chickensaurs.register;

import net.hyper_pigeon.chickensaurs.entity.Chickensaur;
import net.hyper_pigeon.chickensaurs.platform.Services;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class EntityRegistry {

    public static final Supplier<EntityType<Chickensaur>> CHICKENSAUR = registerEntity("chickensaur", Chickensaur::new, 1.3964844F, 1.6F, MobCategory.CREATURE);

    public static void init(){

    }

    public static void registerEntityAttributes(BiConsumer<EntityType<? extends LivingEntity>, AttributeSupplier> registrar) {
        registrar.accept(EntityRegistry.CHICKENSAUR.get(), Chickensaur.createAttributes().build());
    }

    private static <T extends Mob> Supplier<EntityType<T>> registerEntity(String name, EntityType.EntityFactory<T> entity, float width, float height, MobCategory mobCategory) {
        return Services.PLATFORM.registerEntity(name, () -> EntityType.Builder.of(entity,mobCategory).sized(width, height).build(name));
    }
}
