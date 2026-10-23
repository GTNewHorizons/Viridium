package net.junedev.viridium.blocks.sets;

import net.junedev.viridium.blocks.BaseFence;
import net.junedev.viridium.blocks.BaseLeaves;
import net.junedev.viridium.blocks.BasePlanks;
import net.junedev.viridium.blocks.BaseSaplingBlock;
import net.junedev.viridium.blocks.BaseStairs;
import net.junedev.viridium.blocks.SmallLogBlock;
import net.minecraft.block.Block;

import cpw.mods.fml.common.registry.GameRegistry;

public class SmallTreeBlockSet {

    private static final String ASSET_DIRECTORY = "small_trees/";

    public final Block log;
    public final Block bark;
    public final Block leaves;
    public final Block planks;
    public final Block sapling;

    public final Block stairs;
    public final Block fence;

    public SmallTreeBlockSet(String name) {
        this(name, 4, 4, true);
    }

    public SmallTreeBlockSet(String name, int halfWidth, int faceHalfWidth, boolean doSidesConnect) {
        log = new SmallLogBlock(halfWidth, faceHalfWidth, doSidesConnect).setBlockName(name + "_log")
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

        stairs = new BaseStairs(planks).setBlockName(name + "_planks_stairs");
        fence = new BaseFence(planks).setBlockName(name + "_planks_fence");

        // TODO: Deffer registry to method
        GameRegistry.registerBlock(log, name + "_log");
        GameRegistry.registerBlock(leaves, name + "_leaves");
        GameRegistry.registerBlock(planks, name + "_planks");
        GameRegistry.registerBlock(sapling, name + "_sapling");
        GameRegistry.registerBlock(bark, name + "_bark");
        GameRegistry.registerBlock(stairs, name + "_planks_stairs");
        GameRegistry.registerBlock(fence, name + "_planks_fence");
    }
}
