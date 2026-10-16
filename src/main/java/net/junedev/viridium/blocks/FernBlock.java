package net.junedev.viridium.blocks;

import net.junedev.viridium.Viridium;
import net.junedev.viridium.client.renderers.ViriRenderIds;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FernBlock extends Block {

    @SideOnly(Side.CLIENT)
    private int halfSize = 8;
    @SideOnly(Side.CLIENT)
    private double openingAngle = 60;
    @SideOnly(Side.CLIENT)
    private double minimumRadius = 0.0D;

    public FernBlock() {
        super(Material.grass);

        this.setHardness(0.0F);
        this.setCreativeTab(Viridium.VTab);
        this.setStepSound(soundTypeGrass);
    }

    public FernBlock(int halfSize, double openingAngle, double minimumRadius) {
        this();

        this.halfSize = halfSize;
        this.openingAngle = openingAngle;
        this.minimumRadius = minimumRadius;
    }

    @Override
    public int getRenderType() {
        return ViriRenderIds.fernBlockRenderId;
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
    public Block setBlockName(String name) {
        super.setBlockName(name);

        setBlockTextureName(Viridium.MOD_ID + ":ferns/" + name);

        return this;
    }

    @SideOnly(Side.CLIENT)
    public static long getPositionHash(int x, int z) {
        long hash = (long) x * 3129871L ^ (long) z * 116129781L;
        return hash * hash * 42317861L + hash * 11L;
    }

    @SideOnly(Side.CLIENT)
    public static double getRandomOffset(long hash, int shift, double range) {
        double normalized = ((hash >> shift) & 15L) / 15.0D; // 0..1
        return (normalized - 0.5D) * range; // -range/2..+range/2
    }

    @SideOnly(Side.CLIENT)
    public static double getRandomRotation(long hash) {
        return ((hash >>> 32) & 0xFFFFL) / 65536.0D * Math.PI / 2.0D;
    }

    @SideOnly(Side.CLIENT)
    public int getHalfSize() {
        return halfSize;
    }

    @SideOnly(Side.CLIENT)
    public double getOpeningAngle() {
        return openingAngle;
    }

    @SideOnly(Side.CLIENT)
    public double getMinimumRadius() {
        return minimumRadius;
    }

}
