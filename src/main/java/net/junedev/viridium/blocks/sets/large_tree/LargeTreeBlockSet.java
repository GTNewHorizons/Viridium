package net.junedev.viridium.blocks.sets.large_tree;

import java.util.List;
import java.util.function.BiFunction;

import net.junedev.viridium.Viridium;
import net.junedev.viridium.blocks.sets.LargeWoodBlockSet;
import net.junedev.viridium.blocks.sets.WoodBlockSet;
import net.junedev.viridium.blocks.vanilike.BaseLeaves;
import net.junedev.viridium.blocks.vanilike.BaseSaplingBlock;
import net.junedev.viridium.blocks.vanilike.BaseSeedling;
import net.junedev.viridium.blocks.vanilike.LargeConeSeedling;
import net.junedev.viridium.items.BaseSeed;
import net.junedev.viridium.items.ItemBlockScientificName;
import net.junedev.viridium.items.TreeFruit;
import net.junedev.viridium.items.TreeNut;
import net.junedev.viridium.plants.SeedType;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

import cpw.mods.fml.common.registry.GameRegistry;

public class LargeTreeBlockSet extends LargeWoodBlockSet {

    public final Block leaves;
    public final Block sapling;
    public final Item seed;
    public final Block seedling;
    public final SeedType seedType;

    public LargeTreeBlockSet(String name, SeedType seedType, BiFunction<String, WoodBlockSet, BaseSeedling> seedling,
        BiFunction<Block, Block, Item> seed) {
        super(name);

        this.seedType = seedType;
        sapling = new BaseSaplingBlock(Viridium.MOD_ID + ":" + name, this).setBlockName(name + "_sapling")
            .setBlockTextureName(getAssetDirectory() + name + "_sapling");
        this.seedling = seedling.apply(Viridium.MOD_ID + ":" + name, this)
            .setSapling(sapling)
            .setBlockName(name + "_seedling")
            .setBlockTextureName(name);
        this.seed = seed.apply(this.seedling, Blocks.grass)
            .setUnlocalizedName(name + "_" + this.seedType.getName())
            .setTextureName(name + "_" + this.seedType.getName());
        leaves = new BaseLeaves().setItemDropped(this.seed)
            .setBlockName(name + "_leaves")
            .setBlockTextureName(getAssetDirectory() + name + "_leaves");

        GameRegistry.registerBlock(leaves, name + "_leaves");
        GameRegistry.registerBlock(sapling, ItemBlockScientificName.class, name + "_sapling");
        GameRegistry.registerItem(this.seed, name + "_seeds");
        GameRegistry.registerBlock(this.seedling, name + "_crop");
    }

    public static LargeTreeBlockSet genericBlockSet(String name) {
        return new LargeTreeBlockSet(name, SeedType.GENERIC, BaseSeedling::new, BaseSeed::new);
    }

    public static LargeTreeBlockSet acornBlockSet(String name) {
        return new LargeTreeBlockSet(name, SeedType.ACORN, BaseSeedling::new, BaseSeed::new);
    }

    public static LargeTreeBlockSet coneBlockSet(String name) {
        return new LargeTreeBlockSet(name, SeedType.CONE, LargeConeSeedling::new, BaseSeed::new);
    }

    public static LargeTreeBlockSet fruitBlockSet(String name) {
        return new LargeTreeBlockSet(
            name,
            SeedType.FRUIT,
            BaseSeedling::new,
            (seedling, soilblock) -> new TreeFruit(1, 1.0f, seedling, soilblock));
    }

    public static LargeTreeBlockSet nutBlockSet(String name) {
        return new LargeTreeBlockSet(
            name,
            SeedType.NUT,
            BaseSeedling::new,
            (seedling, soilblock) -> new TreeNut(1, 1.0f, seedling, soilblock));
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
