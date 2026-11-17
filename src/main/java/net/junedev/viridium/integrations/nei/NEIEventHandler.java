package net.junedev.viridium.integrations.nei;

import codechicken.nei.event.NEIRegisterHandlerInfosEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.junedev.viridium.ViriPlants;
import net.junedev.viridium.Viridium;
import net.minecraft.item.ItemStack;

public class NEIEventHandler {

    @SubscribeEvent
    public void registerHandlerInfos(NEIRegisterHandlerInfosEvent event){
        event.registerHandlerInfo(NEIViridiumRecipeHandler.class, "Viridium", Viridium.MOD_ID, builder -> builder.setDisplayStack(new ItemStack(ViriPlants.AMARANTH.getBlocks().sapling)));
    }
}
