package net.junedev.viridium.client.renderers.world.sky;

import net.minecraft.world.World;

public final class SkyBiomeBlender {
    private static final int CELL = 32;
    private static final long REFRESH_NANOS = 1_000_000_000L;

    private final SkyProfile blended = new SkyProfile();
    private final SkyProfile rowA = new SkyProfile();
    private final SkyProfile rowB = new SkyProfile();

    private final SkyProfile[] nodes = new SkyProfile[4];

    private World cachedWorld;
    private int cachedCellX = Integer.MIN_VALUE;
    private int cachedCellZ = Integer.MIN_VALUE;
    private long lastRefresh;

    public SkyProfile blend(World world, double x, double z) {
        int blockX = (int) Math.floor(x);
        int blockZ = (int) Math.floor(z);
        int cellX = Math.floorDiv(blockX, CELL);
        int cellZ = Math.floorDiv(blockZ, CELL);

        long now = System.nanoTime();
        if (world != cachedWorld || cellX != cachedCellX || cellZ != cachedCellZ
            || now - lastRefresh > REFRESH_NANOS) {

            SkyProfile here = SkyProfileHandler.get(world.getBiomeGenForCoords(blockX, blockZ).biomeName);

            nodes[0] = profileAt(world, cellX * CELL, cellZ * CELL, here);
            nodes[1] = profileAt(world, (cellX + 1) * CELL, cellZ * CELL, here);
            nodes[2] = profileAt(world, cellX * CELL, (cellZ + 1) * CELL, here);
            nodes[3] = profileAt(world, (cellX + 1) * CELL, (cellZ + 1) * CELL, here);

            cachedWorld = world;
            cachedCellX = cellX;
            cachedCellZ = cellZ;
            lastRefresh = now;
        }

        float fx = smooth((float) ((x - (double) cellX * CELL) / CELL));
        float fz = smooth((float) ((z - (double) cellZ * CELL) / CELL));

        rowA.copyFrom(nodes[0]);
        rowA.lerpTo(nodes[1], fx);
        rowB.copyFrom(nodes[2]);
        rowB.lerpTo(nodes[3], fx);

        blended.copyFrom(rowA);
        blended.lerpTo(rowB, fz);
        return blended;
    }

    private static SkyProfile profileAt(World world, int blockX, int blockZ, SkyProfile fallback) {
        if (!world.blockExists(blockX, 0, blockZ)) {
            return fallback;
        }
        return SkyProfileHandler.get(world.getBiomeGenForCoords(blockX, blockZ).biomeName);
    }

    private static float smooth(float t) {
        return t * t * (3.0F - 2.0F * t);
    }
}
