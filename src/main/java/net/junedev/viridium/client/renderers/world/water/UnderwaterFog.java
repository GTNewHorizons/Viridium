package net.junedev.viridium.client.renderers.world.water;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.junedev.viridium.client.renderers.world.water.profiles.WaterProfile;
import net.junedev.viridium.client.renderers.world.water.profiles.WaterProfileHandler;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import org.lwjgl.opengl.GL11;

import static net.junedev.viridium.client.renderers.utils.RenderingUtils.lerp;

public class UnderwaterFog {

    public static final int MAX_SURFACE_SCAN = 32; // how far up we look for the water surface
    public static float PROFILE_BLEND = 0.05F; // per frame: how fast the fog profile follows the biome
    public static float FOG_COLOR_SMOOTHING = 0.15F; // per frame: how fast the final fog color follows
    // Fog profile actually in use, smoothly blended towards the profile at the camera
    public final WaterProfile current = new WaterProfile();
    public boolean profileInitialized = false;
    // Camera depth factor (0 = right below the surface, 1 = deep), computed once per frame in onFogColors
    public float depthT = 0.0F;
    // Smoothed fog color of the previous frame (smoothR < 0 = not initialized yet)
    public float smoothR = -1.0F, smoothG, smoothB;
    public World smoothWorld;

    private static int findSurface(World world, int x, int y, int z) {
        for (int i = 0; i < MAX_SURFACE_SCAN; i++) {
            if (world.getBlock(x, y + i, z)
                .getMaterial() != Material.water) {
                return y + i;
            }
        }
        return -1;
    }

    /** Fog color under water. Called once per frame; also updates the depth factor and the blended profile. */
    @SubscribeEvent
    public void onFogColors(EntityViewRenderEvent.FogColors event) {
        if (event.block.getMaterial() != Material.water) {
            return;
        }
        EntityLivingBase e = event.entity;
        World world = e.worldObj;
        float pt = (float) event.renderPartialTicks;

        depthT = cameraDepthFactor(world, e, pt);

        // FogColors replaces vanilla's own dimming, so darkness at night and in rain is added here
        float light = Math.max(0.15F, world.getSunBrightness(pt)) * (1.0F - world.getRainStrength(pt) * 0.3F);

        event.red = lerp(current.fogShallowR, current.fogDeepR, depthT) * light;
        event.green = lerp(current.fogShallowG, current.fogDeepG, depthT) * light;
        event.blue = lerp(current.fogShallowB, current.fogDeepB, depthT) * light;
    }

    /**
     * Fog density under water. Fires several times per frame, so it reuses depthT instead of recomputing it.
     * The event only takes effect when cancelled.
     */
    @SubscribeEvent
    public void onFogDensity(EntityViewRenderEvent.FogDensity event) {
        if (event.block.getMaterial() != Material.water) {
            return;
        }
        float density = lerp(current.fogDensityShallow, current.fogDensityDeep, depthT);
        density -= EnchantmentHelper.getRespiration(event.entity) * 0.03F; // like vanilla: respiration helps

        event.density = Math.max(0.01F, density);
        GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_EXP); // density only works in exponential mode
        event.setCanceled(true);
    }

    /**
     * Runs after every other FogColors handler (also above water) and blends the final color over time.
     * Smooths biome changes, diving in and out, and color jumps from other mods.
     */

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onFogColorsSmooth(EntityViewRenderEvent.FogColors event) {
        World world = event.entity.worldObj;
        if (smoothR < 0.0F || world != smoothWorld) {
            // first frame or new world: start exactly at the current color
            smoothR = event.red;
            smoothG = event.green;
            smoothB = event.blue;
            smoothWorld = world;
            return;
        }
        smoothR += (event.red - smoothR) * FOG_COLOR_SMOOTHING;
        smoothG += (event.green - smoothG) * FOG_COLOR_SMOOTHING;
        smoothB += (event.blue - smoothB) * FOG_COLOR_SMOOTHING;
        event.red = smoothR;
        event.green = smoothG;
        event.blue = smoothB;
    }

    /**
     * 0 right below the surface, 1 at fogDepthRange blocks or deeper.
     * Also blends the fog profile towards the profile at the camera.
     */
    private float cameraDepthFactor(World world, EntityLivingBase e, float pt) {
        double eyeY = e.lastTickPosY + (e.posY - e.lastTickPosY) * pt + e.getEyeHeight();
        int bx = MathHelper.floor_double(e.posX);
        int by = MathHelper.floor_double(eyeY);
        int bz = MathHelper.floor_double(e.posZ);

        WaterProfile target = WaterProfileHandler.at(world, bx, by, bz);
        if (!profileInitialized) {
            current.copyFrom(target);
            profileInitialized = true;
        } else {
            current.lerpTo(target, PROFILE_BLEND);
        }

        int surface = findSurface(world, bx, by, bz);
        if (surface < 0) {
            return 1.0F; // deeper than the scan range: fully deep
        }
        return MathHelper.clamp_float((float) ((surface - eyeY) / current.fogDepthRange), 0.0F, 1.0F);
    }
}
