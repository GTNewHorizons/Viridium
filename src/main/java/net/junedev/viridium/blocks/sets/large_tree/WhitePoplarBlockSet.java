package net.junedev.viridium.blocks.sets.large_tree;

import net.junedev.viridium.blocks.BaseLeaves;
import net.junedev.viridium.blocks.BaseSaplingBlock;
import net.junedev.viridium.blocks.sets.LargeWoodBlockSet;
import net.minecraft.block.Block;

import cpw.mods.fml.common.registry.GameRegistry;

public class WhitePoplarBlockSet extends LargeWoodBlockSet {

    private static final String ASSET_DIRECTORY = "large_trees/";

    public final Block leaves_yellow;
    public final Block leaves_orange;
    public final Block leaves_red;

    public final Block sapling_yellow;
    public final Block sapling_orange;
    public final Block sapling_red;

    public WhitePoplarBlockSet(String name) {
        super(name);

        leaves_yellow = new BaseLeaves().setBlockName(name + "_leaves_yellow")
            .setBlockTextureName(ASSET_DIRECTORY + name + "_leaves_yellow");
        leaves_orange = new BaseLeaves().setBlockName(name + "_leaves_orange")
            .setBlockTextureName(ASSET_DIRECTORY + name + "_leaves_orange");
        leaves_red = new BaseLeaves().setBlockName(name + "_leaves_red")
            .setBlockTextureName(ASSET_DIRECTORY + name + "_leaves_red");

        sapling_yellow = new BaseSaplingBlock().setBlockName(name + "_sapling_yellow")
            .setBlockTextureName(ASSET_DIRECTORY + name + "_sapling_yellow");
        sapling_orange = new BaseSaplingBlock().setBlockName(name + "_sapling_orange")
            .setBlockTextureName(ASSET_DIRECTORY + name + "_sapling_orange");
        sapling_red = new BaseSaplingBlock().setBlockName(name + "_sapling_red")
            .setBlockTextureName(ASSET_DIRECTORY + name + "_sapling_red");

        GameRegistry.registerBlock(leaves_yellow, name + "_leaves_yellow");
        GameRegistry.registerBlock(leaves_orange, name + "_leaves_orange");
        GameRegistry.registerBlock(leaves_red, name + "_leaves_red");

        GameRegistry.registerBlock(sapling_yellow, name + "_sapling_yellow");
        GameRegistry.registerBlock(sapling_orange, name + "_sapling_orange");
        GameRegistry.registerBlock(sapling_red, name + "_sapling_red");
    }
}
