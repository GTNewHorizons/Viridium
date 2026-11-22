package net.junedev.viridium.blocks.sets.large_tree;

import java.util.List;

import net.junedev.viridium.blocks.vanilike.BaseLeaves;
import net.junedev.viridium.plants.SeedType;
import net.minecraft.block.Block;

import cpw.mods.fml.common.registry.GameRegistry;

public class BurntLeavesTreeBlockSet extends LargeTreeBlockSetCone {

    public final Block burntLeaves;
    public final SeedType seedType;

    public BurntLeavesTreeBlockSet(String name) {
        super(name);

        seedType = SeedType.CONE;
        burntLeaves = new BaseLeaves().setBlockName(name + "_leaves_burnt")
            .setBlockTextureName(getAssetDirectory() + name + "_leaves_burnt");

        GameRegistry.registerBlock(burntLeaves, name + "_leaves_burnt");
    }

    @Override
    public List<Block> getBlocksWithItems() {
        List<Block> blocks = super.getBlocksWithItems();
        blocks.add(burntLeaves);
        return blocks;
    }
}
