package net.junedev.viridium.client.config;

import com.gtnewhorizon.gtnhlib.config.Config;

@Config(modid = "viridium", category = "rendering")
public final class RenderConfig {

    @Config.Comment("Use Viridium's sky instead of the vanilla sky.")
    @Config.DefaultBoolean(true)
    public static boolean customSky;

    @Config.Comment("Tint the water surface by depth, shore distance and biome.")
    @Config.DefaultBoolean(true)
    public static boolean waterTint;

    @Config.Comment("Depth-dependent fog under water.")
    @Config.DefaultBoolean(true)
    public static boolean underwaterFog;

    @Config.Comment("Light shafts under water.")
    @Config.DefaultBoolean(true)
    public static boolean lightRays;

    @Config.Comment("Radius in cells around the camera in which light shafts are drawn. Higher costs more CPU.")
    @Config.DefaultInt(3)
    @Config.RangeInt(min = 1, max = 6)
    public static int lightRayCellRadius;

    @Config.Comment("Radius in blocks over which water colors blend between biomes. Higher costs more during chunk building.")
    @Config.DefaultInt(4)
    @Config.RangeInt(min = 0, max = 8)
    public static int waterBiomeBlendRadius;
}
