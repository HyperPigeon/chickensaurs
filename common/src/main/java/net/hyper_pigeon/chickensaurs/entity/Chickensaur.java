package net.hyper_pigeon.chickensaurs.entity;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.hyper_pigeon.chickensaurs.Constants;
import net.hyper_pigeon.chickensaurs.entity.ai.behavior.EatFoodInMainHand;
import net.hyper_pigeon.chickensaurs.entity.ai.behavior.IntimidateLivingEntity;
import net.hyper_pigeon.chickensaurs.entity.ai.behavior.MoveToNearestVisibleWantedItem;
import net.hyper_pigeon.chickensaurs.register.EntityRegistry;
import net.hyper_pigeon.chickensaurs.register.ItemRegistry;
import net.hyper_pigeon.chickensaurs.register.SoundRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.tslat.smartbrainlib.api.SmartBrainOwner;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.SmartBrainProvider;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableMeleeAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.look.LookAtTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.BreedWithPartner;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Idle;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.AvoidEntity;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.FloatToSurfaceOfFluid;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.FollowParent;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.MoveToWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.SetRandomLookTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.TargetOrRetaliate;
import net.tslat.smartbrainlib.api.core.navigation.SmoothGroundNavigation;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.custom.GenericAttackTargetSensor;
import net.tslat.smartbrainlib.api.core.sensor.custom.NearbyBlocksSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.HurtBySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyLivingEntitySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyPlayersSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearestItemSensor;
import net.tslat.smartbrainlib.util.BrainUtils;
import net.tslat.smartbrainlib.util.EntityRetrievalUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public class Chickensaur extends TamableAnimal implements SmartBrainOwner<Chickensaur>, Saddleable {
    public float flap;
    public float flapSpeed;
    public float oFlapSpeed;
    public float oFlap;
    public float flapping = 1.0F;
    private float nextFlap = 1.0F;
    public int shedTime;

    private static final Vec3i ITEM_PICKUP_RANGE_EXPANDER = new Vec3i(1,1,1);
    private static final int MAX_BRUSH_AMOUNT = 3;
    private static final int REGEN_SCALE_TIME = 1000;

    private static final EntityDataAccessor<Boolean> INTIMIDATING = SynchedEntityData.defineId(Chickensaur.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> INTIMIDATING_TICKS = SynchedEntityData.defineId(Chickensaur.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(Chickensaur.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> SADDLED = SynchedEntityData.defineId(Chickensaur.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Integer> BRUSH_AMOUNT = SynchedEntityData.defineId(Chickensaur.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> REGEN_SCALES_TICKS = SynchedEntityData.defineId(Chickensaur.class,EntityDataSerializers.INT);

    private static final EntityDataAccessor<Integer> DATA_COLLAR_COLOR = SynchedEntityData.defineId(Chickensaur.class,EntityDataSerializers.INT);

    private static final EntityDataAccessor<Boolean> PICKING_UP_ITEM = SynchedEntityData.defineId(Chickensaur.class, EntityDataSerializers.BOOLEAN);

    public final AnimationState intimidateAnimationState = new AnimationState();
    public final AnimationState biteAnimationState = new AnimationState();
    public final AnimationState eatAnimationState = new AnimationState();;

    private final Predicate<LivingEntity> isCreativeOrChickensaurOwner = entity ->
            entity instanceof ServerPlayer player && (player.isCreative() || isChickensaurOwner(player));

    public Chickensaur(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.shedTime = this.random.nextInt(3000) + 3000;
        this.setPathfindingMalus(PathType.DANGER_FIRE, 0.0F);
        this.setPathfindingMalus(PathType.DAMAGE_FIRE, 0.0F);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(INTIMIDATING,false);
        pBuilder.define(INTIMIDATING_TICKS, 0);
        pBuilder.define(ATTACKING, false);
        pBuilder.define(SADDLED, false);
        pBuilder.define(BRUSH_AMOUNT,MAX_BRUSH_AMOUNT);
        pBuilder.define(REGEN_SCALES_TICKS, 0);
        pBuilder.define(DATA_COLLAR_COLOR, DyeColor.WHITE.getId());
        pBuilder.define(PICKING_UP_ITEM, false);
    }

    protected PathNavigation createNavigation(Level pLevel) {
        return new SmoothGroundNavigation(this, pLevel);
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(Constants.CHICKENSAUR_FOOD);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return EntityRegistry.CHICKENSAUR.get().create(serverLevel);
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

        if(isAttacking()) {
            if(!biteAnimationState.isStarted()) {
                biteAnimationState.start(this.tickCount);
            }
        }
        else {
            if(biteAnimationState.isStarted()) {
                biteAnimationState.stop();
            }
        }


        if(getBrushAmount() <= 0) {
            if(getRegenScaleTicks() > REGEN_SCALE_TIME) {
                setBrushAmount(MAX_BRUSH_AMOUNT);
                setRegenScalesTicks(0);
            }
            else {
                int regenScaleTicks = getRegenScaleTicks() + 1;
                setRegenScalesTicks(regenScaleTicks);
            }
        }

        if (!this.level().isClientSide && this.isAlive()) {
            if (this.random.nextInt(900) == 0 && this.deathTime == 0) {
                this.heal(1.0F);
            }
        }

    }

    protected boolean isFlapping() {
        return this.flyDist > this.nextFlap;
    }

    protected void onFlap() {
        this.nextFlap = this.flyDist + this.flapSpeed / 2.0F;
    }

    protected SoundEvent getAmbientSound() {
        return this.random.nextDouble() < 0.5 ? SoundRegistry.IDLE_ONE.get() : SoundRegistry.IDLE_TWO.get();
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundRegistry.HURT_ONE.get();
    }

    protected SoundEvent getDeathSound() {
        return SoundRegistry.HURT_TWO.get();
    }

    protected void playStepSound(BlockPos pPos, BlockState pBlock) {
        SoundEvent stepSound = this.random.nextDouble() < 0.5 ? SoundRegistry.STEP_ONE.get() : SoundRegistry.STEP_TWO.get();
        this.playSound(stepSound, 0.20F, 1.0F);
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
        return aABB.inflate(0.6, 0.1, 0.6);
    }

    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (itemstack.is(Items.BRUSH) && this.brushOffIronNuggets()) {
            itemstack.hurtAndBreak(24, pPlayer, getSlotForHand(pHand));
            level().playSound(null,this.getX(), this.getY(), this.getZ(), SoundRegistry.BRUSH.get(), SoundSource.NEUTRAL, 1.0F, 1.0F + (level().random.nextFloat() - level().random.nextFloat()) * 0.4F);
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }
        else if(canTame()) {
            if(isFood(itemstack)) {
                setOwnerUUID(pPlayer.getUUID());

                for(int i = 0; i < 7; ++i) {
                    double d0 = this.random.nextGaussian() * 0.02;
                    double d1 = this.random.nextGaussian() * 0.02;
                    double d2 = this.random.nextGaussian() * 0.02;
                    this.level().addParticle(ParticleTypes.HEART, this.getRandomX(1.0), this.getRandomY() + 0.5, this.getRandomZ(1.0), d0, d1, d2);
                }

                return InteractionResult.sidedSuccess(this.level().isClientSide);
            }
        }
        else if(isChickensaurOwner(pPlayer)) {
            if (itemstack.getItem() instanceof DyeItem dyeItem) {
                DyeColor dyecolor = dyeItem.getDyeColor();
                if (dyecolor != this.getCollarColor()) {
                    this.setCollarColor(dyecolor);
                    itemstack.consume(1, pPlayer);
                    return InteractionResult.SUCCESS;
                }
            }
            else if (isFood(itemstack) && !canBreed() && this.getHealth() < this.getMaxHealth()) {
                level().playSound(null, this.getX(), this.getY(), this.getZ(), this.getEatingSound(itemstack), SoundSource.NEUTRAL, 1.0F, 1.0F + (level().random.nextFloat() - level().random.nextFloat()) * 0.4F);
                this.heal(3);
                itemstack.consume(1, pPlayer);
                eatAnimationState.start(this.tickCount);
            }
            else if (this.isSaddled() && !this.isVehicle() && !pPlayer.isSecondaryUseActive()) {
                if (!this.level().isClientSide) {
                    pPlayer.startRiding(this);
                }

                return InteractionResult.sidedSuccess(this.level().isClientSide);
            }
            else {
                InteractionResult interactionresult = super.mobInteract(pPlayer, pHand);
                if (!interactionresult.consumesAction()) {
                    return itemstack.is(Items.SADDLE) ? itemstack.interactLivingEntity(pPlayer, this, pHand) : InteractionResult.PASS;
                }
                return interactionresult;
            }
        }

        return InteractionResult.FAIL;

        /*we'll probably have to add a clause here that prevents the player from brushing the chickensaur if its aggressive (though it might be
        funny if we don't)*/
    }

    public DyeColor getCollarColor() {
        return DyeColor.byId((Integer)this.entityData.get(DATA_COLLAR_COLOR));
    }

    private void setCollarColor(DyeColor pCollarColor) {
        this.entityData.set(DATA_COLLAR_COLOR, pCollarColor.getId());
    }


    private boolean brushOffIronNuggets() {
        int brushAmount = getBrushAmount();
        if (this.isBaby() || brushAmount <= 0) {
            return false;
        } else {
            this.spawnAtLocation(new ItemStack(Items.IRON_NUGGET));
            this.gameEvent(GameEvent.ENTITY_INTERACT);
            this.playSound(SoundEvents.ARMADILLO_BRUSH);
            this.setBrushAmount(brushAmount-1);
            return true;
        }
    }

    public int getBrushAmount(){
        return this.entityData.get(BRUSH_AMOUNT);
    }

    public void setBrushAmount(int brushAmount){
        this.entityData.set(BRUSH_AMOUNT, brushAmount);
    }

    public int getRegenScaleTicks(){
        return this.entityData.get(REGEN_SCALES_TICKS);
    }

    public void setRegenScalesTicks(int regenScalesTicks){
        this.entityData.set(REGEN_SCALES_TICKS, regenScalesTicks);
    }

    private boolean canTame(){
        return isBaby() && getOwner() == null;
    }

    public boolean hasOwner(){
        return getOwner() != null;
    }

    private boolean isChickensaurOwner(LivingEntity livingEntity) {
        return hasOwner() && this.getOwnerUUID().equals(livingEntity.getUUID());
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
        int numHunterAllies =  EntityRetrievalUtil.getEntities(this, hunterFollowRange, 10.0, hunterFollowRange, LivingEntity.class, (entity) -> entity.getType().equals(EntityRegistry.CHICKENSAUR.get())
                && ((Chickensaur)entity).hasOwner()).size();
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

    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    public boolean isAttacking(){
        return this.entityData.get(ATTACKING);
    }

    public void setPickingUpItem(boolean b){
        this.entityData.set(PICKING_UP_ITEM,b);
    }

    public boolean isPickingUpItem(){
        return this.entityData.get(PICKING_UP_ITEM);
    }

    public boolean canAttackTooClosePossiblePlayer(LivingEntity target) {
        return !(target.getType().equals(EntityType.PLAYER) && hasOwner());
    }

    public boolean canAttackTarget(LivingEntity target) {
        EntityType<?> type = target.getType();
        if(!target.isDeadOrDying() && !target.isRemoved()) {
            if(isCreativeOrChickensaurOwner.test(target)) {
                return false;
            }

            if(hasOwner()) {
                LivingEntity owner = getOwner();

                boolean isTargetChickensaurWithSameOwner = (target instanceof Chickensaur chickensaur && chickensaur.isChickensaurOwner(owner));
                LivingEntity ownerHurtByTarget = owner.getLastHurtByMob();
                if(ownerHurtByTarget != null && ownerHurtByTarget.isAlive()) {
                    return target.is(ownerHurtByTarget) && !isTargetChickensaurWithSameOwner;
                }

                LivingEntity ownerHurtTarget = owner.getLastHurtMob();
                if(ownerHurtTarget != null && ownerHurtTarget.isAlive()) {
                    return target.is(ownerHurtTarget) && !isTargetChickensaurWithSameOwner;
                }

            }

            if(type.is(Constants.HUNT)) {
                return true;
            }
            else if(this.getHealth() >= 5) {
                DamageSource damageSource = this.getLastDamageSource();
                if(damageSource != null && damageSource.getEntity() != null) {
                    Entity revengeTarget = damageSource.getEntity();
                    return revengeTarget != null && revengeTarget.is(target);
                }
                else if(!isBaby() && type.is(Constants.INTIMIDATE) && canAttackTooClosePossiblePlayer(target) && this.distanceToSqr(target) < 4) {
                    return true;
                }
                else if(type.is(Constants.GROUP_HUNT)) {
                    return hasNumbersAdvantaqe(target);
                }
            }

        }
        return false;
    }

    public boolean isInvalidTarget(LivingEntity attacker, LivingEntity target) {
        EntityType<?> type = target.getType();
        if(!target.isDeadOrDying() && !target.isRemoved()) {
                if(target instanceof ServerPlayer player && player.isCreative()) {
                    return true;
                }
                if(type.is(Constants.HUNT)) {
                    return false;
                }
                else return attacker.getHealth() < 5;
        }
        return true;
    }

    public void forgivePlayer(ServerPlayer target){
        if(target.level().getGameRules().getBoolean(GameRules.RULE_FORGIVE_DEAD_PLAYERS) && BrainUtils.hasMemory(this,MemoryModuleType.HURT_BY_ENTITY) ) {
            Entity lastHurtBy = BrainUtils.getMemory(this, MemoryModuleType.HURT_BY_ENTITY);
            if(lastHurtBy != null && lastHurtBy.is(target)) {
                BrainUtils.clearMemory(this, MemoryModuleType.HURT_BY_ENTITY);
            }
        }
    }

    public void spawnChildFromBreeding(ServerLevel pLevel, Animal pMate) {
        ItemStack itemstack = new ItemStack(ItemRegistry.CHICKENSAUR_EGG.get());
        ItemEntity itementity = new ItemEntity(pLevel, this.position().x(), this.position().y(), this.position().z(), itemstack);
        itementity.setDefaultPickUpDelay();
        this.finalizeSpawnChildFromBreeding(pLevel, pMate, (AgeableMob)null);
        this.playSound(SoundEvents.SNIFFER_EGG_PLOP, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 0.5F);
        pLevel.addFreshEntity(itementity);
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
                new NearbyLivingEntitySensor<Chickensaur>().setRadius(16),
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
                new FloatToSurfaceOfFluid<Chickensaur>(),              // Have the entity float to the surface of a fluid
                new AvoidEntity<Chickensaur>().avoiding((entity) -> {
                    EntityType<?> entityType = entity.getType();
                    return this.getHealth() < 5 && !this.isChickensaurOwner(entity) && ((this.getLastAttacker() != null && this.getLastAttacker().is(entity)) || (entityType.is(Constants.INTIMIDATE) || entityType.is(Constants.GROUP_HUNT)));
                }).speedModifier(1.2F).noCloserThan(8),
                new EatFoodInMainHand<>().runFor((entity) -> 100),
                new FollowParent<Chickensaur>().startCondition((entity) -> !hasOwner()),
                new LookAtTarget<Chickensaur>().runFor(entity -> entity.getRandom().nextIntBetweenInclusive(40, 300)),                      // Have the entity turn to face and look at its current look target
                new MoveToWalkTarget<Chickensaur>().whenStopping((chickensaur -> {
                    if(chickensaur.isPickingUpItem()) {
                        chickensaur.setPickingUpItem(false);
                    }
                })));                 // Walk towards the current walk target
    }

    @Override
    public BrainActivityGroup<Chickensaur> getIdleTasks() { // These are the tasks that run when the mob isn't doing anything else (usually)
        return BrainActivityGroup.idleTasks(
                new FirstApplicableBehaviour(
                        new TargetOrRetaliate<Chickensaur>().alertAlliesWhen((chickensaur, attacker) -> attacker != null && chickensaur.canAttackTarget((LivingEntity) attacker)).attackablePredicate(this::canAttackTarget),
                        new IntimidateLivingEntity<Chickensaur>().runFor((entity) -> 200).startCondition((entity) -> !entity.hasOwner() && !entity.isBaby()),
                        new MoveToNearestVisibleWantedItem<Chickensaur>().cooldownFor((entity) -> 100)
                            .whenStarting((chickensaur) -> chickensaur.setPickingUpItem(true)),
                        new BreedWithPartner<Chickensaur>(),
                        new OneRandomBehaviour(
                                new SetRandomLookTarget<Chickensaur>(),
                                new SetRandomWalkTarget<Chickensaur>().setRadius(5).speedModifier(0.75F).cooldownFor((entity) -> 150),
                                new Idle<>().runFor(entity -> entity.getRandom().nextIntBetweenInclusive(30,60))
                        )
                )
        );
    }

    @Override
    public BrainActivityGroup<Chickensaur> getFightTasks() { // These are the tasks that handle fighting
        return BrainActivityGroup.fightTasks(
                new InvalidateAttackTarget<>()
                        .invalidateIf(this::isInvalidTarget), // Cancel fighting if the target is no longer valid
                new SetWalkTargetToAttackTarget<>().speedMod((entity, target) -> 1.2F),      // Set the walk target to the attack target
                new AnimatableMeleeAttack<>(6)
                        .whenStarting((mob) -> {
                            this.setAttacking(true);
                        })
                        .whenStopping((mob -> {
                            this.setAttacking(false);
                        }))); // Melee attack the target if close enough
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.37500001192092896)
                .add(Attributes.MAX_HEALTH, 25.0).add(Attributes.ATTACK_DAMAGE, 8.0)
                .add(Attributes.ARMOR, 12F)
                .add(Attributes.FOLLOW_RANGE,16F);
    }

    public static void angerNearbyChickensaurs(LivingEntity livingEntity, boolean angerOnlyIfCanSee) {
        List<Chickensaur> list = livingEntity.level().getEntitiesOfClass(Chickensaur.class, livingEntity.getBoundingBox().inflate(16.0));
        list.stream().filter(chickensaur -> (!angerOnlyIfCanSee || BehaviorUtils.canSee(chickensaur,livingEntity)) && !chickensaur.isChickensaurOwner(livingEntity)).forEach(chickensaur -> {
            BrainUtils.setTargetOfEntity(chickensaur, livingEntity);
            BrainUtils.clearMemory(chickensaur, MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE);
        });
    }

    public static ServerPlayer imprintOnNearestPlayer(Chickensaur chickensaur) {
        ServerPlayer serverPlayer = chickensaur.level().getNearestEntity(ServerPlayer.class, TargetingConditions.forNonCombat().range(6.0),chickensaur,
                chickensaur.getX(),
                chickensaur.getY(),
                chickensaur.getZ(),
                chickensaur.getBoundingBox().inflate(6.0, 2.0, 6.0));

        return serverPlayer;
    }

    public LivingEntity getControllingPassenger() {
        if (this.isSaddled()) {
            Entity entity = this.getFirstPassenger();
            if (entity instanceof Player player && isChickensaurOwner(player)) {
                return (Player)entity;
            }
        }

        return super.getControllingPassenger();
    }

    protected Vec2 getRiddenRotation(LivingEntity pEntity) {
        return new Vec2(pEntity.getXRot() * 0.5F, pEntity.getYRot());
    }

    protected void tickRidden(Player pPlayer, Vec3 pTravelVector) {
        super.tickRidden(pPlayer, pTravelVector);
        Vec2 vec2 = this.getRiddenRotation(pPlayer);
        this.setRot(vec2.y, vec2.x);
        this.yRotO = this.yBodyRot = this.yHeadRot = this.getYRot();
    }

    protected Vec3 getRiddenInput(Player pPlayer, Vec3 pTravelVector) {
        float f = pPlayer.xxa * 0.5F;
        float f1 = pPlayer.zza;
        if (f1 <= 0.0F) {
            f1 *= 0.25F;
        }

        return new Vec3((double)f, 0.0, (double)f1);
    }

    protected float getRiddenSpeed(Player pPlayer) {
        return (float)this.getAttributeValue(Attributes.MOVEMENT_SPEED);
    }

    protected Vec3 getPassengerAttachmentPoint(Entity pEntity, EntityDimensions pDimensions, float pPartialTick) {
        return super.getPassengerAttachmentPoint(pEntity, pDimensions, pPartialTick).add(0.0, -0.15F*pPartialTick, (-0.5 * pPartialTick)).yRot(-this.getYRot() * 0.017453292F);
    }

    @Override
    public boolean isSaddleable() {
        return this.isAlive() && !this.isBaby() && this.hasOwner();
    }

    @Override
    public void equipSaddle(ItemStack itemStack, @Nullable SoundSource soundSource) {
        this.entityData.set(SADDLED,true);
    }

    @Override
    public boolean isSaddled() {
        return this.entityData.get(SADDLED);
    }

    public void setSaddled(boolean is_saddled){
        this.entityData.set(SADDLED,is_saddled);
    }


    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("intimidating",isIntimidating());
        pCompound.putInt("intimidatingTicks",getIntimidatingTicks());
        pCompound.putBoolean("saddled",isSaddled());
        pCompound.putInt("brushAmount",getBrushAmount());
        pCompound.putInt("regenScaleTicks", getRegenScaleTicks());
        pCompound.putByte("collarColor", (byte)this.getCollarColor().getId());
        pCompound.putBoolean("isPickingUpItem", this.isPickingUpItem());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        setIntimidating(pCompound.getBoolean("intimidating"));
        setIntimidatingTicks(pCompound.getInt("intimidatingTicks"));
        setSaddled(pCompound.getBoolean("saddled"));
        setBrushAmount(pCompound.getInt("brushAmount"));
        setRegenScalesTicks(pCompound.getInt("regenScaleTicks"));
        if (pCompound.contains("collarColor", 99)) {
            this.setCollarColor(DyeColor.byId(pCompound.getInt("collarColor")));
        }
        setPickingUpItem(pCompound.getBoolean("isPickingUpItem"));
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        if (level.getRandom().nextFloat() < 0.05F) {
            this.setBaby(true);
        }
        this.setBrushAmount(MAX_BRUSH_AMOUNT);
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }
}
