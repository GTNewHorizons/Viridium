package net.junedev.viridium.blocks.vanilike;

import java.util.List;
import java.util.Random;

import net.junedev.viridium.Viridium;
import net.junedev.viridium.blocks.sets.WoodBlockSet;
import net.junedev.viridium.worldgen.treegen.SaplingLayout;
import net.junedev.viridium.worldgen.treegen.TreeRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.util.ForgeDirection;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BaseSeedling extends BaseSaplingBlock {

    public Item treeSeeds;
    public Block treeSapling;

    public BaseSeedling(String treeId, WoodBlockSet blockSet) {
        super(treeId, blockSet);
        this.setTickRandomly(true);
        float f = 0.5F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
        this.setCreativeTab((CreativeTabs) null);
        this.setHardness(0.0F);
        this.setStepSound(soundTypeGrass);
        this.disableStats();
    }

    @Override
    protected boolean canPlaceBlockOn(Block ground) {
        return ground == Blocks.grass;
    }

    @Override
    public Block setBlockTextureName(String texName) {
        this.textureName = Viridium.MOD_ID + ":" + "ground/seeded_grass_decorator_healthy";
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
        List<SaplingLayout.Offset> offsets = match.layout()
            .getOffsets();

        // Check if any sapling of the layout is not above an invalid soil
        for (SaplingLayout.Offset offset : offsets) {
            if (!canBlockStay(world, match.originX() + offset.x(), y, match.originZ() + offset.z())) return;
        }

        WorldGenerator generator = TreeRegistry.createGenerator2(treeId, blockSet, match.layout(), true);

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

            SaplingLayout.Match match = layout.findMatch(x, y, z, this, world);

            if (match != null) return match;
        }
        // Unregistered layouts do not consume neighboring saplings.
        return new SaplingLayout.Match(SaplingLayout.SINGLE, x, z);
    }

    @Override
    public void updateTick(World worldIn, int x, int y, int z, Random random) {
        super.updateTick(worldIn, x, y, z, random);

        if (worldIn.getBlockLightValue(x, y + 1, z) >= 9) {
            int l = worldIn.getBlockMetadata(x, y, z);

            if (l < 3) {
                float f = this.func_149864_n(worldIn, x, y, z);

                if (random.nextInt((int) (25.0F / f) + 1) == 0) {
                    ++l;
                    worldIn.setBlockMetadataWithNotify(x, y, z, l, 2);
                }
            }
        }
    }

    public float func_149864_n(World world, int x, int y, int z) {
        float f = 1.0F;
        Block block = world.getBlock(x, y, z - 1);
        Block block1 = world.getBlock(x, y, z + 1);
        Block block2 = world.getBlock(x - 1, y, z);
        Block block3 = world.getBlock(x + 1, y, z);
        Block block4 = world.getBlock(x - 1, y, z - 1);
        Block block5 = world.getBlock(x + 1, y, z - 1);
        Block block6 = world.getBlock(x + 1, y, z + 1);
        Block block7 = world.getBlock(x - 1, y, z + 1);
        boolean flag = block2 == this || block3 == this;
        boolean flag1 = block == this || block1 == this;
        boolean flag2 = block4 == this || block5 == this || block6 == this || block7 == this;

        for (int l = x - 1; l <= x + 1; ++l) {
            for (int i1 = z - 1; i1 <= z + 1; ++i1) {
                float f1 = 0.0F;

                if (world.getBlock(l, y - 1, i1)
                    .canSustainPlant(world, l, y - 1, i1, ForgeDirection.UP, this)) {
                    f1 = 1.0F;

                    if (world.getBlock(l, y - 1, i1)
                        .isFertile(world, l, y - 1, i1)) {
                        f1 = 3.0F;
                    }
                }

                if (l != x || i1 != z) {
                    f1 /= 4.0F;
                }

                f += f1;
            }
        }

        if (flag2 || flag && flag1) {
            f /= 2.0F;
        }

        return f;
    }

    public void setTreeSeeds(Item treeSeeds) {
        this.treeSeeds = treeSeeds;
    }

    public Item getTreeSeeds() {
        return this.treeSeeds;
    }

    public Block setSapling(Block sapling) {
        this.treeSapling = sapling;
        return this;
    }

    public Block getSapling() {
        return this.treeSapling;
    }
}
