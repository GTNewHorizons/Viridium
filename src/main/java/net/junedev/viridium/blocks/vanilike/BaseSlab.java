package net.junedev.viridium.blocks.vanilike;

import java.util.Random;

import net.junedev.viridium.Viridium;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BaseSlab extends BlockSlab {

    private final Block sourceBlock;
    private final BaseSlab singleSlab;

    public BaseSlab(Block sourceBlock) {
        this(sourceBlock, null);
    }

    /** Creates the double variant linked to an existing single slab. */
    public BaseSlab(BaseSlab singleSlab) {
        this(singleSlab.sourceBlock, singleSlab);
        if (singleSlab.field_150004_a) {
            throw new IllegalArgumentException("The source slab must be a single slab");
        }
    }

    private BaseSlab(Block fullBlock, BaseSlab singleSlab) {
        super(singleSlab != null, fullBlock.getMaterial());
        this.sourceBlock = fullBlock;
        this.singleSlab = singleSlab == null ? this : singleSlab;
        this.setStepSound(fullBlock.stepSound);
        this.slipperiness = fullBlock.slipperiness;
        this.useNeighborBrightness = !this.field_150004_a;

        String harvestTool = fullBlock.getHarvestTool(0);
        if (harvestTool != null) {
            this.setHarvestLevel(harvestTool, fullBlock.getHarvestLevel(0));
        }

        if (!this.field_150004_a) {
            this.setCreativeTab(Viridium.VTab);
        }
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

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int metadata) {
        // Slab metadata describes placement, not a variant of the source block.
        return sourceBlock.getIcon(side, 0);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister register) {
        // Source block already registers the icons
    }

    @Override
    public Item getItemDropped(int metadata, Random random, int fortune) {
        return Item.getItemFromBlock(singleSlab);
    }

    @Override
    public int damageDropped(int metadata) {
        return 0;
    }

    @Override
    protected ItemStack createStackedBlock(int metadata) {
        // Silk Touch must also return single slabs when harvesting the double variant.
        return new ItemStack(singleSlab, this.field_150004_a ? 2 : 1, 0);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Item getItem(World world, int x, int y, int z) {
        return Item.getItemFromBlock(singleSlab);
    }

    @Override
    public int getDamageValue(World world, int x, int y, int z) {
        return 0;
    }

    @Override
    public String func_150002_b(int metadata) {
        return singleSlab.getUnlocalizedName();
    }
}
