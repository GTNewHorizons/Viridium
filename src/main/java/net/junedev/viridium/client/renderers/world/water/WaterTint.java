package net.junedev.viridium.client.renderers.world.water;

import static net.junedev.viridium.client.renderers.utils.RenderingUtils.lerp;
import static net.junedev.viridium.client.renderers.utils.RenderingUtils.smoothstep01;

import net.junedev.viridium.client.renderers.world.water.profiles.WaterProfile;
import net.junedev.viridium.client.renderers.world.water.profiles.WaterProfileHandler;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;

/** Depth-dependent water tint and shore brightening. Called during chunk meshing (worker threads!). */
public final class WaterTint {

    private static final int DEPTH_SMOOTH_RADIUS = 2;
    public static int MAX_DEPTH = 8;
    public static boolean DEBUG_SHOW_DEPTH = false;

    public WaterTint() {}

    public static int apply(int original, IBlockAccess world, int x, int y, int z) {
        WaterProfile currentProfile = WaterProfileHandler.at(world, x, y, z);
        int range = Math.max(2, Math.min(MAX_DEPTH, (int) currentProfile.depthRange));

        // Depth
        boolean isSurface = world.getBlock(x, y + 1, z)
            .getMaterial() != Material.water;
        float avg = isSurface ? smoothedDepth(world, x, y, z, range) : columnDepth(world, x, y, z, range);
        float t = smoothstep01((avg - 1.0F) / (range - 1));

        // Shore
        float shore = 0.0F;
        if (isSurface && currentProfile.shoreRange > 0) {
            int dist = distanceToShore(world, x, y, z, currentProfile.shoreRange);
            if (dist > 0) {
                float near = 1.0F - (dist - 1) / (float) currentProfile.shoreRange;
                shore = currentProfile.shoreBrightness * near * near * (1.0F - t);
            }
        }

        float tr = lerp(lerp(currentProfile.shallowR, currentProfile.deepR, t), 1.0F, shore);
        float tg = lerp(lerp(currentProfile.shallowG, currentProfile.deepG, t), 1.0F, shore);
        float tb = lerp(lerp(currentProfile.shallowB, currentProfile.deepB, t), 1.0F, shore);

        float r = channel(original, 16) * tr;
        float g = channel(original, 8) * tg;
        float b = channel(original, 0) * tb;

        if (DEBUG_SHOW_DEPTH) {
            // shallow = green, deep = blue, shore = white
            float dr = shore;
            float dg = lerp(1.0F, 0.2F, t) + shore;
            float db = lerp(0.2F, 1.0F, t) + shore;
            return (to255(dr) << 16) | (to255(dg) << 8) | to255(db);
        }

        return (to255(r) << 16) | (to255(g) << 8) | to255(b);
    }

    /** One color channel (0..1) from a packed 0xRRGGBB value. */
    private static float channel(int rgb, int shift) {
        return ((rgb >> shift) & 0xFF) / 255.0F;
    }

    private static int to255(float v) {
        int i = (int) (v * 255.0F + 0.5F);
        return i < 0 ? 0 : (Math.min(i, 255));
    }

    private static int distanceToShore(IBlockAccess world, int x, int y, int z, int maxRange) {
        for (int radius = 1; radius <= maxRange; radius++) {
            // walk along the four edges of the ring with radius r
            for (int i = -radius; i <= radius; i++) {
                if (isShore(world, x + i, y, z - radius) || isShore(world, x + i, y, z + radius)
                    || isShore(world, x - radius, y, z + i)
                    || isShore(world, x + radius, y, z + i)) {
                    return radius;
                }
            }
        }
        return 0;
    }

    private static boolean isShore(IBlockAccess world, int x, int y, int z) {
        return world.getBlock(x, y, z)
            .isOpaqueCube();
    }

    private static float smoothedDepth(IBlockAccess world, int x, int y, int z, int range) {
        float sum = 0.0F;
        float weights = 0.0F;
        int radius = DEPTH_SMOOTH_RADIUS;
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                float weight = radius + 1 - Math.max(Math.abs(dx), Math.abs(dz));
                sum += weight * columnDepth(world, x + dx, y, z + dz, range);
                weights += weight;
            }
        }
        return sum / weights;
    }

    private static int columnDepth(IBlockAccess world, int x, int y, int z, int maxDepth) {
        int depth = 0;
        while (depth < maxDepth && world.getBlock(x, y - depth, z)
            .getMaterial() == Material.water) {
            depth++;
        }
        return depth;
    }
}
