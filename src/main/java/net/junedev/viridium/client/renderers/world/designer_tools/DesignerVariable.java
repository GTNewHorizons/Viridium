package net.junedev.viridium.client.renderers.world.designer_tools;

public class DesignerVariable {

    public enum Type {
        FLOAT,
        INT,
        BOOL
    }

    public interface Getter {

        float get();
    }

    public interface Setter {

        void set(float v);
    }

    public final String name;
    public final Type type;
    public final float min, max;
    public final float defaultValue;
    private final Getter getter;
    private final Setter setter;

    public DesignerVariable(String name, Type type, float min, float max, Getter getter, Setter setter) {
        this.name = name;
        this.type = type;
        this.min = min;
        this.max = max;
        this.getter = getter;
        this.setter = setter;
        this.defaultValue = getter.get();
    }

    public float get() {
        return getter.get();
    }

    public void set(float v) {
        if (type == Type.INT) v = Math.round(v);
        if (type == Type.BOOL) v = v >= 0.5F ? 1F : 0F;
        setter.set(v);
    }

    public void reset() {
        set(defaultValue);
    }

    public String display() {
        return switch (type) {
            case INT -> Integer.toString(Math.round(get()));
            case BOOL -> get() >= 0.5F ? "on" : "off";
            default -> String.format("%.3f", get());
        };
    }

    public String exportValue() {
        return switch (type) {
            case INT -> Integer.toString(Math.round(get()));
            case BOOL -> get() >= 0.5F ? "true" : "false";
            default -> get() + "F";
        };
    }
}
