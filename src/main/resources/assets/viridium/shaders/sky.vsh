#version 330 core

layout(location = 0) in vec3 aDir;

uniform mat4 uModelView;
uniform mat4 uProjection;

out vec3 vDir;

void main() {
    vDir = aDir;
    gl_Position = uProjection * uModelView * vec4(aDir * 100.0, 1.0);
}
