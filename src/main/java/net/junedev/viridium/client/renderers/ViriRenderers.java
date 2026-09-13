package net.junedev.viridium.client.renderers;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.junedev.viridium.client.renderers.blocks.BushBlockRenderer;
import net.junedev.viridium.client.renderers.blocks.SmallLogBlockRenderer;

public class ViriRenderers {

    public void preInit(FMLPreInitializationEvent event)
    {
        ViriRenderIds.bushBlockRenderId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new BushBlockRenderer(ViriRenderIds.bushBlockRenderId));

        ViriRenderIds.smallLogBlockRenderId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new SmallLogBlockRenderer(ViriRenderIds.smallLogBlockRenderId));
    }
}
