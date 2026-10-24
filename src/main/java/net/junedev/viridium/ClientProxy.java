package net.junedev.viridium;

import net.junedev.viridium.client.renderers.ViriRenderers;
import net.junedev.viridium.client.renderers.world.designer_tools.DesignerCommand;
import net.junedev.viridium.client.renderers.world.designer_tools.DesignerInput;
import net.junedev.viridium.client.renderers.world.designer_tools.DesignerScreen;
import net.junedev.viridium.client.renderers.world.fog.CustomFogRenderer;
import net.junedev.viridium.client.renderers.world.sky.CustomSkyRenderer;
import net.junedev.viridium.client.renderers.world.water.UnderwaterFog;
import net.junedev.viridium.client.renderers.world.water.UnderwaterLightRays;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ClientProxy extends CommonProxy {

    private static ViriRenderers renderers;
    private static CustomSkyRenderer skyRenderer;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);

        MinecraftForge.EVENT_BUS.register(new UnderwaterLightRays());
        MinecraftForge.EVENT_BUS.register(new UnderwaterFog());

        DesignerScreen.init();
        DesignerInput.init();

        renderers = new ViriRenderers();
        renderers.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

        ClientCommandHandler.instance.registerCommand(new DesignerCommand());
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new CustomFogRenderer());
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        if (event.world.isRemote && event.world.provider.dimensionId == 0) {
            if (skyRenderer == null) {
                skyRenderer = new CustomSkyRenderer();
            }
            event.world.provider.setSkyRenderer(skyRenderer);
        }
    }
}
