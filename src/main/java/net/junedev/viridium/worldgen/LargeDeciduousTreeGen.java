package net.junedev.viridium.worldgen;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;

// Thank you ExtrabiomesXL for being CC
public class LargeDeciduousTreeGen extends LargeTreeWorldGenBase {

    public Block leafBlock;
    public Block trunkBlock;
    public int baseTrunkHeight; // Original default: 8
    public int trunkHeightVariance; // Original default: 4
    public double trunkHeightPercent; // Original default: 0.30D
    public int baseBranchCount; // Original default: 2
    public int maxExtraBranches; // Original default: 4
    public int leafCanopyWidth; // Original default: 8
    public int leafCanopyWidthVariance; // Original default: 6

    public LargeDeciduousTreeGen(boolean par1, Block leafBlock, Block trunkBlock, int baseTrunkHeight,
        int trunkHeightVariance, double trunkHeightPercent, int baseBranchCount, int maxExtraBranches,
        int leafCanopyWidth, int leafCanopyWidthVariance) {
        super(par1);
        this.leafBlock = leafBlock; // Primary leaf block
        this.trunkBlock = trunkBlock; // Primary trunk log
        this.baseTrunkHeight = baseTrunkHeight; // The base height for trees
        this.trunkHeightVariance = trunkHeightVariance; // The max extra height that a tree (via main trunk) can have
        this.trunkHeightPercent = trunkHeightPercent; // What percent of the total height the main trunk extends
        this.baseBranchCount = baseBranchCount; // The total number of branches + leaf blobs on the tree
        this.maxExtraBranches = maxExtraBranches; // The max additional branches that can occur on the tree
        this.leafCanopyWidth = leafCanopyWidth; // How many blocks will this tree cover
        this.leafCanopyWidthVariance = leafCanopyWidthVariance; // How many extra blocks can the canopy extend
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

    static int last = 0;

    protected boolean checkTree(World world, Random rand, int x, int y, int z) {
        final int height = rand.nextInt(this.trunkHeightVariance) + this.baseTrunkHeight;
        final double radius = (this.leafCanopyWidth + rand.nextInt(this.leafCanopyWidthVariance)) / 2.0D;
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
        if (!check1x1Trunk(x, y, z, trunkPercent, this.trunkBlock, world)) return false;
        // Generate the branches
        if (!checkBranches(world, rand, x, y + trunkPercent, z, height - trunkPercent - 2, radius)) return false;

        return true;
    }

    protected boolean generateTree(World world, Random rand, int x, int y, int z) {
        final int height = rand.nextInt(this.trunkHeightVariance) + this.baseTrunkHeight;
        final double radius = (this.leafCanopyWidth + rand.nextInt(this.leafCanopyWidthVariance)) / 2.0D;
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
        if (place1x1Trunk(x, y, z, trunkPercent, this.trunkBlock, world)) {
            // Generate the branches
            generateBranches(world, rand, x, y + trunkPercent, z, height - trunkPercent - 2, radius);

            return true;
        }

        return false;
    }

    public boolean checkBranches(World world, Random rand, int x, int y, int z, int height, double radius) {
        int branchCount = this.baseBranchCount + rand.nextInt(this.maxExtraBranches);
        double curAngle = 0.0D;

        double[] average = { 0, 0, 0 };
        int[] start = { x, y, z };
        Queue<int[]> branches = new LinkedList<int[]>();

        // Generate the branches
        for (int i = 0; i < branchCount; i++) {
            // Get the branch radius and height
            double angle = (rand.nextInt(50) + 35) / 90.0D;
            double thisHeight = (height + 1) * Math.sin(angle) / 1.3;
            double thisRadius = radius * Math.cos(angle);

            // Get the branch rotation
            curAngle += (rand.nextInt(360 / branchCount) + (360 / branchCount)) / 90.0D;// + (360.0D/branchCount) /
                                                                                        // 180.0D ;

            int x1 = (int) ((thisRadius) * Math.cos(curAngle));
            int z1 = (int) ((thisRadius) * Math.sin(curAngle));

            // Add the average count
            average[0] += x1;
            average[1] += thisHeight;
            average[2] += z1;

            // Add to the branch list
            int[] node = new int[] { x1 + x, (int) thisHeight + y, z1 + z };

            // Add the branch end for leaf generation
            branches.add(node);

            // Generate the branch
            if (!checkBlockLine(start, node, this.trunkBlock, world)) return false;
        }

        // Place the branch tips
        Iterator<int[]> itt = branches.iterator();
        while (itt.hasNext()) {
            int[] cluster = itt.next();
            if (!checkLeafCluster(world, cluster[0], cluster[1], cluster[2], 2, 1)) return false;
        }

        // Calculate the center position
        average[0] /= branchCount;
        average[1] = (branchCount / average[1]) + 2.3D;
        average[2] /= branchCount;

        // Generate the canopy
        if (!checkCanopy(world, average[0] + x, y, average[2] + z, radius, height)) return false;

        return true;

    }

    public void generateBranches(World world, Random rand, int x, int y, int z, int height, double radius) {
        int branchCount = this.baseBranchCount + rand.nextInt(this.maxExtraBranches);
        double curAngle = 0.0D;

        double[] average = { 0, 0, 0 };
        int[] start = { x, y, z };
        Queue<int[]> branches = new LinkedList<int[]>();

        // Generate the branches
        for (int i = 0; i < branchCount; i++) {
            // Get the branch radius and height
            double angle = (rand.nextInt(50) + 35) / 90.0D;
            double thisHeight = (height + 1) * Math.sin(angle) / 1.3;
            double thisRadius = radius * Math.cos(angle);

            // Get the branch rotation
            curAngle += (rand.nextInt(360 / branchCount) + (360 / branchCount)) / 90.0D;// + (360.0D/branchCount) /
                                                                                        // 180.0D ;

            int x1 = (int) ((thisRadius) * Math.cos(curAngle));
            int z1 = (int) ((thisRadius) * Math.sin(curAngle));

            // Add the average count
            average[0] += x1;
            average[1] += thisHeight;
            average[2] += z1;

            // Add to the branch list
            int[] node = new int[] { x1 + x, (int) thisHeight + y, z1 + z };

            // Add the branch end for leaf generation
            branches.add(node);

            // Generate the branch
            placeBlockLine(start, node, this.trunkBlock, world);
        }

        // Place the branch tips
        Iterator<int[]> itt = branches.iterator();
        while (itt.hasNext()) {
            int[] cluster = itt.next();
            generateLeafCluster(world, cluster[0], cluster[1], cluster[2], 2, 1, this.leafBlock);
        }

        // Calculate the center position
        average[0] /= branchCount;
        average[1] = (branchCount / average[1]) + 2.3D;
        average[2] /= branchCount;

        // Generate the canopy
        // generateCanopy(world, rand, average[0] + x, y, average[2] + z, radius, height, this.leafBlock);
        generateCanopy(world, rand, x, y, z, radius, height, this.leafBlock);

        // Generate the center cone
        generateVerticalCone(world, x, y, z, height - 1, .75, 2, this.leafBlock);

    }

    public void checkBranches2x2(World world, Random rand, int x, int y, int z, int height, double radius,
        int startOffset) {
        int branchCount = this.baseBranchCount + rand.nextInt(this.maxExtraBranches);
        final int trunkPercent = (int) (height * this.trunkHeightPercent);
        double curAngle = 0.0D;
        int width = (int) (2 * radius);
        width = (width % 2 == 1) ? width + 1 : width;
        int offset = width / 2;

        double[] average = { 0, 0, 0 };
        int branchStart = trunkPercent + startOffset;
        int maxBranchHeight = height - trunkPercent - 3;
        int[] start = { x, y, z };
        int[] end = { 0, 0, 0 };
        Queue<int[]> branches = new LinkedList<int[]>();

        // Generate the branches
        for (int branch = 0; branch < branchCount; branch++) {
            double angle = (rand.nextInt(50) + 35) / 90.0D;
            // double thisHeight = (height + 1) * Math.sin(angle) / 1.3;
            double thisRadius = radius * Math.cos(angle);

            // Get the branch rotation
            curAngle += (rand.nextInt(360 / branchCount) + (360 / branchCount)) / 90.0D;

            // The end position
            end[0] = rand.nextInt(width + 1) - offset + x;
            end[1] = rand.nextInt(maxBranchHeight) + branchStart + y;
            end[2] = rand.nextInt(width + 1) - offset + z;

            // Max of tree height
            // Min of branch start
            start[1] = Math
                .max(branchStart + y, Math.min(height, rand.nextInt(Math.max(end[1] - branchStart - y, 1)) + y));

            if (end[0] > x && end[2] > z) {
                start[0] = x + 1;
                start[2] = z + 1;
            } else if (end[0] > x) {
                start[0] = x + 1;
                start[2] = z;
            } else if (end[2] > z) {
                start[0] = x;
                start[2] = z + 1;
            } else {
                start[0] = x;
                start[2] = z;
            }

            // Check the branch
            checkBlockLine(start, end, this.trunkBlock, world);

            int x1 = (int) ((thisRadius) * Math.cos(curAngle));
            int z1 = (int) ((thisRadius) * Math.sin(curAngle));

            int[] node = new int[] { end[0] + x1, end[1], end[2] + z1 };

            // Add the branch end for leaf generation
            branches.add(node);
        }

        // Place the branch tips
        Iterator<int[]> itt = branches.iterator();
        while (itt.hasNext()) {
            int[] cluster = itt.next();
            checkLeafCluster(world, cluster[0], cluster[1], cluster[2], 2, 1);
        }

        // Calculate the center position
        average[0] /= branchCount;
        average[1] = (branchCount / average[1]) + 2.3D;
        average[2] /= branchCount;

        // Generate the canopy
        // generateCanopy(world, rand, average[0] + x, y, average[2] + z, radius, height, this.leafBlock);
        checkCanopy(world, x, y, z, radius, height);

    }

    public void generateBranches2x2(World world, Random rand, int x, int y, int z, int height, double radius,
        int startOffset) {
        int branchCount = this.baseBranchCount + rand.nextInt(this.maxExtraBranches);
        final int trunkPercent = (int) (height * this.trunkHeightPercent);
        double curAngle = 0.0D;
        int width = ((int) (2 * radius) % 2 == 1) ? (int) (2 * radius) + 1 : (int) (2 * radius);
        int offset = width / 2;

        double[] average = { 0, 0, 0 };
        int branchStart = trunkPercent + startOffset;
        int maxBranchHeight = height - trunkPercent - 1;
        int[] start = { x, y, z };
        int[] end = { 0, 0, 0 };
        Queue<int[]> branches = new LinkedList<int[]>();

        // Generate the branches
        for (int branch = 0; branch < branchCount; branch++) {
            curAngle += (rand.nextInt(360 / branchCount) + (360 / branchCount)) / 90.0D;
            double angle = (rand.nextInt(50) + 35) / 90.0D;
            double thisRadius = radius * Math.cos(angle);

            // The end position
            int x1 = (int) ((thisRadius) * Math.cos(curAngle));
            int z1 = (int) ((thisRadius) * Math.sin(curAngle));

            // end[0] = rand.nextInt(width) - offset + x;
            end[0] = x1 + x;
            end[1] = rand.nextInt(maxBranchHeight) + branchStart + y;
            // end[2] = rand.nextInt(width) - offset + z;
            end[2] = z1 + z;

            // Max of tree height
            // Min of branch start
            start[1] = Math
                .max(branchStart + y, Math.min(height, rand.nextInt(Math.max(end[1] - branchStart - y, 1)) + y));

            if (end[0] > x && end[2] > z) {
                start[0] = x + 1;
                start[2] = z + 1;
            } else if (end[0] > x) {
                start[0] = x + 1;
                start[2] = z;
            } else if (end[2] > z) {
                start[0] = x;
                start[2] = z + 1;
            } else if (end[2] < z) {
                start[0] = x;
                start[2] = z + 1;
            } else {
                start[0] = x;
                start[2] = z;
            }

            // Place the branch
            placeBlockLine(start, end, this.trunkBlock, world);

            int[] node = new int[] { end[0], end[1], end[2] };

            // Add the branch end for leaf generation
            branches.add(node);
        }

        // Place the branch tips
        Iterator<int[]> itt = branches.iterator();
        while (itt.hasNext()) {
            int[] cluster = itt.next();
            generateLeafCluster(world, cluster[0], cluster[1], cluster[2], 2, 1, this.leafBlock);
        }

        // Calculate the center position
        average[0] /= branchCount;
        average[1] = (branchCount / average[1]) + 2.3D;
        average[2] /= branchCount;

        // Generate the canopy
        // generateCanopy(world, rand, average[0] + x, y, average[2] + z, radius, height, this.leafBlock);
        generateCanopy(world, rand, x, y, z, radius, height, this.leafBlock);

        // Generate the center cone
        generateVerticalCone(world, x, y, z, height - 1, .75, 2, this.leafBlock);

    }

    public boolean checkCanopy(World world, double x, double y, double z, double radius, int height) {
        int layers = height + 2;
        for (int y1 = (int) y, layer = 0; layer < layers; layer++, y1++) {
            if (!checkCanopyLayer(world, x, y1, z, radius * Math.cos((layer) / (height / 1.3)))) return false;
        }

        return true;
    }

    public void generateCanopy(World world, Random rand, double x, double y, double z, double radius, int height,
        Block leafBlock) {
        int layers = height + 2;
        for (int y1 = (int) y, layer = 0; layer < layers; layer++, y1++) {
            if (layer < 2) {
                generateCanopyLayer(
                    world,
                    rand,
                    x,
                    y1,
                    z,
                    radius * Math.cos((layer) / (height / 1.3)),
                    2 + (layer * 5),
                    leafBlock);
            } else {
                generateCanopyLayer(
                    world,
                    rand,
                    x,
                    y1,
                    z,
                    radius * Math.cos((layer) / (height / 1.3)),
                    1000,
                    leafBlock);
            }
        }
    }

    public void generateVerticalCone(World world, int x, int y, int z, int height, double r1, double r2,
        Block leafBlock) {
        double ratio = (r2 - r1) / (height - 1);

        for (int offset = 0; offset < height; offset++) {
            placeLeavesCircle(x, y + offset, z, (ratio * offset) + r1, leafBlock, world);
        }
    }

    public boolean checkCanopyLayer(World world, double x, double y, double z, double radius) {
        double minDist = (radius - 2 > 0) ? ((radius - 2) * (radius - 2)) : -1;
        double maxDist = radius * radius;

        for (int z1 = (int) -radius; z1 < (radius + 1); z1++) {
            for (int x1 = (int) -radius; x1 < (radius + 1); x1++) {
                Block block;

                try {
                    block = world.getBlock((int) (x1 + x), (int) y, (int) (z1 + z));
                } catch (Exception e) {
                    return false;
                }

                if ((((x1 * x1) + (z1 * z1)) <= maxDist) && (((x1 * x1) + (z1 * z1)) >= minDist)) {
                    if (block != null && !block.isReplaceable(world, (int) (x1 + x), (int) y, (int) (z1 + z))) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public void generateCanopyLayer(World world, Random rand, double x, double y, double z, double radius,
        int skipChance, Block leafBlock) {
        double minDist = (radius - 2 > 0) ? ((radius - 2) * (radius - 2)) : -1;
        double maxDist = radius * radius;

        for (int z1 = (int) -radius; z1 < (radius + 1); z1++) {
            for (int x1 = (int) -radius; x1 < (radius + 1); x1++) {
                final Block block = world.getBlock((int) (x1 + x), (int) y, (int) (z1 + z));

                if ((((x1 * x1) + (z1 * z1)) <= maxDist) && (((x1 * x1) + (z1 * z1)) >= minDist)) {
                    if (block == null || block.canBeReplacedByLeaves(world, (int) (x1 + x), (int) y, (int) (z1 + z))) {
                        if (rand.nextInt(skipChance) != 0) {
                            setLeafBlock(world, (int) (x1 + x), (int) y, (int) (z1 + z), leafBlock);
                        }
                    }
                }
            }
        }
    }

    public static long getLastSeed() {
        return lastSeed;
    }

}
