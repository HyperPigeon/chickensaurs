package net.hyper_pigeon.chickensaurs.register;

import net.hyper_pigeon.chickensaurs.Constants;
import net.hyper_pigeon.chickensaurs.worldgen.structure.ChickensaurNestGenerator;
import net.hyper_pigeon.chickensaurs.worldgen.structure.ChickensaurNestStructure;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;

public class StructureRegistry {
    public static StructureType<ChickensaurNestStructure> CHICKENSAUR_NEST;
    public static StructurePieceType CHICKENSAUR_NEST_PIECES;

    public static void init(){
        CHICKENSAUR_NEST = Registry.register
                (BuiltInRegistries.STRUCTURE_TYPE,
                        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "chickensaur_nest"),
                        () -> ChickensaurNestStructure.CODEC);
        CHICKENSAUR_NEST_PIECES = Registry.register(BuiltInRegistries.STRUCTURE_PIECE,
                ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "chickensaur_nest"),  ChickensaurNestGenerator.Piece::new);
    }
}
