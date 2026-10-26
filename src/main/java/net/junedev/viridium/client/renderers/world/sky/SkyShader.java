package net.junedev.viridium.client.renderers.world.sky;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;

import net.junedev.viridium.Viridium;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class SkyShader {

    private static final ResourceLocation VERTEX_FILE = new ResourceLocation("viridium", "shaders/sky.vsh");
    private static final ResourceLocation FRAGMENT_FILE = new ResourceLocation("viridium", "shaders/sky.fsh");

    private final int program;
    private final int uSunDir, uRain;
    private final int uBase, uMieG, uHaloColor, uSunsetColor;
    private final int uSunsetWidth, uDayBias, uHorizonBase, uRainDarkening, uRayleighScale, uHorizonSlope;
    private final int uModelView, uProjection;
    private final FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
    private int previousProgram;

    private SkyShader(int program) {
        this.program = program;
        uSunDir = loc("uSunDir");
        uRain = loc("uRain");
        uBase = loc("uBase");
        uMieG = loc("uMieG");
        uHaloColor = loc("uHaloColor");
        uSunsetColor = loc("uSunsetColor");
        uSunsetWidth = loc("uSunsetWidth");
        uDayBias = loc("uDayBias");
        uHorizonBase = loc("uHorizonBase");
        uRainDarkening = loc("uRainDarkening");
        uRayleighScale = loc("uRayleighScale");
        uHorizonSlope = loc("uHorizonSlope");

        uModelView = loc("uModelView");
        uProjection = loc("uProjection");
    }

    public static SkyShader create() {
        try {
            if (!OpenGlHelper.shadersSupported) {
                return null;
            }

            String vertexSrc = readShader(VERTEX_FILE);
            String fragmentSrc = readShader(FRAGMENT_FILE);
            if (vertexSrc == null || fragmentSrc == null) {
                return null;
            }

            int vs = compile(GL20.GL_VERTEX_SHADER, vertexSrc);
            int fs = compile(GL20.GL_FRAGMENT_SHADER, fragmentSrc);
            if (vs == 0 || fs == 0) {
                if (vs != 0) GL20.glDeleteShader(vs);
                if (fs != 0) GL20.glDeleteShader(fs);
                return null;
            }

            int program = GL20.glCreateProgram();
            GL20.glAttachShader(program, vs);
            GL20.glAttachShader(program, fs);
            GL20.glLinkProgram(program);

            GL20.glDeleteShader(vs);
            GL20.glDeleteShader(fs);

            if (GL20.glGetProgrami(program, GL20.GL_LINK_STATUS) == GL11.GL_FALSE) {
                Viridium.LOGGER.error("Failed to link Shader: {}", GL20.glGetProgramInfoLog(program, 2048));
                GL20.glDeleteProgram(program);
                return null;
            }

            return new SkyShader(program);
        } catch (Throwable t) {
            Viridium.LOGGER.error("Failed to create Shader: ", t);
            return null;
        }
    }

    private static int compile(int type, String src) {
        int shader = GL20.glCreateShader(type);
        GL20.glShaderSource(shader, src);
        GL20.glCompileShader(shader);
        if (GL20.glGetShaderi(shader, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            Viridium.LOGGER.error("Failed to compile Shader: {}", GL20.glGetShaderInfoLog(shader, 2048));
            GL20.glDeleteShader(shader);
            return 0;
        }
        return shader;
    }

    private static String readShader(ResourceLocation location) {
        try (InputStream in = Minecraft.getMinecraft()
            .getResourceManager()
            .getResource(location)
            .getInputStream(); BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line)
                    .append('\n');
            }
            return sb.toString();
        } catch (IOException e) {
            Viridium.LOGGER.error("Could not read file: {} ({})", location, e);
            return null;
        }
    }

    public void bind(SkyProfile p, float sunX, float sunY, float sunZ, float rain) {
        previousProgram = GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM);
        GL20.glUseProgram(program);
        uploadMatrix(uModelView, GL11.GL_MODELVIEW_MATRIX);
        uploadMatrix(uProjection, GL11.GL_PROJECTION_MATRIX);
        GL20.glUniform3f(uSunDir, sunX, sunY, sunZ);
        GL20.glUniform1f(uRain, rain);

        GL20.glUniform3f(uBase, p.baseR, p.baseG, p.baseB);
        GL20.glUniform1f(uMieG, p.mieG);
        GL20.glUniform3f(uHaloColor, p.haloR, p.haloG, p.haloB);
        GL20.glUniform3f(uSunsetColor, p.sunsetR, p.sunsetG, p.sunsetB);
        GL20.glUniform1f(uSunsetWidth, p.sunsetWidth);
        GL20.glUniform1f(uDayBias, p.dayBias);
        GL20.glUniform1f(uHorizonBase, p.horizonBase);
        GL20.glUniform1f(uRainDarkening, p.rainDarkening);
        GL20.glUniform1f(uRayleighScale, p.rayleighScale);
        GL20.glUniform1f(uHorizonSlope, p.horizonSlope);
    }

    public void unbind() {
        GL20.glUseProgram(previousProgram);
    }

    public void delete() {
        GL20.glDeleteProgram(program);
    }

    private int loc(String name) {
        int location = GL20.glGetUniformLocation(program, name);
        if (location == -1) {
            Viridium.LOGGER.warn("Uniform '{}' not found", name);
        }
        return location;
    }

    private void uploadMatrix(int location, int matrixEnum) {
        matrixBuffer.clear();
        GL11.glGetFloat(matrixEnum, matrixBuffer);
        matrixBuffer.rewind();
        GL20.glUniformMatrix4(location, false, matrixBuffer);
    }
}
