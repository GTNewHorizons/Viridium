package net.junedev.viridium.client.textures;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.IIcon;

public class CroppedIcon implements IIcon {

    private final IIcon parent;
    private final String name;

    private final int width;
    private final int height;
    private final int x;
    private final int part;
    private final int parentPadding;

    public CroppedIcon(IIcon parent, int part, int x, int segmentWidth, int segmentHeight) {
        this.parent = parent;
        // Vanilla adds pixels on each side for Anisotropic Filtering, but its UV bounds already exclude them.
        // Capture the setting during icon registration, before this atlas is stitched.
        this.parentPadding = parent instanceof TextureAtlasSprite
            && Minecraft.getMinecraft().gameSettings.anisotropicFiltering > 1 ? 16 : 0;

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
        return getParentHeight() - (part + 1) * height;
    }

    private int getParentWidth() {
        return parent.getIconWidth() - parentPadding;
    }

    private int getParentHeight() {
        return parent.getIconHeight() - parentPadding;
    }

    // Atlas coordinates
    @Override
    public float getMinU() {
        float range = parent.getMaxU() - parent.getMinU();
        return parent.getMinU() + (float) getX() * range / getParentWidth();
    }

    @Override
    public float getMaxU() {
        float range = parent.getMaxU() - parent.getMinU();
        return getMinU() + (float) width * range / getParentWidth();
    }

    @Override
    public float getMinV() {
        float range = parent.getMaxV() - parent.getMinV();
        return parent.getMinV() + (float) getY() * range / getParentHeight();
    }

    @Override
    public float getMaxV() {
        float range = parent.getMaxV() - parent.getMinV();
        return getMinV() + (float) height * range / getParentHeight();
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
