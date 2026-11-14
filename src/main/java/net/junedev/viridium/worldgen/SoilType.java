package net.junedev.viridium.worldgen;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public enum SoilType {

    GENERIC(Blocks.dirt, Blocks.grass),
    SAND(Blocks.sand),
    ALFISOL,
    ANDISOL,
    ARIDISOL,
    ENTISOL,
    GELISOL,
    HISTOSOL,
    INCEPTISOL,
    MOLLISOL,
    OXISOL,
    SPODOSOL,
    ULTISOL,
    VERTISOL;

    private final List<Block> blocks;

    SoilType(Block... blocks) {
        this.blocks = Collections.unmodifiableList(Arrays.asList(blocks));
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public boolean matches(Block soil) {
        return blocks.contains(soil);
    }
}
