package net.junedev.viridium.client.textures;

import net.minecraft.util.IIcon;

public class CroppedIcon implements IIcon {

    private final IIcon parent;
    private final String name;

    private final int width;
    private final int height;
    private final int x;
    private final int part;

    public CroppedIcon(IIcon parent, int part, int x, int segmentWidth, int segmentHeight) {
        this.parent = parent;

        this.name = parent.getIconName() + "#" + x + "," + part;

        this.width = segmentWidth;
        this.height = segmentHeight;
        this.x = x;

        this.part = part;
    }

    @Override
    public int getIconWidth() {
        return width;
    }

    @Override
    public int getIconHeight() {
        return height;
    }

    // All these computations must be done at runtime and not in the constructor due to loading order
    private int getX() {
        return x;
    }

    private int getY() {
        return parent.getIconHeight() - (part + 1) * height;
    }

    // Atlas coordinates
    @Override
    public float getMinU() {
        float range = parent.getMaxU() - parent.getMinU();
        return parent.getMinU() + (float) getX() * range / parent.getIconWidth();
    }

    @Override
    public float getMaxU() {
        float range = parent.getMaxU() - parent.getMinU();
        return getMinU() + (float) width * range / parent.getIconWidth();
    }

    @Override
    public float getMinV() {
        float range = parent.getMaxV() - parent.getMinV();
        return parent.getMinV() + (float) getY() * range / parent.getIconHeight();
    }

    @Override
    public float getMaxV() {
        float range = parent.getMaxV() - parent.getMinV();
        return getMinV() + (float) height * range / parent.getIconHeight();
    }

    @Override
    public float getInterpolatedU(double value) {
        return getMinU() + (getMaxU() - getMinU()) * (float) value / 16.0F;
    }

    @Override
    public float getInterpolatedV(double value) {
        return getMinV() + (getMaxV() - getMinV()) * (float) value / 16.0F;
    }

    @Override
    public String getIconName() {
        return name;
    }
}
