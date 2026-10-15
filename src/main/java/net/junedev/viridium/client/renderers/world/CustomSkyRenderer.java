package net.junedev.viridium.client.renderers.world;

import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

import net.junedev.viridium.client.renderers.world.SkyEditor.SkyDebugHook;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.client.IRenderHandler;

import org.lwjgl.opengl.GL11;

public class CustomSkyRenderer extends IRenderHandler {

    // private static final ResourceLocation SUN_TEXTURE = new ResourceLocation("viridium",
    // "textures/environment/custom_sun.png");
    // private static final ResourceLocation MOON_TEXTURE = new ResourceLocation("viridium",
    // "textures/environment/custom_moon.png");

    private static final ResourceLocation SUN_TEXTURE = new ResourceLocation("textures/environment/sun.png");
    private static final ResourceLocation MOON_TEXTURE = new ResourceLocation("textures/environment/moon_phases.png");

    private static final double DOME_RADIUS = 100.0;
    private static final int DOME_LAT = 32;
    private static final int DOME_LONG = 64;

    private static boolean listenerRegistered = false;
    private static final AtomicInteger reloadGeneration = new AtomicInteger(0);

    private static SkyProfile currentSkyProfile = new SkyProfile();

    public static volatile SkyDebugHook debugHook = null;

    private static synchronized void registerReloadListenerOnce() {
        if (listenerRegistered) {
            return;
        }
        IResourceManager rm = Minecraft.getMinecraft()
            .getResourceManager();
        if (rm instanceof IReloadableResourceManager) {
            ((IReloadableResourceManager) rm).registerReloadListener(new IResourceManagerReloadListener() {

                @Override
                public void onResourceManagerReload(IResourceManager manager) {
                    reloadGeneration.incrementAndGet();
                }
            });
            listenerRegistered = true;
        }
    }

    private int appliedGeneration = -1;
    private SkyShader skyShader;
    private int domeList = -1;

    private static boolean irisLookedUp = false;
    private static Object irisInstance;
    private static Method irisInUse;

    private static final boolean FAST_DAY = true;
    private static final double FAST_DAY_SECONDS = 30.0;

    @Override
    public void render(float partialTicks, WorldClient world, Minecraft mc) {
        registerReloadListenerOnce();

        int generation = reloadGeneration.get();
        if (appliedGeneration != generation) {
            releaseGlResources();
            skyShader = SkyShader.create();
            if (skyShader != null) {
                domeList = buildDomeList();
            }
            appliedGeneration = generation;
        }

        if (skyShader == null || domeList == -1 || isShaderPackActive()) {
            renderVanillaSky(partialTicks, world, mc);
            return;
        }

        float celestialAngle = getSkyAngle(world, partialTicks);
        float rain = world.getRainStrength(partialTicks);

        getSkyParameters(world, mc);

        // Calculating sun vector
        double sunAngleRad = celestialAngle * Math.PI * 2.0;
        float sunX = (float) -Math.sin(sunAngleRad);
        float sunY = (float) Math.cos(sunAngleRad);
        float sunZ = 0.0F;

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);

        skyShader.bind(currentSkyProfile, sunX, sunY, sunZ, rain);
        GL11.glCallList(domeList);
        skyShader.unbind();

        renderSunAndMoon(world, mc, rain, celestialAngle);

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glDepthMask(true);
    }

    private static void renderSunAndMoon(WorldClient world, Minecraft mc, float rain, float celestialAngle) {
        // Sun/Moon Prep
        Tessellator tessellator = Tessellator.instance;
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE, 1, 0);

        GL11.glPushMatrix();
        GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(celestialAngle * 360.0F, 1.0F, 0.0F, 0.0F);

        // Sun
        mc.getTextureManager()
            .bindTexture(SUN_TEXTURE);
        double sunSize = 25.0;
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA_F(1.0F, 0.9F, 0.7F, 1.0F - rain);
        tessellator.addVertexWithUV(-sunSize, 99.0, -sunSize, 0.0, 0.0);
        tessellator.addVertexWithUV(sunSize, 99.0, -sunSize, 1.0, 0.0);
        tessellator.addVertexWithUV(sunSize, 99.0, sunSize, 1.0, 1.0);
        tessellator.addVertexWithUV(-sunSize, 99.0, sunSize, 0.0, 1.0);
        tessellator.draw();

        // Moon
        mc.getTextureManager()
            .bindTexture(MOON_TEXTURE);
        int phase = world.getMoonPhase();
        double u0 = (phase % 4) / 4.0;
        double v0 = (phase / 4 % 2) / 2.0;
        double u1 = u0 + 0.25;
        double v1 = v0 + 0.5;
        double moonSize = 20.0;
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA_F(0.8F, 0.9F, 1.0F, 1.0F - rain);
        tessellator.addVertexWithUV(-moonSize, -99.0, moonSize, u0, v0);
        tessellator.addVertexWithUV(moonSize, -99.0, moonSize, u1, v0);
        tessellator.addVertexWithUV(moonSize, -99.0, -moonSize, u1, v1);
        tessellator.addVertexWithUV(-moonSize, -99.0, -moonSize, u0, v1);
        tessellator.draw();

        GL11.glPopMatrix();

        OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
    }

    private void renderVanillaSky(float partialTicks, WorldClient world, Minecraft mc) {
        IRenderHandler self = world.provider.getSkyRenderer();
        world.provider.setSkyRenderer(null);
        try {
            mc.renderGlobal.renderSky(partialTicks);
        } finally {
            world.provider.setSkyRenderer(self);
        }
    }

    private void releaseGlResources() {
        if (skyShader != null) {
            skyShader.delete();
            skyShader = null;
        }
        if (domeList != -1) {
            GL11.glDeleteLists(domeList, 1);
            domeList = -1;
        }
    }

    private int buildDomeList() {
        int list = GL11.glGenLists(1);
        GL11.glNewList(list, GL11.GL_COMPILE);

        Tessellator t = Tessellator.instance;
        t.startDrawingQuads();

        for (int i = 0; i < DOME_LAT; i++) {
            double lat0 = Math.PI * (-0.5 + (double) i / DOME_LAT);
            double y0 = Math.sin(lat0);
            double r0 = Math.cos(lat0);

            double lat1 = Math.PI * (-0.5 + (double) (i + 1) / DOME_LAT);
            double y1 = Math.sin(lat1);
            double r1 = Math.cos(lat1);

            for (int j = 0; j < DOME_LONG; j++) {
                double long0 = 2 * Math.PI * (double) j / DOME_LONG;
                double x0 = Math.cos(long0);
                double z0 = Math.sin(long0);

                double long1 = 2 * Math.PI * (double) (j + 1) / DOME_LONG;
                double x1 = Math.cos(long1);
                double z1 = Math.sin(long1);

                t.addVertex(x0 * r0 * DOME_RADIUS, y0 * DOME_RADIUS, z0 * r0 * DOME_RADIUS);
                t.addVertex(x1 * r0 * DOME_RADIUS, y0 * DOME_RADIUS, z1 * r0 * DOME_RADIUS);
                t.addVertex(x1 * r1 * DOME_RADIUS, y1 * DOME_RADIUS, z1 * r1 * DOME_RADIUS);
                t.addVertex(x0 * r1 * DOME_RADIUS, y1 * DOME_RADIUS, z0 * r1 * DOME_RADIUS);
            }
        }

        t.draw();
        GL11.glEndList();
        return list;
    }

    private float getSkyAngle(WorldClient world, float partialTicks) {
        float angle = world.getCelestialAngle(partialTicks);

        SkyDebugHook hook = debugHook;
        if (hook != null) {
            float ticks = hook.overrideTimeTicks();
            if (ticks >= 0.0F) {
                long whole = (long) ticks;
                angle = world.provider.calculateCelestialAngle(whole, ticks - whole);
            }
        }
        return angle;
    }

    // TODO: Test and rework this
    private static boolean isShaderPackActive() {
        if (!irisLookedUp) {
            irisLookedUp = true;
            try {
                Class<?> api = Class.forName("net.irisshaders.iris.api.v0.IrisApi");
                irisInstance = api.getMethod("getInstance")
                    .invoke(null);
                irisInUse = api.getMethod("isShaderPackInUse");
            } catch (Throwable t) {
                irisInUse = null;
            }
        }
        if (irisInUse == null) {
            return false;
        }
        try {
            return (Boolean) irisInUse.invoke(irisInstance);
        } catch (Throwable t) {
            return false;
        }
    }

    private void getSkyParameters(World world, Minecraft mc) {
        int playerX = (int) mc.renderViewEntity.posX;
        int playerZ = (int) mc.renderViewEntity.posZ;
        BiomeGenBase biome = world.getBiomeGenForCoords(playerX, playerZ);

        SkyProfile target = SkyProfileHandler.get(biome.biomeName);
        float k = 0.02F;

        SkyDebugHook hook = debugHook;
        if (hook != null) {
            SkyProfile override = hook.overrideProfile();
            if (override != null) {
                target = override;
                k = 1.0F;
            }
        }

        currentSkyProfile.lerpTo(target, k);
    }
}
