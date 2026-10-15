package net.junedev.viridium.client.renderers.world.SkyEditor;

import net.junedev.viridium.client.renderers.world.SkyProfile;

public interface SkyDebugHook {

    SkyProfile overrideProfile();

    float overrideTimeTicks();
}
