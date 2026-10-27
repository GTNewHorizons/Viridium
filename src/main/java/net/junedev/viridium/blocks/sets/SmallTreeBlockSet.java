package net.junedev.viridium.blocks.sets;

import net.junedev.viridium.blocks.SmallLogBlock;
import net.junedev.viridium.blocks.vanilike.BaseBark;
import net.junedev.viridium.blocks.vanilike.BaseLeaves;
import net.junedev.viridium.blocks.vanilike.BaseSaplingBlock;
import net.minecraft.block.Block;

import cpw.mods.fml.common.registry.GameRegistry;

public class SmallTreeBlockSet extends WoodBlockSet {

    public final Block log;
    public final Block bark;
    public final Block leaves;
    public final Block sapling;

    public SmallTreeBlockSet(String name) {
        this(name, 4, 4, true);
    }

    public SmallTreeBlockSet(String name, int halfWidth, int faceHalfWidth, boolean doSidesConnect) {
        super(name);

        log = new SmallLogBlock(halfWidth, faceHalfWidth, doSidesConnect).setBlockName(name + "_log")
            .setBlockTextureName(getAssetDirectory() + name + "_log");
        leaves = new BaseLeaves().setBlockName(name + "_leaves")
            .setBlockTextureName(getAssetDirectory() + name + "_leaves");

        sapling = new BaseSaplingBlock(name, this).setBlockName(name + "_sapling")
            .setBlockTextureName(getAssetDirectory() + name + "_sapling");

        // TODO: Change to custom block type?
        bark = new BaseBark().setBlockName(name + "_bark")
            .setBlockTextureName(getAssetDirectory() + name + "_bark");

        // TODO: Deffer registry to method
        GameRegistry.registerBlock(log, name + "_log");
        GameRegistry.registerBlock(leaves, name + "_leaves");
        GameRegistry.registerBlock(sapling, name + "_sapling");
        GameRegistry.registerBlock(bark, name + "_bark");

    }

    @Override
    public String getAssetDirectory() {
        return "small_trees/";
    }
}
