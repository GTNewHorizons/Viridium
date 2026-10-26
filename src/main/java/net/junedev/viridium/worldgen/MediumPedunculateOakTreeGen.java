package net.junedev.viridium.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class MediumPedunculateOakTreeGen extends LargeDeciduousTreeGen {

    public MediumPedunculateOakTreeGen(boolean par1, Block leafBlock, Block trunkBlock, int baseTrunkHeight,
        int trunkHeightVariance, double trunkHeightPercent, int baseBranchCount, int maxExtraBranches,
        int leafCanopyWidth, int leafCanopyWidthVariance) {
        super(
            par1,
            leafBlock,
            trunkBlock,
            baseTrunkHeight,
            trunkHeightVariance,
            trunkHeightPercent,
            baseBranchCount,
            maxExtraBranches,
            leafCanopyWidth,
            leafCanopyWidthVariance);
    }

    // Store the last seed that was used to generate a tree
    private static long lastSeed = 1234;

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        // Store the seed
        lastSeed = rand.nextLong();

        // Make sure the tree can generate
        if (!checkTree(world, new Random(lastSeed), x, y, z)) return false;

        return generateTree(world, new Random(lastSeed), x, y, z);
    }

    @Override
    protected boolean checkTree(World world, Random rand, int x, int y, int z) {
        final int height = rand.nextInt(trunkHeightVariance) + baseTrunkHeight;
        final double radius = (leafCanopyWidth + rand.nextInt(leafCanopyWidthVariance)) / 2.0D;
        final int chunkCheck = (int) Math.ceil(radius) + 5;
        final int trunkPercent = (int) (height * this.trunkHeightPercent);

        // Make sure that a tree can grow on the soil
        if (!SoilTypeRegistry.isValidGeneric(world.getBlock(x, y - 1, z))) return false;

        // make sure that we have room to grow the tree
        if (y >= 256 - height - 4) return false;

        // Make sure that the tree can fit in the world
        if (y < 1 || y + height + 4 > 256) return false;

        // Make sure that all the needed chunks are loaded
        if (!world.checkChunksExist(
            x - chunkCheck,
            y - chunkCheck,
            z - chunkCheck,
            x + chunkCheck,
            y + chunkCheck,
            z + chunkCheck)) return false;

        // Draw the main trunk
        if (!check2x2Trunk(x, y, z, trunkPercent, this.trunkBlock, world, false)) return false;
        // Generate the branches
        if (!checkBranches(world, rand, x, y + trunkPercent, z, height - trunkPercent - 2, radius)) return false;

        return true;
    }

    @Override
    protected boolean generateTree(World world, Random rand, int x, int y, int z) {
        final int height = rand.nextInt(trunkHeightVariance) + baseTrunkHeight;
        final double radius = (leafCanopyWidth + rand.nextInt(leafCanopyWidthVariance)) / 2.0D;
        final int chunkCheck = (int) Math.ceil(radius) + 1;
        final int trunkPercent = (int) (height * this.trunkHeightPercent);

        // Make sure that a tree can grow on the soil
        if (!SoilTypeRegistry.isValidGeneric(world.getBlock(x, y - 1, z))) return false;

        // make sure that we have room to grow the tree
        if (y >= 256 - height - 4) return false;

        // Make sure that the tree can fit in the world
        if (y < 1 || y + height + 4 > 256) return false;

        // Make sure that all the needed chunks are loaded
        if (!world.checkChunksExist(
            x - chunkCheck,
            y - chunkCheck,
            z - chunkCheck,
            x + chunkCheck,
            y + chunkCheck,
            z + chunkCheck)) return false;

        // Draw the main trunk
        if (place2x2Trunk(x, y, z, height - 2, this.trunkBlock, world)) {
            // Generate the branches
            generateBranches2x2(world, rand, x, y + trunkPercent, z, height - trunkPercent, radius, -1);

            return true;
        }

        return false;
    }

    public static long getLastSeed() {
        return lastSeed;
    }
}
