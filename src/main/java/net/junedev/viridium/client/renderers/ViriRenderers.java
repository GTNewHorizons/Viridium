package net.junedev.viridium.client.renderers;

import net.junedev.viridium.client.renderers.blocks.BushBlockRenderer;
import net.junedev.viridium.client.renderers.blocks.FernBlockRenderer;
import net.junedev.viridium.client.renderers.blocks.GroundPlaneBlockRenderer;
import net.junedev.viridium.client.renderers.blocks.SmallLogBlockRenderer;
import net.junedev.viridium.client.renderers.blocks.TallPlantBlockRenderer;
import net.junedev.viridium.client.renderers.blocks.XShapedPlantBlockRenderer;
import net.junedev.viridium.client.renderers.world.designer_tools.DesignerCommand;
import net.junedev.viridium.client.renderers.world.designer_tools.DesignerInput;
import net.junedev.viridium.client.renderers.world.designer_tools.DesignerScreen;
import net.junedev.viridium.client.renderers.world.fog.CustomFogRenderer;
import net.junedev.viridium.client.renderers.world.water.UnderwaterFog;
import net.junedev.viridium.client.renderers.world.water.UnderwaterLightRays;
import net.junedev.viridium.client.renderers.world.water.profiles.WaterProfileHandler;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/** Everything client-side that is registered at startup, in three separate sections. */
public class ViriRenderers {

    public void preInit(FMLPreInitializationEvent event) {
        registerBlockRenderers();
        registerWorldEffects();
        registerDesigner();
    }

    public void init(FMLInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new DesignerCommand());
    }

    public void loadComplete(FMLLoadCompleteEvent event) {
        WaterProfileHandler.validate();
    }

    private void registerBlockRenderers() {
        ViriRenderIds.bushBlockRenderId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new BushBlockRenderer(ViriRenderIds.bushBlockRenderId));

        ViriRenderIds.smallLogBlockRenderId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new SmallLogBlockRenderer(ViriRenderIds.smallLogBlockRenderId));

        ViriRenderIds.tallPlantBlockRenderId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new TallPlantBlockRenderer(ViriRenderIds.tallPlantBlockRenderId));

        ViriRenderIds.xShapePlantBlockRenderId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new XShapedPlantBlockRenderer(ViriRenderIds.xShapePlantBlockRenderId));

        ViriRenderIds.fernBlockRenderId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new FernBlockRenderer(ViriRenderIds.fernBlockRenderId));

        ViriRenderIds.groundPlaneBlockRenderId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new GroundPlaneBlockRenderer(ViriRenderIds.groundPlaneBlockRenderId));
    }

    private void registerWorldEffects() {
        MinecraftForge.EVENT_BUS.register(new CustomFogRenderer());
        MinecraftForge.EVENT_BUS.register(new UnderwaterFog());
        MinecraftForge.EVENT_BUS.register(new UnderwaterLightRays());
    }

    private void registerDesigner() {
        DesignerScreen.init();
        DesignerInput.init();
    }
}
