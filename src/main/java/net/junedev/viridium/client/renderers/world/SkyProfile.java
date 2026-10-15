package net.junedev.viridium.client.renderers.world;

public class SkyProfile {

    public float baseR = 0.25F, baseG = 0.5F, baseB = 1.0F;

    public float mieG = 0.78F;
    public float haloR = 0.15F, haloG = 0.12F, haloB = 0.08F;

    public float sunsetR = 1.0F, sunsetG = 0.4F, sunsetB = 0.1F;
    public float sunsetWidth = 4.0F;

    public float dayBias = 0.3F;
    public float horizonBase = 0.8F;
    public float rainDarkening = 0.7F;
    public float rayleighScale = 0.75F;
    public float horizonSlope = 1.0F;

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
        mieG = o.mieG;
        haloR = o.haloR;
        haloG = o.haloG;
        haloB = o.haloB;
        sunsetR = o.sunsetR;
        sunsetG = o.sunsetG;
        sunsetB = o.sunsetB;
        sunsetWidth = o.sunsetWidth;
        dayBias = o.dayBias;
        horizonBase = o.horizonBase;
        rainDarkening = o.rainDarkening;
        rayleighScale = o.rayleighScale;
        horizonSlope = o.horizonSlope;
    }

    public void lerpTo(SkyProfile t, float k) {
        baseR = lerp(baseR, t.baseR, k);
        baseG = lerp(baseG, t.baseG, k);
        baseB = lerp(baseB, t.baseB, k);
        mieG = lerp(mieG, t.mieG, k);
        haloR = lerp(haloR, t.haloR, k);
        haloG = lerp(haloG, t.haloG, k);
        haloB = lerp(haloB, t.haloB, k);
        sunsetR = lerp(sunsetR, t.sunsetR, k);
        sunsetG = lerp(sunsetG, t.sunsetG, k);
        sunsetB = lerp(sunsetB, t.sunsetB, k);
        sunsetWidth = lerp(sunsetWidth, t.sunsetWidth, k);
        dayBias = lerp(dayBias, t.dayBias, k);
        horizonBase = lerp(horizonBase, t.horizonBase, k);
        rainDarkening = lerp(rainDarkening, t.rainDarkening, k);
        rayleighScale = lerp(rayleighScale, t.rayleighScale, k);
        horizonSlope = lerp(horizonSlope, t.horizonSlope, k);
    }

    private static float lerp(float from, float to, float k) {
        return from + (to - from) * k;
    }
}
