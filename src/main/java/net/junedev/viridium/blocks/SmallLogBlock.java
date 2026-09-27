package net.junedev.viridium.blocks;

import java.util.ArrayList;
import java.util.List;

import net.junedev.viridium.Viridium;
import net.junedev.viridium.client.renderers.ViriRenderIds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
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

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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
    private IIcon sideIcon;

    public SmallLogBlock(int halfWidth, int faceHalfWidth, boolean connectSides) {
        super(Material.wood);

        if (halfWidth < 1 || halfWidth > 8 || faceHalfWidth < 1 || faceHalfWidth > 8) {
            Viridium.LOGGER.warn("Small Log: Half widths must be between 1 and 8 pixels. Falling back to default.");
            this.halfWidth = 4;
            this.faceHalfWidth = 4;
        } else {
            this.halfWidth = halfWidth;
            this.faceHalfWidth = faceHalfWidth;
        }

        this.connectSides = connectSides;

        this.setHardness(1.0F);
        this.setCreativeTab(Viridium.VTab);
        this.setStepSound(soundTypeWood);
    }

    @Override
    public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z) {
        return true;
    }

    @Override
    public boolean isWood(IBlockAccess world, int x, int y, int z) {
        return true;
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
        int meta = worldIn.getBlockMetadata(x, y, z);

        double coreMin = getCoreMin();
        double coreMax = getCoreMax();

        double minX = getConnectionType(worldIn, x, y, z, ForgeDirection.WEST, meta) != ConnectionType.NONE ? 0.0
            : coreMin;
        double maxX = getConnectionType(worldIn, x, y, z, ForgeDirection.EAST, meta) != ConnectionType.NONE ? 1.0
            : coreMax;
        double minY = getConnectionType(worldIn, x, y, z, ForgeDirection.DOWN, meta) != ConnectionType.NONE ? 0.0
            : coreMin;
        double maxY = getConnectionType(worldIn, x, y, z, ForgeDirection.UP, meta) != ConnectionType.NONE ? 1.0
            : coreMax;
        double minZ = getConnectionType(worldIn, x, y, z, ForgeDirection.NORTH, meta) != ConnectionType.NONE ? 0.0
            : coreMin;
        double maxZ = getConnectionType(worldIn, x, y, z, ForgeDirection.SOUTH, meta) != ConnectionType.NONE ? 1.0
            : coreMax;

        return AxisAlignedBB.getBoundingBox(x + minX, y + minY, z + minZ, x + maxX, y + maxY, z + maxZ);
    }

    @Override
    public void addCollisionBoxesToList(World worldIn, int x, int y, int z, AxisAlignedBB mask,
        List<AxisAlignedBB> list, Entity collider) {

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

        int meta = worldIn.getBlockMetadata(x, y, z);

        double coreMin = getCoreMin();
        double coreMax = getCoreMax();

        boolean hasConnections = false;

        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            if (direction.offsetX < 0 || direction.offsetY < 0 || direction.offsetZ < 0) continue;

            double min = coreMin;
            double max = coreMax;
            if (getConnectionType(worldIn, x, y, z, direction, meta) != ConnectionType.NONE) max = 1.0;
            if (getConnectionType(worldIn, x, y, z, direction.getOpposite(), meta) != ConnectionType.NONE) min = 0.0;

            if (max == coreMax && min == coreMin) continue;

            AxisAlignedBB platform;
            switch (direction) {
                case EAST:
                    platform = AxisAlignedBB
                        .getBoundingBox(x + min, y + coreMin, z + coreMin, x + max, y + coreMax, z + coreMax);
                    break;

                case UP:
                    platform = AxisAlignedBB
                        .getBoundingBox(x + coreMin, y + min, z + coreMin, x + coreMax, y + max, z + coreMax);
                    break;

                case SOUTH:
                    platform = AxisAlignedBB
                        .getBoundingBox(x + coreMin, y + coreMin, z + min, x + coreMax, y + coreMax, z + max);
                    break;

                default:
                    continue;
            }

            logBoxes.add(platform);

            hasConnections = true;
        }

        if (!hasConnections) {
            AxisAlignedBB platform = AxisAlignedBB
                .getBoundingBox(x + coreMin, y + coreMin, z + coreMin, x + coreMax, y + coreMax, z + coreMax);

            logBoxes.add(platform);
        }

        return logBoxes;
    }

    public ConnectionType getConnectionType(IBlockAccess world, int x, int y, int z, ForgeDirection direction,
        int meta) {

        int j1 = meta & 12;

        boolean isLogEnd = false;
        switch (j1) {
            case 0:
                if (direction == ForgeDirection.UP || direction == ForgeDirection.DOWN) {
                    isLogEnd = true;
                } else if (!doSidesConnect()) {
                    return ConnectionType.NONE;
                }
                break;
            case 4:
                if (direction == ForgeDirection.WEST || direction == ForgeDirection.EAST) {
                    isLogEnd = true;
                } else if (!doSidesConnect()) {
                    return ConnectionType.NONE;
                }
                break;
            case 8:
                if (direction == ForgeDirection.NORTH || direction == ForgeDirection.SOUTH) {
                    isLogEnd = true;
                } else if (!doSidesConnect()) {
                    return ConnectionType.NONE;
                }
                break;
        }

        int neighborX = x + direction.offsetX;
        int neighborY = y + direction.offsetY;
        int neighborZ = z + direction.offsetZ;

        // If it is the log orientation, connect.
        if (isLogEnd) {
            if (world.isSideSolid(neighborX, neighborY, neighborZ, direction.getOpposite(), false))
                return ConnectionType.SOLID;
            return ConnectionType.TRANSPARENT;
        }

        // Connects to all neighbor leaves
        if (world.getBlock(neighborX, neighborY, neighborZ) instanceof BlockLeaves) return ConnectionType.TRANSPARENT;

        // If neighbor is small log, connect only if it is pointing towards the log.
        if (world.getBlock(neighborX, neighborY, neighborZ) instanceof SmallLogBlock neighbor) {
            ConnectionType typeIfConnected = ((SmallLogBlock) world.getBlock(x, y, z)).halfWidth < neighbor.halfWidth
                ? ConnectionType.SOLID
                : ConnectionType.TRANSPARENT;
            int neighborj1 = world.getBlockMetadata(neighborX, neighborY, neighborZ) & 12;
            switch (neighborj1) {
                case 0:
                    return direction.offsetY != 0 ? typeIfConnected : ConnectionType.NONE;
                case 4:
                    return direction.offsetX != 0 ? typeIfConnected : ConnectionType.NONE;
                case 8:
                    return direction.offsetZ != 0 ? typeIfConnected : ConnectionType.NONE;
            }
        }

        // In any other case, do not connect
        return ConnectionType.NONE;
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

    public boolean doSidesConnect() {
        return connectSides;
    }

    @Override
    public Block setBlockTextureName(String textureName) {
        return super.setBlockTextureName(Viridium.MOD_ID + ":" + textureName);
    }

    public int onBlockPlaced(World worldIn, int x, int y, int z, int side, float subX, float subY, float subZ,
        int meta) {
        if (!doSidesConnect()) return 0;

        int j1 = meta & 3;
        byte b0 = switch (side) {
            case 0, 1 -> 0;
            case 2, 3 -> 8;
            case 4, 5 -> 4;
            default -> 0;
        };

        return j1 | b0;
    }

    // Icon stuff
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        topIcon = reg.registerIcon(getTextureName() + "_top");
        sideIcon = reg.registerIcon(getTextureName() + "_side");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        int k = meta & 12;
        return k == 0 && (side == 1 || side == 0) ? this.getTopIcon()
            : (k == 4 && (side == 5 || side == 4) ? this.getTopIcon()
                : (k == 8 && (side == 2 || side == 3) ? this.getTopIcon() : this.getSideIcon()));
    }

    @SideOnly(Side.CLIENT)
    public IIcon getTopIcon() {
        return topIcon;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getSideIcon() {
        return sideIcon;
    }

    public enum ConnectionType {
        NONE,
        SOLID,
        TRANSPARENT
    }
}
