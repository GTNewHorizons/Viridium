package net.junedev.viridium.blocks.sets.small_tree;

import java.util.List;
import java.util.function.BiFunction;

import net.junedev.viridium.Viridium;
import net.junedev.viridium.blocks.SmallLogBlock;
import net.junedev.viridium.blocks.sets.WoodBlockSet;
import net.junedev.viridium.blocks.vanilike.BaseBark;
import net.junedev.viridium.blocks.vanilike.BaseLeaves;
import net.junedev.viridium.blocks.vanilike.BaseSeedling;
import net.junedev.viridium.blocks.vanilike.SmallConeSeedling;
import net.junedev.viridium.blocks.vanilike.SmallSaplingBlock;
import net.junedev.viridium.items.BaseSeed;
import net.junedev.viridium.items.ItemBlockScientificName;
import net.junedev.viridium.items.TreeFruit;
import net.junedev.viridium.items.TreeNut;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

import cpw.mods.fml.common.registry.GameRegistry;

public class SmallTreeBlockSet extends WoodBlockSet {

    public final Block log;
    public final Block bark;
    public final Block leaves;
    public final Block sapling;
    public final Block seedling;
    public final Item seed;

    public SmallTreeBlockSet(String name) {
        this(name, 4, 4, true, "_seeds", BaseSeedling::new, BaseSeed::new);
    }

    public SmallTreeBlockSet(String name, int halfWidth, int faceHalfWidth, boolean doSidesConnect, String suffix,
        BiFunction<String, WoodBlockSet, BaseSeedling> seedling, BiFunction<Block, Block, Item> seed) {
        super(name);

        log = new SmallLogBlock(halfWidth, faceHalfWidth, doSidesConnect).setBlockName(name + "_log")
            .setBlockTextureName(getAssetDirectory() + name + "_log");

        sapling = new SmallSaplingBlock(Viridium.MOD_ID + ":" + name, this).setBlockName(name + "_sapling")
            .setBlockTextureName(getAssetDirectory() + name + "_sapling");

        this.seedling = seedling.apply(Viridium.MOD_ID + ":" + name, this)
            .setSapling(sapling)
            .setBlockName(name + "_seedling")
            .setBlockTextureName(name);
        this.seed = seed.apply(this.seedling, Blocks.grass)
            .setUnlocalizedName(name + suffix)
            .setTextureName(name + suffix);

        leaves = new BaseLeaves().setItemDropped(this.seed)
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
        GameRegistry.registerBlock(this.seedling, name + "_seedling");
        GameRegistry.registerItem(this.seed, name + suffix);
    }

    public static SmallTreeBlockSet genericBlockSet(String name) {
        return new SmallTreeBlockSet(name, 4, 4, true, "_seed", BaseSeedling::new, BaseSeed::new);
    }

    public static SmallTreeBlockSet acornBlockSet(String name) {
        return new SmallTreeBlockSet(name, 4, 4, true, "_acorn", BaseSeedling::new, BaseSeed::new);
    }

    public static SmallTreeBlockSet coneBlockSet(String name) {
        return new SmallTreeBlockSet(name, 4, 4, true, "_cone", SmallConeSeedling::new, BaseSeed::new);
    }

    public static SmallTreeBlockSet fruitBlockSet(String name) {
        return new SmallTreeBlockSet(
            name,
            4,
            4,
            true,
            "_fruit",
            BaseSeedling::new,
            (seedling, soilblock) -> new TreeFruit(1, 1.0f, seedling, soilblock));
    }

    public static SmallTreeBlockSet nutBlockSet(String name) {
        return new SmallTreeBlockSet(
            name,
            4,
            4,
            true,
            "_nut",
            BaseSeedling::new,
            (seedling, soilblock) -> new TreeNut(1, 1.0f, seedling, soilblock));
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
