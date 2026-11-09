package net.junedev.viridium.plants.forms;

import net.junedev.viridium.blocks.TallPlantBlock;
import net.junedev.viridium.plants.PlantCategory;
import net.minecraft.block.Block;

public record TallPlantForm(int verticalBlockSize, int pixelWidth, int textureX) implements SingleBlockForm {

    @Override
    public PlantCategory category() {
        return PlantCategory.TALL_PLANT;
    }

    @Override
    public String textureFolder() {
        return "flowers_tall/";
    }

    @Override
    public Block newBlock() {
        return new TallPlantBlock(verticalBlockSize, pixelWidth, textureX);
    }
}
