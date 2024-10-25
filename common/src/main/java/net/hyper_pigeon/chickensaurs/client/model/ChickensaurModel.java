package net.hyper_pigeon.chickensaurs.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.hyper_pigeon.chickensaurs.Constants;
import net.hyper_pigeon.chickensaurs.client.animation.definitions.ChickensaurAnimation;
import net.hyper_pigeon.chickensaurs.entity.Chickensaur;
import net.minecraft.client.animation.definitions.FrogAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class ChickensaurModel extends HierarchicalModel<Chickensaur> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "chickensaur"), "main");

    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart upper;
    private final ModelPart neck;
    private final ModelPart beak;
    private final ModelPart beakd;
    private final ModelPart beaku;
    private final ModelPart body;
    private final ModelPart wingr;
    private final ModelPart wingl;
    private final ModelPart legr;
    private final ModelPart legl;


    public ChickensaurModel(ModelPart root) {
        this.root = root;
        this.upper = root.getChild("upper");
        this.neck = this.upper.getChild("neck");
        this.head = this.upper.getChild("head");
        this.beak = this.head.getChild("beak");
        this.beakd = this.beak.getChild("beakd");
        this.beaku = this.beak.getChild("beaku");
        this.body = root.getChild("body");
        this.wingr = root.getChild("wingr");
        this.wingl = root.getChild("wingl");
        this.legr = root.getChild("legr");
        this.legl = root.getChild("legl");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition upper = partdefinition.addOrReplaceChild("upper", CubeListBuilder.create(), PartPose.offset(0.0F, 10.0F, -5.0F));

        PartDefinition neck = upper.addOrReplaceChild("neck", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, -1.0F));

        PartDefinition cube_r1 = neck.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(21, 11).addBox(-2.0F, -2.0F, -1.0F, 4.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, 0.0F, 0.0F));

        PartDefinition cube_r2 = neck.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(21, 11).addBox(-2.0F, -3.0F, -1.0F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, 0.0F, -0.3054F, 0.0F, 0.0F));

        PartDefinition head = upper.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 12).addBox(-1.5F, -7.0F, -6.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(12, 13).addBox(-1.0F, 0.0F, -2.75F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, -1.0F));

        PartDefinition beak = head.addOrReplaceChild("beak", CubeListBuilder.create(), PartPose.offset(1.0F, -2.0F, -4.0F));

        PartDefinition beakd = beak.addOrReplaceChild("beakd", CubeListBuilder.create(), PartPose.offset(-1.0F, 1.0F, 1.0F));

        PartDefinition cube_r3 = beakd.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(22, 40).addBox(-3.0F, 0.0F, -7.0F, 6.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1309F, 0.0F, 0.0F));

        PartDefinition beaku = beak.addOrReplaceChild("beaku", CubeListBuilder.create().texOffs(24, 0).addBox(-4.0F, -3.0F, -6.0F, 6.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 20).addBox(-4.0F, -3.0F, -7.0F, 8.0F, 7.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 2.0F));

        PartDefinition cube_r4 = body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 40).addBox(-3.0F, -2.0F, -1.0F, 6.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 6.0F, -0.4363F, 0.0F, 0.0F));

        PartDefinition wingr = partdefinition.addOrReplaceChild("wingr", CubeListBuilder.create().texOffs(29, 19).mirror().addBox(-1.0F, 0.0F, -4.0F, 1.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.0F, 8.0F, 1.0F));

        PartDefinition wingl = partdefinition.addOrReplaceChild("wingl", CubeListBuilder.create().texOffs(29, 19).addBox(0.0F, 0.0F, -4.0F, 1.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 8.0F, 1.0F));

        PartDefinition legr = partdefinition.addOrReplaceChild("legr", CubeListBuilder.create().texOffs(5, 22).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(1, 22).mirror().addBox(-1.0F, 9.0F, -3.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, 15.0F, 3.0F));

        PartDefinition legl = partdefinition.addOrReplaceChild("legl", CubeListBuilder.create().texOffs(5, 22).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(1, 22).addBox(-1.0F, 9.0F, -3.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 15.0F, 3.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(Chickensaur entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.setHeadAngles(netHeadYaw,headPitch);
        this.animate(entity.intimidateAnimationState, ChickensaurAnimation.CHICKENSAUR_INTIMIDATE, ageInTicks);
        this.animateWalk(ChickensaurAnimation.CHICKENSAUR_WALK, limbSwing, limbSwingAmount, 2F, 2F);
    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30.0F, 30.0F);
        headPitch = Mth.clamp(headPitch, -30.0F, 30.0F);

        this.head.yRot = headYaw * 0.017453292F;
        this.head.xRot = headPitch * -0.017453292F;
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int i1, int i2) {
        upper.render(poseStack, vertexConsumer, i, i1, i2);
        body.render(poseStack, vertexConsumer, i, i1, i2);
        wingr.render(poseStack, vertexConsumer, i, i1, i2);
        wingl.render(poseStack, vertexConsumer, i, i1, i2);
        legr.render(poseStack, vertexConsumer, i, i1, i2);
        legl.render(poseStack, vertexConsumer, i, i1, i2);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }
}