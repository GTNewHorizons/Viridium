package net.junedev.viridium.blocks.sets.small_tree;

import java.util.List;

import net.junedev.viridium.Viridium;
import net.junedev.viridium.blocks.SmallLogBlock;
import net.junedev.viridium.blocks.sets.WoodBlockSet;
import net.junedev.viridium.blocks.vanilike.*;
import net.junedev.viridium.items.ItemBlockScientificName;
import net.junedev.viridium.items.TreeNut;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

import cpw.mods.fml.common.registry.GameRegistry;

public class SmallTreeBlockSetNut extends WoodBlockSet {

    public final Block log;
    public final Block bark;
    public final Block leaves;
    public final Block sapling;
    public final Block seedling;
    public final Item seed;

    public SmallTreeBlockSetNut(String name) {
        this(name, 4, 4, true);
    }

    public SmallTreeBlockSetNut(String name, int halfWidth, int faceHalfWidth, boolean doSidesConnect) {
        super(name);

        log = new SmallLogBlock(halfWidth, faceHalfWidth, doSidesConnect).setBlockName(name + "_log")
            .setBlockTextureName(getAssetDirectory() + name + "_log");

        sapling = new SmallSaplingBlock(Viridium.MOD_ID + ":" + name, this).setBlockName(name + "_sapling")
            .setBlockTextureName(getAssetDirectory() + name + "_sapling");

        seedling = new BaseSeedling(Viridium.MOD_ID + ":" + name, this).setSapling(sapling);
        seedling.setBlockName(name + "_seedling")
            .setBlockTextureName(name);
        seed = new TreeNut(1, 1.0F, seedling, Blocks.grass).setUnlocalizedName(name + "_nut")
            .setTextureName(name + "_nut");

        leaves = new BaseLeaves().setItemDropped(seed)
            .setBlockName(name + "_leaves")
            .setBlockTextureName(getAssetDirectory() + name + "_leaves");

        // TODO: Change to custom block type?
        bark = new BaseBark().setBlockName(name + "_bark")
            .setBlockTextureName(getAssetDirectory() + name + "_bark");

        // TODO: Deffer registry to method
        GameRegistry.registerBlock(log, name + "_log");
        GameRegistry.registerBlock(leaves, name + "_leaves");
        GameRegistry.registerBlock(sapling, ItemBlockScientificName.class, name + "_sapling");
        GameRegistry.registerBlock(bark, name + "_bark");
        GameRegistry.registerBlock(seedling, name + "_seedling");
        GameRegistry.registerItem(seed, name + "_nut");

    }

    @Override
    public String getAssetDirectory() {
        return "small_trees/";
    }

    @Override
    public List<Block> getBlocksWithItems() {
        List<Block> blocks = super.getBlocksWithItems();
        blocks.add(log);
        blocks.add(leaves);
        blocks.add(sapling);
        blocks.add(bark);
        blocks.add(seedling);
        return blocks;
    }
}
