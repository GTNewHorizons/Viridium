package net.junedev.viridium.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.junedev.viridium.Viridium;
import net.junedev.viridium.client.renderers.ViriRenderIds;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.List;

public class SmallLogBlock extends Block {
    /** Half of the physical log cross-section, in block pixels. */
    private final int halfWidth;
    /** Half of the quad span on its tangent axes; may exceed halfWidth. */
    private final int faceHalfWidth;
    /** If the log should connect to the sides. */
    private final boolean connectSides;

    @SideOnly(Side.CLIENT)
    private IIcon topIcon;

    @SideOnly(Side.CLIENT)
    private  IIcon sideIcon;

    public SmallLogBlock(int halfWidth, int faceHalfWidth, boolean connectSides) {
        super(Material.wood);

        if (halfWidth < 1 || halfWidth > 8 || faceHalfWidth < 1 || faceHalfWidth > 8) {
            throw new IllegalArgumentException("Small log half widths must be between 1 and 8 pixels");
        }

        this.halfWidth = halfWidth;
        this.faceHalfWidth = faceHalfWidth;
        this.connectSides = connectSides;

        this.setHardness(1.0F);
        this.setCreativeTab(Viridium.VTab);
        this.setStepSound(soundTypeWood);
    }

    @Override
    public int getRenderType() {
        return ViriRenderIds.smallLogBlockRenderId;
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

    // Selected BB: Must be cubic so it is the smallest containing all the log
    @Override
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World worldIn, int x, int y, int z) {
        double coreMin = getCoreMin();
        double coreMax = getCoreMax();

        double minX = isConnected(worldIn, x, y, z, ForgeDirection.WEST) ? 0.0 : coreMin;
        double maxX = isConnected(worldIn, x, y, z, ForgeDirection.EAST) ? 1.0 : coreMax;
        double minY = isConnected(worldIn, x, y, z, ForgeDirection.DOWN) ? 0.0 : coreMin;
        double maxY = isConnected(worldIn, x, y, z, ForgeDirection.UP) ? 1.0 : coreMax;
        double minZ = isConnected(worldIn, x, y, z, ForgeDirection.NORTH) ? 0.0 : coreMin;
        double maxZ = isConnected(worldIn, x, y, z, ForgeDirection.SOUTH) ? 1.0 : coreMax;

        return AxisAlignedBB.getBoundingBox(
            x + minX, y + minY, z + minZ,
            x + maxX, y + maxY, z + maxZ);
    }


    @Override
    public void addCollisionBoxesToList(World worldIn, int x, int y, int z, AxisAlignedBB mask, List<AxisAlignedBB> list, Entity collider) {

        List<AxisAlignedBB> logBoxes = getLogBoxes(worldIn, x, y, z);
        for (AxisAlignedBB logBox : logBoxes) {
            if (mask.intersectsWith(logBox)) {
                list.add(logBox);
            }
        }
    }

    // Block selecting, uses the same BB as the collisions
    @Override
    public MovingObjectPosition collisionRayTrace(World worldIn, int x, int y, int z, Vec3 start, Vec3 end) {
        MovingObjectPosition closestHit = null;
        double closestDistanceSq = Double.MAX_VALUE;

        for (AxisAlignedBB logBox : getLogBoxes(worldIn, x, y, z)) {
            MovingObjectPosition hit = logBox.calculateIntercept(start, end);
            if (hit == null) continue;

            double distanceSq = start.squareDistanceTo(hit.hitVec);
            if (distanceSq < closestDistanceSq) {
                closestDistanceSq = distanceSq;
                closestHit = new MovingObjectPosition(x, y, z, hit.sideHit, hit.hitVec);
            }
        }

        return closestHit;
    }

    private List<AxisAlignedBB> getLogBoxes(IBlockAccess worldIn, int x, int y, int z) {
        List<AxisAlignedBB> logBoxes = new ArrayList<AxisAlignedBB>();

        double coreMin = getCoreMin();
        double coreMax = getCoreMax();

        boolean hasConnections = false;

        for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            if(direction.offsetX < 0 || direction.offsetY < 0 || direction.offsetZ < 0) continue;

            double min = coreMin;
            double max = coreMax;
            if(isConnected(worldIn, x, y, z, direction)) max = 1.0;
            if(isConnected(worldIn, x, y, z, direction.getOpposite())) min = 0.0;

            if(max == coreMax && min == coreMin) continue;

            AxisAlignedBB platform;
            switch (direction) {
                case EAST:
                    platform = AxisAlignedBB.getBoundingBox(
                        x + min, y + coreMin, z + coreMin,
                       x + max, y + coreMax, z + coreMax);
                    break;

                case UP:
                    platform = AxisAlignedBB.getBoundingBox(
                        x + coreMin, y + min, z + coreMin,
                        x + coreMax, y + max, z + coreMax);
                    break;

                case SOUTH:
                    platform = AxisAlignedBB.getBoundingBox(
                        x + coreMin, y + coreMin, z + min,
                        x + coreMax, y + coreMax, z + max);
                    break;

                default:
                    continue;
            }

            logBoxes.add(platform);

            hasConnections = true;
        }

        if(!hasConnections)
        {
            AxisAlignedBB platform = AxisAlignedBB.getBoundingBox(
                x + coreMin, y + coreMin, z + coreMin,
                x + coreMax, y + coreMax, z + coreMax);

            logBoxes.add(platform);
        }

        return logBoxes;
    }

    private boolean isConnected(IBlockAccess world, int x, int y, int z, ForgeDirection direction) {

        if(!doSidesConnect() && !(direction == ForgeDirection.UP || direction == ForgeDirection.DOWN)) return false;

        int neighborX = x + direction.offsetX;
        int neighborY = y + direction.offsetY;
        int neighborZ = z + direction.offsetZ;
        return world.isSideSolid(neighborX, neighborY, neighborZ, direction.getOpposite(), false)
            || world.getBlock(neighborX, neighborY, neighborZ) instanceof SmallLogBlock;
    }

    public double getCoreMin() {
        return 0.5D - halfWidth / 16.0D;
    }

    public double getCoreMax() {
        return 0.5D + halfWidth / 16.0D;
    }

    public double getFaceMin() {
        return 0.5D - faceHalfWidth / 16.0D;
    }

    public double getFaceMax() {
        return 0.5D + faceHalfWidth / 16.0D;
    }

    public boolean doSidesConnect() { return connectSides; }

    @Override
    public Block setBlockName(String name) {
        super.setBlockName(name);

        setBlockTextureName(Viridium.MOD_ID + ":small_trees/" + name);

        return (Block) this;
    }

    // Icon stuff
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        topIcon = reg.registerIcon(getTextureName()+"_top");
        sideIcon = reg.registerIcon(getTextureName() + "_side");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {

        if(side==0 || side==1) return topIcon;

        return sideIcon;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getTopIcon() {
        return topIcon;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getSideIcon() {
        return sideIcon;
    }

}
