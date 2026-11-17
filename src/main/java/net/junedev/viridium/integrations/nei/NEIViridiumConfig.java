package net.junedev.viridium.integrations.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import net.junedev.viridium.Viridium;
import net.minecraftforge.common.MinecraftForge;

public class NEIViridiumConfig implements IConfigureNEI {
    @Override
    public void loadConfig() {
        API.registerRecipeHandler(new NEIViridiumRecipeHandler());
    }

    @Override
    public String getName() {
        return Viridium.MOD_ID;
    }

    @Override
    public String getVersion() {
        return "0.0.1";
    }
}
