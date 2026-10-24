package net.junedev.viridium.client.renderers.world.designer_tools;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import com.cleanroommc.modularui.factory.ClientGUI;
import com.cleanroommc.modularui.screen.ModularScreen;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class DesignerInput {

    public static final KeyBinding TOGGLE = new KeyBinding(
        "key.viridium.designer",
        Keyboard.KEY_O,
        "key.categories.viridium");

    public static void init() {
        ClientRegistry.registerKeyBinding(TOGGLE);
        FMLCommonHandler.instance()
            .bus()
            .register(new DesignerInput());
    }

    @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent event) {
        if (TOGGLE.isPressed()) {
            ClientGUI.open(new DesignerScreen());
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START) return;
        if (!ModularScreen.isActive(DesignerScreen.OWNER, DesignerScreen.NAME)) return;

        GameSettings s = Minecraft.getMinecraft().gameSettings;
        KeyBinding[] movement = { s.keyBindForward, s.keyBindBack, s.keyBindLeft, s.keyBindRight, s.keyBindJump,
            s.keyBindSneak, s.keyBindSprint };
        for (KeyBinding kb : movement) {
            int code = kb.getKeyCode();
            if (code > 0) {
                KeyBinding.setKeyBindState(code, Keyboard.isKeyDown(code));
            }
        }
    }
}
