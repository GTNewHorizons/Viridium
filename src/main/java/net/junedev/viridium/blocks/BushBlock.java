package net.junedev.viridium.blocks;

import java.util.List;

import net.junedev.viridium.Viridium;
import net.junedev.viridium.client.renderers.ViriRenderIds;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BushBlock extends Block {

    private static final double PLATFORM_HEIGHT = 0.75;
    private double movementAttenuation = 0.4D;
    private float fallAttenuation = 0.35f;

    @SideOnly(Side.CLIENT)
    private IIcon branchIcon;

    @SideOnly(Side.CLIENT)
    private IIcon leaveIcon;

    @SideOnly(Side.CLIENT)
    private IIcon fastLeaveIcon;

    public BushBlock() {
        super(Material.leaves);

        this.setLightOpacity(1);
        this.setHardness(0.4F);
        this.setCreativeTab(Viridium.VTab);
        this.setStepSound(soundTypeGrass);
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, int x, int y, int z, Entity entityIn) {
        entityIn.motionX *= movementAttenuation;
        entityIn.motionZ *= movementAttenuation;
        if (entityIn.motionY < 0) {
            entityIn.motionY *= movementAttenuation;
        }
    }

    @Override
    public void onFallenUpon(World world, int x, int y, int z, Entity entity, float fallDistance) {
        // Adjust that value before EntityLivingBase reaches its normal fall code.
        entity.fallDistance *= fallAttenuation;
    }

    // Rendering
    @Override
    public int getRenderType() {
        return ViriRenderIds.bushBlockRenderId;
    }

    @Override
    public boolean isOpaqueCube() {
        return Blocks.leaves.isOpaqueCube();
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getAmbientOcclusionLightValue() {
        return 0.2F;
    }

    // Hitboxes
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World worldIn, int x, int y, int z) {
        return null;
    }

    @Override
    public void addCollisionBoxesToList(World worldIn, int x, int y, int z, AxisAlignedBB mask,
        List<AxisAlignedBB> list, Entity collider) {
        AxisAlignedBB platform = AxisAlignedBB.getBoundingBox(x, y, z, x + 1.0, y + PLATFORM_HEIGHT, z + 1.0);

        boolean isEntintyAbove = collider != null && collider.boundingBox.minY >= platform.maxY - 0.01
            && collider.motionY <= 0.0;

        if (isEntintyAbove && mask.intersectsWith(platform)) {
            list.add(platform);
        }
    }

    // Setters
    @Override
    public Block setBlockName(String name) {
        super.setBlockName(name);

        setBlockTextureName(Viridium.MOD_ID + ":bushes/" + name);

        return this;
    }

    public Block setMovementAttenuation(double movementAttenuation) {
        this.movementAttenuation = movementAttenuation;
        return this;
    }

    public Block setFallAttenuation(float fallAttenuation) {
        this.fallAttenuation = fallAttenuation;
        return this;
    }

    // Icon stuff
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        branchIcon = reg.registerIcon(getTextureName() + "_branches");
        leaveIcon = reg.registerIcon(getTextureName() + "_leaves");
        fastLeaveIcon = reg.registerIcon(getTextureName() + "_leaves_fast");
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

    @SideOnly(Side.CLIENT)
    public IIcon getFastLeaveIcon() {
        return fastLeaveIcon;
    }

}
