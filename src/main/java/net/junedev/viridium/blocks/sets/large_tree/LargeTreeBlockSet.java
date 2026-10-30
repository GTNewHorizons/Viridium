package net.junedev.viridium.blocks.sets.large_tree;

import net.junedev.viridium.Viridium;
import net.junedev.viridium.blocks.sets.LargeWoodBlockSet;
import net.junedev.viridium.blocks.vanilike.BaseLeaves;
import net.junedev.viridium.blocks.vanilike.BaseSaplingBlock;
import net.minecraft.block.Block;

import cpw.mods.fml.common.registry.GameRegistry;

public class LargeTreeBlockSet extends LargeWoodBlockSet {

    public final Block leaves;
    public final Block sapling;

    public LargeTreeBlockSet(String name) {
        super(name);

        sapling = new BaseSaplingBlock(Viridium.MOD_ID + ":" + name, this).setBlockName(name + "_sapling")
            .setBlockTextureName(getAssetDirectory() + name + "_sapling");
        leaves = new BaseLeaves().setBlockName(name + "_leaves")
            .setBlockTextureName(getAssetDirectory() + name + "_leaves");

        GameRegistry.registerBlock(leaves, name + "_leaves");
        GameRegistry.registerBlock(sapling, name + "_sapling");
    }
}
