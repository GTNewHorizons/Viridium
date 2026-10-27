package net.junedev.viridium.mixin;

import com.gtnewhorizon.gtnhmixins.builders.IMixins;
import com.gtnewhorizon.gtnhmixins.builders.MixinBuilder;

public enum ViridiumMixins implements IMixins {

    WATER_DEPTH_TINT(new MixinBuilder("Depth-dependent water tint and shore brightening")
        .addClientMixins("minecraft.MixinRenderBlocks_WaterTint")
        .setPhase(Phase.EARLY)),

    WATER_TEXTURES(new MixinBuilder("Own water textures in the viridium namespace")
        .addClientMixins("minecraft.MixinBlockLiquid_WaterTextures")
        .setPhase(Phase.EARLY));

    private final MixinBuilder builder;

    ViridiumMixins(MixinBuilder builder) {
        this.builder = builder;
    }

    @Override
    public MixinBuilder getBuilder() {
        return builder;
    }
}
