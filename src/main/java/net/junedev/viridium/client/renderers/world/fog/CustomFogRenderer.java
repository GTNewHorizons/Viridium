package net.junedev.viridium.client.renderers.world.fog;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraftforge.client.event.EntityViewRenderEvent;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public final class CustomFogRenderer {

    private final float[] rgb = new float[3];

    @SubscribeEvent
    public void onFogColors(EntityViewRenderEvent.FogColors event) {
        if (!SkyState.isActive()) {
            return;
        }

        Minecraft mc = Minecraft.getMinecraft();
        if (mc.theWorld == null) {
            return;
        }

        if (event.block.getMaterial()
            .isLiquid()) {
            return;
        }

        if (event.entity.isPotionActive(Potion.blindness)) {
            return;
        }

        float yaw = event.entity.prevRotationYaw
            + (event.entity.rotationYaw - event.entity.prevRotationYaw) * (float) event.renderPartialTicks;

        SkyMath.horizonColor(SkyState.profile, SkyState.sunX, SkyState.sunY, SkyState.sunZ, SkyState.rain, yaw, rgb);

        event.red = rgb[0];
        event.green = rgb[1];
        event.blue = rgb[2];
    }
}
