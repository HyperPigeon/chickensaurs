package net.hyper_pigeon.chickensaurs.worldgen.structure;

import net.hyper_pigeon.chickensaurs.register.StructureRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePieceAccessor;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import org.jetbrains.annotations.NotNull;

public class ChickensaurNestGenerator {

    public ChickensaurNestGenerator(){

    }

    public static void addPieces(StructureTemplateManager manager, StructurePieceAccessor holder, @NotNull RandomSource random, BlockPos pos) {
        Rotation blockRotation = Rotation.getRandom(random);
        holder.addPiece(new ChickensaurNestGenerator.Piece(manager, ChickensaurNestStructure.RESOURCE_LOCATION, pos, blockRotation));
    }

    public static class Piece extends TemplateStructurePiece {
        public Piece(StructureTemplateManager manager, ResourceLocation template, BlockPos pos, Rotation rotation) {
            super(StructureRegistry.CHICKENSAUR_NEST_PIECES, 0, manager, template, template.toString(), createPlacementData(rotation), pos);
        }

        public Piece(StructureTemplateManager manager, CompoundTag nbt) {
            super(StructureRegistry.CHICKENSAUR_NEST_PIECES, nbt, manager, (id) -> {
                return createPlacementData(Rotation.valueOf(nbt.getString("Rot")));
            });
        }

        public Piece(StructurePieceSerializationContext structureContext, CompoundTag nbt) {
            super(StructureRegistry.CHICKENSAUR_NEST_PIECES, nbt, structureContext.structureTemplateManager(), (id) -> {
                return createPlacementData(Rotation.valueOf(nbt.getString("Rot")));
            });
        }


        private static StructurePlaceSettings createPlacementData(Rotation rotation) {
            return (new StructurePlaceSettings()).setRotation(rotation).setMirror(Mirror.NONE).addProcessor(BlockIgnoreProcessor.STRUCTURE_AND_AIR);
        }

        @Override
        protected void addAdditionalSaveData(StructurePieceSerializationContext context, CompoundTag nbt) {
            super.addAdditionalSaveData(context, nbt);
            nbt.putString("Rot", this.placeSettings.getRotation().name());
        }

        @Override
        protected void handleDataMarker(String metadata, BlockPos pos, ServerLevelAccessor world, @NotNull RandomSource random, BoundingBox boundingBox) {
        }

        @Override
        public void postProcess(WorldGenLevel world, StructureManager structureAccessor, ChunkGenerator chunkGenerator, @NotNull RandomSource random, BoundingBox chunkBox, ChunkPos chunkPos, BlockPos pivot) {
            chunkBox.encapsulate(this.template.getBoundingBox(this.placeSettings, this.templatePosition));
            super.postProcess(world, structureAccessor, chunkGenerator, random, chunkBox, chunkPos, pivot);
        }
    }
}
