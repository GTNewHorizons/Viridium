package net.junedev.viridium.items;

import net.junedev.viridium.Viridium;
import net.minecraft.item.Item;

public class SeedItem extends Item {

    public SeedItem() {
        super();
        this.setCreativeTab(Viridium.VTab);
    }

    @Override
    public Item setTextureName(String texName) {
        this.iconString = Viridium.MOD_ID + ":seeds/" + texName;
        return this;
    }
}
