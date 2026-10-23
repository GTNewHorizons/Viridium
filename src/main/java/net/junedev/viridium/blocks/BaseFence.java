package net.junedev.viridium.blocks;

import net.junedev.viridium.Viridium;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BaseFence extends BlockFence {

    private final Block fullBlock;

    public BaseFence(Block fullBlock) {
        super(fullBlock.getItemIconName(), fullBlock.getMaterial());

        this.fullBlock = fullBlock;

        this.setCreativeTab(Viridium.VTab);
    }

    @Override
    public boolean canPlaceTorchOnTop(World world, int x, int y, int z) {
        return true;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister reg) {
        this.blockIcon = fullBlock.getIcon(0, 0);
    }
}
