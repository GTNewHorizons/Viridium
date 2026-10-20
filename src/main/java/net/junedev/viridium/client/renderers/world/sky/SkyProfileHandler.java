package net.junedev.viridium.client.renderers.world.sky;

import java.util.LinkedHashMap;
import java.util.Map;

public final class SkyProfileHandler {

    public static final SkyProfile DEFAULT = new SkyProfile();

    private static final Map<String, SkyProfile> PROFILES = new LinkedHashMap<String, SkyProfile>();

    static {
        PROFILES.put(
            "Desert",
            new SkyProfile().base(0.55F, 0.50F, 0.35F)
                .mie(0.85F));

        PROFILES.put(
            "Swampland",
            new SkyProfile().base(0.3F, 0.45F, 0.3F)
                .mie(0.80F));
    }

    public static SkyProfile get(String biomeName) {
        SkyProfile profile = PROFILES.get(biomeName);

        if (profile == null) {
            // Viridium.LOGGER.warn("SkyProfile for Biome {} not found", biomeName);
            return DEFAULT;
        } else {
            return profile;
        }
    }
}
