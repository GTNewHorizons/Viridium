package net.junedev.viridium.client.renderers.world.designer_tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.junedev.viridium.Viridium;
import net.junedev.viridium.client.renderers.world.designer_tools.bindings.SkyBindings;
import net.junedev.viridium.client.renderers.world.designer_tools.bindings.WaterBindings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ChatComponentText;

import com.cleanroommc.modularui.api.drawable.IKey;
import com.cleanroommc.modularui.api.widget.IWidget;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.screen.ModularScreen;
import com.cleanroommc.modularui.value.BoolValue;
import com.cleanroommc.modularui.value.DoubleValue;
import com.cleanroommc.modularui.widget.scroll.HorizontalScrollData;
import com.cleanroommc.modularui.widgets.ButtonWidget;
import com.cleanroommc.modularui.widgets.ListWidget;
import com.cleanroommc.modularui.widgets.PageButton;
import com.cleanroommc.modularui.widgets.PagedWidget;
import com.cleanroommc.modularui.widgets.SliderWidget;
import com.cleanroommc.modularui.widgets.ToggleButton;
import com.cleanroommc.modularui.widgets.layout.Flow;

public class DesignerScreen extends ModularScreen {

    public static final String OWNER = "viridium";
    public static final String NAME = "designer";

    public static void init() {
        DesignerTime.bind();
        SkyBindings.bind();
        WaterBindings.bind();
    }

    public DesignerScreen() {
        super(OWNER, buildPanel());
    }

    @Override
    public boolean onKeyPressed(char typedChar, int keyCode) {
        if (keyCode == DesignerInput.TOGGLE.getKeyCode()) {
            close();
            return true;
        }
        return super.onKeyPressed(typedChar, keyCode);
    }

    private static ModularPanel buildPanel() {
        PagedWidget.Controller controller = new PagedWidget.Controller();
        List<String> names = new ArrayList<>();

        ListWidget<IWidget, ?> tabs = new ListWidget<>().scrollDirection(new HorizontalScrollData(false, 3))
            .widthRel(1f)
            .height(17);

        PagedWidget<?> pages = new PagedWidget<>().controller(controller)
            .widthRel(1f)
            .expanded();

        FontRenderer font = Minecraft.getMinecraft().fontRenderer;
        for (Map.Entry<String, List<DesignerVariable>> category : DesignerRegistry.all()
            .entrySet()) {
            int index = names.size();
            names.add(category.getKey());
            int width = font.getStringWidth(category.getKey()) + 10;
            tabs.child(
                new PageButton(index, controller).size(width, 14)
                    .marginRight(2)
                    .overlay(IKey.str(category.getKey())));
            pages.addPage(buildPage(category.getValue()));
        }

        Flow buttons = Flow.row()
            .widthRel(1f)
            .height(16)
            .childPadding(4)
            .child(
                button(
                    "Reset tab",
                    () -> {
                        if (!names.isEmpty()) DesignerRegistry.reset(names.get(controller.getActivePageIndex()));
                    }))
            .child(button("Export", DesignerScreen::export))
            .child(button("Rebuild chunks", DesignerRegistry::rebuildChunks));

        return new ModularPanel(NAME).size(340, 230)
            .right(5)
            .top(5)
            .child(
                Flow.col()
                    .sizeRel(1f)
                    .padding(6)
                    .childPadding(4)
                    .child(tabs)
                    .child(pages)
                    .child(buttons));
    }

    private static IWidget buildPage(List<DesignerVariable> vars) {
        return new ListWidget<>().sizeRel(1f)
            .children(vars.size(), i -> row(vars.get(i)));
    }

    private static IWidget row(DesignerVariable v) {
        Flow row = Flow.row()
            .widthRel(1f)
            .height(14)
            .childPadding(4);
        row.child(
            IKey.str(v.name)
                .asWidget()
                .width(100));

        if (v.type == DesignerVariable.Type.BOOL) {
            row.child(
                new ToggleButton().size(30, 14)
                    .value(new BoolValue.Dynamic(() -> v.get() >= 0.5F, b -> v.set(b ? 1F : 0F)))
                    .overlay(IKey.dynamic(v::display)));
        } else {
            SliderWidget slider = new SliderWidget().bounds(v.min, v.max)
                .value(new DoubleValue.Dynamic(v::get, d -> v.set((float) d)))
                .height(14)
                .expanded();
            if (v.type == DesignerVariable.Type.INT && v.max - v.min <= 32) {
                slider.stopper(1);
            }
            row.child(slider);
            row.child(
                IKey.dynamic(v::display)
                    .asWidget()
                    .width(42));
        }
        return row;
    }

    private static ButtonWidget<?> button(String label, Runnable action) {
        return new ButtonWidget<>().size(80, 16)
            .overlay(IKey.str(label))
            .onMousePressed(mouseButton -> {
                action.run();
                return true;
            });
    }

    private static void export() {
        String text = DesignerRegistry.export();
        GuiScreen.setClipboardString(text);
        Viridium.LOGGER.info("Designer export:\n{}", text);
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Designer values copied to clipboard"));
    }
}
