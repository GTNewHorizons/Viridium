package net.junedev.viridium.integrations.nei;

import net.junedev.viridium.ViriPlants;
import net.junedev.viridium.Viridium;
import net.minecraft.item.ItemStack;

import codechicken.nei.event.NEIRegisterHandlerInfosEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class NEIEventHandler {

    @SubscribeEvent
    public void registerHandlerInfos(NEIRegisterHandlerInfosEvent event) {
        event.registerHandlerInfo(NEIViridiumRecipeHandler.class, "Viridium", Viridium.MOD_ID, builder -> {
            builder.setDisplayStack(new ItemStack(ViriPlants.AMARANTH.getBlocks().sapling));
            builder.setWidth(146);
            builder.setHeight(180);
            builder.setMultipleWidgetsAllowed(false);
        });
    }
}
