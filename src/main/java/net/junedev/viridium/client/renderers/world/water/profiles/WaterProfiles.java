package net.junedev.viridium.client.renderers.world.water.profiles;

import net.minecraft.world.IBlockAccess;

public final class WaterProfiles {

    // TEMPORARY: one profile for everything, until the new registry exists
    private static final WaterProfile DEFAULT = new WaterProfile();

    private WaterProfiles() {}

    public static WaterProfile at(IBlockAccess world, int x, int y, int z) {
        return DEFAULT;
    }

    public static WaterProfile defaultProfile() {
        return DEFAULT;
    }
}
