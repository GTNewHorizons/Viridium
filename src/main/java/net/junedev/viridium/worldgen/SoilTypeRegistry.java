package net.junedev.viridium.worldgen;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public enum SoilTypeRegistry {

    INSTANCE;

    private static final List<Block> genericSoil;
    private static final List<Block> sand;
    private static final List<Block> alfisol;
    private static final List<Block> andisol;
    private static final List<Block> aridisol;
    private static final List<Block> entisol;
    private static final List<Block> gelisol;
    private static final List<Block> histosol;
    private static final List<Block> inceptisol;
    private static final List<Block> mollisol;
    private static final List<Block> oxisol;
    private static final List<Block> spodosol;
    private static final List<Block> ultisol;
    private static final List<Block> vertisol;

    static {
        genericSoil = new ArrayList<Block>();
        addSoil(genericSoil, Blocks.grass);
        addSoil(genericSoil, Blocks.dirt);

        sand = new ArrayList<Block>();
        addSoil(sand, Blocks.sand);

        alfisol = new ArrayList<Block>();

        andisol = new ArrayList<Block>();

        aridisol = new ArrayList<Block>();

        entisol = new ArrayList<Block>();

        gelisol = new ArrayList<Block>();

        histosol = new ArrayList<Block>();

        inceptisol = new ArrayList<Block>();

        mollisol = new ArrayList<Block>();

        oxisol = new ArrayList<Block>();

        spodosol = new ArrayList<Block>();

        ultisol = new ArrayList<Block>();

        vertisol = new ArrayList<Block>();

    }

    public static void addSoil(List<Block> soilType, Block soil) {
        soilType.add(soil);
    }

    public static boolean isValidGeneric(Block soil) {
        return genericSoil.contains(soil);
    }

    // TODO: When soils have been added, make sure to add functions for checking generic validity and type
}
