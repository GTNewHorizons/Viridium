package net.junedev.viridium.items;

import java.util.HashMap;
import java.util.Map;

public final class ScientificNameRegistry {

    private static final Map<String, String> SCIENTIFIC_NAMES = new HashMap<>();

    public static void preInit() {
        register("viridium:black_spruce", "Picea Mariana");
        register("viridium:blackthorn", "Prunus Spinosa");

        register("viridium:ainselu", "Rubus Ellipticus");

        register("viridium:african_lily", "Agapanthoideae");
    }

    private static void register(String id, String name) {
        SCIENTIFIC_NAMES.put(id, name);
    }

    public static String getScientificName(String id) {
        return SCIENTIFIC_NAMES.getOrDefault(id, "Tonto Quien Lo Lea");
    }
}
