package net.junedev.viridium.worldgen.treegen;

import java.util.EnumSet;
import java.util.Random;

import net.junedev.viridium.blocks.sets.WoodBlockSet;
import net.junedev.viridium.blocks.sets.large_tree.LargeTreeBlockSet;
import net.junedev.viridium.blocks.sets.large_tree.LargeTreeBlockSetAcorn;
import net.junedev.viridium.blocks.sets.large_tree.LargeTreeBlockSetCone;
import net.junedev.viridium.blocks.sets.large_tree.LargeTreeBlockSetFruit;
import net.junedev.viridium.blocks.sets.large_tree.LargeTreeBlockSetNut;
import net.junedev.viridium.worldgen.SoilType;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class SeedlingTreeGen extends LargeTreeWorldGenBase {

    public Block sapling;

    public SeedlingTreeGen(boolean par1, WoodBlockSet blockSet) {
        super(par1);
        setSoils(EnumSet.of(SoilType.GENERIC, SoilType.SAND));

        switch (blockSet) {
            case LargeTreeBlockSet tree -> this.sapling = tree.sapling;
            case LargeTreeBlockSetCone tree -> this.sapling = tree.sapling;
            case LargeTreeBlockSetAcorn tree -> this.sapling = tree.sapling;
            case LargeTreeBlockSetFruit tree -> this.sapling = tree.sapling;
            case LargeTreeBlockSetNut tree -> this.sapling = tree.sapling;
            case null, default -> throw new IllegalArgumentException("Tree generation requires a tree block set");
        }
    }

    private static long lastSeed = 1234;

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        // Store the seed
        lastSeed = rand.nextLong();

        return generateSeedling(world, new Random(lastSeed), x, y, z);
    }

    private boolean generateSeedling(World world, Random rand, int x, int y, int z) {
        final double radius = 1.0D;
        final int chunkCheck = (int) Math.ceil(radius) + 1;

        // Make sure that a tree can grow on the soil
        if (!hasValidSoil(world, x, y, z, SaplingLayout.SINGLE)) return false;

        // make sure that we have room to grow the tree
        if (y >= 256 - 1) return false;

        // Make sure that the tree can fit in the world
        if (y < 1) return false;

        // Make sure that all the needed chunks are loaded
        if (!world.checkChunksExist(
            x - chunkCheck,
            y - chunkCheck,
            z - chunkCheck,
            x + chunkCheck,
            y + chunkCheck,
            z + chunkCheck)) return false;

        // Replace the seedling with a "fully grown" sapling
        setBlockAndNotifyAdequately(world, x, y, z, this.sapling, 1);
        return true;

    }
}
