package net.junedev.viridium.plants.forms;

import java.util.Collections;
import java.util.List;

import net.junedev.viridium.plants.PlantForm;
import net.minecraft.block.Block;

import cpw.mods.fml.common.registry.GameRegistry;

public interface SingleBlockForm extends PlantForm<Block> {

    Block newBlock();

    String textureFolder();

    @Override
    default Block createBlocks(String registryName) {
        Block block = newBlock().setBlockName(registryName)
            .setBlockTextureName(textureFolder() + registryName);
        GameRegistry.registerBlock(block, registryName);
        return block;
    }

    @Override
    default List<Block> getBlocksWithItems(Block block) {
        return Collections.singletonList(block);
    }
}
