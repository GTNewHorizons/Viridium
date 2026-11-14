package net.junedev.viridium.worldgen.treegen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;

/** Shared treegen for species with blocks but no dedicated generator yet. */
public final class SimpleSpeciesTreeGen extends LargeTreeWorldGenBase {

    public enum Shape {
        BROADLEAF,
        CONIFER,
        BAMBOO
    }

    private final Block log;
    private final Block leaves;
    private final int minHeight;
    private final int extraHeight;
    private final Shape shape;

    public SimpleSpeciesTreeGen(boolean notify, Block log, Block leaves, int minHeight, int extraHeight, Shape shape) {
        super(notify);
        this.log = log;
        this.leaves = leaves;
        this.minHeight = minHeight;
        this.extraHeight = extraHeight;
        this.shape = shape;
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        int height = minHeight + random.nextInt(extraHeight + 1);
        int radius = shape == Shape.BAMBOO ? 1 : 3;
        if (y < 1 || y + height + 1 >= world.getHeight()
            || !world.checkChunksExist(x - radius, y - 1, z - radius, x + radius, y + height + 1, z + radius)
            || !hasValidSoil(world, x, y, z, SaplingLayout.SINGLE)) return false;

        // Check all trunk and canopy positions before placing any blocks.
        for (int dy = 0; dy <= height; dy++) {
            int layerRadius = canopyRadius(dy, height);
            for (int dx = -layerRadius; dx <= layerRadius; dx++) {
                for (int dz = -layerRadius; dz <= layerRadius; dz++) {
                    if (!canReplace(world, x + dx, y + dy, z + dz)) return false;
                }
            }
        }
        for (int dy = 0; dy <= height; dy++) {
            int layerRadius = canopyRadius(dy, height);
            if (layerRadius == 0) continue;
            for (int dx = -layerRadius; dx <= layerRadius; dx++) {
                for (int dz = -layerRadius; dz <= layerRadius; dz++) {
                    if (dx == 0 && dz == 0 && dy < height) continue;
                    if (dx * dx + dz * dz > layerRadius * layerRadius + 1) continue;
                    setBlockAndNotifyAdequately(world, x + dx, y + dy, z + dz, leaves, 0);
                }
            }
        }
        for (int dy = 0; dy < height; dy++) {
            setBlockAndNotifyAdequately(world, x, y + dy, z, log, 0);
        }
        return true;
    }

    private int canopyRadius(int dy, int height) {
        if (shape == Shape.BAMBOO) return dy >= height - 2 ? 1 : 0;
        if (shape == Shape.CONIFER) {
            if (dy < height / 3) return 0;
            return Math.min(3, 1 + (height - dy) / 3);
        }
        if (dy < height - 3) return 0;
        return dy == height ? 1 : 2;
    }

    private boolean canReplace(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        return block.isAir(world, x, y, z) || block.isLeaves(world, x, y, z) || block.isReplaceable(world, x, y, z);
    }
}
