package net.junedev.viridium.client.renderers.world.sky;

import static net.junedev.viridium.client.renderers.utils.RenderingUtils.lerp;
import static net.junedev.viridium.client.renderers.utils.RenderingUtils.smoothstep01;

public class SkyProfile {

    public float baseR = 0.25F, baseG = 0.5F, baseB = 1.0F;
    public float baseBrightness = 0.0F;

    public float mieG = 0.78F;
    public float haloR = 0.15F, haloG = 0.12F, haloB = 0.08F;

    public float sunsetR = 1.0F, sunsetG = 0.4F, sunsetB = 0.1F;
    public float sunsetWidth = 4.0F;
    public float sunriseR = 1.0F, sunriseG = 0.4F, sunriseB = 0.1F;

    public float dayBias = 0.3F;
    public float horizonBase = 0.8F;
    public float rainDarkening = 0.7F;
    public float rayleighScale = 0.75F;
    public float horizonSlope = 1.0F;
    public float horizonDesaturation = 0.0F; // 0 = unchanged, 1 = grey at the horizon
    public float horizonLift = 0.0F; // 0 = unchanged, 1 = white at the horizon

    public SkyProfile() {}

    public SkyProfile(SkyProfile other) {
        copyFrom(other);
    }

    public SkyProfile base(float r, float g, float b) {
        this.baseR = r;
        this.baseG = g;
        this.baseB = b;
        return this;
    }

    public SkyProfile baseBrightness(float baseBrightness) {
        this.baseBrightness = baseBrightness;
        return this;
    }

    public SkyProfile mie(float g) {
        this.mieG = g;
        return this;
    }

    public SkyProfile halo(float r, float g, float b) {
        this.haloR = r;
        this.haloG = g;
        this.haloB = b;
        return this;
    }

    public SkyProfile sunset(float r, float g, float b, float width) {
        this.sunsetR = r;
        this.sunsetG = g;
        this.sunsetB = b;
        this.sunsetWidth = width;
        return this;
    }

    public SkyProfile sunrise(float r, float g, float b) {
        this.sunriseR = r;
        this.sunriseG = g;
        this.sunriseB = b;
        return this;
    }

    public SkyProfile horizonHaze(float desaturation, float lift) {
        this.horizonDesaturation = desaturation;
        this.horizonLift = lift;
        return this;
    }

    /** 1 while the sun is in the east (morning), 0 in the west (evening). */
    public static float morningWeight(float sunX) {
        return smoothstep01(Math.max(0F, Math.min(1F, 0.5F + sunX * 2F)));
    }

    public SkyProfile dayBias(float dayBias) {
        this.dayBias = dayBias;
        return this;
    }

    public SkyProfile horizonBase(float horizonBase) {
        this.horizonBase = horizonBase;
        return this;
    }

    public SkyProfile horizonSlope(float horizonSlope) {
        this.horizonSlope = horizonSlope;
        return this;
    }

    public SkyProfile rayleighScale(float rayleighScale) {
        this.rayleighScale = rayleighScale;
        return this;
    }

    public SkyProfile rain(float rainDarkening) {
        this.rainDarkening = rainDarkening;
        return this;
    }

    public void copyFrom(SkyProfile o) {
        baseR = o.baseR;
        baseG = o.baseG;
        baseB = o.baseB;
        baseBrightness = o.baseBrightness;
        mieG = o.mieG;
        haloR = o.haloR;
        haloG = o.haloG;
        haloB = o.haloB;
        sunsetR = o.sunsetR;
        sunsetG = o.sunsetG;
        sunsetB = o.sunsetB;
        sunsetWidth = o.sunsetWidth;
        sunriseR = o.sunriseR;
        sunriseG = o.sunriseG;
        sunriseB = o.sunriseB;
        dayBias = o.dayBias;
        horizonBase = o.horizonBase;
        rainDarkening = o.rainDarkening;
        rayleighScale = o.rayleighScale;
        horizonSlope = o.horizonSlope;
        horizonDesaturation = o.horizonDesaturation;
        horizonLift = o.horizonLift;
    }

    public void lerpTo(SkyProfile t, float k) {
        baseR = lerp(baseR, t.baseR, k);
        baseG = lerp(baseG, t.baseG, k);
        baseB = lerp(baseB, t.baseB, k);
        baseBrightness = lerp(baseBrightness, t.baseBrightness, k);
        mieG = lerp(mieG, t.mieG, k);
        haloR = lerp(haloR, t.haloR, k);
        haloG = lerp(haloG, t.haloG, k);
        haloB = lerp(haloB, t.haloB, k);
        sunsetR = lerp(sunsetR, t.sunsetR, k);
        sunsetG = lerp(sunsetG, t.sunsetG, k);
        sunsetB = lerp(sunsetB, t.sunsetB, k);
        sunsetWidth = lerp(sunsetWidth, t.sunsetWidth, k);
        sunriseR = lerp(sunriseR, t.sunriseR, k);
        sunriseG = lerp(sunriseG, t.sunriseG, k);
        sunriseB = lerp(sunriseB, t.sunriseB, k);
        dayBias = lerp(dayBias, t.dayBias, k);
        horizonBase = lerp(horizonBase, t.horizonBase, k);
        rainDarkening = lerp(rainDarkening, t.rainDarkening, k);
        rayleighScale = lerp(rayleighScale, t.rayleighScale, k);
        horizonSlope = lerp(horizonSlope, t.horizonSlope, k);
        horizonDesaturation = lerp(horizonDesaturation, t.horizonDesaturation, k);
        horizonLift = lerp(horizonLift, t.horizonLift, k);
    }

}
