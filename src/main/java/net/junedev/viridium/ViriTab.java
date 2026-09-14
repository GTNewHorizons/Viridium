package net.junedev.viridium;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ViriTab extends CreativeTabs {

    public ViriTab(String label) {
        super(label);
    }

    @Override
    public Item getTabIconItem() {
        return Item.getItemFromBlock(ViriBlocks.amaranthSapling);
    }
}
