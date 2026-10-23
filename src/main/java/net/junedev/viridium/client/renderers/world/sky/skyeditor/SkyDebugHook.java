package net.junedev.viridium.client.renderers.world.sky.skyeditor;

import net.junedev.viridium.client.renderers.world.sky.SkyProfile;

public interface SkyDebugHook {

    SkyProfile overrideProfile();

    float overrideTimeTicks();
}
