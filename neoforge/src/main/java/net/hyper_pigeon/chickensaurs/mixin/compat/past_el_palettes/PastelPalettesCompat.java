package net.hyper_pigeon.chickensaurs.mixin.compat.past_el_palettes;

import com.mynamesraph.pastelpalettes.item.PastelDyeItem;
import net.hyper_pigeon.chickensaurs.entity.Chickensaur;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Chickensaur.class)
public abstract class PastelPalettesCompat {

    @Shadow
    protected abstract void setCollarColor(int id);

    @Inject(method = "mobInteract", at = @At(value = "INVOKE",
            target = "net/minecraft/world/item/ItemStack.getItem ()Lnet/minecraft/world/item/Item;",
            shift = At.Shift.BEFORE),
    cancellable = true)
    public void handlePastelDyeItem(Player pPlayer, InteractionHand pHand, CallbackInfoReturnable<InteractionResult> cir){
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (itemstack.getItem() instanceof PastelDyeItem pastelDyeItem) {
            int id = pastelDyeItem.getDyeColor().getTextureDefuseColor();
            this.setCollarColor(id);
            itemstack.consume(1, pPlayer);
            cir.setReturnValue(InteractionResult.SUCCESS);
        }
    }
}
