package net.hyper_pigeon.chickensaurs.mixin;

import net.hyper_pigeon.chickensaurs.entity.Chickensaur;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value=Skeleton.class)
public abstract class SkeletonMixin extends AbstractSkeleton {

    protected SkeletonMixin(EntityType<? extends AbstractSkeleton> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Inject(method = "dropCustomDeathLoot", at=@At("TAIL"))
    public void dropHeadIfKilledByChickensaur(ServerLevel pLevel, DamageSource pDamageSource, boolean pRecentlyHit, CallbackInfo ci){
        Entity entity = pDamageSource.getEntity();
        if (entity instanceof Chickensaur) {
            this.spawnAtLocation(Items.SKELETON_SKULL);
        }
    }
}
