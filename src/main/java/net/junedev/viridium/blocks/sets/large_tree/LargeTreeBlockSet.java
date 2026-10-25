package net.junedev.viridium.blocks.sets.large_tree;

import net.junedev.viridium.blocks.BaseLeaves;
import net.junedev.viridium.blocks.BaseSaplingBlock;
import net.junedev.viridium.blocks.sets.LargeWoodBlockSet;
import net.minecraft.block.Block;

import cpw.mods.fml.common.registry.GameRegistry;

public class LargeTreeBlockSet extends LargeWoodBlockSet {

    public final Block leaves;
    public final Block sapling;

    public LargeTreeBlockSet(String name) {
        super(name);

        leaves = new BaseLeaves().setBlockName(name + "_leaves")
            .setBlockTextureName(getAssetDirectory() + name + "_leaves");
        sapling = new BaseSaplingBlock().setBlockName(name + "_sapling")
            .setBlockTextureName(getAssetDirectory() + name + "_sapling");

        GameRegistry.registerBlock(leaves, name + "_leaves");
        GameRegistry.registerBlock(sapling, name + "_sapling");
    }
}
