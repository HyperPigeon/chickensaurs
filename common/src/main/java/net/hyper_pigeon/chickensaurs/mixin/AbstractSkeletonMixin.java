package net.hyper_pigeon.chickensaurs.mixin;

import net.hyper_pigeon.chickensaurs.entity.Chickensaur;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = AbstractSkeleton.class, priority = 1)
public abstract class AbstractSkeletonMixin extends Monster {

    protected AbstractSkeletonMixin(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    /**
     * @author CyborgPigeon
     * @reason The hurt() method was overrided in AbstractSkeletonMixin to allow Chickensaurs to do extra damage to skeletons and provide them with an insta-kill attack on AbstractSkeletons
     * with 1/3 health or less.
     */
    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        if(pSource.getEntity() instanceof Chickensaur && getHealth() <= (1/3)*getMaxHealth()){
            super.hurt(pSource, 1000.0F);
            return true;
        } else {
            return this.isInvulnerableTo(pSource) ? false : super.hurt(pSource, pAmount);
        }

    }
}
