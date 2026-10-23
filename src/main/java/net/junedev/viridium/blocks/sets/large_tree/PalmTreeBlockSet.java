package net.junedev.viridium.blocks.sets.large_tree;

import net.junedev.viridium.blocks.BaseLeaves;
import net.minecraft.block.Block;

import cpw.mods.fml.common.registry.GameRegistry;

public class PalmTreeBlockSet extends LargeTreeBlockSet {

    private static final String ASSET_DIRECTORY = "large_trees/";

    public final Block crown;

    public PalmTreeBlockSet(String name) {
        super(name);

        crown = new BaseLeaves().setBlockName(name + "_crown")
            .setBlockTextureName(ASSET_DIRECTORY + name + "_crown");

        GameRegistry.registerBlock(crown, name + "_crown");
    }
}
