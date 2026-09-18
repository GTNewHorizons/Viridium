package net.junedev.viridium.blocks;

import java.util.ArrayList;

import net.junedev.viridium.Viridium;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BaseLeaves extends BlockLeaves {

    @SideOnly(Side.CLIENT)
    private IIcon fancyIcon;

    @SideOnly(Side.CLIENT)
    private IIcon fastIcon;

    public BaseLeaves() {
        super();
        this.setHardness(0.2F);
        this.setStepSound(soundTypeGrass);
        this.setCreativeTab(Viridium.VTab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        fancyIcon = register.registerIcon(getTextureName());
        fastIcon = register.registerIcon(getTextureName() + "_fast");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (!this.isOpaqueCube()) {
            return this.getFancyIcon();
        } else {
            System.out.println("kill me now!!!");
            return this.getFastIcon();
        }
    }

    @Override
    public String[] func_150125_e() {
        return new String[0];
    }

    @Override
    public boolean isOpaqueCube() {
        return Blocks.leaves.isOpaqueCube();
    }

    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
        return false;
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
        return null;
    }

    @Override
    public BlockLeaves setBlockTextureName(String textureName) {
        super.setBlockTextureName(Viridium.MOD_ID + ":" + textureName);
        return this;
    }

    // Getters
    public IIcon getFancyIcon() {
        return fancyIcon;
    }

    public IIcon getFastIcon() {
        return fastIcon;
    }
}
