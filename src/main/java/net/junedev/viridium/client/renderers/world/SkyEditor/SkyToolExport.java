package net.junedev.viridium.client.renderers.world.SkyEditor;

import net.junedev.viridium.client.renderers.world.SkyProfile;

import java.util.Locale;

final class SkyToolExport {

    private SkyToolExport() {
    }

    static String toJavaCode(String biomeName, SkyProfile p) {
        return String.format(Locale.ROOT,
            "PROFILES.put(\"%s\", new SkyProfile()\n"
                + "        .base(%.3fF, %.3fF, %.3fF)\n"
                + "        .mie(%.3fF)\n"
                + "        .halo(%.3fF, %.3fF, %.3fF)\n"
                + "        .sunset(%.3fF, %.3fF, %.3fF, %.3fF)\n"
                + "        .light(%.3fF, %.3fF, %.3fF)\n"
                + "        .rain(%.3fF));",
            biomeName,
            p.baseR, p.baseG, p.baseB,
            p.mieG,
            p.haloR, p.haloG, p.haloB,
            p.sunsetR, p.sunsetG, p.sunsetB, p.sunsetWidth,
            p.dayBias, p.horizonBase, p.rayleighScale,
            p.rainDarkening);
    }
}
