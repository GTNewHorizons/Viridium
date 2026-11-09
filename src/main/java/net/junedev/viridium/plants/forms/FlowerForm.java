package net.junedev.viridium.plants.forms;

import net.junedev.viridium.blocks.BaseFlora;
import net.junedev.viridium.plants.PlantCategory;
import net.minecraft.block.Block;

public record FlowerForm() implements SingleBlockForm {

    @Override
    public PlantCategory category() {
        return PlantCategory.FLOWER;
    }

    @Override
    public String textureFolder() {
        return "flowers/";
    }

    @Override
    public Block newBlock() {
        return new BaseFlora();
    }
}
