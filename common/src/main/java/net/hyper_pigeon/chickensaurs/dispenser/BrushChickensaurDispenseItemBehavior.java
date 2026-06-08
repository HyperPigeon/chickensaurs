package net.hyper_pigeon.chickensaurs.dispenser;

import net.hyper_pigeon.chickensaurs.entity.Chickensaur;
import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.phys.AABB;

public class BrushChickensaurDispenseItemBehavior extends OptionalDispenseItemBehavior {
    @Override
    protected ItemStack execute(BlockSource pBlockSource, ItemStack pItem) {
        ServerLevel serverlevel = pBlockSource.level();
        if (!serverlevel.isClientSide()) {
            BlockPos blockpos = pBlockSource.pos().relative(pBlockSource.state().getValue(DispenserBlock.FACING));
            this.setSuccess(tryBrushChickensaur(serverlevel, blockpos, pItem));
            if (this.isSuccess()) {
                pItem.hurtAndBreak(1, serverlevel, null, p_348118_ -> {
                });
            }
        }

        return pItem;
    }

    private static boolean tryBrushChickensaur(ServerLevel pLevel, BlockPos pPos, ItemStack stack) {
        for (Chickensaur chickensaur : pLevel.getEntitiesOfClass(Chickensaur.class, new AABB(pPos), EntitySelector.NO_SPECTATORS)) {
            if (chickensaur.brush(stack)) {
                return true;
            }
        }

        return false;
    }
}
