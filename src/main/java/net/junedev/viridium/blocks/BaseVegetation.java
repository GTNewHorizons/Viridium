package net.junedev.viridium.blocks;

import net.junedev.viridium.Viridium;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class BaseVegetation extends Block {

    public BaseVegetation() {
        super(Material.grass);

        this.setHardness(0.0F);
        this.setCreativeTab(Viridium.VTab);
        this.setStepSound(soundTypeGrass);
    }

    @Override
    public Block setBlockTextureName(String textureName) {
        return super.setBlockTextureName(Viridium.MOD_ID + ":" + textureName);
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
    public MovingObjectPosition collisionRayTrace(World worldIn, int x, int y, int z, Vec3 start, Vec3 end) {

        AxisAlignedBB bBox = getSelectedBoundingBoxFromPool(worldIn, x, y, z);
        MovingObjectPosition hit = bBox.calculateIntercept(start, end);

        if (hit == null) return null;

        MovingObjectPosition closestHit = new MovingObjectPosition(x, y, z, hit.sideHit, hit.hitVec);;

        return closestHit;
    }
}
