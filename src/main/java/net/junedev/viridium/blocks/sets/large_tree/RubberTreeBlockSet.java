package net.junedev.viridium.blocks.sets.large_tree;

import java.util.List;

import net.junedev.viridium.blocks.vanilike.BaseFullLog;
import net.junedev.viridium.blocks.vanilike.BaseSeedling;
import net.junedev.viridium.items.BaseSeed;
import net.junedev.viridium.plants.SeedType;
import net.minecraft.block.Block;

import cpw.mods.fml.common.registry.GameRegistry;

public class RubberTreeBlockSet extends LargeTreeBlockSet {

    public final Block logExpended;
    public final Block logResinous;

    public RubberTreeBlockSet(String name) {
        super(name, SeedType.GENERIC, BaseSeedling::new, BaseSeed::new);

        logExpended = new BaseFullLog().setBlockName(name + "_log_expended")
            .setBlockTextureName(getAssetDirectory() + name + "_log_expended");
        logResinous = new BaseFullLog().setBlockName(name + "_log_resinous")
            .setBlockTextureName(getAssetDirectory() + name + "_log_resinous");

        GameRegistry.registerBlock(logExpended, name + "_log_expended");
        GameRegistry.registerBlock(logResinous, name + "_log_resinous");
    }

    @Override
    public List<Block> getBlocksWithItems() {
        List<Block> blocks = super.getBlocksWithItems();
        blocks.add(logExpended);
        blocks.add(logResinous);
        return blocks;
    }
}
