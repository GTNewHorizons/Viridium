#version 330 core

in vec3 vDir;
out vec4 fragColor;

uniform vec3  uSunDir;
uniform float uRain;

uniform vec3  uBase;
uniform float uMieG;
uniform vec3  uHaloColor;
uniform vec3  uSunsetColor;
uniform float uSunsetWidth;
uniform float uDayBias;
uniform float uHorizonBase;
uniform float uHorizonSlope;
uniform float uRainDarkening;
uniform float uRayleighScale;

const float STAR_SCALE = 120.0;
const float STAR_THRESHOLD = 0.97;

float hash(vec2 p) {
    return fract(sin(dot(p, vec2(12.9898, 78.233))) * 43758.5453);
}

float hash13(vec3 p) {
    return fract(sin(dot(p, vec3(12.9898, 78.233, 37.719))) * 43758.5453);
}

vec3 starField(vec3 d, float px) {
    vec3 p = d * STAR_SCALE;
    vec3 cell = floor(p);

    float h = hash13(cell);
    if (h < STAR_THRESHOLD) {
        return vec3(0.0);
    }

    vec3 pos = vec3(hash13(cell + 1.7), hash13(cell + 3.1), hash13(cell + 5.3)) * 0.6 + 0.2;
    float dist = length(fract(p) - pos);

    float r = max(0.18, px * 0.75);
    float s = 1.0 - smoothstep(r * 0.4, r, dist);

    float brightness = 0.4 + 0.6 * fract(h * 97.0);
    return vec3(0.85, 0.92, 1.0) * s * brightness;
}

// When changing the sky color math, keep skyColor in net.junedev.viridium.client.renderers.world.fog.SkyMath in sync (stars are shader-only).
void main() {
    vec3 dir = normalize(vDir);
    float px = length(fwidth(dir)) * STAR_SCALE;
    float cosTheta = dot(dir, uSunDir);
    float sunHeight = uSunDir.y;

    // Rayleigh scattering (overall sky brightness)
    float rayleigh = uRayleighScale * (1.0 + cosTheta * cosTheta);

    // Mie scattering (Henyey-Greenstein): halo around the sun
    float g2 = uMieG * uMieG;
    float d = max(1.0 + g2 - 2.0 * uMieG * cosTheta, 0.0001);
    float mie = (1.0 - g2) / (4.0 * 3.14159265 * d * sqrt(d));

    // sunsetFactor = how close the sun is to the horizon, band = height mask, azimuth = side of the sun
    float sunsetFactor = max(0.0, 1.0 - abs(sunHeight) * uSunsetWidth);
    float band = 1.0 - smoothstep(0.0, 0.35, abs(dir.y));
    vec2 h = normalize(dir.xz + vec2(1e-5));
    vec2 s = normalize(uSunDir.xz + vec2(1e-5));
    float cosAz = dot(h, s);
    float az = max(0.5 + 0.5 * cosAz, 0.0);
    float azimuth = 0.25 + 0.75 * az * az;

    float dayFactor = clamp(sunHeight * 2.0 + uDayBias, 0.0, 1.0);
    float horizon = clamp(uHorizonBase + dir.y * uHorizonSlope, 0.0, 1.0);

    vec3 col = uBase * rayleigh * dayFactor * horizon
    + uSunsetColor * sunsetFactor * band * azimuth
    + mie * uHaloColor * dayFactor;

    float lum = dot(col, vec3(0.299, 0.587, 0.114));
    float visibility = 1.0 - smoothstep(0.02, 0.22, lum);

    if (visibility > 0.0) {
        vec3 sd = vec3(dir.x * uSunDir.y - dir.y * uSunDir.x,
            dir.x * uSunDir.x + dir.y * uSunDir.y,
            dir.z);
        float elev = mix(0.4, 1.0, smoothstep(0.0, 0.3, dir.y));
        col += starField(sd, px) * visibility * (1.0 - uRain) * elev;
    }

    col *= 1.0 - uRain * uRainDarkening;

    col += (hash(gl_FragCoord.xy) - 0.5) / 255.0;

    fragColor = vec4(clamp(col, 0.0, 1.0), 1.0);
}
