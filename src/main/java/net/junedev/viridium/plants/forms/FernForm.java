package net.junedev.viridium.plants.forms;

import net.junedev.viridium.blocks.FernBlock;
import net.junedev.viridium.plants.PlantCategory;
import net.minecraft.block.Block;

public record FernForm(int halfSize, double openingAngle, double minimumRadius) implements SingleBlockForm {

    @Override
    public PlantCategory category() {
        return PlantCategory.FERN;
    }

    @Override
    public String textureFolder() {
        return "ferns/";
    }

    @Override
    public Block newBlock() {
        return new FernBlock(halfSize, openingAngle, minimumRadius);
    }
}
