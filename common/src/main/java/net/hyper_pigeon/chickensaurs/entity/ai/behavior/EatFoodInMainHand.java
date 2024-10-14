package net.hyper_pigeon.chickensaurs.entity.ai.behavior;

import com.mojang.datafixers.util.Pair;
import net.hyper_pigeon.chickensaurs.entity.Chickensaur;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.tslat.smartbrainlib.api.core.behaviour.ExtendedBehaviour;

import java.util.List;

public class EatFoodInMainHand<E extends PathfinderMob> extends ExtendedBehaviour<E> {
    @Override
    protected List<Pair<MemoryModuleType<?>, MemoryStatus>> getMemoryRequirements() {
        return List.of();
    }

    public boolean hasFood(Chickensaur chickensaur) {
        return chickensaur.isFood(chickensaur.getMainHandItem());
    }


    protected boolean checkExtraStartConditions(ServerLevel level, E entity) {
        return hasFood((Chickensaur) entity);
    }

    protected boolean shouldKeepRunning(E entity) {
        return hasFood((Chickensaur) entity);
    }

    protected void stop(ServerLevel level, E entity, long gameTime) {
        if(hasFood((Chickensaur) entity)) {
            this.eat((Chickensaur) entity,level,entity.getMainHandItem());
            entity.heal(5.0F);
        }
    }

    public ItemStack eat(Chickensaur chickensaur, Level pLevel, ItemStack pFood) {
        pLevel.playSound(null, chickensaur.getX(), chickensaur.getY(), chickensaur.getZ(), chickensaur.getEatingSound(pFood), SoundSource.NEUTRAL, 1.0F, 1.0F + (pLevel.random.nextFloat() - pLevel.random.nextFloat()) * 0.4F);
        pFood.consume(1, chickensaur);
        chickensaur.gameEvent(GameEvent.EAT);
        return pFood;
    }
}
