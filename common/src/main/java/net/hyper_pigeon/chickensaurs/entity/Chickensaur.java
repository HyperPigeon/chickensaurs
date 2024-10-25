package net.hyper_pigeon.chickensaurs.entity;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.hyper_pigeon.chickensaurs.Constants;
import net.hyper_pigeon.chickensaurs.entity.ai.behavior.EatFoodInMainHand;
import net.hyper_pigeon.chickensaurs.entity.ai.behavior.IntimidateLivingEntity;
import net.hyper_pigeon.chickensaurs.entity.ai.behavior.MoveToNearestVisibleWantedItem;
import net.hyper_pigeon.chickensaurs.register.EntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.tslat.smartbrainlib.api.SmartBrainOwner;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.SmartBrainProvider;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableMeleeAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.look.LookAtTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.AvoidEntity;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.FloatToSurfaceOfFluid;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.MoveToWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.TargetOrRetaliate;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.custom.GenericAttackTargetSensor;
import net.tslat.smartbrainlib.api.core.sensor.custom.NearbyBlocksSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.HurtBySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyLivingEntitySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyPlayersSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearestItemSensor;
import net.tslat.smartbrainlib.util.EntityRetrievalUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Chickensaur extends TamableAnimal implements SmartBrainOwner<Chickensaur> {
    public float flap;
    public float flapSpeed;
    public float oFlapSpeed;
    public float oFlap;
    public float flapping = 1.0F;
    private float nextFlap = 1.0F;
    public int shedTime;

    private static final Vec3i ITEM_PICKUP_RANGE_EXPANDER = new Vec3i(1,1,1);

    private static final EntityDataAccessor<Boolean> INTIMIDATING = SynchedEntityData.defineId(Chickensaur.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> INTIMIDATING_TICKS = SynchedEntityData.defineId(Chickensaur.class, EntityDataSerializers.INT);

    public final AnimationState walkAnimationState = new AnimationState();
    public final AnimationState intimidateAnimationState = new AnimationState();


    public Chickensaur(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.shedTime = this.random.nextInt(6000) + 6000;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(INTIMIDATING,false);
        pBuilder.define(INTIMIDATING_TICKS, 0);
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.getItem().equals(Items.BONE) || itemStack.getItem().equals(Items.BONE_BLOCK)
                && itemStack.getItem().equals(Items.SKELETON_SKULL) || itemStack.getItem().equals(Items.PORKCHOP);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }

    public void aiStep() {
        super.aiStep();
        this.oFlap = this.flap;
        this.oFlapSpeed = this.flapSpeed;
        this.flapSpeed += (this.onGround() ? -1.0F : 4.0F) * 0.3F;
        this.flapSpeed = Mth.clamp(this.flapSpeed, 0.0F, 1.0F);
        if (!this.onGround() && this.flapping < 1.0F) {
            this.flapping = 1.0F;
        }

        this.flapping *= 0.9F;
        Vec3 vec3 = this.getDeltaMovement();
        if (!this.onGround() && vec3.y < 0.0) {
            this.setDeltaMovement(vec3.multiply(1.0, 0.6, 1.0));
        }

        this.flap += this.flapping * 2.0F;
        if (!this.level().isClientSide && this.isAlive() && !this.isBaby() &&  --this.shedTime <= 0) {
            this.playSound(SoundEvents.CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            this.spawnAtLocation(Items.IRON_NUGGET);
            this.gameEvent(GameEvent.ENTITY_PLACE);
            this.shedTime = this.random.nextInt(6000) + 6000;
        }

        if(isIntimidating()) {
            if(this.level().isClientSide) {
                if(!intimidateAnimationState.isStarted()) {
                    intimidateAnimationState.start(this.tickCount);
                }
                if(getIntimidatingTicks() > 20) {
                    if(this.random.nextDouble() < 0.5) {
                        level().addParticle(ParticleTypes.SOUL_FIRE_FLAME,
                                this.getX() + this.random.nextDouble() / 5.0,
                                this.getY(1.1),
                                this.getZ() + this.random.nextDouble() / 5.0,
                                (this.random.nextDouble() * (2) - 1) * 0.25,
                                -this.random.nextDouble() * 0.25,
                                (this.random.nextDouble() * (2) - 1) * 0.25);
                    }
                }
            }

            //as of right now, this code results in some weird behavior and might be too destructive.
//            if(getIntimidatingTicks() > 40) {
//                if(this.random.nextDouble() < 0.10) {
//                    BlockPos blockPos = this.getBlockPosBelowThatAffectsMyMovement();
//                    BlockPos fireBlockPos = new BlockPos(blockPos.getX() + this.random.nextIntBetweenInclusive(1,3), blockPos.getY(), blockPos.getZ() + this.random.nextIntBetweenInclusive(1,3));
//                    if(BaseFireBlock.canBePlacedAt(level(), fireBlockPos, Direction.getRandom(this.random))) {
//                        level().playSound(null,fireBlockPos,SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level().getRandom().nextFloat() * 0.4F + 0.8F);
//                        level().setBlockAndUpdate(fireBlockPos, BaseFireBlock.getState(level(), fireBlockPos));
//                        level().gameEvent(null, GameEvent.BLOCK_PLACE, fireBlockPos);
//                    }
//                }
//            }

            incrementIntimidatingTicks();
        }
        else {
            if(this.level().isClientSide) {
                if(!isIntimidating() && intimidateAnimationState.isStarted()) {
                    intimidateAnimationState.stop();
                    setIntimidatingTicks(0);
                }
            }
        }

//        if(this.level().isClientSide) {
//            if(isIntimidating()) {
//                if(!intimidateAnimationState.isStarted()) {
//                    intimidateAnimationState.start(this.tickCount);
//                }
//                if(getIntimidatingTicks() > 20) {
//                    if(this.random.nextDouble() < 0.5) {
//                        level().addParticle(ParticleTypes.SOUL_FIRE_FLAME,
//                                this.getX() + this.random.nextDouble() / 5.0,
//                                this.getY(1.1),
//                                this.getZ() + this.random.nextDouble() / 5.0,
//                                (this.random.nextDouble() * (2) - 1) * 0.25,
//                                -this.random.nextDouble() * 0.25,
//                                (this.random.nextDouble() * (2) - 1) * 0.25);
//                    }
//                }
//                incrementIntimidatingTicks();
//            }
//            else if(!isIntimidating() && intimidateAnimationState.isStarted()) {
//                intimidateAnimationState.stop();
//                setIntimidatingTicks(0);
//            }
//        }
//        if(getIntimidatingTicks() > 40) {
//            if(this.random.nextDouble() < 0.30) {
//                BlockPos blockPos = new BlockPos(getBlockX() + this.random.nextIntBetweenInclusive(1,3),getBlockY() + this.random.nextIntBetweenInclusive(1,3),getBlockZ() + this.random.nextIntBetweenInclusive(1,3));
//                if(!level().getBlockState(blockPos).is(BlockTags.AIR)) {
//                    BlockState blockstate = BaseFireBlock.getState(level(), blockPos);
//                    level().setBlock(blockPos, blockstate, 11);
//                }
//            }
//        }
    }

    protected boolean isFlapping() {
        return this.flyDist > this.nextFlap;
    }

    protected void onFlap() {
        this.nextFlap = this.flyDist + this.flapSpeed / 2.0F;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.CHICKEN_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.CHICKEN_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.CHICKEN_DEATH;
    }

    protected Vec3i getPickupReach(){
        return ITEM_PICKUP_RANGE_EXPANDER;
    }


    public boolean wantsToPickUp(ItemStack stack) {
        return stack.is(Constants.CHICKENSAUR_FOOD);
    }

    public boolean canPickUpLoot() {
        return true;
    }

    public boolean canTakeItem(ItemStack stack) {
        EquipmentSlot equipmentSlot = this.getEquipmentSlotForItem(stack);
        if (!this.getItemBySlot(equipmentSlot).isEmpty()) {
            return false;
        } else {
            return equipmentSlot == EquipmentSlot.MAINHAND && super.canTakeItem(stack);
        }
    }


    protected void pickUpItem(ItemEntity item) {
        ItemStack itemStack = item.getItem();
        if (this.canHoldItem(itemStack) && getMainHandItem().isEmpty()) {
            this.onItemPickup(item);
            this.setItemSlot(EquipmentSlot.MAINHAND, itemStack.split(1));
            this.setGuaranteedDrop(EquipmentSlot.MAINHAND);
            this.take(item, itemStack.getCount());
        }
    }


    protected AABB getAttackBoundingBox() {
        AABB aABB = super.getAttackBoundingBox();
        return aABB.inflate(0.5, 0.0, 0.5);
    }

    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (itemstack.is(Items.BRUSH) && this.brushOffIronNuggets()) {
            itemstack.hurtAndBreak(24, pPlayer, getSlotForHand(pHand));
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }
        /*we'll probably have to add a clause here that prevents the player from brushing the chickensaur if its aggressive (though it might be
        funny if we don't)*/

        return super.mobInteract(pPlayer, pHand);
    }

    private boolean brushOffIronNuggets() {
        if (this.isBaby()) {
            return false;
        } else {
            this.spawnAtLocation(new ItemStack(Items.IRON_NUGGET));
            this.gameEvent(GameEvent.ENTITY_INTERACT);
            this.playSound(SoundEvents.ARMADILLO_BRUSH);
            return true;
        }
    }

    protected float getBlockSpeedFactor() {
        BlockPos blockPos = this.getBlockPosBelowThatAffectsMyMovement();
        BlockState blockState = this.level().getBlockState(blockPos);
        if(blockState.is(BlockTags.SOUL_SPEED_BLOCKS)){
            return 1.2F;
        }
        return super.getBlockSpeedFactor();
    }

    public static boolean checkChickensaurSpawnRules(EntityType<Chickensaur> pChickensaur, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        return pLevel.getBlockState(pPos.below()).is(BlockTags.SOUL_SPEED_BLOCKS);
    }

    public float getWalkTargetValue(BlockPos pPos, LevelReader pLevel) {
        return pLevel.getBlockState(pPos.below()).is(BlockTags.SOUL_SPEED_BLOCKS) ? 10.0F : 0.0F;
    }

    public boolean hasNumbersAdvantaqe(LivingEntity potentialPrey) {
        double hunterFollowRange = this.getAttributeValue(Attributes.FOLLOW_RANGE);
        int numHunterAllies =  EntityRetrievalUtil.getEntities(this, hunterFollowRange, 10.0, hunterFollowRange, LivingEntity.class, (entity) -> entity.getType().equals(EntityRegistry.CHICKENSAUR.get())).size();
        double preyFollowRange = this.getAttributeValue(Attributes.FOLLOW_RANGE);
        int numPreyAllies = EntityRetrievalUtil.getEntities(this, hunterFollowRange, 10.0, preyFollowRange, LivingEntity.class, (entity) -> entity.getType().equals(potentialPrey.getType())).size();

        if(numHunterAllies >= 3 && numHunterAllies > numPreyAllies) {
            return true;
        }
        return false;
    }


    public void setIntimidating(boolean isIntimidating) {
        this.entityData.set(INTIMIDATING, isIntimidating);
    }

    public boolean isIntimidating() {
        return this.entityData.get(INTIMIDATING);
    }

    public void setIntimidatingTicks(int ticks) {
        this.entityData.set(INTIMIDATING_TICKS, ticks);
    }

    public void incrementIntimidatingTicks() {
        this.entityData.set(INTIMIDATING_TICKS,this.entityData.get(INTIMIDATING_TICKS)+1);
    }

    public int getIntimidatingTicks() {
        return this.entityData.get(INTIMIDATING_TICKS);
    }




    @Override
    protected Brain.Provider<?> brainProvider() {
        return new SmartBrainProvider<>(this);
    }

    @Override
    protected void customServerAiStep() {
        tickBrain(this);
    }

    @Override
    public List<? extends ExtendedSensor<? extends Chickensaur>> getSensors() {
        return ObjectArrayList.of(
                new NearbyPlayersSensor<>(),
                new NearbyLivingEntitySensor<Chickensaur>().setRadius(32).setPredicate((target,entity) -> {
                    EntityType<?> entityType = target.getType();
                    return entityType.is(Constants.HUNT) || entityType.is(Constants.GROUP_HUNT) || entityType.is(Constants.INTIMIDATE);
                }),
                new HurtBySensor<>(),
                new GenericAttackTargetSensor<>(),
                new NearestItemSensor<Chickensaur>().
                        setRadius(16,16).setPredicate(((itemEntity, chickensaur) -> isFood(itemEntity.getItem()))),
                new NearbyBlocksSensor<>()
        );
    }

    @Override
    public BrainActivityGroup<Chickensaur> getCoreTasks() { // These are the tasks that run all the time (usually)
        return BrainActivityGroup.coreTasks(
                new FloatToSurfaceOfFluid<>(),              // Have the entity float to the surface of a fluid
                new AvoidEntity<>().avoiding((entity) -> {
                    EntityType<?> entityType = entity.getType();
                    return this.getHealth() <= 5 && (entityType.is(Constants.INTIMIDATE) || entityType.is(Constants.GROUP_HUNT));
                }).speedModifier(1.2F).noCloserThan(5),
                new LookAtTarget<>(),                      // Have the entity turn to face and look at its current look target
                new MoveToWalkTarget<>());                 // Walk towards the current walk target
    }

    @Override
    public BrainActivityGroup<Chickensaur> getIdleTasks() { // These are the tasks that run when the mob isn't doing anything else (usually)
        return BrainActivityGroup.idleTasks(
                new FirstApplicableBehaviour(
                        new TargetOrRetaliate().attackablePredicate(target -> {
                                LivingEntity livingEntity = (LivingEntity) target;
                                EntityType<?> entityType = livingEntity.getType();
                                if(entityType.is(Constants.HUNT)) {
                                    return true;
                                }
                                else if(this.getHealth() > 5) {
                                    if(entityType.is(Constants.INTIMIDATE) && this.distanceToSqr(livingEntity) < 4) {
                                        return true;
                                    }
                                    else if(entityType.is(Constants.GROUP_HUNT)) {
                                        return hasNumbersAdvantaqe(livingEntity);
                                    }
                                }
                                return false;
                            }
                        ),
                        new IntimidateLivingEntity().runFor((entity) -> 200),
                        new EatFoodInMainHand<>().runFor((entity) -> 150),
                        new MoveToNearestVisibleWantedItem()
                )
        );
    }

    @Override
    public BrainActivityGroup<Chickensaur> getFightTasks() { // These are the tasks that handle fighting
        return BrainActivityGroup.fightTasks(
                new InvalidateAttackTarget<>().invalidateIf((attacker, target) -> {
                    EntityType<?> entityType = target.getType();
                    return attacker.getHealth() <= 5 && (entityType.is(Constants.INTIMIDATE) || entityType.is(Constants.GROUP_HUNT));
                }), // Cancel fighting if the target is no longer valid
                new SetWalkTargetToAttackTarget<>().speedMod((entity, target) -> 1.2F),      // Set the walk target to the attack target
                new AnimatableMeleeAttack<>(5)); // Melee attack the target if close enough
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.40000001192092896).add(Attributes.MAX_HEALTH, 30.0).add(Attributes.ATTACK_DAMAGE, 8.0).add(Attributes.ARMOR, 12F);
    }

}
