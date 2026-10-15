#version 120

varying vec3 vDir;

uniform vec3  uSunDir;
uniform float uRain;

uniform vec3  uBase;
uniform float uMieG;
uniform vec3  uHaloColor;
uniform vec3  uSunsetColor;
uniform float uSunsetWidth;
uniform float uDayBias;
uniform float uHorizonBase;
uniform float uRainDarkening;
uniform float uRayleighScale;
uniform float uHorizonSlope;

float hash(vec2 p) {
    return fract(sin(dot(p, vec2(12.9898, 78.233))) * 43758.5453);
}

void main() {
    vec3 dir = normalize(vDir);
    float cosTheta = dot(dir, uSunDir);
    float sunHeight = uSunDir.y;

    float rayleigh = uRayleighScale * (1.0 + cosTheta * cosTheta);

    float g2 = uMieG * uMieG;
    float d = max(1.0 + g2 - 2.0 * uMieG * cosTheta, 0.0001);
    float mie = (1.0 - g2) / (4.0 * 3.14159265 * d * sqrt(d));

    float sunsetFactor = max(0.0, 1.0 - abs(sunHeight) * uSunsetWidth);
    float sunsetDir = 0.5 + 0.5 * cosTheta;

    float dayFactor = clamp(sunHeight * 2.0 + uDayBias, 0.0, 1.0);
    float horizon = clamp(uHorizonBase + dir.y * uHorizonSlope, 0.0, 1.0);

    vec3 col = uBase * rayleigh * dayFactor * horizon
    + uSunsetColor * sunsetFactor * sunsetDir
    + mie * uHaloColor * dayFactor;

    col *= 1.0 - uRain * uRainDarkening;

    col += (hash(gl_FragCoord.xy) - 0.5) / 255.0;

    gl_FragColor = vec4(clamp(col, 0.0, 1.0), 1.0);
}
