package net.hyper_pigeon.chickensaurs.mixin;

import net.hyper_pigeon.chickensaurs.entity.Chickensaur;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BaseFireBlock.class)
public abstract class BaseFireBlockMixin extends Block {

    public BaseFireBlockMixin(Properties pProperties) {
        super(pProperties);
    }

    @Inject(method = "entityInside", at = @At("HEAD"), cancellable = true)
    public void chickensaurImmunity(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity, CallbackInfo ci){
        if(pEntity instanceof Chickensaur) {
            entityInside(pState,pLevel,pPos,pEntity);
            ci.cancel();
        }
    }

}
