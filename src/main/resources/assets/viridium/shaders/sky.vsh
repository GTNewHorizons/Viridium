#version 120

varying vec3 vDir;

void main() {
    vDir = gl_Vertex.xyz;
    gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;
}
