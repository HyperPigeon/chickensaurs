package net.hyper_pigeon.chickensaurs.block;

import net.hyper_pigeon.chickensaurs.entity.Chickensaur;
import net.hyper_pigeon.chickensaurs.register.EntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.Vec3;

public class ChickensaurEggBlock extends Block {
    private static final int HATCH_TIME_TICKS = 100;
    private static final int MAX_HATCH_LEVEL = 1; // No intermediate hatching stages
    public static final IntegerProperty HATCH = BlockStateProperties.HATCH;

    public ChickensaurEggBlock(Properties p_49795_) {
        super(p_49795_);
    }

    public ChickensaurEggBlock(){
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.RAW_IRON)
                .forceSolidOn()
                .strength(5F)
                .sound(SoundType.METAL)
                .randomTicks()
                .noOcclusion()
                .pushReaction(PushReaction.DESTROY));
        this.registerDefaultState((this.stateDefinition.any()).setValue(HATCH, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(HATCH);
    }

    public static int getHatchLevel(BlockState pState) {
        return pState.getValue(HATCH);
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (getHatchLevel(pState) == MAX_HATCH_LEVEL) {
            pLevel.playSound(null, pPos, SoundEvents.SNIFFER_EGG_HATCH, SoundSource.BLOCKS, 0.7F, 0.9F + pRandom.nextFloat() * 0.2F);
            pLevel.destroyBlock(pPos, false);
            Chickensaur chickensaur = EntityRegistry.CHICKENSAUR.get().create(pLevel);
            if (chickensaur != null) {
                Vec3 vec3 = pPos.getCenter();
                chickensaur.setBaby(true);
                chickensaur.moveTo(vec3.x(), vec3.y(), vec3.z(), Mth.wrapDegrees(pLevel.random.nextFloat() * 360.0F), 0.0F);
                pLevel.addFreshEntity(chickensaur);
            }
        } else {
            pLevel.setBlock(pPos, pState.setValue(HATCH, getHatchLevel(pState) + 1), 2);
        }
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pMovedByPiston) {
        pLevel.gameEvent(GameEvent.BLOCK_PLACE, pPos, GameEvent.Context.of(pState));
        pLevel.scheduleTick(pPos, this, HATCH_TIME_TICKS);
    }
}
