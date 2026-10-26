package net.junedev.viridium.blocks.vanilike;

import java.util.List;
import java.util.Objects;
import java.util.Random;

import net.junedev.viridium.Viridium;
import net.junedev.viridium.blocks.sets.WoodBlockSet;
import net.junedev.viridium.worldgen.treegen.TreeRegistry;
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

    private final String treeId;
    private final WoodBlockSet blockSet;
    private final String leafVariant;

    public BaseSaplingBlock(String treeId, WoodBlockSet blockSet) {
        this(treeId, blockSet, "yellow");
    }

    public BaseSaplingBlock(String treeId, WoodBlockSet blockSet, String leafVariant) {
        super();
        this.treeId = Objects.requireNonNull(treeId, "treeId");
        this.blockSet = Objects.requireNonNull(blockSet, "blockSet");
        this.leafVariant = Objects.requireNonNull(leafVariant, "leafVariant");
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
        WorldGenerator generator = TreeRegistry.createGenerator(treeId, blockSet);
        int metadata = world.getBlockMetadata(x, y, z);
        world.setBlock(x, y, z, Blocks.air);
        if (!generator.generate(world, rand, x, y, z)) {
            world.setBlock(x, y, z, this, metadata, 3);
        }
    }

    @Override
    protected boolean canPlaceBlockOn(Block ground) {
        return super.canPlaceBlockOn(ground) || (TreeRegistry.growsOnSand(treeId) && ground == Blocks.sand);
    }

    // Getters

    public String getTreeId() {
        return treeId;
    }

    public WoodBlockSet getBlockSet() {
        return blockSet;
    }
}
