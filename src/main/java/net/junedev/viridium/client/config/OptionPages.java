package net.junedev.viridium.client.config;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.resources.I18n;

import com.google.common.collect.ImmutableList;

import me.jellysquid.mods.sodium.client.gui.options.OptionFlag;
import me.jellysquid.mods.sodium.client.gui.options.OptionGroup;
import me.jellysquid.mods.sodium.client.gui.options.OptionImpact;
import me.jellysquid.mods.sodium.client.gui.options.OptionImpl;
import me.jellysquid.mods.sodium.client.gui.options.OptionPage;
import me.jellysquid.mods.sodium.client.gui.options.control.ControlValueFormatter;
import me.jellysquid.mods.sodium.client.gui.options.control.SliderControl;
import me.jellysquid.mods.sodium.client.gui.options.control.TickBoxControl;

public final class OptionPages {

    private static final ViriOptionStorage STORAGE = new ViriOptionStorage();

    private OptionPages() {}

    public static OptionPage viridium() {
        List<OptionGroup> groups = new ArrayList<>();

        groups.add(
            OptionGroup.createBuilder()
                .add(
                    OptionImpl.createBuilder(boolean.class, STORAGE)
                        .setName(I18n.format("options.viridium.custom_sky"))
                        .setTooltip(I18n.format("options.viridium.custom_sky.tooltip"))
                        .setControl(TickBoxControl::new)
                        .setBinding((data, enabled) -> RenderConfig.customSky = enabled, data -> RenderConfig.customSky)
                        .setImpact(OptionImpact.LOW)
                        .build())
                .build());

        groups.add(
            OptionGroup.createBuilder()
                .add(
                    OptionImpl.createBuilder(boolean.class, STORAGE)
                        .setName(I18n.format("options.viridium.water_tint"))
                        .setTooltip(I18n.format("options.viridium.water_tint.tooltip"))
                        .setControl(TickBoxControl::new)
                        .setBinding((data, enabled) -> RenderConfig.waterTint = enabled, data -> RenderConfig.waterTint)
                        .setImpact(OptionImpact.MEDIUM)
                        .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                        .build())
                .add(
                    OptionImpl.createBuilder(int.class, STORAGE)
                        .setName(I18n.format("options.viridium.water_blend"))
                        .setTooltip(I18n.format("options.viridium.water_blend.tooltip"))
                        .setControl(option -> new SliderControl(option, 0, 8, 1, ControlValueFormatter.number()))
                        .setBinding(
                            (data, enabled) -> RenderConfig.waterBiomeBlendRadius = enabled,
                            data -> RenderConfig.waterBiomeBlendRadius)
                        .setImpact(OptionImpact.MEDIUM)
                        .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                        .build())
                .build());

        groups.add(
            OptionGroup.createBuilder()
                .add(
                    OptionImpl.createBuilder(boolean.class, STORAGE)
                        .setName(I18n.format("options.viridium.underwater_fog"))
                        .setTooltip(I18n.format("options.viridium.underwater_fog.tooltip"))
                        .setControl(TickBoxControl::new)
                        .setBinding(
                            (data, enabled) -> RenderConfig.underwaterFog = enabled,
                            data -> RenderConfig.underwaterFog)
                        .setImpact(OptionImpact.LOW)
                        .build())
                .add(
                    OptionImpl.createBuilder(boolean.class, STORAGE)
                        .setName(I18n.format("options.viridium.light_rays"))
                        .setTooltip(I18n.format("options.viridium.light_rays.tooltip"))
                        .setControl(TickBoxControl::new)
                        .setBinding((data, enabled) -> RenderConfig.lightRays = enabled, data -> RenderConfig.lightRays)
                        .setImpact(OptionImpact.LOW)
                        .build())
                .add(
                    OptionImpl.createBuilder(int.class, STORAGE)
                        .setName(I18n.format("options.viridium.light_ray_radius"))
                        .setTooltip(I18n.format("options.viridium.light_ray_radius.tooltip"))
                        .setControl(option -> new SliderControl(option, 1, 6, 1, ControlValueFormatter.number()))
                        .setBinding(
                            (data, radius) -> RenderConfig.lightRayCellRadius = radius,
                            data -> RenderConfig.lightRayCellRadius)
                        .setImpact(OptionImpact.MEDIUM)
                        .build())
                .build());

        return new OptionPage(I18n.format("options.viridium.page"), ImmutableList.copyOf(groups));
    }
}
