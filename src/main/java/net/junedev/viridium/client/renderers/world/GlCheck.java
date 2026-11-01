package net.junedev.viridium.client.renderers.world;

import net.junedev.viridium.Viridium;

import org.lwjgl.opengl.GL11;

public final class GlCheck {

    private GlCheck() {}

    public static void check(String where) {
        int error;
        while ((error = GL11.glGetError()) != GL11.GL_NO_ERROR) {
            Viridium.LOGGER.warn("GL error {} at {}", error, where);
        }
    }
}
