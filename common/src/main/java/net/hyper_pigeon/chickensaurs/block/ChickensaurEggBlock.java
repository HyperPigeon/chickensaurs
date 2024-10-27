package net.hyper_pigeon.chickensaurs.block;

import net.hyper_pigeon.chickensaurs.Chickensaurs;
import net.hyper_pigeon.chickensaurs.Constants;
import net.hyper_pigeon.chickensaurs.entity.Chickensaur;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class ChickensaurEggBlock extends Block {
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
    }

    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (state.is(Constants.GUARDED_BY_CHICKENSAURS)) {
            Chickensaur.angerNearbyChickensaurs(player,false);
        }
        return super.playerWillDestroy(level, pos, state, player);
    }
}
