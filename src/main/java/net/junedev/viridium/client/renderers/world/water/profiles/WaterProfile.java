package net.junedev.viridium.client.renderers.world.water.profiles;

import static net.junedev.viridium.client.renderers.utils.RenderingUtils.lerp;

public final class WaterProfile {

    public float shallowR = 0.6F, shallowG = 0.8F, shallowB = 1.0F;
    public float deepR = 0.3F, deepG = 0.5F, deepB = 1.0F;
    public float depthRange = 8.0F;
    public float shoreBrightness = 0.2F; // 0..1: how far the shore pulls the tint back to untinted
    public int shoreRange = 3;

    public float fogShallowR = 0.10F, fogShallowG = 0.32F, fogShallowB = 0.45F;
    public float fogDeepR = 0.02F, fogDeepG = 0.08F, fogDeepB = 0.20F;
    public float fogDensityShallow = 0.06F;
    public float fogDensityDeep = 0.15F;
    public float fogDepthRange = 24.0F;

    public float godRayStrength = 0.5F;

    public WaterProfile() {}

    public WaterProfile(WaterProfile other) {
        copyFrom(other);
    }

    public WaterProfile tint(float sr, float sg, float sb, float dr, float dg, float db) {
        shallowR = sr;
        shallowG = sg;
        shallowB = sb;
        deepR = dr;
        deepG = dg;
        deepB = db;
        return this;
    }

    public WaterProfile shore(float brightness) {
        shoreBrightness = brightness;
        return this;
    }

    public WaterProfile fog(float sr, float sg, float sb, float dr, float dg, float db, float densityShallow,
        float densityDeep) {
        fogShallowR = sr;
        fogShallowG = sg;
        fogShallowB = sb;
        fogDeepR = dr;
        fogDeepG = dg;
        fogDeepB = db;
        fogDensityShallow = densityShallow;
        fogDensityDeep = densityDeep;
        return this;
    }

    public WaterProfile godRays(float strength) {
        godRayStrength = strength;
        return this;
    }

    public void copyFrom(WaterProfile o) {
        shallowR = o.shallowR;
        shallowG = o.shallowG;
        shallowB = o.shallowB;
        deepR = o.deepR;
        deepG = o.deepG;
        deepB = o.deepB;
        depthRange = o.depthRange;
        shoreBrightness = o.shoreBrightness;
        fogShallowR = o.fogShallowR;
        fogShallowG = o.fogShallowG;
        fogShallowB = o.fogShallowB;
        fogDeepR = o.fogDeepR;
        fogDeepG = o.fogDeepG;
        fogDeepB = o.fogDeepB;
        fogDensityShallow = o.fogDensityShallow;
        fogDensityDeep = o.fogDensityDeep;
        fogDepthRange = o.fogDepthRange;
        godRayStrength = o.godRayStrength;
        shoreRange = o.shoreRange;
    }

    public void lerpTo(WaterProfile t, float k) {
        shallowR = lerp(shallowR, t.shallowR, k);
        shallowG = lerp(shallowG, t.shallowG, k);
        shallowB = lerp(shallowB, t.shallowB, k);
        deepR = lerp(deepR, t.deepR, k);
        deepG = lerp(deepG, t.deepG, k);
        deepB = lerp(deepB, t.deepB, k);
        depthRange = lerp(depthRange, t.depthRange, k);
        shoreBrightness = lerp(shoreBrightness, t.shoreBrightness, k);
        fogShallowR = lerp(fogShallowR, t.fogShallowR, k);
        fogShallowG = lerp(fogShallowG, t.fogShallowG, k);
        fogShallowB = lerp(fogShallowB, t.fogShallowB, k);
        fogDeepR = lerp(fogDeepR, t.fogDeepR, k);
        fogDeepG = lerp(fogDeepG, t.fogDeepG, k);
        fogDeepB = lerp(fogDeepB, t.fogDeepB, k);
        fogDensityShallow = lerp(fogDensityShallow, t.fogDensityShallow, k);
        fogDensityDeep = lerp(fogDensityDeep, t.fogDensityDeep, k);
        fogDepthRange = lerp(fogDepthRange, t.fogDepthRange, k);
        godRayStrength = lerp(godRayStrength, t.godRayStrength, k);
        shoreRange = k >= 0.5F ? t.shoreRange : shoreRange;
    }
}
