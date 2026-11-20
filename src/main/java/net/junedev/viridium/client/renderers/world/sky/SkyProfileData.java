package net.junedev.viridium.client.renderers.world.sky;

final class SkyProfileData {

    String parent;
    String[] biomes;

    float[] base;
    Float baseBrightness;
    Float mieG;
    float[] halo;
    SunsetData sunset;
    Float dayBias;
    Float horizonBase;
    Float horizonSlope;
    Float rayleighScale;
    Float rainDarkening;

    static final class SunsetData {

        float[] color;
        Float width;
    }
}
