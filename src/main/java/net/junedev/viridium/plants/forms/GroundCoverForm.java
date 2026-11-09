package net.junedev.viridium.plants.forms;

import net.junedev.viridium.blocks.GroundPlaneBlock;
import net.junedev.viridium.plants.PlantCategory;
import net.minecraft.block.Block;

public record GroundCoverForm() implements SingleBlockForm {

    @Override
    public PlantCategory category() {
        return PlantCategory.GROUND_COVER;
    }

    @Override
    public String textureFolder() {
        return "ground/";
    }

    @Override
    public Block newBlock() {
        return new GroundPlaneBlock();
    }
}
