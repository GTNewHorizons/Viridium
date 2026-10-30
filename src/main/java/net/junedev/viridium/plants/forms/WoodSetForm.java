package net.junedev.viridium.plants.forms;

import java.util.List;
import java.util.function.Function;

import net.junedev.viridium.blocks.sets.WoodBlockSet;
import net.junedev.viridium.plants.PlantCategory;
import net.junedev.viridium.plants.PlantForm;
import net.minecraft.block.Block;

// A plant made of a whole wood block set (trees, driftwood). The block set constructor registers its blocks.
public record WoodSetForm<S extends WoodBlockSet> (PlantCategory category, Function<String, S> blockSetFactory)
    implements PlantForm<S> {

    @Override
    public S createBlocks(String registryName) {
        return blockSetFactory.apply(registryName);
    }

    @Override
    public List<Block> getBlocksWithItems(S blockSet) {
        return blockSet.getBlocksWithItems();
    }
}
