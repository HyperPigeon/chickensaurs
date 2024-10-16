package net.hyper_pigeon.chickensaurs.mixin;

import net.hyper_pigeon.chickensaurs.entity.Chickensaur;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.SoulFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SoulFireBlock.class)
public abstract class SoulFireBlockMixin extends BaseFireBlock {

    public SoulFireBlockMixin(Properties pProperties, float pFireDamage) {
        super(pProperties, pFireDamage);
    }

    protected void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if(!(pEntity instanceof Chickensaur)){
            super.entityInside(pState, pLevel, pPos, pEntity);
        }
    }
}
