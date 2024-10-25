package net.hyper_pigeon.chickensaurs.entity.ai.behavior;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.hyper_pigeon.chickensaurs.Constants;
import net.hyper_pigeon.chickensaurs.entity.Chickensaur;
import net.hyper_pigeon.chickensaurs.entity.ai.memory_types.ChickensaurMemoryTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.behavior.EntityTracker;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.tslat.smartbrainlib.api.core.behaviour.ExtendedBehaviour;
import net.tslat.smartbrainlib.util.BrainUtils;

import java.util.List;
import java.util.Optional;

public class IntimidateLivingEntity<E extends PathfinderMob> extends ExtendedBehaviour<E> {

    private LivingEntity intimidateTarget;

    private static final List<Pair<MemoryModuleType<?>, MemoryStatus>> MEMORY_REQUIREMENTS =
            ObjectArrayList.of(Pair.of(MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES, MemoryStatus.VALUE_PRESENT), Pair.of(ChickensaurMemoryTypes.INTIMIDATE_TARGET.get(), MemoryStatus.VALUE_ABSENT));

    @Override
    protected List<Pair<MemoryModuleType<?>, MemoryStatus>> getMemoryRequirements() {
        return MEMORY_REQUIREMENTS;
    }

    public boolean isIntimidateableEntity(LivingEntity livingEntity) {
        return livingEntity.getType().is(Constants.INTIMIDATE);
    }

    @Override
    protected boolean checkExtraStartConditions(ServerLevel level, E entity) {
        Optional<LivingEntity> livingEntity = BrainUtils.getMemory(entity, MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES).findClosest((livingEntity1) -> {
            Double distanceToChickensaur = livingEntity1.distanceToSqr(entity);
            return isIntimidateableEntity(livingEntity1) && distanceToChickensaur < 25 && distanceToChickensaur > 4;
        });
        if (entity.getHealth() > 5 && livingEntity.isPresent() && intimidateTarget == null) {
            this.intimidateTarget = livingEntity.get();
            return true;
        }
        return false;

    }

    @Override
    protected void start(E entity) {
        if (entity instanceof Chickensaur chickensaur) {
            chickensaur.setIntimidating(true);
            BrainUtils.setMemory(chickensaur, MemoryModuleType.LOOK_TARGET, new EntityTracker(intimidateTarget, true));
            BrainUtils.setMemory(chickensaur, ChickensaurMemoryTypes.INTIMIDATE_TARGET.get(), intimidateTarget);
        }
    }

    protected boolean shouldKeepRunning(E entity) {
        if (intimidateTarget != null && intimidateTarget.isAlive()) {
            Double distanceToChickensaur = intimidateTarget.distanceToSqr(entity);
            if (distanceToChickensaur < 25 && distanceToChickensaur > 4) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void stop(E entity) {
        Chickensaur chickensaur = (Chickensaur) entity;
        chickensaur.setIntimidating(false);
        this.intimidateTarget = null;
        BrainUtils.clearMemory(chickensaur, ChickensaurMemoryTypes.INTIMIDATE_TARGET.get());
        BrainUtils.clearMemory(chickensaur, MemoryModuleType.LOOK_TARGET);
    }
}
