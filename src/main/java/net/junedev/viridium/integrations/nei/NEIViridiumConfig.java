package net.junedev.viridium.integrations.nei;

import net.junedev.viridium.Viridium;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

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
