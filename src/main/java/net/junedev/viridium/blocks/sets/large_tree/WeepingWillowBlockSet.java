package net.junedev.viridium.blocks.sets.large_tree;

import java.util.List;

import net.junedev.viridium.blocks.vanilike.BaseSeedling;
import net.junedev.viridium.blocks.vanilike.WillowVineBlock;
import net.junedev.viridium.items.BaseSeed;
import net.junedev.viridium.plants.SeedType;
import net.minecraft.block.Block;

import cpw.mods.fml.common.registry.GameRegistry;

public class WeepingWillowBlockSet extends LargeTreeBlockSet {

    public final Block vines;

    public WeepingWillowBlockSet(String name) {
        super(name, SeedType.GENERIC, BaseSeedling::new, BaseSeed::new);

        // WillowVineBlock sets its own texture in setBlockName.
        vines = new WillowVineBlock().setBlockName(name + "_vines");

        GameRegistry.registerBlock(vines, name + "_vines");
    }

    @Override
    public List<Block> getBlocksWithItems() {
        List<Block> blocks = super.getBlocksWithItems();
        blocks.add(vines);
        return blocks;
    }
}
