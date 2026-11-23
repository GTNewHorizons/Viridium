package net.junedev.viridium.client.renderers.world.designer_tools;

import net.junedev.viridium.client.renderers.utils.ColorSpaces;

public final class HsvSliders {

    public interface RgbGetter {

        float[] get();
    }

    public interface RgbSetter {

        void set(float red, float green, float blue);
    }

    private static final float EPSILON = 0.0001F;

    private final RgbGetter getter;
    private final RgbSetter setter;

    private float lastHue;
    private float lastSaturation;

    private HsvSliders(RgbGetter getter, RgbSetter setter) {
        this.getter = getter;
        this.setter = setter;
    }

    public static void register(String category, String name, RgbGetter getter, RgbSetter setter) {
        HsvSliders sliders = new HsvSliders(getter, setter);
        DesignerRegistry.register(category, name + "H", 0F, 360F, sliders::hue, sliders::setHue);
        DesignerRegistry.register(category, name + "S", 0F, 1F, sliders::saturation, sliders::setSaturation);
        DesignerRegistry.register(category, name + "V", 0F, 1F, sliders::value, sliders::setValue);
    }

    private float[] currentHsv() {
        float[] rgb = getter.get();
        float[] hsv = ColorSpaces.rgbToHsv(rgb[0], rgb[1], rgb[2]);
        if (hsv[2] > EPSILON) {
            lastSaturation = hsv[1];
            if (hsv[1] > EPSILON) {
                lastHue = hsv[0];
            }
        }
        return new float[] { lastHue, lastSaturation, hsv[2] };
    }

    private float hue() {
        return currentHsv()[0];
    }

    private float saturation() {
        return currentHsv()[1];
    }

    private float value() {
        return currentHsv()[2];
    }

    private void setHue(float hue) {
        float[] hsv = currentHsv();
        lastHue = hue;
        write(hue, hsv[1], hsv[2]);
    }

    private void setSaturation(float saturation) {
        float[] hsv = currentHsv();
        lastSaturation = saturation;
        write(hsv[0], saturation, hsv[2]);
    }

    private void setValue(float value) {
        float[] hsv = currentHsv();
        write(hsv[0], hsv[1], value);
    }

    private void write(float hue, float saturation, float value) {
        float[] rgb = ColorSpaces.hsvToRgb(hue, saturation, value);
        setter.set(rgb[0], rgb[1], rgb[2]);
    }
}
