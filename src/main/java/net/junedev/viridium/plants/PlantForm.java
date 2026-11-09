package net.junedev.viridium.plants;

import java.util.List;

import net.minecraft.block.Block;

public interface PlantForm<B> {

    PlantCategory category();

    B createBlocks(String registryName);

    List<Block> getBlocksWithItems(B createdBlocks);
}
