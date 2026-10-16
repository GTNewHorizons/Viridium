package net.junedev.viridium.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.junedev.viridium.Viridium;
import net.junedev.viridium.client.renderers.ViriRenderIds;
import net.junedev.viridium.client.textures.CroppedIcon;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.Random;

public class FernBlock extends Block {

    @SideOnly(Side.CLIENT)
    private int halfSize = 8;
    @SideOnly(Side.CLIENT)
    private double openingAngle = 60;

    public FernBlock() {
        super(Material.grass);

        this.setHardness(0.0F);
        this.setCreativeTab(Viridium.VTab);
        this.setStepSound(soundTypeGrass);
    }

    public FernBlock(int halfSize, double openingAngle){
        this();

        this.halfSize = halfSize;
        this.openingAngle = openingAngle;
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
    public int getHalfSize(){
        return halfSize;
    }

    @SideOnly(Side.CLIENT)
    public double getOpeningAngle(){
        return openingAngle;
    }
}
