package net.junedev.viridium.client.renderers.world.sky;

import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import net.junedev.viridium.Viridium;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.util.ResourceLocation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/** Reads assets/viridium/shaders/profiles/sky/*.json at startup and on every F3+T. */
public final class SkyProfileLoader implements IResourceManagerReloadListener {

    private static final String FOLDER = "shaders/profiles/sky/";
    private static final Gson GSON = new Gson();
    private static final Gson PRETTY_GSON = new GsonBuilder().setPrettyPrinting()
        .create();

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {
        SkyProfileData defaultData = read(resourceManager, "_default", SkyProfileData.class);
        IndexData index = read(resourceManager, "index", IndexData.class);
        if (defaultData == null || index == null) {
            return;
        }

        SkyProfileHandler.DEFAULT.copyFrom(new SkyProfile());
        applyFile(SkyProfileHandler.DEFAULT, defaultData);

        SkyProfileHandler.clear();
        for (String fileName : index.profiles) {
            if (fileName.equals("_default")) {
                continue;
            }
            SkyProfileData data = read(resourceManager, fileName, SkyProfileData.class);
            if (data == null || data.biomes == null) {
                continue;
            }
            if (data.biomes.length == 0) {
                Viridium.LOGGER.warn("Sky profiles: {}.json has no biomes", fileName);
            }
            SkyProfile profile = new SkyProfile(SkyProfileHandler.DEFAULT);
            applyFile(profile, data);
            for (String biomeName : data.biomes) {
                SkyProfileHandler.put(biomeName, profile);
            }
        }
    }

    private static void applyFile(SkyProfile profile, SkyProfileData data) {
        if (data.base != null) {
            profile.base(data.base[0], data.base[1], data.base[2]);
        }
        if (data.baseBrightness != null) profile.baseBrightness = data.baseBrightness;
        if (data.mieG != null) profile.mieG = data.mieG;
        if (data.halo != null) {
            profile.halo(data.halo[0], data.halo[1], data.halo[2]);
        }
        if (data.sunset != null) {
            if (data.sunset.color != null) {
                profile.sunsetR = data.sunset.color[0];
                profile.sunsetG = data.sunset.color[1];
                profile.sunsetB = data.sunset.color[2];
            }
            if (data.sunset.width != null) profile.sunsetWidth = data.sunset.width;
        }
        if (data.sunrise != null) {
            profile.sunrise(data.sunrise[0], data.sunrise[1], data.sunrise[2]);
        }
        if (data.dayBias != null) profile.dayBias = data.dayBias;
        if (data.horizonBase != null) profile.horizonBase = data.horizonBase;
        if (data.horizonSlope != null) profile.horizonSlope = data.horizonSlope;
        if (data.rayleighScale != null) profile.rayleighScale = data.rayleighScale;
        if (data.rainDarkening != null) profile.rainDarkening = data.rainDarkening;
        if (data.horizonDesaturation != null) profile.horizonDesaturation = data.horizonDesaturation;
        if (data.horizonLift != null) profile.horizonLift = data.horizonLift;
    }

    private static <T> T read(IResourceManager resourceManager, String fileName, Class<T> type) {
        ResourceLocation location = new ResourceLocation(Viridium.MOD_ID, FOLDER + fileName + ".json");
        try (Reader reader = new InputStreamReader(
            resourceManager.getResource(location)
                .getInputStream(),
            StandardCharsets.UTF_8)) {
            return GSON.fromJson(reader, type);
        } catch (Exception exception) {
            Viridium.LOGGER.error("Sky profiles: could not read {}", location, exception);
            return null;
        }
    }

    private static final class IndexData {

        String[] profiles;
    }

    public static String toJson(SkyProfile profile) {
        SkyProfileData data = new SkyProfileData();
        data.biomes = new String[0];
        data.base = color(profile.baseR, profile.baseG, profile.baseB);
        data.baseBrightness = round(profile.baseBrightness);
        data.mieG = round(profile.mieG);
        data.halo = color(profile.haloR, profile.haloG, profile.haloB);
        data.sunset = new SkyProfileData.SunsetData();
        data.sunset.color = color(profile.sunsetR, profile.sunsetG, profile.sunsetB);
        data.sunset.width = round(profile.sunsetWidth);
        data.sunrise = color(profile.sunriseR, profile.sunriseG, profile.sunriseB);
        data.dayBias = round(profile.dayBias);
        data.horizonBase = round(profile.horizonBase);
        data.horizonSlope = round(profile.horizonSlope);
        data.rayleighScale = round(profile.rayleighScale);
        data.rainDarkening = round(profile.rainDarkening);
        data.horizonDesaturation = round(profile.horizonDesaturation);
        data.horizonLift = round(profile.horizonLift);
        return PRETTY_GSON.toJson(data);
    }

    private static float round(float value) {
        return Math.round(value * 1000F) / 1000F;
    }

    private static float[] color(float red, float green, float blue) {
        return new float[] { round(red), round(green), round(blue) };
    }
}
