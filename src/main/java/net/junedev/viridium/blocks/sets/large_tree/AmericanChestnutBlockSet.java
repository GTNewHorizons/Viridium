package net.junedev.viridium.blocks.sets.large_tree;

import java.util.List;

import net.junedev.viridium.blocks.vanilike.BasePlanks;
import net.junedev.viridium.blocks.vanilike.BaseSeedling;
import net.junedev.viridium.items.BaseSeed;
import net.junedev.viridium.items.TreeNut;
import net.junedev.viridium.plants.SeedType;
import net.minecraft.block.Block;

import cpw.mods.fml.common.registry.GameRegistry;

public class AmericanChestnutBlockSet extends LargeTreeBlockSet {

    public final Block planksWormy;

    public AmericanChestnutBlockSet(String name) {
        super(name, SeedType.NUT, BaseSeedling::new, (seedling, soilblock) -> new TreeNut(1, 1.0f, seedling, soilblock));

        planksWormy = new BasePlanks().setBlockName(name + "_planks_wormy")
            .setBlockTextureName(getAssetDirectory() + name + "_planks_wormy");

        GameRegistry.registerBlock(planksWormy, name + "_planks_wormy");
    }

    @Override
    public List<Block> getBlocksWithItems() {
        List<Block> blocks = super.getBlocksWithItems();
        blocks.add(planksWormy);
        return blocks;
    }
}
