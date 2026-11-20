package net.junedev.viridium.blocks;

import net.junedev.viridium.client.renderers.ViriRenderIds;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FernBlock extends BaseVegetation {

    private int halfSize = 8;
    private double openingAngle = 60;
    private double minimumRadius = 0.0D;

    private int leaveCount = 4;

    public FernBlock() {
        super();
    }

    public FernBlock(int halfSize, double openingAngle, double minimumRadius, int leaveCount) {
        this();

        this.halfSize = halfSize;
        this.openingAngle = openingAngle;
        this.minimumRadius = minimumRadius;

        this.leaveCount = leaveCount;
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

    @SideOnly(Side.CLIENT)
    public int getLeaveCount() {
        return leaveCount;
    }

}
