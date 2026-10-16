package net.junedev.viridium.client.renderers.world.SkyEditor;

import net.junedev.viridium.client.renderers.world.CustomSkyRenderer;
import net.junedev.viridium.client.renderers.world.SkyProfile;
import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import com.cleanroommc.modularui.factory.ClientGUI;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

public final class SkyTool implements SkyDebugHook {

    private static final boolean ENABLED = false;

    static final SkyTool INSTANCE = new SkyTool();

    private static final KeyBinding KEY = new KeyBinding(
        "key.viridium.skytool",
        Keyboard.KEY_F8,
        "key.categories.misc");

    final SkyProfile theme = new SkyProfile();

    boolean timeActive = false;
    boolean autoCycle = false;
    float timeTicks = 6000.0F;
    float cycleSeconds = 30.0F;

    private long lastNanos = 0L;

    private SkyTool() {}

    public static void init() {
        if (!ENABLED) {
            return;
        }
        CustomSkyRenderer.debugHook = INSTANCE;
        ClientRegistry.registerKeyBinding(KEY);
        FMLCommonHandler.instance()
            .bus()
            .register(INSTANCE);
    }

    @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent event) {
        if (KEY.isPressed()) {
            ClientGUI.open(SkyToolGui.createScreen());
        }
    }

    @Override
    public SkyProfile overrideProfile() {
        return theme;
    }

    @Override
    public float overrideTimeTicks() {
        long now = System.nanoTime();
        if (lastNanos != 0L && timeActive && autoCycle) {
            double dt = (now - lastNanos) / 1.0e9;
            timeTicks = (float) ((timeTicks + dt * 24000.0 / cycleSeconds) % 24000.0);
        }
        lastNanos = now;
        return timeActive ? timeTicks : -1.0F;
    }
}
