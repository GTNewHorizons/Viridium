package net.junedev.viridium.blocks.sets.large_tree;

import net.junedev.viridium.blocks.BaseLeaves;
import net.minecraft.block.Block;

import cpw.mods.fml.common.registry.GameRegistry;

public class BurntLeavesTreeBlockSet extends LargeTreeBlockSet {

    public final Block burntLeaves;

    public BurntLeavesTreeBlockSet(String name) {
        super(name);

        burntLeaves = new BaseLeaves().setBlockName(name + "_leaves_burnt")
            .setBlockTextureName(getAssetDirectory() + name + "_leaves_burnt");

        GameRegistry.registerBlock(burntLeaves, name + "_leaves_burnt");
    }
}
