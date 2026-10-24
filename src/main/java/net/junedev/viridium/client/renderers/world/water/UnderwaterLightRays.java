package net.junedev.viridium.client.renderers.world.water;

import static net.junedev.viridium.client.renderers.utils.RenderingUtils.hash01;
import static net.junedev.viridium.client.renderers.utils.RenderingUtils.smoothstep;

import net.junedev.viridium.client.renderers.world.designer_tools.RayLook;
import net.junedev.viridium.client.renderers.world.water.profiles.WaterProfile;
import net.junedev.viridium.client.renderers.world.water.profiles.WaterProfiles;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public final class UnderwaterLightRays {

    public static int cell = 6;
    public static int cellRadius = 3;

    // Maximum length of a shaft. A shaft is shortened where the light hits a solid block (see clippedLength).
    public static float shaftLength = 18.0F;
    public static float shaftStartAbove = 0.0F;
    public static float fadeDepth = 20.0F;

    // Soft end of a shaft that hit something solid
    public static float endFadeLength = 3.0F; // blocks over which a shaft fades out before it ends
    public static float endMargin = 1.0F; // a shaft reaches this far past the blocking block (the ground hides the
                                          // rest)

    // Brightness ALONG the shaft: rowT = position (0 = top, 1 = bottom), rowAlpha = brightness at that row.
    public static float[] rowT = { 0.0F, 0.12F, 0.3F, 0.55F, 0.8F, 1.0F };
    public static float[] rowAlpha = { 0.0F, 0.45F, 0.55F, 0.4F, 0.18F, 0.0F };

    // Brightness ACROSS the shaft: colX = position (-1 = left edge, 0 = center, 1 = right edge),
    public static float[] colX = { -1.0F, -0.75F, -0.5F, -0.25F, 0.0F, 0.25F, 0.5F, 0.75F, 1.0F };
    public static float[] colAlpha = { 0.0F, 0.10F, 0.36F, 0.78F, 1.0F, 0.78F, 0.36F, 0.10F, 0.0F };

    // Hiding the shafts where the flat camera-facing sprite would become obvious
    public static float nearFadeStart = 1.0F;
    public static float nearFadeEnd = 4.0F;

    // Glow at shaft entry
    public static double glowOffset = 0.03;
    public static float[] glowGrid = { -1.0F, -0.5F, 0.0F, 0.5F, 1.0F };

    public static int maxSurfaceScan = 32;
    public static double surfaceOffset = 0.11;

    // Test value: with 1.1 the sun ray is checked for every shaft (the sun height is never above 1).
    // Later something like 0.6, or a per-cell cache.
    private static final float SHADOW_CHECK_BELOW = 1.1F;
    private static final double SUN_RAY_MAX_DISTANCE = 64.0;

    private final UnderwaterSun sun = new UnderwaterSun();

    // Rows of the shaft currently being drawn: distance from the top in blocks, and brightness at that row.
    // Room for rowT.length + 2 rows (rowT should have fewer than 30 entries).
    private final float[] rowDistance = new float[32];
    private final float[] rowBrightness = new float[32];

    private boolean wasTexture, wasLighting, wasCull, wasBlend, wasAlphaTest;

    public static void register() {
        MinecraftForge.EVENT_BUS.register(new UnderwaterLightRays());
    }

    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        World world = mc.theWorld;
        EntityLivingBase viewer = mc.renderViewEntity;
        if (world == null || viewer == null) {
            return;
        }
        float partialTicks = event.partialTicks;

        Block atEye = ActiveRenderInfo.getBlockAtEntityViewpoint(world, viewer, partialTicks);
        if (atEye.getMaterial() != Material.water) {
            return;
        }

        double camX = RenderManager.renderPosX;
        double camY = RenderManager.renderPosY;
        double camZ = RenderManager.renderPosZ;
        int camBlockX = MathHelper.floor_double(camX);
        int camBlockY = MathHelper.floor_double(camY);
        int camBlockZ = MathHelper.floor_double(camZ);

        int surfaceBlock = findSurface(world, camBlockX, camBlockY, camBlockZ);
        if (surfaceBlock < 0) {
            return;
        }
        double surfaceY = surfaceBlock - surfaceOffset;

        sun.update(world, partialTicks);

        WaterProfile profile = WaterProfiles.at(world, camBlockX, camBlockY, camBlockZ);
        float rainFade = 1.0F - world.getRainStrength(partialTicks);
        float depthFade = 1.0F - smoothstep(0.0F, fadeDepth, (float) (surfaceY - camY));
        float strength = profile.godRayStrength * sun.transmissionStrength * rainFade * depthFade;

        if (strength <= 0.01F) {
            return;
        }

        double time = world.getTotalWorldTime() + partialTicks;

        Tessellator t = Tessellator.instance;
        beginDrawing();
        t.startDrawingQuads();

        float maxWidth = Math.max(RayLook.minWidth, RayLook.maxWidth);
        int cellX0 = Math.floorDiv(camBlockX, cell);
        int cellZ0 = Math.floorDiv(camBlockZ, cell);
        for (int cellX = cellX0 - cellRadius; cellX <= cellX0 + cellRadius; cellX++) {
            for (int cellZ = cellZ0 - cellRadius; cellZ <= cellZ0 + cellRadius; cellZ++) {

                if (hash01(cellX, cellZ, 0) > RayLook.shaftChance) {
                    continue;
                }

                double placementX = (cellX + hash01(cellX, cellZ, 1)) * cell;
                double placementZ = (cellZ + hash01(cellX, cellZ, 2)) * cell;

                // Fade out at edge
                double dx = placementX - camX;
                double dz = placementZ - camZ;
                float distance = (float) Math.sqrt(dx * dx + dz * dz);
                float distanceFade = 1.0F - smoothstep(cell * (cellRadius - 1), cell * cellRadius, distance);
                if (distanceFade <= 0.0F) {
                    continue;
                }

                int placementBlockX = MathHelper.floor_double(placementX);
                int placementBlockZ = MathHelper.floor_double(placementZ);

                boolean waterBelowSurface = world.getBlock(placementBlockX, surfaceBlock - 1, placementBlockZ)
                    .getMaterial() == Material.water;
                boolean waterAtSurface = world.getBlock(placementBlockX, surfaceBlock, placementBlockZ)
                    .getMaterial() == Material.water;
                if (!waterBelowSurface || waterAtSurface) {
                    continue;
                }

                if (!world.canBlockSeeTheSky(placementBlockX, surfaceBlock, placementBlockZ)) {
                    continue;
                }

                if (sun.sunY < SHADOW_CHECK_BELOW && !sunReaches(world, placementX, surfaceBlock, placementZ)) {
                    continue;
                }

                float clippedLength = (float) distanceToOpaque(
                    world,
                    placementX,
                    surfaceY,
                    placementZ,
                    sun.lightDirX,
                    sun.lightDirY,
                    sun.lightDirZ,
                    shaftLength);
                if (clippedLength < 0.5F) {
                    continue;
                }

                float drawnLength = Math.min(shaftLength, clippedLength + endMargin);

                float width = RayLook.minWidth + (maxWidth - RayLook.minWidth) * hash01(cellX, cellZ, 3);
                float brightness = 0.5F + 0.5F * hash01(cellX, cellZ, 4);
                float phase = hash01(cellX, cellZ, 5) * 6.2832F;
                float pulse = 0.65F + 0.35F * (float) Math.sin(time * 0.04 + phase);

                float baseAlpha = strength * RayLook.alphaScale * brightness * pulse * distanceFade;

                double topX = dx;
                double topY = surfaceY - camY + shaftStartAbove;
                double topZ = dz;

                emitSurfaceGlow(
                    t,
                    topX,
                    surfaceY - camY - glowOffset,
                    topZ,
                    width * RayLook.glowRadiusScale,
                    baseAlpha * RayLook.glowStrength);

                float shaftAlpha = baseAlpha * spriteFade(topX, topY, topZ, drawnLength);
                if (shaftAlpha > 0.002F) {
                    emitShaft(t, topX, topY, topZ, width, shaftAlpha, drawnLength);
                }
            }
        }

        t.draw();
        endDrawing();
    }

    private void emitSurfaceGlow(Tessellator t, double x, double y, double z, float radius, float alpha) {
        if (alpha <= 0.002F) {
            return;
        }
        for (int i = 0; i < glowGrid.length - 1; i++) {
            for (int j = 0; j < glowGrid.length - 1; j++) {
                glowVertex(t, x, y, z, radius, alpha, glowGrid[i], glowGrid[j]);
                glowVertex(t, x, y, z, radius, alpha, glowGrid[i + 1], glowGrid[j]);
                glowVertex(t, x, y, z, radius, alpha, glowGrid[i + 1], glowGrid[j + 1]);
                glowVertex(t, x, y, z, radius, alpha, glowGrid[i], glowGrid[j + 1]);
            }
        }
    }

    private void glowVertex(Tessellator t, double x, double y, double z, float radius, float alpha, float gx,
        float gz) {
        float d2 = gx * gx + gz * gz;
        float a = (float) Math.exp(-d2 / 0.25) * alpha;
        t.setColorRGBA_F(sun.lightColorR, sun.lightColorG, sun.lightColorB, a);
        t.addVertex(x + gx * radius, y, z + gz * radius);
    }

    private float spriteFade(double topX, double topY, double topZ, float drawnLength) {
        float dx = sun.lightDirX, dy = sun.lightDirY, dz = sun.lightDirZ;

        double vecToShaftMidX = topX + dx * drawnLength * 0.5;
        double vecToShaftMidY = topY + dy * drawnLength * 0.5;
        double vecToShaftMidZ = topZ + dz * drawnLength * 0.5;
        double distanceToShaftMid = Math
            .sqrt(vecToShaftMidX * vecToShaftMidX + vecToShaftMidY * vecToShaftMidY + vecToShaftMidZ * vecToShaftMidZ);
        if (distanceToShaftMid < 1.0e-4) {
            return 0.0F;
        }

        float align = (float) Math
            .abs((vecToShaftMidX * dx + vecToShaftMidY * dy + vecToShaftMidZ * dz) / distanceToShaftMid);
        float alignFade = 1.0F - smoothstep(RayLook.alignFadeStart, RayLook.alignFadeEnd, align);

        // Distance from the camera to the shaft line |(Q − T) × d| / |d|
        double crossX = topY * dz - topZ * dy;
        double crossY = topZ * dx - topX * dz;
        double crossZ = topX * dy - topY * dx;
        float lineDist = (float) Math.sqrt(crossX * crossX + crossY * crossY + crossZ * crossZ);
        float nearFade = smoothstep(nearFadeStart, nearFadeEnd, lineDist);

        return alignFade * nearFade;
    }

    private boolean sunReaches(World world, double originX, double originY, double originZ) {
        return distanceToOpaque(world, originX, originY, originZ, sun.sunX, sun.sunY, sun.sunZ, SUN_RAY_MAX_DISTANCE)
            >= SUN_RAY_MAX_DISTANCE;
    }

    private void emitShaft(Tessellator t, double x, double y, double z, float width, float alpha, float drawnLength) {
        float lightDirX = sun.lightDirX, lightDirY = sun.lightDirY, lightDirZ = sun.lightDirZ;

        // Width direction: cross product dir x m, with m = vector from the camera to the shaft's middle
        double midX = x + lightDirX * drawnLength * 0.5;
        double midY = y + lightDirY * drawnLength * 0.5;
        double midZ = z + lightDirZ * drawnLength * 0.5;
        double crossX = lightDirY * midZ - lightDirZ * midY;
        double crossY = lightDirZ * midX - lightDirX * midZ;
        double crossZ = lightDirX * midY - lightDirY * midX;
        double crossLength = Math.sqrt(crossX * crossX + crossY * crossY + crossZ * crossZ);
        if (crossLength < 1.0e-4) {
            return;
        }
        double widthX = crossX / crossLength;
        double widthY = crossY / crossLength;
        double widthZ = crossZ / crossLength;

        int rows = buildRows(drawnLength);
        for (int row = 0; row < rows - 1; row++) {
            for (int col = 0; col < colX.length - 1; col++) {
                shaftVertex(t, x, y, z, widthX, widthY, widthZ, width, alpha, row, col);
                shaftVertex(t, x, y, z, widthX, widthY, widthZ, width, alpha, row, col + 1);
                shaftVertex(t, x, y, z, widthX, widthY, widthZ, width, alpha, row + 1, col + 1);
                shaftVertex(t, x, y, z, widthX, widthY, widthZ, width, alpha, row + 1, col);
            }
        }
    }

    /**
     * Fills rowDistance / rowBrightness for a shaft of the given length and returns the number of rows:
     * the configured rows up to the start of the fade-out, one row at the start of the fade-out (normal
     * brightness there) and one at the end (brightness 0), so the end of a shaft is never a hard edge.
     */
    private int buildRows(float drawnLength) {
        float fadeStart = Math.max(drawnLength - endFadeLength, drawnLength * 0.5F);
        int count = 0;
        for (int i = 0; i < rowT.length && count < rowDistance.length - 2; i++) {
            float distance = rowT[i] * shaftLength;
            if (distance >= fadeStart) {
                break;
            }
            rowDistance[count] = distance;
            rowBrightness[count] = rowAlpha[i];
            count++;
        }
        rowDistance[count] = fadeStart;
        rowBrightness[count] = rowAlphaAt(fadeStart / shaftLength);
        count++;
        rowDistance[count] = drawnLength;
        rowBrightness[count] = 0.0F;
        count++;
        return count;
    }

    private void shaftVertex(Tessellator t, double x, double y, double z, double wx, double wy, double wz, float width,
        float alpha, int row, int col) {
        float distance = rowDistance[row];
        float along = distance / shaftLength;

        double cx = x + sun.lightDirX * distance;
        double cy = y + sun.lightDirY * distance;
        double cz = z + sun.lightDirZ * distance;

        double half = width * 0.5 * (1.0 + (RayLook.spread - 1.0) * along);
        double offset = half * colX[col];

        t.setColorRGBA_F(sun.lightColorR, sun.lightColorG, sun.lightColorB, alpha * rowBrightness[row] * colAlpha[col]);
        t.addVertex(cx + wx * offset, cy + wy * offset, cz + wz * offset);
    }

    private static float rowAlphaAt(float along) {
        for (int i = 0; i < rowT.length - 1; i++) {
            if (along <= rowT[i + 1]) {
                float f = (along - rowT[i]) / (rowT[i + 1] - rowT[i]);
                return rowAlpha[i] + (rowAlpha[i + 1] - rowAlpha[i]) * f;
            }
        }
        return rowAlpha[rowAlpha.length - 1];
    }

    private static double distanceToOpaque(World world, double originX, double originY, double originZ, double dirX,
        double dirY, double dirZ, double maxDistance) {
        int blockX = MathHelper.floor_double(originX);
        int blockY = MathHelper.floor_double(originY);
        int blockZ = MathHelper.floor_double(originZ);
        if (world.getBlock(blockX, blockY, blockZ)
            .isOpaqueCube()) {
            return 0.0;
        }

        int stepX = dirX > 0 ? 1 : -1;
        int stepY = dirY > 0 ? 1 : -1;
        int stepZ = dirZ > 0 ? 1 : -1;

        double distancePerBlockX = dirX == 0 ? Double.MAX_VALUE : Math.abs(1.0 / dirX);
        double distancePerBlockY = dirY == 0 ? Double.MAX_VALUE : Math.abs(1.0 / dirY);
        double distancePerBlockZ = dirZ == 0 ? Double.MAX_VALUE : Math.abs(1.0 / dirZ);

        double nextBoundaryX = dirX == 0 ? Double.MAX_VALUE
            : (dirX > 0 ? blockX + 1 - originX : originX - blockX) * distancePerBlockX;
        double nextBoundaryY = dirY == 0 ? Double.MAX_VALUE
            : (dirY > 0 ? blockY + 1 - originY : originY - blockY) * distancePerBlockY;
        double nextBoundaryZ = dirZ == 0 ? Double.MAX_VALUE
            : (dirZ > 0 ? blockZ + 1 - originZ : originZ - blockZ) * distancePerBlockZ;

        while (true) {
            double distance;
            if (nextBoundaryX < nextBoundaryY && nextBoundaryX < nextBoundaryZ) {
                distance = nextBoundaryX;
                blockX += stepX;
                nextBoundaryX += distancePerBlockX;
            } else if (nextBoundaryY < nextBoundaryZ) {
                distance = nextBoundaryY;
                blockY += stepY;
                nextBoundaryY += distancePerBlockY;
            } else {
                distance = nextBoundaryZ;
                blockZ += stepZ;
                nextBoundaryZ += distancePerBlockZ;
            }
            if (distance >= maxDistance || blockY >= world.getHeight()) {
                return maxDistance;
            }
            if (world.getBlock(blockX, blockY, blockZ)
                .isOpaqueCube()) {
                return distance;
            }
        }
    }

    private void beginDrawing() {
        wasTexture = GL11.glIsEnabled(GL11.GL_TEXTURE_2D);
        wasLighting = GL11.glIsEnabled(GL11.GL_LIGHTING);
        wasCull = GL11.glIsEnabled(GL11.GL_CULL_FACE);
        wasBlend = GL11.glIsEnabled(GL11.GL_BLEND);
        wasAlphaTest = GL11.glIsEnabled(GL11.GL_ALPHA_TEST);

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE, 1, 0);
        GL11.glDepthMask(false);
    }

    private void endDrawing() {
        GL11.glDepthMask(true);
        OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
        setEnabled(GL11.GL_ALPHA_TEST, wasAlphaTest);
        setEnabled(GL11.GL_BLEND, wasBlend);
        setEnabled(GL11.GL_CULL_FACE, wasCull);
        setEnabled(GL11.GL_LIGHTING, wasLighting);
        setEnabled(GL11.GL_TEXTURE_2D, wasTexture);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private static void setEnabled(int capability, boolean enabled) {
        if (enabled) {
            GL11.glEnable(capability);
        } else {
            GL11.glDisable(capability);
        }
    }

    private static int findSurface(World world, int x, int y, int z) {
        for (int i = 0; i < maxSurfaceScan; i++) {
            if (world.getBlock(x, y + i, z)
                .getMaterial() != Material.water) {
                return y + i;
            }
        }
        return -1;
    }
}
