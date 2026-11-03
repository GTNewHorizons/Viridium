package net.junedev.viridium.client.config;

import net.irisshaders.iris.api.v0.IrisApi;

public final class RenderState {

    private RenderState() {}

    public static boolean shaderPackActive() {
        return IrisApi.getInstance()
            .isShaderPackInUse();
    }

    public static boolean customSky() {
        return RenderConfig.customSky && !shaderPackActive();
    }

    public static boolean skyFog() {
        return customSky();
    }

    public static boolean waterTint() {
        return RenderConfig.waterTint;
    }

    public static boolean underwaterFog() {
        return RenderConfig.underwaterFog && !shaderPackActive();
    }

    public static boolean lightRays() {
        return RenderConfig.lightRays && !shaderPackActive();
    }
}
