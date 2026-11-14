package net.junedev.viridium.client.renderers.world.designer_tools.bindings;

import static net.junedev.viridium.client.renderers.world.designer_tools.DesignerRegistry.register;

import net.junedev.viridium.client.renderers.world.designer_tools.DesignerTime;
import net.junedev.viridium.client.renderers.world.sky.SkyProfile;
import net.junedev.viridium.client.renderers.world.sky.SkyProfileHandler;

public class SkyBindings {

    public static void bind() {
        DesignerTime.bind();

        SkyProfile p = SkyProfileHandler.DEFAULT;
        String c = "sky";

        register(c, "baseR", 0F, 1F, () -> p.baseR, v -> p.baseR = v);
        register(c, "baseG", 0F, 1F, () -> p.baseG, v -> p.baseG = v);
        register(c, "baseB", 0F, 1F, () -> p.baseB, v -> p.baseB = v);

        register(c, "mieG", 0F, 0.99F, () -> p.mieG, v -> p.mieG = v);
        register(c, "haloR", 0F, 1F, () -> p.haloR, v -> p.haloR = v);
        register(c, "haloG", 0F, 1F, () -> p.haloG, v -> p.haloG = v);
        register(c, "haloB", 0F, 1F, () -> p.haloB, v -> p.haloB = v);

        register(c, "sunsetR", 0F, 1F, () -> p.sunsetR, v -> p.sunsetR = v);
        register(c, "sunsetG", 0F, 1F, () -> p.sunsetG, v -> p.sunsetG = v);
        register(c, "sunsetB", 0F, 1F, () -> p.sunsetB, v -> p.sunsetB = v);
        register(c, "sunsetWidth", 0F, 10F, () -> p.sunsetWidth, v -> p.sunsetWidth = v);

        register(c, "dayBias", 0F, 1F, () -> p.dayBias, v -> p.dayBias = v);
        register(c, "horizonBase", 0F, 2F, () -> p.horizonBase, v -> p.horizonBase = v);
        register(c, "rainDarkening", 0F, 1F, () -> p.rainDarkening, v -> p.rainDarkening = v);
        register(c, "rayleighScale", 0F, 2F, () -> p.rayleighScale, v -> p.rayleighScale = v);
        register(c, "horizonSlope", 0F, 4F, () -> p.horizonSlope, v -> p.horizonSlope = v);
    }
}
