package net.junedev.viridium.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.junedev.viridium.Viridium;
import net.junedev.viridium.client.renderers.ViriRenderIds;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

public class BushBlock extends Block {

    private static final double PLATFORM_HEIGHT = 0.75;

    @SideOnly(Side.CLIENT)
    private IIcon branchIcon;

    @SideOnly(Side.CLIENT)
    private  IIcon leaveIcon;

    public  BushBlock() {
        super(Material.leaves);

        this.setHardness(1.0F); // Hardness of vanilla logs?
        this.setCreativeTab(Viridium.VTab);
        this.setStepSound(soundTypeWood);
    }

    @Override
    public int getRenderType() {
        return ViriRenderIds.bushBlockRenderId;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World worldIn, int x, int y, int z) {
        return null;
    }

    @Override
    public void addCollisionBoxesToList(World worldIn, int x, int y, int z, AxisAlignedBB mask, List<AxisAlignedBB> list, Entity collider) {
        AxisAlignedBB platform = AxisAlignedBB.getBoundingBox(
            x, y, z,
            x + 1.0, y + PLATFORM_HEIGHT, z + 1.0
        );

        boolean isEntintyAbove = collider != null
            && collider.boundingBox.minY >= platform.maxY - 0.01
            && collider.motionY <= 0.0;

        if(isEntintyAbove && mask.intersectsWith(platform)) {
            list.add(platform);
        }
    }

    @Override
    public Block setBlockName(String name) {
        super.setBlockName(name);

        setBlockTextureName(Viridium.MOD_ID + ":bushes/" + name);

        return (Block) this;
    }

    // Icon stuff
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        branchIcon = reg.registerIcon(getTextureName() + "_branches");
        leaveIcon = reg.registerIcon(getTextureName()+"_leaves");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return getBranchIcon();
    }

    @SideOnly(Side.CLIENT)
    public IIcon getBranchIcon() {
        return branchIcon;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getLeaveIcon() {
        return leaveIcon;
    }
}
