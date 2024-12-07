package net.hyper_pigeon.chickensaurs.mixin.compat.familiar_magic;

import net.hyper_pigeon.chickensaurs.client.hat.ChickensaurHatLayer;
import net.hyper_pigeon.chickensaurs.client.model.ChickensaurModel;
import net.hyper_pigeon.chickensaurs.client.renderer.entity.ChickensaurRenderer;
import net.hyper_pigeon.chickensaurs.entity.Chickensaur;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChickensaurRenderer.class)
public abstract class ChickensaurHatRendererMixin extends MobRenderer<Chickensaur, ChickensaurModel> {
    public ChickensaurHatRendererMixin(EntityRendererProvider.Context pContext, ChickensaurModel pModel, float pShadowRadius) {
        super(pContext, pModel, pShadowRadius);
    }

    @Inject(at = @At("TAIL"), method = "<init>")
    private void init(EntityRendererProvider.Context context, CallbackInfo ci) {
        this.addLayer(new ChickensaurHatLayer(this, context.getItemInHandRenderer()));
    }
}
