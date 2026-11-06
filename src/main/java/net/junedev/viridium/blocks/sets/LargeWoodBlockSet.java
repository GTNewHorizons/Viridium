package net.junedev.viridium.blocks.sets;

import java.util.List;

import net.junedev.viridium.blocks.vanilike.BaseBark;
import net.junedev.viridium.blocks.vanilike.BaseFullLog;
import net.minecraft.block.Block;

import cpw.mods.fml.common.registry.GameRegistry;

public class LargeWoodBlockSet extends WoodBlockSet {

    public final Block log;
    public final Block stripped;
    public final Block bark;

    public LargeWoodBlockSet(String name) {
        super(name);

        log = new BaseFullLog().setBlockName(name + "_log")
            .setBlockTextureName(getAssetDirectory() + name + "_log");
        stripped = new BaseFullLog().setBlockName(name + "_stripped")
            .setBlockTextureName(getAssetDirectory() + name + "_stripped");

        // TODO: Change to custom block type?
        bark = new BaseBark().setBlockName(name + "_bark")
            .setBlockTextureName(getAssetDirectory() + name + "_log_side");

        // TODO: Deffer registry to method
        GameRegistry.registerBlock(log, name + "_log");
        GameRegistry.registerBlock(stripped, name + "_stripped");
        GameRegistry.registerBlock(bark, name + "_bark");
    }

    @Override
    public String getAssetDirectory() {
        return "large_trees/";
    }

    @Override
    public List<Block> getBlocksWithItems() {
        List<Block> blocks = super.getBlocksWithItems();
        blocks.add(log);
        blocks.add(stripped);
        blocks.add(bark);
        return blocks;
    }
}
