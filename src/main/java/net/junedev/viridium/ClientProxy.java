package net.junedev.viridium;

import net.junedev.viridium.client.renderers.ViriRenderers;
import net.junedev.viridium.client.renderers.world.fog.CustomFogRenderer;
import net.junedev.viridium.client.renderers.world.sky.CustomSkyRenderer;
import net.junedev.viridium.client.renderers.world.sky.skyeditor.SkyTool;
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

        SkyTool.init();

        renderers = new ViriRenderers();
        renderers.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

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
