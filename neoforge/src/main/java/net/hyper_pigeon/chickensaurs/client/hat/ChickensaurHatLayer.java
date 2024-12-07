package net.hyper_pigeon.chickensaurs.client.hat;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.hyper_pigeon.chickensaurs.client.model.ChickensaurModel;
import net.hyper_pigeon.chickensaurs.entity.Chickensaur;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Quaternionf;

import javax.annotation.ParametersAreNonnullByDefault;

import static io.github.afamiliarquiet.familiar_magic.FamiliarTricks.getHat;

@ParametersAreNonnullByDefault
public class ChickensaurHatLayer extends RenderLayer<Chickensaur, ChickensaurModel> {
    private final ItemInHandRenderer actuallyItsAHatRenderer;

    public ChickensaurHatLayer(RenderLayerParent<Chickensaur, ChickensaurModel> renderer, ItemInHandRenderer itemInHandRenderer) {
        super(renderer);
        this.actuallyItsAHatRenderer = itemInHandRenderer;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, Chickensaur chickensaur, float v, float v1, float v2, float v3, float v4, float v5) {
        ItemStack hat = getHat(chickensaur);
        if (hat.isEmpty()) {
            return;
        }

        poseStack.pushPose();

        ModelPart head = this.getParentModel().head;
        poseStack.translate(head.x / 16.0f, head.y / 16.0f, head.z / 16.0f);
        poseStack.mulPose(new Quaternionf().rotationZYX(head.zRot, head.yRot, head.xRot));
        poseStack.scale(head.xScale, head.yScale, head.zScale);

        poseStack.mulPose(Axis.ZP.rotationDegrees(180));
        poseStack.translate(0.0F, -0.15F, -0.15F);

        poseStack.scale(0.3625f, 0.3625f, 0.3625f);

        this.actuallyItsAHatRenderer.renderItem(chickensaur, hat, ItemDisplayContext.HEAD, false, poseStack, multiBufferSource, i);
        poseStack.popPose();
    }
}
