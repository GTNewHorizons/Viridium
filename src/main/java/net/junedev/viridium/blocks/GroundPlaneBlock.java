package net.junedev.viridium.blocks;

import net.junedev.viridium.Viridium;
import net.junedev.viridium.client.renderers.ViriRenderIds;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class GroundPlaneBlock extends Block {

    public GroundPlaneBlock() {
        super(Material.grass);

        this.setHardness(0.0F);
        this.setCreativeTab(Viridium.VTab);
        this.setStepSound(soundTypeGrass);
    }

    @Override
    public Block setBlockName(String name) {
        super.setBlockName(name);

        setBlockTextureName(Viridium.MOD_ID + ":ground/" + name);

        return this;
    }

    @Override
    public int getRenderType() {
        return ViriRenderIds.groundPlaneBlockRenderId;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World worldIn, int x, int y, int z) {
        return null;
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World worldIn, int x, int y, int z) {

        return AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 0.05D, z + 1);
    }

    @Override
    public MovingObjectPosition collisionRayTrace(World worldIn, int x, int y, int z, Vec3 start, Vec3 end) {

        AxisAlignedBB bBox = getSelectedBoundingBoxFromPool(worldIn, x, y, z);
        MovingObjectPosition hit = bBox.calculateIntercept(start, end);

        if (hit == null) return null;

        MovingObjectPosition closestHit = new MovingObjectPosition(x, y, z, hit.sideHit, hit.hitVec);;

        return closestHit;
    }
}
