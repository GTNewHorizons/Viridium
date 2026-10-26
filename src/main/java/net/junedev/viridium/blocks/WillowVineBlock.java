package net.junedev.viridium.blocks;

import net.junedev.viridium.Viridium;
import net.minecraft.block.BlockVine;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WillowVineBlock extends BlockVine {

    @SideOnly(Side.CLIENT)
    private IIcon normalIcon;

    public WillowVineBlock() {
        super();
        this.setTickRandomly(true);
        this.setStepSound(soundTypeGrass);
        this.setCreativeTab(Viridium.VTab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        normalIcon = register.registerIcon(getTextureName());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return this.getNormalIcon();
    }

    // Setters
    @Override
    public BlockVine setBlockName(String name) {
        super.setBlockName(name);
        setBlockTextureName(Viridium.MOD_ID + ":large_trees/" + name);
        return this;
    }

    // Getters
    public IIcon getNormalIcon() {
        return normalIcon;
    }
}
