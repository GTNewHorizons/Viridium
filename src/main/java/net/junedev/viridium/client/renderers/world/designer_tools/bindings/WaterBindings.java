package net.junedev.viridium.client.renderers.world.designer_tools.bindings;

import net.junedev.viridium.client.renderers.world.designer_tools.DesignerRegistry;
import net.junedev.viridium.client.renderers.world.designer_tools.DesignerVariable;
import net.junedev.viridium.client.renderers.world.designer_tools.RayLook;
import net.junedev.viridium.client.renderers.world.water.profiles.WaterProfile;
import net.junedev.viridium.client.renderers.world.water.profiles.WaterProfiles;

public final class WaterBindings {

    private static final String TINT = "Water Tint";
    private static final String FOG = "Water Fog";
    private static final String RAYS = "Water Rays";

    private WaterBindings() {}

    public static void bind() {
        WaterProfile p = WaterProfiles.defaultProfile();

        rgb(
            TINT,
            "shallow",
            () -> p.shallowR,
            v -> p.shallowR = v,
            () -> p.shallowG,
            v -> p.shallowG = v,
            () -> p.shallowB,
            v -> p.shallowB = v);
        rgb(
            TINT,
            "deep",
            () -> p.deepR,
            v -> p.deepR = v,
            () -> p.deepG,
            v -> p.deepG = v,
            () -> p.deepB,
            v -> p.deepB = v);
        DesignerRegistry.register(TINT, "depthRange", 2F, 8F, () -> p.depthRange, v -> p.depthRange = v);
        DesignerRegistry.register(TINT, "shoreBrightness", 0F, 1F, () -> p.shoreBrightness, v -> p.shoreBrightness = v);
        DesignerRegistry.registerInt(TINT, "shoreRange", 0, 6, () -> p.shoreRange, v -> p.shoreRange = (int) v);

        rgb(
            FOG,
            "fogShallow",
            () -> p.fogShallowR,
            v -> p.fogShallowR = v,
            () -> p.fogShallowG,
            v -> p.fogShallowG = v,
            () -> p.fogShallowB,
            v -> p.fogShallowB = v);
        rgb(
            FOG,
            "fogDeep",
            () -> p.fogDeepR,
            v -> p.fogDeepR = v,
            () -> p.fogDeepG,
            v -> p.fogDeepG = v,
            () -> p.fogDeepB,
            v -> p.fogDeepB = v);
        DesignerRegistry
            .register(FOG, "fogDensShallow", 0F, 0.5F, () -> p.fogDensityShallow, v -> p.fogDensityShallow = v);
        DesignerRegistry.register(FOG, "fogDensDeep", 0F, 0.5F, () -> p.fogDensityDeep, v -> p.fogDensityDeep = v);
        DesignerRegistry.register(FOG, "fogDepthRange", 4F, 64F, () -> p.fogDepthRange, v -> p.fogDepthRange = v);

        DesignerRegistry.register(RAYS, "godRayStrength", 0F, 1.5F, () -> p.godRayStrength, v -> p.godRayStrength = v);
        DesignerRegistry.register(RAYS, "shaftChance", 0F, 1F, () -> RayLook.shaftChance, v -> RayLook.shaftChance = v);
        DesignerRegistry.register(RAYS, "minWidth", 0.2F, 6F, () -> RayLook.minWidth, v -> RayLook.minWidth = v);
        DesignerRegistry.register(RAYS, "maxWidth", 0.2F, 8F, () -> RayLook.maxWidth, v -> RayLook.maxWidth = v);
        DesignerRegistry.register(RAYS, "spread", 1F, 5F, () -> RayLook.spread, v -> RayLook.spread = v);
        DesignerRegistry.register(RAYS, "alphaScale", 0F, 1F, () -> RayLook.alphaScale, v -> RayLook.alphaScale = v);
        DesignerRegistry
            .register(RAYS, "glowStrength", 0F, 1F, () -> RayLook.glowStrength, v -> RayLook.glowStrength = v);
        DesignerRegistry.register(
            RAYS,
            "glowRadiusScale",
            0.5F,
            4F,
            () -> RayLook.glowRadiusScale,
            v -> RayLook.glowRadiusScale = v);
    }

    private static void rgb(String category, String label, DesignerVariable.Getter getR, DesignerVariable.Setter setR,
        DesignerVariable.Getter getG, DesignerVariable.Setter setG, DesignerVariable.Getter getB,
        DesignerVariable.Setter setB) {
        DesignerRegistry.register(category, label + "R", 0F, 1F, getR, setR);
        DesignerRegistry.register(category, label + "G", 0F, 1F, getG, setG);
        DesignerRegistry.register(category, label + "B", 0F, 1F, getB, setB);
    }
}
