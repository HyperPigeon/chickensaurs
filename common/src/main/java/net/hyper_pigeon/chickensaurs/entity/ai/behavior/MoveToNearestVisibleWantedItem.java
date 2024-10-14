package net.hyper_pigeon.chickensaurs.entity.ai.behavior;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.EntityTracker;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.memory.WalkTarget;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;
import net.tslat.smartbrainlib.api.core.behaviour.ExtendedBehaviour;
import net.tslat.smartbrainlib.util.BrainUtils;

import java.util.List;
import java.util.function.BiFunction;

public class MoveToNearestVisibleWantedItem<E extends PathfinderMob> extends ExtendedBehaviour<E> {

    private static final List<Pair<MemoryModuleType<?>, MemoryStatus>> MEMORY_REQUIREMENTS = ObjectArrayList.of(Pair.of(MemoryModuleType.NEAREST_VISIBLE_WANTED_ITEM, MemoryStatus.VALUE_PRESENT));
    protected BiFunction<E, Vec3, Float> speedModifier = (entity, targetPos) -> 1f;
    private ItemEntity targetItem;

    @Override
    protected List<Pair<MemoryModuleType<?>, MemoryStatus>> getMemoryRequirements() {
        return MEMORY_REQUIREMENTS;
    }

    /**
     * Set the movespeed modifier for the path when chosen.
     * @param modifier The movespeed modifier/multiplier
     * @return this
     */
    public MoveToNearestVisibleWantedItem<E> speedModifier(float modifier) {
        return speedModifier((entity, targetPos) -> modifier);
    }

    /**
     * Set the movespeed modifier for the path when chosen.
     * @param function The movespeed modifier/multiplier function
     * @return this
     */
    public MoveToNearestVisibleWantedItem<E> speedModifier(BiFunction<E, Vec3, Float> function) {
        this.speedModifier = function;

        return this;
    }

    public boolean hasFood(E entity) {
        return !entity.getMainHandItem().isEmpty();
    }

    protected boolean doStartCheck(ServerLevel level, E entity, long gameTime) {
        return super.doStartCheck(level, entity, gameTime);
    }

    @Override
    protected boolean checkExtraStartConditions(ServerLevel level, E entity) {
        ItemEntity itemEntity = BrainUtils.getMemory(entity, MemoryModuleType.NEAREST_VISIBLE_WANTED_ITEM);
        return !hasFood(entity) && (this.targetItem == null || entity.distanceToSqr(targetItem) > entity.distanceTo(itemEntity));
    }


    @Override
    protected void start(E entity) {
        ItemEntity itemEntity = BrainUtils.getMemory(entity, MemoryModuleType.NEAREST_VISIBLE_WANTED_ITEM);
        this.targetItem = itemEntity;

        EntityTracker entityLookTarget = new EntityTracker(itemEntity, false);
        WalkTarget walkTarget = new WalkTarget(entityLookTarget, this.speedModifier.apply(entity,this.targetItem.position()), 1);

        BrainUtils.clearMemory(entity, MemoryModuleType.WALK_TARGET);
        BrainUtils.setMemory(entity, MemoryModuleType.WALK_TARGET, walkTarget);

        BrainUtils.clearMemory(entity, MemoryModuleType.LOOK_TARGET);
        BrainUtils.setMemory(entity, MemoryModuleType.LOOK_TARGET, entityLookTarget);
    }
}
