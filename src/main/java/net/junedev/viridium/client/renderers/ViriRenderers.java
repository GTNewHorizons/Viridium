package net.junedev.viridium.client.renderers;

import net.junedev.viridium.client.renderers.blocks.BushBlockRenderer;
import net.junedev.viridium.client.renderers.blocks.SmallLogBlockRenderer;
import net.junedev.viridium.client.renderers.blocks.TallPlantBlockRenderer;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ViriRenderers {

    public void preInit(FMLPreInitializationEvent event) {
        ViriRenderIds.bushBlockRenderId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new BushBlockRenderer(ViriRenderIds.bushBlockRenderId));

        ViriRenderIds.smallLogBlockRenderId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new SmallLogBlockRenderer(ViriRenderIds.smallLogBlockRenderId));

        ViriRenderIds.tallPlantBlockRenderId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new TallPlantBlockRenderer(ViriRenderIds.tallPlantBlockRenderId));
    }

}
