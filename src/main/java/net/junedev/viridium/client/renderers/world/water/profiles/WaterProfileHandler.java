package net.junedev.viridium.client.renderers.world.water.profiles;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import net.junedev.viridium.Viridium;
import net.junedev.viridium.client.renderers.world.designer_tools.DesignerRegistry;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeGenBase;

public final class WaterProfileHandler {

    private static final WaterProfile DEFAULT = new WaterProfile();

    private static final WaterProfile FALLBACK_MARKER = new WaterProfile().tint(1.0F, 0.0F, 1.0F, 0.6F, 0.0F, 0.6F);
    private static final Map<String, WaterProfile> PROFILES = new ConcurrentHashMap<>();
    private static final Set<String> reportedFallbacks = ConcurrentHashMap.newKeySet();
    // Scratch object for blendedAt(): one per thread, because chunk meshing runs on several worker threads
    private static final ThreadLocal<WaterProfile> blendScratch = ThreadLocal.withInitial(WaterProfile::new);
    public static volatile boolean debugMarkFallback = false;

    static {
        registerAll(new WaterProfile().tint(0.30F, 0.60F, 0.95F, 0.10F, 0.25F, 0.65F), "Ocean", "Deep Ocean");
        register("Swampland", new WaterProfile().tint(0.45F, 0.55F, 0.42F, 0.30F, 0.40F, 0.30F));
    }

    private WaterProfileHandler() {}

    public static WaterProfile at(IBlockAccess world, int x, int y, int z) {
        if (DesignerRegistry.active) {
            return DEFAULT;
        }

        BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
        if (biome == null || biome.biomeName == null) {
            return DEFAULT;
        }

        WaterProfile profile = PROFILES.get(biome.biomeName);
        if (profile != null) {
            return profile;
        }

        if (reportedFallbacks.add(biome.biomeName)) {
            Viridium.LOGGER
                .info("Water: no profile for biome '{}' (id {}), using the default", biome.biomeName, biome.biomeID);
        }
        return debugMarkFallback ? FALLBACK_MARKER : DEFAULT;
    }

    public static WaterProfile blendedAt(IBlockAccess world, int x, int y, int z, int radius) {
        WaterProfile centerProfile = at(world, x, y, z);
        if (radius <= 0 || DesignerRegistry.active) {
            return centerProfile;
        }

        float weightSum = 0.0F;
        float sumShallowR = 0.0F, sumShallowG = 0.0F, sumShallowB = 0.0F;
        float sumDeepR = 0.0F, sumDeepG = 0.0F, sumDeepB = 0.0F;
        float sumDepthRange = 0.0F, sumShoreBrightness = 0.0F;
        boolean allSameAsCenter = true;

        for (int offsetZ = -radius; offsetZ <= radius; offsetZ++) {
            for (int offsetX = -radius; offsetX <= radius; offsetX++) {
                WaterProfile neighborProfile = at(world, x + offsetX, y, z + offsetZ);
                if (neighborProfile != centerProfile) {
                    allSameAsCenter = false;
                }

                float weight = radius + 1 - Math.max(Math.abs(offsetX), Math.abs(offsetZ));
                weightSum += weight;
                sumShallowR += weight * neighborProfile.shallowR;
                sumShallowG += weight * neighborProfile.shallowG;
                sumShallowB += weight * neighborProfile.shallowB;
                sumDeepR += weight * neighborProfile.deepR;
                sumDeepG += weight * neighborProfile.deepG;
                sumDeepB += weight * neighborProfile.deepB;
                sumDepthRange += weight * neighborProfile.depthRange;
                sumShoreBrightness += weight * neighborProfile.shoreBrightness;
            }
        }

        if (allSameAsCenter) {
            return centerProfile;
        }

        WaterProfile blendedProfile = blendScratch.get();
        blendedProfile.copyFrom(centerProfile);
        blendedProfile.shallowR = sumShallowR / weightSum;
        blendedProfile.shallowG = sumShallowG / weightSum;
        blendedProfile.shallowB = sumShallowB / weightSum;
        blendedProfile.deepR = sumDeepR / weightSum;
        blendedProfile.deepG = sumDeepG / weightSum;
        blendedProfile.deepB = sumDeepB / weightSum;
        blendedProfile.depthRange = sumDepthRange / weightSum;
        blendedProfile.shoreBrightness = sumShoreBrightness / weightSum;
        return blendedProfile;
    }

    public static WaterProfile defaultProfile() {
        return DEFAULT;
    }

    public static void register(String biomeName, WaterProfile profile) {
        PROFILES.put(biomeName, profile);
    }

    public static void registerAll(WaterProfile profile, String... biomeNames) {
        for (String biomeName : biomeNames) {
            PROFILES.put(biomeName, profile);
        }
    }

    public static void validate() {
        Set<String> known = new java.util.HashSet<>();
        for (BiomeGenBase biome : BiomeGenBase.getBiomeGenArray()) {
            if (biome != null && biome.biomeName != null) {
                known.add(biome.biomeName);
            }
        }
        for (String key : PROFILES.keySet()) {
            if (!known.contains(key)) {
                Viridium.LOGGER.warn("WaterProfileHandler: no biome named '{}'", key);
            }
        }
    }
}
