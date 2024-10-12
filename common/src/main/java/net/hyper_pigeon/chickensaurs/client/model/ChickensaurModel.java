package net.hyper_pigeon.chickensaurs.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.hyper_pigeon.chickensaurs.Constants;
import net.hyper_pigeon.chickensaurs.entity.Chickensaur;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class ChickensaurModel extends HierarchicalModel<Chickensaur> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "chickensaur"), "main");

    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart wingr;
    private final ModelPart wingl;
    private final ModelPart legr;
    private final ModelPart legl;
    private final ModelPart bb_main;


    public ChickensaurModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.wingr = root.getChild("wingr");
        this.wingl = root.getChild("wingl");
        this.legr = root.getChild("legr");
        this.legl = root.getChild("legl");
        this.bb_main = root.getChild("bb_main");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(20, 25).addBox(-3.0F, -10.0F, -4.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 20).addBox(-3.0F, -9.0F, 2.0F, 6.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(38, 31).addBox(-1.5F, -11.0F, -1.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 20).addBox(-1.0F, -4.0F, 0.75F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 9.0F, 7.0F));

        PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(29, 0).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0F, 2.0F, -0.1309F, 0.0F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -16.0F, -7.0F, 8.0F, 7.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 24.0F, -1.0F));

        PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(20, 37).addBox(-3.0F, -2.0F, -4.0F, 6.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -13.0F, -7.0F, 0.4363F, 0.0F, 0.0F));

        PartDefinition wingr = partdefinition.addOrReplaceChild("wingr", CubeListBuilder.create().texOffs(0, 31).addBox(0.0F, -16.0F, -4.0F, 1.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 24.0F, -2.0F));

        PartDefinition wingl = partdefinition.addOrReplaceChild("wingl", CubeListBuilder.create().texOffs(33, 11).addBox(-7.0F, -16.0F, -3.0F, 1.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 24.0F, -3.0F));

        PartDefinition legr = partdefinition.addOrReplaceChild("legr", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -9.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 24.0F, -3.0F));

        PartDefinition legl = partdefinition.addOrReplaceChild("legl", CubeListBuilder.create().texOffs(0, 45).addBox(-1.0F, -9.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 24.0F, -3.0F));

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition cube_r3 = bb_main.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(44, 8).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.0F, 6.0F, -0.4363F, 0.0F, 0.0F));

        PartDefinition cube_r4 = bb_main.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(42, 39).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -19.0F, 6.0F, 0.3054F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(Chickensaur entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int i1, int i2) {
        head.render(poseStack, vertexConsumer, i, i1, i2);
        body.render(poseStack, vertexConsumer, i, i1, i2);
        wingr.render(poseStack, vertexConsumer, i, i1, i2);
        wingl.render(poseStack, vertexConsumer, i, i1, i2);
        legr.render(poseStack, vertexConsumer, i, i1, i2);
        legl.render(poseStack, vertexConsumer, i, i1, i2);
        bb_main.render(poseStack, vertexConsumer, i, i1, i2);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }
}