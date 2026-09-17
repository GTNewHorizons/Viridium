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

    @SideOnly(Side.CLIENT)
    private IIcon fullIcon;

    @SideOnly(Side.CLIENT)
    private IIcon[] croppedIcons;

    public TallPlantBlock(int verticalBlockSize, int pixelWidth) {
        super(Material.grass);

        this.verticalBlockSize = verticalBlockSize;
        this.pixelWidth = pixelWidth;

        this.setHardness(0.0F);
        this.setCreativeTab(Viridium.VTab);
        this.setStepSound(soundTypeGrass);
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
            croppedIcons[i] = new CroppedIcon(fullIcon, i, getPixelWidth() , 16);
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
    public static long positionHash(int x, int z) {
        final long M1 = 0x5bd1e995;
        final long M2 = 0x1b873593;

        long h = x * M1;
        h ^= h >>> 15;
        h *= M2;

        h ^= z * M2;
        h ^= h >>> 13;
        h *= M1;

        h ^= h >>> 16;
        h *= 0x85ebca6bL;
        h ^= h >>> 13;
        h *= 0xc2b2ae35L;
        h ^= h >>> 16;

        return h;
    }

    public static double offset(long hash, int shift, double range) {
        double normalized = ((hash >> shift) & 15L) / 15.0D; // 0..1
        return (normalized - 0.5D) * range;                  // -range/2..+range/2
    }
}
