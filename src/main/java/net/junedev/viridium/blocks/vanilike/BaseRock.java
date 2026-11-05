package net.junedev.viridium.blocks.vanilike;

import net.junedev.viridium.Viridium;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BaseRock extends Block {

    public BaseRock() {
        super(Material.rock);
        this.setHardness(5.0F);
        this.setCreativeTab(Viridium.VTab);
        this.setStepSound(soundTypeStone);
    }

    @Override
    public Block setBlockTextureName(String texName) {
        this.textureName = Viridium.MOD_ID + ":" + texName;
        return this;
    }
}
