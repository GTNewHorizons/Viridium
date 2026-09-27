package net.junedev.viridium;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.junedev.viridium.blocks.BaseFullLog;
import net.junedev.viridium.blocks.BaseLeaves;
import net.junedev.viridium.blocks.BasePlanks;
import net.junedev.viridium.blocks.BaseSaplingBlock;
import net.junedev.viridium.blocks.BushBlock;
import net.junedev.viridium.blocks.SmallLogBlock;
import net.junedev.viridium.blocks.TallPlantBlock;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ViriTab extends CreativeTabs {

    private ItemSorter itemSorter = new ItemSorter();

    public ViriTab(String label) {
        super(label);
    }

    @Override
    public Item getTabIconItem() {
        return Item.getItemFromBlock(ViriBlocks.amaranthSapling);
    }

    @Override
    public void displayAllReleventItems(List items) {
        super.displayAllReleventItems(items);

        Collections.sort(items, itemSorter);
    }

    // Sorts items in alphabetical order using their display names
    private static class ItemSorter implements Comparator<ItemStack> {

        @Override
        public int compare(ItemStack o1, ItemStack o2) {
            Item item1 = o1.getItem();
            Item item2 = o2.getItem();

            int typeValue1 = 0;
            int typeValue2 = 0;

            // If item1 is a block and item2 isn't, sort item1 before item2
            if (((item1 instanceof ItemBlock)) && (!(item2 instanceof ItemBlock))) {
                return -1;
            }

            // If item2 is a block and item1 isn't, sort item1 after item2
            if (((item2 instanceof ItemBlock)) && (!(item1 instanceof ItemBlock))) {
                return 1;
            }

            // Use a ponderated sistem for blocks
            if (((item2 instanceof ItemBlock itemBlock1)) && ((item1 instanceof ItemBlock itemBlock2))) {
                typeValue1 = getTypeValue(itemBlock1);
                typeValue2 = getTypeValue(itemBlock2);

                if (typeValue1 > typeValue2) return -1;
                if (typeValue2 > typeValue1) return 1;
            }

            // Else alphabetically
            String displayName1 = o1.getDisplayName();
            String displayName2 = o2.getDisplayName();

            int result = displayName1.compareToIgnoreCase(displayName2);
            return result;
        }

        public int getTypeValue(ItemBlock item) {
            Block block = item.field_150939_a;
            if (block instanceof BushBlock) return 10;
            if (block instanceof BaseFullLog) return 20;
            if (block instanceof SmallLogBlock) return 30;
            if (block instanceof BaseLeaves) return 40;
            if (block instanceof BasePlanks) return 50;
            if (block instanceof BaseSaplingBlock) return 60;
            if (block instanceof TallPlantBlock) return 70;

            return 0;
        }
    }
}
