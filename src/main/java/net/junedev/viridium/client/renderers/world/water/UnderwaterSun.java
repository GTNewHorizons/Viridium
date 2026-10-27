package net.junedev.viridium.client.renderers.world.water;

import static net.junedev.viridium.client.renderers.utils.RenderingUtils.smoothstep;

import net.junedev.viridium.client.renderers.world.fog.SkyState;
import net.minecraft.world.World;

public final class UnderwaterSun {

    private static final float WATER_INDEX = 1.33F;

    // Light color
    private static final float DAY_R = 0.85F, DAY_G = 0.95F, DAY_B = 1.0F;
    private static final float LOW_R = 1.0F, LOW_G = 0.62F, LOW_B = 0.30F;
    private static final float WARM_AMOUNT = 0.8F; // how far the color moves towards LOW at the horizon

    // Unit vec in air
    public float sunX, sunY, sunZ;
    public float lightDirX, lightDirY = -1.0F, lightDirZ;
    // 0..1: how much light passes the surface (0 at night and at the horizon)
    public float transmissionStrength;
    // Light color
    public float lightColorR = DAY_R, lightColorG = DAY_G, lightColorB = DAY_B;

    public void update(World world, float partialTicks) {
        float rawSunVecX, rawSunVecY, rawSunVecZ;
        if (SkyState.isActive()) {
            rawSunVecX = SkyState.sunX;
            rawSunVecY = SkyState.sunY;
            rawSunVecZ = SkyState.sunZ;
        } else {
            double angle = world.getCelestialAngle(partialTicks) * Math.PI * 2.0;
            rawSunVecX = (float) -Math.sin(angle);
            rawSunVecY = (float) Math.cos(angle);
            rawSunVecZ = 0.0F;
        }
        float len = (float) Math.sqrt(rawSunVecX * rawSunVecX + rawSunVecY * rawSunVecY + rawSunVecZ * rawSunVecZ);
        if (len < 1.0e-4F) {
            transmissionStrength = 0.0F;
            return;
        }
        sunX = rawSunVecX / len;
        sunY = rawSunVecY / len;
        sunZ = rawSunVecZ / len;

        // Snell
        float hx = sunX / WATER_INDEX;
        float hz = sunZ / WATER_INDEX;
        float hy = (float) Math.sqrt(Math.max(0.0F, 1.0F - hx * hx - hz * hz));
        lightDirX = -hx;
        lightDirY = -hy;
        lightDirZ = -hz;

        // Fresnel reflection (Schlick's approximation, R0 = 0.02 for water),
        float cosI = Math.max(0.0F, sunY);
        float reflect = 0.02F + 0.98F * (float) Math.pow(1.0F - cosI, 5.0);
        transmissionStrength = (1.0F - reflect) * smoothstep(0.02F, 0.25F, sunY);

        // warmer the lower the sun is
        float warm = (1.0F - smoothstep(0.05F, 0.35F, sunY)) * WARM_AMOUNT;
        lightColorR = DAY_R + (LOW_R - DAY_R) * warm;
        lightColorG = DAY_G + (LOW_G - DAY_G) * warm;
        lightColorB = DAY_B + (LOW_B - DAY_B) * warm;
    }
}
