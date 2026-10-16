package net.junedev.viridium.blocks;

import net.junedev.viridium.client.renderers.ViriRenderIds;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class GroundPlaneBlock extends BaseVegetation {

    public GroundPlaneBlock() {
        super();
    }

    @Override
    public int getRenderType() {
        return ViriRenderIds.groundPlaneBlockRenderId;
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World worldIn, int x, int y, int z) {

        return AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 0.05D, z + 1);
    }
}
