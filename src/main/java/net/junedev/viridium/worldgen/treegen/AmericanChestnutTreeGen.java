package net.junedev.viridium.worldgen.treegen;

import java.util.Random;

import net.junedev.viridium.blocks.sets.WoodBlockSet;
import net.minecraft.world.World;

public class AmericanChestnutTreeGen extends LargeDeciduousTreeGen {

    public AmericanChestnutTreeGen(boolean par1, WoodBlockSet blockSet, int baseTrunkHeight, int trunkHeightVariance,
        double trunkHeightPercent, int baseBranchCount, int maxExtraBranches, int leafCanopyWidth,
        int leafCanopyWidthVariance) {
        super(
            par1,
            blockSet,
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

    static int last = 0;

    @Override
    protected boolean checkTree(World world, Random rand, int x, int y, int z) {
        final int height = rand.nextInt(this.trunkHeightVariance) + this.baseTrunkHeight;
        final double radius = (this.leafCanopyWidth + rand.nextInt(this.leafCanopyWidthVariance)) / 2.0D;
        final int chunkCheck = (int) Math.ceil(radius) + 5;
        final int trunkPercent = (int) (height * this.trunkHeightPercent);

        // Make sure that a tree can grow on the soil
        if (!hasValidSoil(world, x, y, z, SaplingLayout.TWO_BY_TWO)) return false;

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
        if (!check2x2Trunk(x, y, z, trunkPercent + 3, this.trunkBlock, world, false)) return false;
        // Generate the branches
        if (!checkBranches(world, rand, x, y + trunkPercent, z, height - trunkPercent - 1, radius)) return false;

        return true;
    }

    @Override
    protected boolean generateTree(World world, Random rand, int x, int y, int z) {
        final int height = rand.nextInt(this.trunkHeightVariance) + this.baseTrunkHeight;
        final double radius = (this.leafCanopyWidth + rand.nextInt(this.leafCanopyWidthVariance)) / 2.0D;
        final int chunkCheck = (int) Math.ceil(radius) + 1;
        final int trunkPercent = (int) (height * this.trunkHeightPercent);

        // Make sure that a tree can grow on the soil
        if (!hasValidSoil(world, x, y, z, SaplingLayout.TWO_BY_TWO)) return false;

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
        if (place2x2Trunk(x, y, z, trunkPercent + 3, this.trunkBlock, world)) {
            // Generate the branches
            generateBranches2x2(world, rand, x, y + trunkPercent, z, height - trunkPercent - 1, radius, -4);
            place1x1Trunk(x, y + trunkPercent + 3, z, height - trunkPercent - 3, this.trunkBlock, world);

            return true;
        }

        return false;
    }

    public static long getLastSeed() {
        return lastSeed;
    }

}
