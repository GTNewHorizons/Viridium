package net.junedev.viridium.blocks.vanilike;

import java.util.List;
import java.util.Objects;
import java.util.Random;

import net.junedev.viridium.Viridium;
import net.junedev.viridium.blocks.sets.WoodBlockSet;
import net.junedev.viridium.worldgen.treegen.SaplingLayout;
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
        SaplingLayout.Match match = findLayout(world, x, y, z);
        WorldGenerator generator = TreeRegistry.createGenerator(treeId, blockSet, match.layout(), true);
        List<SaplingLayout.Offset> offsets = match.layout()
            .getOffsets();
        // We store the blocks data and remove them for the generation check
        int[] metadata = new int[offsets.size()];
        for (int i = 0; i < offsets.size(); i++) {
            SaplingLayout.Offset offset = offsets.get(i);
            metadata[i] = world.getBlockMetadata(match.originX() + offset.x(), y, match.originZ() + offset.z());
        }
        for (SaplingLayout.Offset offset : offsets) {
            world.setBlock(match.originX() + offset.x(), y, match.originZ() + offset.z(), Blocks.air, 0, 4);
        }
        if (!generator.generate(world, rand, match.originX(), y, match.originZ())) {
            // Restore them if the tree was not placed
            for (int i = 0; i < offsets.size(); i++) {
                SaplingLayout.Offset offset = offsets.get(i);
                world.setBlock(match.originX() + offset.x(), y, match.originZ() + offset.z(), this, metadata[i], 3);
            }
        }
    }

    private SaplingLayout.Match findLayout(World world, int x, int y, int z) {
        for (SaplingLayout layout : SaplingLayout.largestFirst()) {
            if (!TreeRegistry.hasLayout(treeId, layout)) continue;

            SaplingLayout.Match match = layout.findMatch(
                x,
                z,
                (saplingX, saplingZ) -> world.blockExists(saplingX, y, saplingZ)
                    && world.getBlock(saplingX, y, saplingZ) == this);

            if (match != null) return match;
        }
        // Unregistered layouts do not consume neighboring saplings.
        return new SaplingLayout.Match(SaplingLayout.SINGLE, x, z);
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
