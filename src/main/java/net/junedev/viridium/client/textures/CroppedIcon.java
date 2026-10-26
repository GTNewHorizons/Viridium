package net.junedev.viridium.client.textures;

import java.lang.reflect.Field;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.ReflectionHelper;

public class CroppedIcon implements IIcon {

    private static final Field USE_ANISOTROPIC_FILTERING = ReflectionHelper
        .findField(TextureAtlasSprite.class, "useAnisotropicFiltering", "field_147966_k");

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
        return getParentHeight() - (part + 1) * height;
    }

    private int getParentWidth() {
        return parent.getIconWidth() - getParentPadding();
    }

    private int getParentHeight() {
        return parent.getIconHeight() - getParentPadding();
    }

    private int getParentPadding() {
        if (!(parent instanceof TextureAtlasSprite)) return 0;

        // Vanilla adds 16 pixels to the sprite dimensions, but excludes them from its UV bounds.
        // Read the loaded sprite's flag: Angelica disables this padding even when the game setting is enabled.
        try {
            return USE_ANISOTROPIC_FILTERING.getBoolean(parent) ? 16 : 0;
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Cannot read texture sprite padding", e);
        }
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
