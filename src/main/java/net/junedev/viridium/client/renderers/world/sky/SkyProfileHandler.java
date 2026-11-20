package net.junedev.viridium.client.renderers.world.sky;

import java.util.HashMap;
import java.util.Map;

public final class SkyProfileHandler {

    public static final SkyProfile DEFAULT = new SkyProfile();

    private static final Map<String, SkyProfile> PROFILES = new HashMap<>();

    public static SkyProfile get(String biomeName) {
        SkyProfile profile = PROFILES.get(biomeName);
        return profile != null ? profile : DEFAULT;
    }

    static void clear() {
        PROFILES.clear();
    }

    static void put(String biomeName, SkyProfile profile) {
        PROFILES.put(biomeName, profile);
    }
}
