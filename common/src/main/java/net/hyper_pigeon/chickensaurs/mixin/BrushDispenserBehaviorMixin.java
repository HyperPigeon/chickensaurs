package net.hyper_pigeon.chickensaurs.mixin;

import net.hyper_pigeon.chickensaurs.entity.Chickensaur;
import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net/minecraft/core/dispenser/DispenseItemBehavior$16")
public class BrushDispenserBehaviorMixin extends OptionalDispenseItemBehavior {
    @Inject(method = "execute(Lnet/minecraft/core/dispenser/BlockSource;Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/item/ItemStack;", at=@At("HEAD"), cancellable = true)
    protected void chickensaurs$execute(BlockSource pBlockSource, ItemStack pItem, CallbackInfoReturnable<ItemStack> cir) {
        ServerLevel serverlevel = pBlockSource.level();
        if (!serverlevel.isClientSide()) {
            BlockPos blockpos = pBlockSource.pos().relative(pBlockSource.state().getValue(DispenserBlock.FACING));
            this.setSuccess(chickensaurs$tryBrushChickensaur(serverlevel, blockpos, pItem));
            if (this.isSuccess()) {
                pItem.hurtAndBreak(16, serverlevel, null, p_348118_ -> {
                });
                cir.setReturnValue(pItem);
            }
        }
    }

    @Unique
    private static boolean chickensaurs$tryBrushChickensaur(ServerLevel pLevel, BlockPos pPos, ItemStack stack) {
        for (Chickensaur chickensaur : pLevel.getEntitiesOfClass(Chickensaur.class, new AABB(pPos), EntitySelector.NO_SPECTATORS)) {
            if (chickensaur.brush(stack)) {
                return true;
            }
        }

        return false;
    }
}
