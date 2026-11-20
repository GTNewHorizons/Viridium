package net.junedev.viridium.client.renderers.utils;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
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
    public static long positionHash(int x, int z) {
        long hash = (long) x * 3129871L ^ (long) z * 116129781L;
        return hash * hash * 42317861L + hash * 11L;
    }

    public static float hash01(int x, int z, int salt) {
        int h = x * 73856093 ^ z * 19349663 ^ salt * 83492791;
        h ^= h >>> 13;
        h *= 0x5bd1e995;
        h ^= h >>> 15;
        return (h & 0xFFFFFF) / (float) 0x1000000;
    }

    public static double getRandomOffset(long hash, int shift, double range) {
        double normalized = ((hash >> shift) & 15L) / 15.0D; // 0..1
        return (normalized - 0.5D) * range; // -range/2..+range/2
    }

    public static double getRandomRotation(long hash) {
        return ((hash >>> 32) & 0xFFFFL) / 65536.0D * Math.PI / 2.0D;
    }

    public static float lerp(float a, float b, float t) {
        return a + (b - a) * t;
    }

    public static float smoothstep01(float t) {
        t = t < 0.0F ? 0.0F : (Math.min(t, 1.0F));
        return t * t * (3.0F - 2.0F * t);
    }

    public static float smoothstep(float edge0, float edge1, float x) {
        return smoothstep01((x - edge0) / (edge1 - edge0));
    }

    public static boolean renderXShapedPlant(IBlockAccess world, int x, int y, int z, Block block, IIcon icon,
        double halfSize, RenderBlocks renderer, double randomOffset) {
        long hash = RenderingUtils.positionHash(x, z);

        double offsetX = RenderingUtils.getRandomOffset(hash, 16, randomOffset);
        double offsetZ = RenderingUtils.getRandomOffset(hash, 24, randomOffset);

        double rotation = RenderingUtils.getRandomRotation(hash);

        Tessellator tessellator = Tessellator.instance;

        RenderingUtils.resetLightAndColor(world, tessellator, block, x, y, z);

        renderCrossedPlanes(x, y, z, icon, 0.5D + offsetX, 0.5D + offsetZ, halfSize, rotation);

        renderer.setRenderBoundsFromBlock(block);

        return true;
    }

    public static void renderCrossedPlanes(int x, int y, int z, IIcon icon, double centerX, double centerZ,
        double halfSize, double rotation) {

        halfSize *= 0.70710678;

        Tessellator tessellator = Tessellator.instance;
        double uMin = icon.getMinU();
        double uMax = icon.getMaxU();
        double vMin = icon.getMinV();
        double vMax = icon.getMaxV();
        double cos = Math.cos(rotation);
        double sin = Math.sin(rotation);

        // Rotation matrix
        // ( c -s )
        // ( s c )
        // Og positions relative to center (-h, -h) -> (h, h), (-h, h) -> (h, -h)
        double firstMinX = centerX - halfSize * cos + halfSize * sin;
        double firstMinZ = centerZ - halfSize * sin - halfSize * cos;
        double firstMaxX = centerX + halfSize * cos - halfSize * sin;
        double firstMaxZ = centerZ + halfSize * sin + halfSize * cos;

        RenderingUtils.addDoubleSidedQuad(
            tessellator,
            x + firstMinX,
            y,
            z + firstMinZ,
            uMin,
            vMax,
            x + firstMaxX,
            y,
            z + firstMaxZ,
            uMax,
            vMax,
            x + firstMaxX,
            y + 1,
            z + firstMaxZ,
            uMax,
            vMin,
            x + firstMinX,
            y + 1,
            z + firstMinZ,
            uMin,
            vMin);

        double secondMinX = centerX - halfSize * cos - halfSize * sin;
        double secondMinZ = centerZ - halfSize * sin + halfSize * cos;
        double secondMaxX = centerX + halfSize * cos + halfSize * sin;
        double secondMaxZ = centerZ + halfSize * sin - halfSize * cos;

        RenderingUtils.addDoubleSidedQuad(
            tessellator,
            x + secondMaxX,
            y,
            z + secondMaxZ,
            uMax,
            vMax,
            x + secondMinX,
            y,
            z + secondMinZ,
            uMin,
            vMax,
            x + secondMinX,
            y + 1,
            z + secondMinZ,
            uMin,
            vMin,
            x + secondMaxX,
            y + 1,
            z + secondMaxZ,
            uMax,
            vMin);
    }

    public static void renderOpeningPlanes(double centerX, double y, double centerZ, IIcon icon, double halfSize,
        double openingAngle, double minimumRadius, double rotation) {
        renderOpeningPlanes(centerX, y, centerZ, icon, halfSize, openingAngle, minimumRadius, rotation, 4);
    }

    public static void renderOpeningPlanes(double centerX, double y, double centerZ, IIcon icon, double halfSize,
        double openingAngle, double minimumRadius, double rotation, int leaveCount) {

        Tessellator tessellator = Tessellator.instance;
        double uMin = icon.getMinU();
        double uMax = icon.getMaxU();
        double vMin = icon.getMinV();
        double vMax = icon.getMaxV();

        double cos = Math.cos(rotation) * halfSize;
        double sin = Math.sin(rotation) * halfSize;

        // Angle above the ground: keep the frond length constant while tilting it.
        double length = halfSize * 2;
        // Normalized plane area
        double height = Math.sin(openingAngle) * length;
        double reach = Math.cos(openingAngle) * length;
        // Sine and Cosine taking into account their actual radius
        double innerOutwardCos = Math.cos(rotation) * minimumRadius;
        double innerOutwardSin = Math.sin(rotation) * minimumRadius;
        double outerOutwardCos = Math.cos(rotation) * (reach + minimumRadius);
        double outerOutwardSin = Math.sin(rotation) * (reach + minimumRadius);

        // Rotation matrix
        // ( c s )
        // ( -s c )

        double step = 2 * Math.PI / leaveCount;

        // Probably would have been better to directly do a matrix library or smt
        for (int i = 0; i < 5; i++) {
            double angle = step * i;
            double polarCos = Math.cos(angle);
            double polarSin = Math.sin(angle);

            addDoubleSidedQuad(
                tessellator,
                centerX + (-cos + innerOutwardSin) * polarCos + (sin + innerOutwardCos) * polarSin,
                y,
                centerZ - (-cos + innerOutwardSin) * polarSin + (sin + innerOutwardCos) * polarCos,
                uMin,
                vMax,
                centerX + (cos + innerOutwardSin) * polarCos + (-sin + innerOutwardCos) * polarSin,
                y,
                centerZ - (cos + innerOutwardSin) * polarSin + (-sin + innerOutwardCos) * polarCos,
                uMax,
                vMax,
                centerX + (cos + outerOutwardSin) * polarCos + (-sin + outerOutwardCos) * polarSin,
                y + height,
                centerZ - (cos + outerOutwardSin) * polarSin + (-sin + outerOutwardCos) * polarCos,
                uMax,
                vMin,
                centerX + (-cos + outerOutwardSin) * polarCos + (sin + outerOutwardCos) * polarSin,
                y + height,
                centerZ - (-cos + outerOutwardSin) * polarSin + (sin + outerOutwardCos) * polarCos,
                uMin,
                vMin);
        }
    }
}
