package net.junedev.viridium.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.junedev.viridium.Viridium;
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
    private final int size;

    @SideOnly(Side.CLIENT)
    private IIcon fullIcon;

    @SideOnly(Side.CLIENT)
    private IIcon[] croppedIcons;

    public TallPlantBlock(int size) {
        super(Material.grass);

        this.size = size;

        this.setHardness(0.0F);
        this.setCreativeTab(Viridium.VTab);
        this.setStepSound(soundTypeGrass);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, int x, int y, int z, EntityLivingBase placer, ItemStack itemIn) {
        for(int i=1; i<size; i++){
            worldIn.setBlock(x, y + i, z, this, i, 2);
        }
    }

    @Override
    public void onBlockPreDestroy(World worldIn, int x, int y, int z, int meta) {
        super.onBlockPreDestroy(worldIn, x, y, z, meta);
    }

    public boolean isTop(int meta){
        return meta == size - 1;
    }

    public boolean isBottom(int meta) {
        return meta == 0;
    }

    public boolean isMiddle(int meta){
        return !isTop(meta) && !isBottom(meta);
    }

    @Override
    public int getRenderType() {
        return 1; // X shaped plant
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

        setBlockTextureName(Viridium.MOD_ID + ":complex_plants/" + name);

        return this;
    }

    // Icon stuff
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        fullIcon = reg.registerIcon(getTextureName());

        croppedIcons = new IIcon[size];
        for(int i=0; i<size; i++){
            croppedIcons[i] = new CroppedIcon(fullIcon, i, 16 );
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return croppedIcons[meta];
    }
}
