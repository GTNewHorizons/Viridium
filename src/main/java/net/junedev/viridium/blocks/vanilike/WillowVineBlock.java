package net.junedev.viridium.blocks.vanilike;

import net.junedev.viridium.Viridium;
import net.minecraft.block.BlockVine;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.IBlockAccess;

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

    @Override
    @SideOnly(Side.CLIENT)
    public int getBlockColor() {
        return 0xFFFFFF;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int metadata) {
        return 0xFFFFFF;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
        return 0xFFFFFF;
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
