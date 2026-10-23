package net.junedev.viridium.blocks.sets.large_tree;

import net.junedev.viridium.blocks.BaseLeaves;
import net.junedev.viridium.blocks.BaseSaplingBlock;
import net.junedev.viridium.blocks.sets.LargeWoodBlockSet;
import net.minecraft.block.Block;

import cpw.mods.fml.common.registry.GameRegistry;

public class LargeTreeBlockSet extends LargeWoodBlockSet {

    private static final String ASSET_DIRECTORY = "large_trees/";

    public final Block leaves;
    public final Block sapling;

    public LargeTreeBlockSet(String name) {
        super(name);

        leaves = new BaseLeaves().setBlockName(name + "_leaves")
            .setBlockTextureName(ASSET_DIRECTORY + name + "_leaves");
        sapling = new BaseSaplingBlock().setBlockName(name + "_sapling")
            .setBlockTextureName(ASSET_DIRECTORY + name + "_sapling");

        GameRegistry.registerBlock(leaves, name + "_leaves");
        GameRegistry.registerBlock(sapling, name + "_sapling");
    }
}
