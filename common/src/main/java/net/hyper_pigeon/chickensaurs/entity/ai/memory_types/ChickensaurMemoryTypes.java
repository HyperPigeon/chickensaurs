package net.hyper_pigeon.chickensaurs.entity.ai.memory_types;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.tslat.smartbrainlib.SBLConstants;

import java.util.Optional;
import java.util.function.Supplier;

public class ChickensaurMemoryTypes {
    public static Supplier<MemoryModuleType<LivingEntity>> INTIMIDATE_TARGET;
    public static void init(){
        INTIMIDATE_TARGET =  SBLConstants.SBL_LOADER.registerMemoryType("intimidate_target", Optional.empty());
    };
}
