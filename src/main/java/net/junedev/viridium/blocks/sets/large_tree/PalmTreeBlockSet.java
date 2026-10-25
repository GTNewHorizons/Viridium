package net.junedev.viridium.blocks.sets.large_tree;

import net.junedev.viridium.blocks.BaseFullLog;
import net.junedev.viridium.blocks.BaseLeaves;
import net.minecraft.block.Block;

import cpw.mods.fml.common.registry.GameRegistry;

public class PalmTreeBlockSet extends LargeTreeBlockSet {

    public final Block crown;

    public PalmTreeBlockSet(String name) {
        super(name);

        crown = new BaseFullLog().setBlockName(name + "_crown")
            .setBlockTextureName(getAssetDirectory() + name + "_crown");

        GameRegistry.registerBlock(crown, name + "_crown");
    }
}
