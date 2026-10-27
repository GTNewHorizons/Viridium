package net.junedev.viridium.blocks.vanilike;

import net.junedev.viridium.Viridium;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class BaseStairs extends BlockStairs {

    public BaseStairs(Block fullBlock) {
        super(fullBlock, 0);

        this.useNeighborBrightness = true;
        this.setCreativeTab(Viridium.VTab);
    }
}
