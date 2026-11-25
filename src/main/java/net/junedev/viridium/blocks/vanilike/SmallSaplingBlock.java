package net.junedev.viridium.blocks.vanilike;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.junedev.viridium.Viridium;
import net.junedev.viridium.blocks.IBlockScientificName;
import net.junedev.viridium.blocks.sets.WoodBlockSet;
import net.junedev.viridium.items.ScientificNameRegistry;
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

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class SmallSaplingBlock extends BaseSaplingBlock {

    protected final String treeId;
    protected final WoodBlockSet blockSet;
    private final String leafVariant;

    public SmallSaplingBlock(String treeId, WoodBlockSet blockSet) {
        this(treeId, blockSet, "yellow");
    }

    public SmallSaplingBlock(String treeId, WoodBlockSet blockSet, String leafVariant) {
        super(treeId, blockSet, leafVariant);
        this.treeId = Objects.requireNonNull(treeId, "treeId");
        this.blockSet = Objects.requireNonNull(blockSet, "blockSet");
        this.leafVariant = Objects.requireNonNull(leafVariant, "leafVariant");
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

        WorldGenerator generator = TreeRegistry.createSmallGenerator(treeId, blockSet, match.layout(), true);

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
}
