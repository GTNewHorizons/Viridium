package net.junedev.viridium.client.renderers.world.SkyEditor;

import java.util.Locale;

import net.junedev.viridium.client.renderers.world.SkyProfile;
import net.minecraft.client.gui.GuiScreen;

import com.cleanroommc.modularui.api.GuiAxis;
import com.cleanroommc.modularui.api.IPanelHandler;
import com.cleanroommc.modularui.api.drawable.IKey;
import com.cleanroommc.modularui.api.widget.IWidget;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.screen.ModularScreen;
import com.cleanroommc.modularui.value.DoubleValue;
import com.cleanroommc.modularui.widgets.ButtonWidget;
import com.cleanroommc.modularui.widgets.ListWidget;
import com.cleanroommc.modularui.widgets.SliderWidget;
import com.cleanroommc.modularui.widgets.layout.Flow;

final class SkyToolGui {

    private static final int ROW_HEIGHT = 16;
    private static final int GAP = 4;

    private interface FloatGetter {

        float get();
    }

    private interface FloatSetter {

        void set(float v);
    }

    private interface Action {

        void run();
    }

    private SkyToolGui() {}

    private static Flow sliderRow(String label, float min, float max, final FloatGetter getter,
        final FloatSetter setter) {
        return new Flow(GuiAxis.X).widthRel(1.0F)
            .height(ROW_HEIGHT)
            .marginBottom(GAP)
            .child(
                IKey.str(label)
                    .asWidget()
                    .width(110)
                    .height(ROW_HEIGHT))
            .child(
                new SliderWidget().bounds(min, max)
                    .value(new DoubleValue.Dynamic(() -> getter.get(), v -> setter.set((float) v)))
                    .expanded()
                    .height(ROW_HEIGHT))
            .child(
                IKey.dynamic(() -> String.format(Locale.ROOT, "%.2f", getter.get()))
                    .asWidget()
                    .width(36)
                    .height(ROW_HEIGHT));
    }

    private static IWidget heading(String text) {
        return IKey.str("§l" + text)
            .asWidget()
            .widthRel(1.0F)
            .height(ROW_HEIGHT)
            .marginTop(6)
            .marginBottom(GAP);
    }

    private static ButtonWidget<?> button(IKey label, final Action action) {
        return new ButtonWidget<>().widthRel(1.0F)
            .height(ROW_HEIGHT + 2)
            .marginBottom(GAP)
            .overlay(label)
            .onMousePressed(mouseButton -> {
                action.run();
                return true;
            });
    }

    private static String onOff(boolean b) {
        return b ? "ON" : "OFF";
    }

    private static String toJavaCode(SkyProfile p) {
        return String.format(
            Locale.ROOT,
            "PROFILES.put(\"BIOMNAME\", new SkyProfile()\n" + "        .base(%.3fF, %.3fF, %.3fF)\n"
                + "        .mie(%.3fF)\n"
                + "        .halo(%.3fF, %.3fF, %.3fF)\n"
                + "        .sunset(%.3fF, %.3fF, %.3fF, %.3fF)\n"
                + "        .light(%.3fF, %.3fF, %.3fF)\n"
                + "        .rain(%.3fF));",
            p.baseR,
            p.baseG,
            p.baseB,
            p.mieG,
            p.haloR,
            p.haloG,
            p.haloB,
            p.sunsetR,
            p.sunsetG,
            p.sunsetB,
            p.sunsetWidth,
            p.dayBias,
            p.horizonBase,
            p.rayleighScale,
            p.rainDarkening);
    }

    static ModularScreen createScreen() {
        ModularPanel root = ModularPanel.defaultPanel("viridium_sky_tool_root", 160, 40);

        final IPanelHandler editor = IPanelHandler.simple(root, (parent, player) -> buildEditorPanel(), true);

        root.child(
            new ButtonWidget<>().size(140, 18)
                .pos(10, 11)
                .overlay(IKey.str("Open sky editor"))
                .onMousePressed(mouseButton -> {
                    editor.openPanel();
                    return true;
                }));

        return new ModularScreen("viridium", root);
    }

    private static ModularPanel buildEditorPanel() {
        final SkyTool tool = SkyTool.INSTANCE;
        final SkyProfile t = tool.theme;

        ModularPanel panel = ModularPanel.defaultPanel("viridium_sky_tool_editor", 320, 340);

        panel.child(
            new ListWidget<>().size(300, 320)
                .pos(10, 10)

                // --- Actions ---
                .child(heading("Actions"))
                .child(button(IKey.str("Copy export to clipboard"), () -> GuiScreen.setClipboardString(toJavaCode(t))))
                .child(button(IKey.str("Reset to defaults"), () -> t.copyFrom(new SkyProfile())))

                // --- Time of day ---
                .child(heading("Time of Day"))
                .child(
                    button(
                        IKey.dynamic(() -> "Override time: " + onOff(tool.timeActive)),
                        () -> tool.timeActive = !tool.timeActive))
                .child(button(IKey.dynamic(() -> "Auto cycle: " + onOff(tool.autoCycle)), () -> {
                    tool.autoCycle = !tool.autoCycle;
                    if (tool.autoCycle) {
                        tool.timeActive = true;
                    }
                }))
                .child(sliderRow("Time (ticks)", 0F, 24000F, () -> tool.timeTicks, v -> {
                    tool.timeTicks = v;
                    tool.timeActive = true;
                }))
                .child(sliderRow("Day length (s)", 5F, 300F, () -> tool.cycleSeconds, v -> tool.cycleSeconds = v))

                // --- Base sky ---
                .child(heading("Base Sky"))
                .child(sliderRow("Base red", 0F, 1F, () -> t.baseR, v -> t.baseR = v))
                .child(sliderRow("Base green", 0F, 1F, () -> t.baseG, v -> t.baseG = v))
                .child(sliderRow("Base blue", 0F, 1F, () -> t.baseB, v -> t.baseB = v))
                .child(sliderRow("Brightness", 0F, 3F, () -> t.rayleighScale, v -> t.rayleighScale = v))

                // --- Sun halo ---
                .child(heading("Sun Halo (Haze)"))
                .child(sliderRow("Tightness (g)", 0F, 0.98F, () -> t.mieG, v -> t.mieG = v))
                .child(sliderRow("Halo red", 0F, 1F, () -> t.haloR, v -> t.haloR = v))
                .child(sliderRow("Halo green", 0F, 1F, () -> t.haloG, v -> t.haloG = v))
                .child(sliderRow("Halo blue", 0F, 1F, () -> t.haloB, v -> t.haloB = v))

                // --- Sunset ---
                .child(heading("Sunset"))
                .child(sliderRow("Sunset red", 0F, 1F, () -> t.sunsetR, v -> t.sunsetR = v))
                .child(sliderRow("Sunset green", 0F, 1F, () -> t.sunsetG, v -> t.sunsetG = v))
                .child(sliderRow("Sunset blue", 0F, 1F, () -> t.sunsetB, v -> t.sunsetB = v))
                .child(sliderRow("Width", 0.5F, 10F, () -> t.sunsetWidth, v -> t.sunsetWidth = v))

                // --- Light and weather ---
                .child(heading("Light and Weather"))
                .child(sliderRow("Daylight offset", 0F, 1F, () -> t.dayBias, v -> t.dayBias = v))
                .child(sliderRow("Horizon base", 0F, 1F, () -> t.horizonBase, v -> t.horizonBase = v))
                .child(sliderRow("Rain darkening", 0F, 1F, () -> t.rainDarkening, v -> t.rainDarkening = v)));

        return panel;
    }
}
