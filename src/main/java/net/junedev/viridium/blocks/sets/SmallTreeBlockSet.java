package net.junedev.viridium.blocks.sets;

import net.junedev.viridium.blocks.BaseLeaves;
import net.junedev.viridium.blocks.BasePlanks;
import net.junedev.viridium.blocks.BaseSaplingBlock;
import net.junedev.viridium.blocks.SmallLogBlock;
import net.minecraft.block.Block;

import cpw.mods.fml.common.registry.GameRegistry;

public class SmallTreeBlockSet {

    private static final String ASSET_DIRECTORY = "small_trees/";

    public final Block smallLog;
    public final Block bark;
    public final Block leaves;
    public final Block planks;
    public final Block sapling;

    public SmallTreeBlockSet(String name) {
        this(name, 4, 4, true);
    }

    public SmallTreeBlockSet(String name, int halfWidth, int faceHalfWidth, boolean doSidesConnect) {
        smallLog = new SmallLogBlock(halfWidth, faceHalfWidth, doSidesConnect).setBlockName(name + "_log")
            .setBlockTextureName(ASSET_DIRECTORY + name + "_log");
        leaves = new BaseLeaves().setBlockName(name + "_leaves")
            .setBlockTextureName(ASSET_DIRECTORY + name + "_leaves");
        planks = new BasePlanks().setBlockName(name + "_planks")
            .setBlockTextureName(ASSET_DIRECTORY + name + "_planks");
        sapling = new BaseSaplingBlock().setBlockName(name + "_sapling")
            .setBlockTextureName(ASSET_DIRECTORY + name + "_sapling");

        // TODO: Change to custom block type?
        bark = new BasePlanks().setBlockName(name + "_bark")
            .setBlockTextureName(ASSET_DIRECTORY + name + "_bark");

        // TODO: Deffer registry to method
        GameRegistry.registerBlock(smallLog, name + "_log");
        GameRegistry.registerBlock(leaves, name + "_leaves");
        GameRegistry.registerBlock(planks, name + "_planks");
        GameRegistry.registerBlock(sapling, name + "_sapling");
        GameRegistry.registerBlock(bark, name + "_bark");
    }
}
