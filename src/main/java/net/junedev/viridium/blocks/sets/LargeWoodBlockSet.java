package net.junedev.viridium.blocks.sets;

import net.junedev.viridium.blocks.BaseFence;
import net.junedev.viridium.blocks.BaseFullLog;
import net.junedev.viridium.blocks.BasePlanks;
import net.junedev.viridium.blocks.BaseStairs;
import net.minecraft.block.Block;

import cpw.mods.fml.common.registry.GameRegistry;

public class LargeWoodBlockSet {

    private static final String ASSET_DIRECTORY = "large_trees/";

    public final Block log;
    public final Block stripped;
    public final Block bark;
    public final Block planks;

    public final Block stairs;
    public final Block fence;

    public LargeWoodBlockSet(String name) {
        log = new BaseFullLog().setBlockName(name + "_log")
            .setBlockTextureName(ASSET_DIRECTORY + name + "_log");
        stripped = new BaseFullLog().setBlockName(name + "_stripped")
            .setBlockTextureName(ASSET_DIRECTORY + name + "_stripped");
        planks = new BasePlanks().setBlockName(name + "_planks")
            .setBlockTextureName(ASSET_DIRECTORY + name + "_planks");

        // TODO: Change to custom block type?
        bark = new BasePlanks().setBlockName(name + "_bark")
            .setBlockTextureName(ASSET_DIRECTORY + name + "_log_side");

        stairs = new BaseStairs(planks).setBlockName(name + "_planks_stairs");
        fence = new BaseFence(planks).setBlockName(name + "_planks_fence");

        // TODO: Deffer registry to method
        GameRegistry.registerBlock(log, name + "_log");
        GameRegistry.registerBlock(stripped, name + "_stripped");
        GameRegistry.registerBlock(planks, name + "_planks");
        GameRegistry.registerBlock(bark, name + "_bark");
        GameRegistry.registerBlock(stairs, name + "_planks_stairs");
        GameRegistry.registerBlock(fence, name + "_planks_fence");
    }
}
