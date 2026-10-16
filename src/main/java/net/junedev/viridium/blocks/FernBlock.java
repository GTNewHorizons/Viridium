package net.junedev.viridium.blocks;

import net.junedev.viridium.client.renderers.ViriRenderIds;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FernBlock extends BaseVegetation {

    @SideOnly(Side.CLIENT)
    private int halfSize = 8;
    @SideOnly(Side.CLIENT)
    private double openingAngle = 60;
    @SideOnly(Side.CLIENT)
    private double minimumRadius = 0.0D;

    public FernBlock() {
        super();
    }

    public FernBlock(int halfSize, double openingAngle, double minimumRadius) {
        this();

        this.halfSize = halfSize;
        this.openingAngle = openingAngle;
        this.minimumRadius = minimumRadius;
    }

    @Override
    public int getRenderType() {
        return ViriRenderIds.fernBlockRenderId;
    }

    @SideOnly(Side.CLIENT)
    public int getHalfSize() {
        return halfSize;
    }

    @SideOnly(Side.CLIENT)
    public double getOpeningAngle() {
        return openingAngle;
    }

    @SideOnly(Side.CLIENT)
    public double getMinimumRadius() {
        return minimumRadius;
    }

}
