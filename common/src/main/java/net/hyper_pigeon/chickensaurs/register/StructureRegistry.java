package net.hyper_pigeon.chickensaurs.register;

import com.mojang.serialization.MapCodec;
import net.hyper_pigeon.chickensaurs.platform.Services;
import net.hyper_pigeon.chickensaurs.worldgen.structure.ChickensaurNestGenerator;
import net.hyper_pigeon.chickensaurs.worldgen.structure.ChickensaurNestStructure;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;

import java.util.function.Supplier;

public class StructureRegistry {
    public static Supplier<StructureType<ChickensaurNestStructure>> CHICKENSAUR_NEST = registerStructureType("chickensaur_nest", () -> ChickensaurNestStructure.CODEC);
    public static Supplier<StructurePieceType> CHICKENSAUR_NEST_PIECES = registerStructurePieceType("chickensaur_nest", () -> ChickensaurNestGenerator.Piece::new);

    public static void init(){
//        CHICKENSAUR_NEST = Registry.register
//                (BuiltInRegistries.STRUCTURE_TYPE,
//                        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "chickensaur_nest"),
//                        () -> ChickensaurNestStructure.CODEC);
//        CHICKENSAUR_NEST_PIECES = Registry.register(BuiltInRegistries.STRUCTURE_PIECE,
//                ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "chickensaur_nest"),  ChickensaurNestGenerator.Piece::new);
    }

    public static <S extends Structure, T extends StructureType<S>> Supplier<T> registerStructureType(String id, Supplier<MapCodec<S>> mapCodec){
        return Services.PLATFORM.registerStructureType(id, mapCodec);
    }

    public static <T extends StructurePieceType> Supplier<T> registerStructurePieceType(String id, Supplier<T> structurePiece){
        return Services.PLATFORM.registerStructurePieceType(id, structurePiece);
    }


}
