package net.junedev.viridium.blocks.sets.large_tree;

import java.util.List;

import net.junedev.viridium.Viridium;
import net.junedev.viridium.blocks.sets.LargeWoodBlockSet;
import net.junedev.viridium.blocks.vanilike.BaseLeaves;
import net.junedev.viridium.blocks.vanilike.BaseSaplingBlock;
import net.junedev.viridium.blocks.vanilike.BaseSeedling;
import net.junedev.viridium.items.BaseSeed;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

import cpw.mods.fml.common.registry.GameRegistry;

public class LargeTreeBlockSet extends LargeWoodBlockSet {

    public final Block leaves;
    public final Block sapling;
    public final Item seed;
    public final Block seedling;

    public LargeTreeBlockSet(String name) {
        super(name);

        sapling = new BaseSaplingBlock(Viridium.MOD_ID + ":" + name, this).setBlockName(name + "_sapling")
            .setBlockTextureName(getAssetDirectory() + name + "_sapling");
        seedling = new BaseSeedling(Viridium.MOD_ID + ":" + name, this).setSapling(sapling);
        seedling.setBlockName(name + "_seedling")
            .setBlockTextureName(name);
        seed = new BaseSeed(seedling, Blocks.grass).setUnlocalizedName(name + "_seeds")
            .setTextureName(name + "_seeds");
        leaves = new BaseLeaves().setItemDropped(seed).setBlockName(name + "_leaves")
            .setBlockTextureName(getAssetDirectory() + name + "_leaves");

        GameRegistry.registerBlock(leaves, name + "_leaves");
        GameRegistry.registerBlock(sapling, name + "_sapling");
        GameRegistry.registerItem(seed, name + "_seeds");
        GameRegistry.registerBlock(seedling, name + "_crop");
    }

    @Override
    public List<Block> getBlocksWithItems() {
        List<Block> blocks = super.getBlocksWithItems();
        blocks.add(leaves);
        blocks.add(sapling);
        blocks.add(seedling);
        return blocks;
    }
}
