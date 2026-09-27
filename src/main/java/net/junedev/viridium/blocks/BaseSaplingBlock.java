package net.junedev.viridium.blocks;

import java.util.List;
import java.util.Random;

import net.junedev.viridium.Viridium;
import net.junedev.viridium.worldgen.AshTreeGen;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BaseSaplingBlock extends BlockSapling {

    public BaseSaplingBlock() {
        super();
        this.setCreativeTab(Viridium.VTab);
        this.setStepSound(soundTypeGrass);
    }

    @Override
    public Block setBlockTextureName(String texName) {
        this.textureName = Viridium.MOD_ID + ":" + texName;
        return this;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        this.blockIcon = register.registerIcon(this.textureName);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return this.blockIcon;
    }

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        list.add(new ItemStack(itemIn, 1, 0));
    }

    @Override
    public void func_149878_d(World world, int x, int y, int z, Random rand) {
        if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(world, rand, x, y, z)) return;
        String treeName = world.getBlock(x, y, z)
            .getUnlocalizedName();
        System.out.println(treeName);
        world.setBlock(x, y, z, Blocks.air);
        WorldGenerator obj = new AshTreeGen(true);

    }
}
