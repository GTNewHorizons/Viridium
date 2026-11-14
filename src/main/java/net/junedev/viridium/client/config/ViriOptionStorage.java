package net.junedev.viridium.client.config;

import com.gtnewhorizon.gtnhlib.config.ConfigurationManager;

import me.jellysquid.mods.sodium.client.gui.options.storage.OptionStorage;

public final class ViriOptionStorage implements OptionStorage<Object> {

    private static final Object DATA = new Object();

    @Override
    public Object getData() {
        return DATA;
    }

    @Override
    public void save() {
        ConfigurationManager.save(RenderConfig.class);
    }
}
