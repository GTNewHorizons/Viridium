package net.junedev.viridium.client.renderers.world.fog;

import net.junedev.viridium.client.renderers.world.sky.SkyProfile;

public final class SkyState {

    public static SkyProfile profile;
    public static float sunX;
    public static float sunY;
    public static float sunZ;
    public static float rain;
    private static boolean active;

    private SkyState() {}

    public static void update(SkyProfile p, float sx, float sy, float sz, float rainStrength) {
        profile = p;
        sunX = sx;
        sunY = sy;
        sunZ = sz;
        rain = rainStrength;
        active = true;
    }

    public static void invalidate() {
        active = false;
    }

    public static boolean isActive() {
        return active && profile != null;
    }
}
