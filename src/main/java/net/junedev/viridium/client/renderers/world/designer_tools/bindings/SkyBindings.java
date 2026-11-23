package net.junedev.viridium.client.renderers.world.designer_tools.bindings;

import static net.junedev.viridium.client.renderers.world.designer_tools.DesignerRegistry.register;

import net.junedev.viridium.client.renderers.world.designer_tools.DesignerTime;
import net.junedev.viridium.client.renderers.world.designer_tools.HsvSliders;
import net.junedev.viridium.client.renderers.world.sky.SkyProfile;
import net.junedev.viridium.client.renderers.world.sky.SkyProfileHandler;

public class SkyBindings {

    public static final String CATEGORY = "sky";

    public static void bind() {
        DesignerTime.bind();

        SkyProfile p = SkyProfileHandler.DEFAULT;
        String c = "sky";

        HsvSliders.register(c, "base", () -> new float[] { p.baseR, p.baseG, p.baseB }, p::base);

        register(c, "mieG", 0F, 0.99F, () -> p.mieG, v -> p.mieG = v);
        HsvSliders.register(c, "halo", () -> new float[] { p.haloR, p.haloG, p.haloB }, p::halo);

        HsvSliders.register(
            c,
            "sunset",
            () -> new float[] { p.sunsetR, p.sunsetG, p.sunsetB },
            (red, green, blue) -> p.sunset(red, green, blue, p.sunsetWidth));
        HsvSliders.register(c, "sunrise", () -> new float[] { p.sunriseR, p.sunriseG, p.sunriseB }, p::sunrise);
        register(c, "sunsetWidth", 0F, 10F, () -> p.sunsetWidth, v -> p.sunsetWidth = v);

        register(c, "dayBias", 0F, 1F, () -> p.dayBias, v -> p.dayBias = v);
        register(c, "horizonBase", 0F, 2F, () -> p.horizonBase, v -> p.horizonBase = v);
        register(c, "rainDarkening", 0F, 1F, () -> p.rainDarkening, v -> p.rainDarkening = v);
        register(c, "rayleighScale", 0F, 2F, () -> p.rayleighScale, v -> p.rayleighScale = v);
        register(c, "horizonSlope", 0F, 4F, () -> p.horizonSlope, v -> p.horizonSlope = v);
        register(c, "baseBrightness", 0F, 1F, () -> p.baseBrightness, v -> p.baseBrightness = v);
        register(c, "horizonDesaturation", 0F, 1F, () -> p.horizonDesaturation, v -> p.horizonDesaturation = v);
        register(c, "horizonLift", 0F, 0.5F, () -> p.horizonLift, v -> p.horizonLift = v);
    }
}
