package net.junedev.viridium.worldgen.biomes;

import net.junedev.viridium.Viridium;
import net.junedev.viridium.utils.parser.BlockParser;
import net.minecraft.block.Block;

public class PlantVariant {
    public Block block;
    public int meta;

    public PlantVariant(Block block, int meta){
        this.block = block;
        this.meta = meta;
    }

    public static PlantVariant create(String blockId, int meta){
        Block block = BlockParser.getBlock(blockId);
        if(block == null){
            // Maybe throw exception ?
            Viridium.LOGGER.warn("PlantVariant: block id not found ({})", blockId);
            return null;
        }
        return new PlantVariant(block, meta);
    }
}
