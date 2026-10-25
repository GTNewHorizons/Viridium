package net.junedev.viridium.blocks.sets;

import net.junedev.viridium.blocks.BaseFence;
import net.junedev.viridium.blocks.BaseFenceGate;
import net.junedev.viridium.blocks.BasePlanks;
import net.junedev.viridium.blocks.BaseSlab;
import net.junedev.viridium.blocks.BaseStairs;
import net.junedev.viridium.items.BaseSlabItem;
import net.minecraft.block.Block;

import cpw.mods.fml.common.registry.GameRegistry;

public class WoodBlockSet {

    public final Block planks;

    public final Block stairs;
    public final Block fence;
    public final Block fenceGate;
    public final BaseSlab slab;
    public final BaseSlab doubleSlab;

    public WoodBlockSet(String name) {

        planks = new BasePlanks().setBlockName(name + "_planks")
            .setBlockTextureName(getAssetDirectory() + name + "_planks");

        stairs = new BaseStairs(planks).setBlockName(name + "_planks_stairs");
        fence = new BaseFence(planks).setBlockName(name + "_planks_fence");
        fenceGate = new BaseFenceGate(planks).setBlockName(name + "_planks_fence_gate");
        slab = new BaseSlab(planks);
        slab.setBlockName(name + "_planks_slab");
        doubleSlab = new BaseSlab(slab);
        doubleSlab.setBlockName(name + "_planks_double_slab");

        // TODO: Deffer registry to method
        GameRegistry.registerBlock(planks, name + "_planks");
        GameRegistry.registerBlock(stairs, name + "_planks_stairs");
        GameRegistry.registerBlock(fence, name + "_planks_fence");
        GameRegistry.registerBlock(fenceGate, name + "_planks_fence_gate");
        // The double slab exists in the world but has no separate inventory item.
        GameRegistry.registerBlock(doubleSlab, null, name + "_planks_double_slab");
        GameRegistry.registerBlock(slab, BaseSlabItem.class, name + "_planks_slab", slab, doubleSlab);
    }

    public String getAssetDirectory() {
        return "";
    }
}
