package net.junedev.viridium.blocks.sets.large_tree;

import net.junedev.viridium.Viridium;
import net.junedev.viridium.blocks.sets.LargeWoodBlockSet;
import net.junedev.viridium.blocks.vanilike.BaseLeaves;
import net.junedev.viridium.blocks.vanilike.BaseSaplingBlock;
import net.minecraft.block.Block;

import cpw.mods.fml.common.registry.GameRegistry;

public class WhitePoplarBlockSet extends LargeWoodBlockSet {

    public final Block leaves_yellow;
    public final Block leaves_orange;
    public final Block leaves_red;

    public final Block sapling_yellow;
    public final Block sapling_orange;
    public final Block sapling_red;

    public WhitePoplarBlockSet(String name) {
        super(name);

        leaves_yellow = new BaseLeaves().setBlockName(name + "_leaves_yellow")
            .setBlockTextureName(getAssetDirectory() + name + "_leaves_yellow");
        leaves_orange = new BaseLeaves().setBlockName(name + "_leaves_orange")
            .setBlockTextureName(getAssetDirectory() + name + "_leaves_orange");
        leaves_red = new BaseLeaves().setBlockName(name + "_leaves_red")
            .setBlockTextureName(getAssetDirectory() + name + "_leaves_red");

        sapling_yellow = new BaseSaplingBlock(Viridium.MOD_ID + ":" + name, this, "yellow")
            .setBlockName(name + "_sapling_yellow")
            .setBlockTextureName(getAssetDirectory() + name + "_sapling_yellow");
        sapling_orange = new BaseSaplingBlock(Viridium.MOD_ID + ":" + name, this, "orange")
            .setBlockName(name + "_sapling_orange")
            .setBlockTextureName(getAssetDirectory() + name + "_sapling_orange");
        sapling_red = new BaseSaplingBlock(Viridium.MOD_ID + ":" + name, this, "red")
            .setBlockName(name + "_sapling_red")
            .setBlockTextureName(getAssetDirectory() + name + "_sapling_red");

        GameRegistry.registerBlock(leaves_yellow, name + "_leaves_yellow");
        GameRegistry.registerBlock(leaves_orange, name + "_leaves_orange");
        GameRegistry.registerBlock(leaves_red, name + "_leaves_red");

        GameRegistry.registerBlock(sapling_yellow, name + "_sapling_yellow");
        GameRegistry.registerBlock(sapling_orange, name + "_sapling_orange");
        GameRegistry.registerBlock(sapling_red, name + "_sapling_red");
    }
}
