package net.junedev.viridium.client.renderers.world.sky;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public final class SkyDome {

    private final int vao;
    private final int vbo;
    private final int ebo;
    private final int indexCount;

    public SkyDome(int latSegments, int longSegments) {
        int vertexCount = (latSegments + 1) * (longSegments + 1);
        FloatBuffer vertices = BufferUtils.createFloatBuffer(vertexCount * 3);

        for (int i = 0; i <= latSegments; i++) {
            double lat = Math.PI * (-0.5 + (double) i / latSegments);
            float y = (float) Math.sin(lat);
            float r = (float) Math.cos(lat);
            for (int j = 0; j <= longSegments; j++) {
                double lon = 2.0 * Math.PI * j / longSegments;
                vertices.put((float) Math.cos(lon) * r);
                vertices.put(y);
                vertices.put((float) Math.sin(lon) * r);
            }
        }
        vertices.flip();

        indexCount = latSegments * longSegments * 6;
        ShortBuffer indices = BufferUtils.createShortBuffer(indexCount);
        int stride = longSegments + 1;
        for (int i = 0; i < latSegments; i++) {
            for (int j = 0; j < longSegments; j++) {
                short a = (short) (i * stride + j);
                short b = (short) (a + 1);
                short c = (short) (a + stride);
                short d = (short) (c + 1);
                indices.put(a)
                    .put(c)
                    .put(b);
                indices.put(b)
                    .put(c)
                    .put(d);
            }
        }
        indices.flip();

        int prevVao = GL11.glGetInteger(GL30.GL_VERTEX_ARRAY_BINDING);
        int prevArrayBuffer = GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING);

        vao = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vao);

        vbo = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertices, GL15.GL_STATIC_DRAW);
        GL20.glEnableVertexAttribArray(0);
        GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0L);

        ebo = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ebo);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indices, GL15.GL_STATIC_DRAW);

        GL30.glBindVertexArray(prevVao);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, prevArrayBuffer);
    }

    public void draw() {
        int prevVao = GL11.glGetInteger(GL30.GL_VERTEX_ARRAY_BINDING);
        GL30.glBindVertexArray(vao);
        GL11.glDrawElements(GL11.GL_TRIANGLES, indexCount, GL11.GL_UNSIGNED_SHORT, 0L);
        GL30.glBindVertexArray(prevVao);
    }

    public void delete() {
        GL30.glDeleteVertexArrays(vao);
        GL15.glDeleteBuffers(vbo);
        GL15.glDeleteBuffers(ebo);
    }
}
