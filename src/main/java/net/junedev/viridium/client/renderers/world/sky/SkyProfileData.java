package net.junedev.viridium.client.renderers.world.sky;

final class SkyProfileData {

    String parent;
    String[] biomes;

    float[] base;
    Float baseBrightness;
    Float mieG;
    float[] halo;
    SunsetData sunset;
    float[] sunrise;
    Float dayBias;
    Float horizonBase;
    Float horizonSlope;
    Float rayleighScale;
    Float rainDarkening;
    Float horizonDesaturation;
    Float horizonLift;

    static final class SunsetData {

        float[] color;
        Float width;
    }
}
