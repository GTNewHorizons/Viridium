package net.junedev.viridium.client.renderers.world.designer_tools;

import java.util.*;

import net.minecraft.client.Minecraft;

public class DesignerRegistry {

    public interface BoolGetter {

        boolean get();
    }

    public interface BoolSetter {

        void set(boolean v);
    }

    public static volatile boolean active = false;

    private static final Map<String, List<DesignerVariable>> categories = new LinkedHashMap<>();

    private static void add(String category, DesignerVariable v) {
        categories.computeIfAbsent(category, k -> new ArrayList<>())
            .add(v);
    }

    public static void register(String c, String name, float min, float max, DesignerVariable.Getter g,
        DesignerVariable.Setter s) {
        add(c, new DesignerVariable(name, DesignerVariable.Type.FLOAT, min, max, g, s));
    }

    public static void registerInt(String c, String name, int min, int max, DesignerVariable.Getter g,
        DesignerVariable.Setter s) {
        add(c, new DesignerVariable(name, DesignerVariable.Type.INT, min, max, g, s));
    }

    public static void registerBool(String c, String name, BoolGetter g, BoolSetter s) {
        add(
            c,
            new DesignerVariable(
                name,
                DesignerVariable.Type.BOOL,
                0F,
                1F,
                () -> g.get() ? 1F : 0F,
                v -> s.set(v >= 0.5F)));
    }

    public static Map<String, List<DesignerVariable>> all() {
        return categories;
    }

    public static void reset(String category) {
        List<DesignerVariable> vars = categories.get(category);
        if (vars == null) return;
        for (DesignerVariable v : vars) v.reset();
    }

    public static String export() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, List<DesignerVariable>> cat : categories.entrySet()) {
            sb.append("// ")
                .append(cat.getKey())
                .append('\n');
            for (DesignerVariable v : cat.getValue()) {
                sb.append(v.name)
                    .append(" = ")
                    .append(v.exportValue())
                    .append(";\n");
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void setActive(boolean on) {
        if (active == on) return;
        active = on;
        rebuildChunks();
    }

    public static void rebuildChunks() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.theWorld != null) mc.renderGlobal.loadRenderers();
    }
}
