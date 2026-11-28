package net.junedev.viridium.blocks.vanilike;

import java.util.List;
import java.util.Random;

import net.junedev.viridium.blocks.sets.WoodBlockSet;
import net.junedev.viridium.worldgen.treegen.SaplingLayout;
import net.junedev.viridium.worldgen.treegen.TreeRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LargeConeSeedling extends BaseSeedling {

    public LargeConeSeedling(String treeId, WoodBlockSet blockSet) {
        super(treeId, blockSet);
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

        WorldGenerator generator = TreeRegistry.createLargeGenerator(treeId, blockSet, match.layout(), true);

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
}
