package net.junedev.viridium.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class CoconutPalmTreeGen extends LargeTreeWorldGenBase {

    public Block leafBlock;
    public Block trunkBlock;

    public int[] dirs;
    public int randX;
    public int randZ;

    public CoconutPalmTreeGen(boolean par1, Block leafBlock, Block trunkBlock) {
        super(par1);
        this.leafBlock = leafBlock;
        this.trunkBlock = trunkBlock;

        this.dirs = new int[] { -1, 0, 1 };
        this.randX = new Random().nextInt(dirs.length);
        this.randZ = new Random().nextInt(dirs.length);
        if (this.randX == 1 && this.randZ == 1) {
            this.randX = 2;
        }
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

    // Variables to control the generation
    public static final int baseTrunkHeight = 7; // The base height for trees
    public static final int trunkHeightVariance = 2; // The Max extra branches that a tree can have
    public static final int trunkCurveBaseHeight = 3;
    public static final int leafCanopyWidth = 5; // How many blocks will this tree cover

    static int last = 0;

    private boolean checkTree(World world, Random rand, int x, int y, int z) {
        final int height = rand.nextInt(trunkHeightVariance) + baseTrunkHeight;
        final double radius = leafCanopyWidth / 2.0D;
        final int chunkCheck = (int) Math.ceil(radius) + 5;

        // Make sure that a tree can grow on the soil (Currently dirt/sand)
        if (!(SoilTypeRegistry.isValidGeneric(world.getBlock(x, y - 1, z))
            || SoilTypeRegistry.isValidSand(world.getBlock(x, y - 1, z)))) return false;

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
        if (!check1x1Trunk(x, y, z, trunkCurveBaseHeight, this.trunkBlock, world)) return false;
        if (!checkCanopy(
            world,
            x,
            2 * this.dirs[this.randX],
            y + height - 2,
            z,
            2 * this.dirs[this.randZ],
            radius,
            height)) return false;

        return true;
    }

    private boolean generateTree(World world, Random rand, int x, int y, int z) {
        int variance = rand.nextInt(trunkHeightVariance);
        final int height = variance + baseTrunkHeight + trunkCurveBaseHeight;
        final double radius = leafCanopyWidth / 2.0D;
        final int chunkCheck = (int) Math.ceil(radius) + 1;

        // Make sure that a tree can grow on the soil
        if (!(SoilTypeRegistry.isValidGeneric(world.getBlock(x, y - 1, z))
            || SoilTypeRegistry.isValidSand(world.getBlock(x, y - 1, z)))) return false;

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
        if (placeTrunkBase(
            x,
            this.dirs[this.randX],
            y,
            z,
            this.dirs[this.randZ],
            trunkCurveBaseHeight,
            baseTrunkHeight,
            variance,
            this.trunkBlock,
            world)) {
            generateCanopy(
                world,
                rand,
                x,
                2 * (this.dirs[this.randX]),
                y + height - 2,
                z,
                2 * (this.dirs[this.randZ]),
                radius,
                height,
                this.leafBlock);

            return true;
        }

        return false;
    }

    public boolean checkCanopy(World world, double x, int offsetX, double y, double z, int offsetZ, double radius,
        int height) {
        int layers = 3;
        for (int y1 = (int) y, layer = 0; layer <= layers; layer++, y1++) {
            if (!checkCanopyLayer(world, x + offsetX, y1, z + offsetZ, radius * Math.cos((layer) / (height / 1.3))))
                return false;
        }

        return true;
    }

    public void generateCanopy(World world, Random rand, int x, int offsetX, int y, int z, int offsetZ, double radius,
        int height, Block leafBlock) {
        int layers = 3;
        for (int y1 = (int) y, layer = 0; layer <= layers; layer++, y1++) {
            switch (layer) {
                case 0:
                    setLeafBlock(world, (x + offsetX + 4), y1, (z + offsetZ), leafBlock);
                    setLeafBlock(world, (x + offsetX + 2), y1, (z + offsetZ + 1), leafBlock);
                    setLeafBlock(world, (x + offsetX + 1), y1, (z + offsetZ + 2), leafBlock);

                    setLeafBlock(world, (x + offsetX - 4), y1, (z + offsetZ), leafBlock);
                    setLeafBlock(world, (x + offsetX - 2), y1, (z + offsetZ + 1), leafBlock);
                    setLeafBlock(world, (x + offsetX - 1), y1, (z + offsetZ + 2), leafBlock);

                    setLeafBlock(world, (x + offsetX), y1, (z + offsetZ + 4), leafBlock);
                    setLeafBlock(world, (x + offsetX + 2), y1, (z + offsetZ - 1), leafBlock);
                    setLeafBlock(world, (x + offsetX + 1), y1, (z + offsetZ - 2), leafBlock);

                    setLeafBlock(world, (x + offsetX), y1, (z + offsetZ - 4), leafBlock);
                    setLeafBlock(world, (x + offsetX - 2), y1, (z + offsetZ - 1), leafBlock);
                    setLeafBlock(world, (x + offsetX - 1), y1, (z + offsetZ - 2), leafBlock);
                    continue;
                case 1:
                    generateCanopyLayer(
                        world,
                        rand,
                        (x + offsetX),
                        y1,
                        (z + offsetZ),
                        radius * Math.cos((layer) / (height / 1.3)),
                        leafBlock);
                    setLeafBlock(world, (x + offsetX + 3), y1, (z + offsetZ), leafBlock);
                    setLeafBlock(world, (x + offsetX + 4), y1, (z + offsetZ), leafBlock);

                    setLeafBlock(world, (x + offsetX - 3), y1, (z + offsetZ), leafBlock);
                    setLeafBlock(world, (x + offsetX - 4), y1, (z + offsetZ), leafBlock);

                    setLeafBlock(world, (x + offsetX), y1, (z + offsetZ + 3), leafBlock);
                    setLeafBlock(world, (x + offsetX), y1, (z + offsetZ + 4), leafBlock);

                    setLeafBlock(world, (x + offsetX), y1, (z + offsetZ - 3), leafBlock);
                    setLeafBlock(world, (x + offsetX), y1, (z + offsetZ - 4), leafBlock);
                    continue;
                case 2:
                    generateCanopyLayer(
                        world,
                        rand,
                        (x + offsetX),
                        y1,
                        (z + offsetZ),
                        (radius - 1) * Math.cos((layer) / (height / 1.3)),
                        leafBlock);
                    setLeafBlock(world, (x + offsetX), y1, (z + offsetZ), leafBlock);

                    setLeafBlock(world, (x + offsetX + 2), y1, (z + offsetZ), leafBlock);
                    setLeafBlock(world, (x + offsetX + 3), y1, (z + offsetZ), leafBlock);

                    setLeafBlock(world, (x + offsetX - 2), y1, (z + offsetZ), leafBlock);
                    setLeafBlock(world, (x + offsetX - 3), y1, (z + offsetZ), leafBlock);

                    setLeafBlock(world, (x + offsetX), y1, (z + offsetZ + 2), leafBlock);
                    setLeafBlock(world, (x + offsetX), y1, (z + offsetZ + 3), leafBlock);

                    setLeafBlock(world, (x + offsetX), y1, (z + offsetZ - 2), leafBlock);
                    setLeafBlock(world, (x + offsetX), y1, (z + offsetZ - 3), leafBlock);
                    continue;
                case 3:
                    setLeafBlock(world, (x + offsetX), y1, (z + offsetZ), leafBlock);
            }
        }
    }

    public boolean checkCanopyLayer(World world, double x, double y, double z, double radius) {
        double minDist = 1;
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
        Block leafBlock) {
        double minDist = 1;
        double maxDist = radius * radius;

        for (int z1 = (int) -radius; z1 < (radius + 1); z1++) {
            for (int x1 = (int) -radius; x1 < (radius + 1); x1++) {
                final Block block = world.getBlock((int) (x1 + x), (int) y, (int) (z1 + z));

                if ((((x1 * x1) + (z1 * z1)) <= maxDist) && (((x1 * x1) + (z1 * z1)) >= minDist)) {
                    if (block == null || block.canBeReplacedByLeaves(world, (int) (x1 + x), (int) y, (int) (z1 + z))) {
                        setLeafBlock(world, (int) (x1 + x), (int) y, (int) (z1 + z), leafBlock);
                    }
                }
            }
        }
    }

    public static long getLastSeed() {
        return lastSeed;
    }

    public boolean placeTrunkBase(int x, int offsetX, int y, int z, int offsetZ, int trunkBaseHeight,
        int trunkMainHeight, int trunkVariance, Block log, World world) {
        Block logBlock = Block.getBlockFromItem(log.getItem(world, x, y, z));

        // Place the trunk
        for (int y1 = y; y1 < (y + trunkBaseHeight + trunkMainHeight + trunkVariance); y1++) {
            if (y1 == y) { // First block at sapling coordinates
                setBlockAndNotifyAdequately(world, x, y1, z, logBlock, 1);
            } else if (y1 < y + trunkBaseHeight) { // Blocks up to the trunk base's height
                setBlockAndNotifyAdequately(world, x + offsetX, y1, z + offsetZ, logBlock, 1);
            } else { // The rest of them
                setBlockAndNotifyAdequately(world, x + (2 * offsetX), y1, z + (2 * offsetZ), logBlock, 1);
            }

        }

        return true;
    }

}
