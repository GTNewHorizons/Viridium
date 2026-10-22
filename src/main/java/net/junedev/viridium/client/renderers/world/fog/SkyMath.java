package net.junedev.viridium.client.renderers.world.fog;

import net.junedev.viridium.client.renderers.world.sky.SkyProfile;

public final class SkyMath {

    private static final int FOG_SAMPLES = 5;
    private static final float FOG_SPREAD_DEG = 40.0F;

    private SkyMath() {}

    // The math of this function has to be the same as in sky.fsh, so that fog and sky color are similar
    public static void skyColor(SkyProfile p, float sunX, float sunY, float sunZ, float rain, float dx, float dy,
        float dz, float[] out) {
        float cosTheta = dx * sunX + dy * sunY + dz * sunZ;
        float rayleigh = p.rayleighScale * (1.0F + cosTheta * cosTheta);

        float g2 = p.mieG * p.mieG;
        float d = Math.max(1.0F + g2 - 2.0F * p.mieG * cosTheta, 0.0001F);
        float mie = (1.0F - g2) / (4.0F * (float) Math.PI * d * (float) Math.sqrt(d));

        float sunsetFactor = Math.max(0.0F, 1.0F - Math.abs(sunY) * p.sunsetWidth);
        float band = 1.0F - smoothstep(Math.abs(dy));

        float hx = dx + 1e-5F;
        float hz = dz + 1e-5F;
        float hl = (float) Math.sqrt(hx * hx + hz * hz);
        hx /= hl;
        hz /= hl;
        float sx = sunX + 1e-5F;
        float sz = sunZ + 1e-5F;
        float sl = (float) Math.sqrt(sx * sx + sz * sz);
        sx /= sl;
        sz /= sl;
        float cosAz = hx * sx + hz * sz;
        float t = Math.max(0.5F + 0.5F * cosAz, 0.0F);
        float azimuth = 0.25F + 0.75F * (t * t);

        float dayFactor = clamp(sunY * 2.0F + p.dayBias);
        float horizon = clamp(p.horizonBase + dy * p.horizonSlope);

        float base = rayleigh * dayFactor * horizon;
        float sunset = sunsetFactor * band * azimuth;
        float halo = mie * dayFactor;
        float rainFactor = 1.0F - rain * p.rainDarkening;

        out[0] = clamp((p.baseR * base + p.sunsetR * sunset + p.haloR * halo) * rainFactor);
        out[1] = clamp((p.baseG * base + p.sunsetG * sunset + p.haloG * halo) * rainFactor);
        out[2] = clamp((p.baseB * base + p.sunsetB * sunset + p.haloB * halo) * rainFactor);
    }

    public static void horizonColor(SkyProfile p, float sunX, float sunY, float sunZ, float rain, float yawDegrees,
        float[] out) {
        float[] sample = new float[3];
        float r = 0.0F;
        float g = 0.0F;
        float b = 0.0F;

        for (int i = 0; i < FOG_SAMPLES; i++) {
            float offset = FOG_SAMPLES == 1 ? 0.0F : (-FOG_SPREAD_DEG + 2.0F * FOG_SPREAD_DEG * i / (FOG_SAMPLES - 1));
            double yaw = Math.toRadians(yawDegrees + offset);

            float dx = (float) -Math.sin(yaw);
            float dz = (float) Math.cos(yaw);

            skyColor(p, sunX, sunY, sunZ, rain, dx, 0.0F, dz, sample);
            r += sample[0];
            g += sample[1];
            b += sample[2];
        }

        out[0] = r / FOG_SAMPLES;
        out[1] = g / FOG_SAMPLES;
        out[2] = b / FOG_SAMPLES;
    }

    private static float smoothstep(float x) {
        float t = clamp((x - (float) 0.0) / 0.35F);
        return t * t * (3.0F - 2.0F * t);
    }

    private static float clamp(float v) {
        return Math.min((float) 1.0, Math.max((float) 0.0, v));
    }
}
