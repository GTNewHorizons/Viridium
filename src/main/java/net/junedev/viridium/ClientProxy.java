package net.junedev.viridium;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.junedev.viridium.client.renderers.ViriRenderers;

public class ClientProxy extends CommonProxy {

    private static ViriRenderers renderers;

    // Override CommonProxy methods here, if you want a different behaviour on the client (e.g. registering renders).
    // Don't forget to call the super methods as well.
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);

        renderers = new ViriRenderers();
        renderers.preInit(event);
    }
}
