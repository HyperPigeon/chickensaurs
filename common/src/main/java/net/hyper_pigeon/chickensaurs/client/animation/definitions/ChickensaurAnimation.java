package net.hyper_pigeon.chickensaurs.client.animation.definitions;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class ChickensaurAnimation {
    public static final AnimationDefinition CHICKENSAUR_WALK = AnimationDefinition.Builder.withLength(1.0F).looping()
            .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.375F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -1.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, -1.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("wingr", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -1.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, -1.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("wingl", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -1.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, -1.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("legr", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(45.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.degreeVec(-45.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(45.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("legl", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(-45.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.degreeVec(45.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(-45.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("upper", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.degreeVec(10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("upper", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, -1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .build();

    public static final AnimationDefinition CHICKENSAUR_INTIMIDATE = AnimationDefinition.Builder.withLength(9.84F)
            .addAnimation("upper", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.88F, KeyframeAnimations.degreeVec(-2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(9.0F, KeyframeAnimations.degreeVec(-2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(9.84F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("upper", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.88F, KeyframeAnimations.posVec(0.0F, 2.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(9.0F, KeyframeAnimations.posVec(0.0F, 2.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(9.84F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("upper", new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(9.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(9.84F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.96F, KeyframeAnimations.degreeVec(-20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.2F, KeyframeAnimations.degreeVec(-30.0F, 25.0F, -20.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.44F, KeyframeAnimations.degreeVec(-20.0F, -25.0F, 20.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.68F, KeyframeAnimations.degreeVec(-30.0F, 25.0F, -20.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.92F, KeyframeAnimations.degreeVec(-20.0F, -25.0F, 20.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.16F, KeyframeAnimations.degreeVec(-20.0F, 25.0F, -20.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.4F, KeyframeAnimations.degreeVec(-10.0F, -25.0F, 20.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.64F, KeyframeAnimations.degreeVec(-10.0F, 25.0F, -20.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.88F, KeyframeAnimations.degreeVec(-20.0F, -25.0F, 20.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.12F, KeyframeAnimations.degreeVec(-20.0F, 25.0F, -20.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.36F, KeyframeAnimations.degreeVec(-20.0F, -25.0F, 20.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.6F, KeyframeAnimations.degreeVec(-30.0F, 25.0F, -20.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.84F, KeyframeAnimations.degreeVec(-20.0F, -25.0F, 20.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.08F, KeyframeAnimations.degreeVec(-20.0F, 25.0F, -20.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.32F, KeyframeAnimations.degreeVec(-10.0F, -25.0F, 20.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.56F, KeyframeAnimations.degreeVec(-20.0F, 25.0F, -20.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.8F, KeyframeAnimations.degreeVec(-20.0F, -25.0F, 20.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5.04F, KeyframeAnimations.degreeVec(-30.0F, 25.0F, -20.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5.28F, KeyframeAnimations.degreeVec(-20.0F, -25.0F, 20.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5.52F, KeyframeAnimations.degreeVec(-30.0F, 25.0F, -20.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5.76F, KeyframeAnimations.degreeVec(-20.0F, -25.0F, 20.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(6.0F, KeyframeAnimations.degreeVec(-10.0F, 25.0F, -20.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(6.24F, KeyframeAnimations.degreeVec(-20.0F, -25.0F, 20.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(6.48F, KeyframeAnimations.degreeVec(-20.0F, 25.0F, -20.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(6.72F, KeyframeAnimations.degreeVec(-30.0F, -25.0F, 20.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(6.96F, KeyframeAnimations.degreeVec(-20.0F, 25.0F, -20.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(7.2F, KeyframeAnimations.degreeVec(-20.0F, -25.0F, 20.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(7.44F, KeyframeAnimations.degreeVec(-10.0F, 25.0F, -20.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(7.68F, KeyframeAnimations.degreeVec(-30.0F, -25.0F, 20.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(7.92F, KeyframeAnimations.degreeVec(-20.0F, 25.0F, -20.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(8.16F, KeyframeAnimations.degreeVec(-10.0F, -25.0F, 20.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(8.4F, KeyframeAnimations.degreeVec(-20.0F, 25.0F, -20.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(8.64F, KeyframeAnimations.degreeVec(-30.0F, -25.0F, 20.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(8.88F, KeyframeAnimations.degreeVec(-20.0F, 25.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(9.0F, KeyframeAnimations.degreeVec(-20.0F, 25.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(9.84F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(9.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(9.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(9.84F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("beakd", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.96F, KeyframeAnimations.degreeVec(45.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(9.0F, KeyframeAnimations.degreeVec(45.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(9.84F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("beakd", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(9.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("beakd", new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(9.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("beaku", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.96F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(9.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(9.84F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("beaku", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(9.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("beaku", new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(9.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.88F, KeyframeAnimations.degreeVec(-30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(9.0F, KeyframeAnimations.degreeVec(-30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(9.84F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(9.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(9.84F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(9.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(9.84F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("wingr", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.88F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.96F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.04F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.12F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.2F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.28F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.36F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.44F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.52F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.6F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.68F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.76F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.84F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.92F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.08F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.16F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.24F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.32F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.4F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.48F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.56F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.64F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.72F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.8F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.88F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.96F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.04F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.12F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.2F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.28F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.36F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.44F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.52F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.6F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.68F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.76F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.84F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.92F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.0F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.08F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.16F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.24F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.32F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.4F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.48F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.56F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.64F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.72F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.8F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.88F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.96F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5.04F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5.12F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5.2F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5.28F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5.36F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5.44F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5.52F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5.6F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5.68F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5.76F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5.84F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5.92F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(6.0F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(6.08F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(6.16F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(6.24F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(6.32F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(6.4F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(6.48F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(6.56F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(6.64F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(6.72F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(6.8F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(6.88F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(6.96F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(7.04F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(7.12F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(7.2F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(7.28F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(7.36F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(7.44F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(7.52F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(7.6F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(7.68F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(7.76F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(7.84F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(7.92F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(8.0F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(8.08F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(8.16F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(8.24F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(8.32F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(8.4F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(8.48F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(8.56F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(8.64F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(8.72F, KeyframeAnimations.degreeVec(5.7252F, 28.4803F, 110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(8.8F, KeyframeAnimations.degreeVec(-22.217F, 19.3622F, 47.7353F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(9.0F, KeyframeAnimations.degreeVec(5.7252F, 28.48F, 110.01F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(9.84F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("wingr", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(9.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(9.84F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("wingr", new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(9.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(9.84F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("wingl", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.88F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.96F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.04F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.12F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.2F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.28F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.36F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.44F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.52F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.6F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.68F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.76F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.84F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.92F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.08F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.16F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.24F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.32F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.4F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.48F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.56F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.64F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.72F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.8F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.88F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.96F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.04F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.12F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.2F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.28F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.36F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.44F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.52F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.6F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.68F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.76F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.84F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.92F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.0F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.08F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.16F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.24F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.32F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.4F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.48F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.56F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.64F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.72F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.8F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.88F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.96F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5.04F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5.12F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5.2F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5.28F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5.36F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5.44F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5.52F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5.6F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5.68F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5.76F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5.84F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(5.92F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(6.0F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(6.08F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(6.16F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(6.24F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(6.32F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(6.4F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(6.48F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(6.56F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(6.64F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(6.72F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(6.8F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(6.88F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(6.96F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(7.04F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(7.12F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(7.2F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(7.28F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(7.36F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(7.44F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(7.52F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(7.6F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(7.68F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(7.76F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(7.84F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(7.92F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(8.0F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(8.08F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(8.16F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(8.24F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(8.32F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(8.4F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(8.48F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(8.56F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(8.64F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(8.72F, KeyframeAnimations.degreeVec(9.8489F, -28.4803F, -110.0053F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(8.8F, KeyframeAnimations.degreeVec(-21.4272F, -21.728F, -43.8497F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(9.0F, KeyframeAnimations.degreeVec(9.8489F, -28.48F, -110.01F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(9.84F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("wingl", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(9.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(9.84F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("wingl", new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(9.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(9.84F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("legr", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(9.84F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("legr", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(9.84F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("legr", new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(9.84F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("legl", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(9.84F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("legl", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(9.84F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("legl", new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(9.84F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .build();
}