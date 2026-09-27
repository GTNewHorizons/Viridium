package net.junedev.viridium.blocks;

import net.junedev.viridium.Viridium;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BasePlanks extends Block {

    public BasePlanks() {
        super(Material.wood);
        this.setHardness(2.0F);
        this.setCreativeTab(Viridium.VTab);
        this.setStepSound(soundTypeWood);
    }

    @Override
    public Block setBlockTextureName(String texName) {
        this.textureName = Viridium.MOD_ID + ":" + texName;
        return this;
    }
}
