package net.hyper_pigeon.chickensaurs.worldgen.structure;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.hyper_pigeon.chickensaurs.Constants;
import net.hyper_pigeon.chickensaurs.register.StructureRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.EmptyBlockGetter;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.WorldGenerationContext;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;

import java.util.Optional;

public class ChickensaurNestStructure extends Structure {
    public static final MapCodec<ChickensaurNestStructure> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
        return instance.group(settingsCodec(instance), HeightProvider.CODEC.fieldOf("height").forGetter((chickensaurNestStructure) -> {
            return chickensaurNestStructure.height;
        })).apply(instance, ChickensaurNestStructure::new);
    });

    public static final ResourceLocation RESOURCE_LOCATION = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "chickensaur_nest");

    public final HeightProvider height;

    public ChickensaurNestStructure(Structure.StructureSettings settings, HeightProvider height) {
        super(settings);
        this.height = height;
    }

    public Optional<Structure.GenerationStub> findGenerationPoint(Structure.GenerationContext context) {
        WorldgenRandom worldgenRandom = context.random();
        int i = context.chunkPos().getMiddleBlockX();
        int j = context.chunkPos().getMiddleBlockZ();
        int k = context.chunkGenerator().getSeaLevel();
        WorldGenerationContext worldGenerationContext = new WorldGenerationContext(context.chunkGenerator(), context.heightAccessor());
        int l = this.height.sample(worldgenRandom, worldGenerationContext);
        NoiseColumn noiseColumn = context.chunkGenerator().getBaseColumn(i, j, context.heightAccessor(), context.randomState());
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos(i, l, j);

        while(l > k) {
            BlockState blockState = noiseColumn.getBlock(l);
            --l;
            BlockState blockState2 = noiseColumn.getBlock(l);
            if (blockState.isAir() && (blockState2.is(Blocks.SOUL_SAND) || blockState2.isFaceSturdy(EmptyBlockGetter.INSTANCE, mutableBlockPos.setY(l), Direction.UP))) {
                break;
            }
        }

        if (l <= k) {
            return Optional.empty();
        } else {
            BlockPos blockPos = new BlockPos(i, l-1, j);
            return Optional.of(new Structure.GenerationStub(blockPos, (structurePiecesBuilder) -> {
                ChickensaurNestGenerator.addPieces(context.structureTemplateManager(), structurePiecesBuilder, worldgenRandom, blockPos);
            }));
        }
    }

    @Override
    public StructureType<?> type() {
        return StructureRegistry.CHICKENSAUR_NEST.get();
    }
}
