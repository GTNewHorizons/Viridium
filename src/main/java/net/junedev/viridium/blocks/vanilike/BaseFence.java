package net.junedev.viridium.blocks.vanilike;

import net.junedev.viridium.Viridium;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BaseFence extends BlockFence {

    private final Block sourceBlock;

    public BaseFence(Block sourceBlock) {
        super("", sourceBlock.getMaterial());

        this.sourceBlock = sourceBlock;
        this.setStepSound(sourceBlock.stepSound);
        this.slipperiness = sourceBlock.slipperiness;

        String harvestTool = sourceBlock.getHarvestTool(0);
        if (harvestTool != null) {
            this.setHarvestLevel(harvestTool, sourceBlock.getHarvestLevel(0));
        }

        this.setCreativeTab(Viridium.VTab);
    }

    @Override
    public float getBlockHardness(World world, int x, int y, int z) {
        return sourceBlock.getBlockHardness(world, x, y, z);
    }

    @Override
    public float getExplosionResistance(Entity exploder) {
        return sourceBlock.getExplosionResistance(exploder);
    }

    @Override
    public float getExplosionResistance(Entity exploder, World world, int x, int y, int z, double explosionX,
        double explosionY, double explosionZ) {
        return sourceBlock.getExplosionResistance(exploder, world, x, y, z, explosionX, explosionY, explosionZ);
    }

    @Override
    public boolean canPlaceTorchOnTop(World world, int x, int y, int z) {
        return true;
    }

    @Override
    public boolean canConnectFenceTo(IBlockAccess world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);

        // By default minecraft checks if the block is a vanilla fence gate
        if (block instanceof BaseFenceGate) return true;

        return super.canConnectFenceTo(world, x, y, z);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int metadata) {
        return sourceBlock.getIcon(side, 0);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister reg) {
        // Source block already registers the icons
    }
}
