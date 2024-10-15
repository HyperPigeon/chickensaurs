package net.hyper_pigeon.chickensaurs.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
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
}
