package net.junedev.viridium.plants.forms;

import net.junedev.viridium.blocks.BushBlock;
import net.junedev.viridium.plants.PlantCategory;
import net.minecraft.block.Block;

public record BushForm() implements SingleBlockForm {

    @Override
    public PlantCategory category() {
        return PlantCategory.BUSH;
    }

    @Override
    public String textureFolder() {
        return "bushes/";
    }

    @Override
    public Block newBlock() {
        return new BushBlock();
    }
}
