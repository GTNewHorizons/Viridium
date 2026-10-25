package net.junedev.viridium.blocks.sets.large_tree;

import net.junedev.viridium.blocks.BasePlanks;
import net.minecraft.block.Block;

import cpw.mods.fml.common.registry.GameRegistry;

public class AmericanChestnutBlockSet extends LargeTreeBlockSet {

    public final Block planksWormy;

    public AmericanChestnutBlockSet(String name) {
        super(name);

        planksWormy = new BasePlanks().setBlockName(name + "_planks_wormy")
            .setBlockTextureName(getAssetDirectory() + name + "_planks_wormy");

        GameRegistry.registerBlock(planksWormy, name + "_planks_wormy");
    }
}
