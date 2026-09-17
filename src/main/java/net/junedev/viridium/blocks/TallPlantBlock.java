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
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class TallPlantBlock extends Block {
    private final int verticalBlockSize;
    private final int pixelWidth;
    private final int textureX;

    @SideOnly(Side.CLIENT)
    private IIcon fullIcon;

    @SideOnly(Side.CLIENT)
    private IIcon[] croppedIcons;

    public TallPlantBlock(int verticalBlockSize, int pixelWidth, int textureX) {
        super(Material.grass);

        this.verticalBlockSize = verticalBlockSize;
        this.pixelWidth = pixelWidth;
        this.textureX = textureX;

        this.setHardness(0.0F);
        this.setCreativeTab(Viridium.VTab);
        this.setStepSound(soundTypeGrass);
    }

    public TallPlantBlock(int verticalBlockSize, int pixelWidth) {
        this(verticalBlockSize, pixelWidth, 0);
    }

    public TallPlantBlock(int verticalBlockSize) {
        this(verticalBlockSize, 16);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, int x, int y, int z, EntityLivingBase placer, ItemStack itemIn) {
        for(int i = 1; i< verticalBlockSize; i++){
            worldIn.setBlock(x, y + i, z, this, i, 2);
        }
    }

    @Override
    public void onBlockPreDestroy(World worldIn, int x, int y, int z, int meta) {
        super.onBlockPreDestroy(worldIn, x, y, z, meta);
    }

    public boolean isTop(int meta){
        return meta == verticalBlockSize - 1;
    }

    public boolean isBottom(int meta) {
        return meta == 0;
    }

    public boolean isMiddle(int meta){
        return !isTop(meta) && !isBottom(meta);
    }

    @Override
    public int getRenderType() {
        return ViriRenderIds.tallPlantBlockRenderId;
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
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World worldIn, int x, int y, int z) {

        double halfWidth = getPixelWidth() / 32D;
        return AxisAlignedBB.getBoundingBox(
            x + 0.5D - halfWidth, y, z + 0.5D - halfWidth,
            x + 0.5D + halfWidth, y + 1, z + 0.5D + halfWidth);
    }

    @Override
    public Block setBlockName(String name) {
        super.setBlockName(name);

        setBlockTextureName(Viridium.MOD_ID + ":complex_plants/" + name);

        return this;
    }

    // Icon stuff
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        fullIcon = reg.registerIcon(getTextureName());

        croppedIcons = new IIcon[verticalBlockSize];
        for(int i = 0; i< verticalBlockSize; i++){
            croppedIcons[i] = new CroppedIcon(fullIcon, i, textureX, getPixelWidth(), 16);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return croppedIcons[meta];
    }

    public int getPixelWidth() {
        return pixelWidth;
    }

    @SideOnly(Side.CLIENT)
    public static long getPositionHash(int x, int z) {
        long hash = (long) x * 3129871L ^ (long) z * 116129781L;
        return hash * hash * 42317861L + hash * 11L;
    }

    @SideOnly(Side.CLIENT)
    public static double getRandomOffset(long hash, int shift, double range) {
        double normalized = ((hash >> shift) & 15L) / 15.0D; // 0..1
        return (normalized - 0.5D) * range;                  // -range/2..+range/2
    }

    @SideOnly(Side.CLIENT)
    public static double getRandomRotation(long hash) {
        return ((hash >>> 32) & 0xFFFFL) / 65536.0D * Math.PI / 2.0D;
    }
}
