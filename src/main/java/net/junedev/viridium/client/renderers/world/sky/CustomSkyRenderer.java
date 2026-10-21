package net.junedev.viridium.client.renderers.world.sky;

import java.util.concurrent.atomic.AtomicInteger;

import net.irisshaders.iris.api.v0.IrisApi;
import net.junedev.viridium.client.renderers.world.fog.SkyState;
import net.junedev.viridium.client.renderers.world.sky.SkyEditor.SkyDebugHook;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.IRenderHandler;

import org.lwjgl.opengl.GL11;

public class CustomSkyRenderer extends IRenderHandler {

    // private static final ResourceLocation SUN_TEXTURE = new ResourceLocation("viridium",
    // "textures/environment/custom_sun.png");
    // private static final ResourceLocation MOON_TEXTURE = new ResourceLocation("viridium",
    // "textures/environment/custom_moon.png");

    private static final ResourceLocation SUN_TEXTURE = new ResourceLocation("textures/environment/sun.png");
    private static final ResourceLocation MOON_TEXTURE = new ResourceLocation("textures/environment/moon_phases.png");

    private static boolean listenerRegistered = false;
    private static final AtomicInteger reloadGeneration = new AtomicInteger(0);

    private static SkyProfile currentSkyProfile = new SkyProfile();

    public static volatile SkyDebugHook debugHook = null;
    private final SkyBiomeBlender blender = new SkyBiomeBlender();

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
    private SkyDome dome;

    @Override
    public void render(float partialTicks, WorldClient world, Minecraft mc) {
        registerReloadListenerOnce();

        int generation = reloadGeneration.get();
        if (appliedGeneration != generation) {
            releaseGlResources();
            skyShader = SkyShader.create();
            if (skyShader != null) {
                dome = new SkyDome(16, 24);
            }
            appliedGeneration = generation;
        }

        if (skyShader == null || dome == null || isShaderPackActive()) {
            renderVanillaSky(partialTicks, world, mc);
            SkyState.invalidate();
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

        SkyState.update(currentSkyProfile, sunX, sunY, sunZ, rain);

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);

        skyShader.bind(currentSkyProfile, sunX, sunY, sunZ, rain);
        dome.draw();
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
        if (dome != null) {
            dome.delete();
            dome = null;
        }
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

    private static boolean isShaderPackActive() {
        return IrisApi.getInstance()
            .isShaderPackInUse();
    }

    private void getSkyParameters(World world, Minecraft mc) {
        SkyProfile target = blender.blend(world, mc.renderViewEntity.posX, mc.renderViewEntity.posZ);
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
