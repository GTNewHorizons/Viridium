package net.junedev.viridium.blocks;

import net.junedev.viridium.Viridium;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BaseFenceGate extends BlockFenceGate {

    Block sourceBlock;

    public BaseFenceGate(Block sourceBlock) {
        super();

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
    public IIcon getIcon(int side, int meta) {
        return sourceBlock.getIcon(side, meta);
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        // Source block already registers the icons
    }
}
