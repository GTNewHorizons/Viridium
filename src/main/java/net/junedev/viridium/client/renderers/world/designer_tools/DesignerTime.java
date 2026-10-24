package net.junedev.viridium.client.renderers.world.designer_tools;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class DesignerTime {

    public static boolean frozen = false;
    public static int dayTime = 6000;

    public static void bind() {
        DesignerRegistry.registerInt("sky", "dayTime", 0, 23999, DesignerTime::currentDayTime, v -> {
            dayTime = (int) v;
            frozen = true;
        });
        DesignerRegistry.registerBool("sky", "freezeTime", () -> frozen, v -> frozen = v);

        FMLCommonHandler.instance()
            .bus()
            .register(new DesignerTime());
    }

    private static float currentDayTime() {
        World w = Minecraft.getMinecraft().theWorld;
        if (frozen || w == null) return dayTime;
        return w.getWorldTime() % 24000L;
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (!DesignerRegistry.active || !frozen) return;

        World w = Minecraft.getMinecraft().theWorld;
        if (w == null) return;
        long day = w.getWorldTime() / 24000L;
        w.setWorldTime(day * 24000L + dayTime);
    }
}
