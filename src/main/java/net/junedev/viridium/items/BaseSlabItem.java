package net.junedev.viridium.items;

import net.junedev.viridium.blocks.BaseSlab;
import net.minecraft.block.Block;
import net.minecraft.item.ItemSlab;

public class BaseSlabItem extends ItemSlab {

    // GameRegistry resolves constructor arguments by their exact runtime types.
    public BaseSlabItem(Block block, BaseSlab singleSlab, BaseSlab doubleSlab) {
        super(block, singleSlab, doubleSlab, block == doubleSlab);
    }
}
