package net.junedev.viridium.client.renderers.utils;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

public final class RenderingUtils {

    public static void addDoubleSidedQuad(Tessellator tessellator, double x1, double y1, double z1, double u1,
        double v1, double x2, double y2, double z2, double u2, double v2, double x3, double y3, double z3, double u3,
        double v3, double x4, double y4, double z4, double u4, double v4) {

        addSingleQuad(tessellator, x1, y1, z1, u1, v1, x2, y2, z2, u2, v2, x3, y3, z3, u3, v3, x4, y4, z4, u4, v4);

        addSingleQuad(tessellator, x4, y4, z4, u4, v4, x3, y3, z3, u3, v3, x2, y2, z2, u2, v2, x1, y1, z1, u1, v1);
    }

    public static void addSingleQuad(Tessellator tessellator, double x1, double y1, double z1, double u1, double v1,
        double x2, double y2, double z2, double u2, double v2, double x3, double y3, double z3, double u3, double v3,
        double x4, double y4, double z4, double u4, double v4) {

        tessellator.addVertexWithUV(x1, y1, z1, u1, v1);
        tessellator.addVertexWithUV(x2, y2, z2, u2, v2);
        tessellator.addVertexWithUV(x3, y3, z3, u3, v3);
        tessellator.addVertexWithUV(x4, y4, z4, u4, v4);
    }

    /// Needs to be done every time a block is placed, standard renderer techniques already implement it
    public static void resetLightAndColor(IBlockAccess world, Tessellator tessellator, Block block, int x, int y,
        int z) {
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));

        tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);
    }

    // Randomized hashed positions and rotations
    public static long getPositionHash(int x, int z) {
        long hash = (long) x * 3129871L ^ (long) z * 116129781L;
        return hash * hash * 42317861L + hash * 11L;
    }

    public static double getRandomOffset(long hash, int shift, double range) {
        double normalized = ((hash >> shift) & 15L) / 15.0D; // 0..1
        return (normalized - 0.5D) * range; // -range/2..+range/2
    }

    public static double getRandomRotation(long hash) {
        return ((hash >>> 32) & 0xFFFFL) / 65536.0D * Math.PI / 2.0D;
    }
}
