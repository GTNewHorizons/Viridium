package net.junedev.viridium.client.renderers.blocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.junedev.viridium.blocks.SmallLogBlock;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

public class SmallLogBlockRenderer implements ISimpleBlockRenderingHandler {
    private static final int HALF_WIDTH = 4;

    private final int renderId;

    public SmallLogBlockRenderer(int renderId) {
        this.renderId = renderId;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        SmallLogBlock smallLogBlockRenderer = (SmallLogBlock) block;

        double coreMin = 0.5 - HALF_WIDTH * 0.0625;
        double coreMax = 0.5 + HALF_WIDTH * 0.0625;

        GL11.glPushMatrix();
        GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

        renderer.setRenderBounds(coreMin, 0.0625, coreMin, coreMax, 0.9375, coreMax);
        renderInventoryCuboid(block, metadata, renderer, null);

        renderer.setRenderBoundsFromBlock(block);
        GL11.glPopMatrix();
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        SmallLogBlock smallLogBlock = (SmallLogBlock) block;

        renderBranchesCuboid(world, x, y, z, block, renderer, smallLogBlock);

        renderer.setRenderBoundsFromBlock(block);

        return true;
    }


    private void renderBranchesCuboid(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer, SmallLogBlock smallLogBlock) {
        double coreMin = 0.5 - HALF_WIDTH * 0.0625;
        double coreMax = 0.5 + HALF_WIDTH * 0.0625;

        boolean west = isConnected(world, x, y, z, ForgeDirection.WEST);
        boolean east = isConnected(world, x, y, z, ForgeDirection.EAST);
        boolean down = isConnected(world, x, y, z, ForgeDirection.DOWN);
        boolean up = isConnected(world, x, y, z, ForgeDirection.UP);
        boolean north = isConnected(world, x, y, z, ForgeDirection.NORTH);
        boolean south = isConnected(world, x, y, z, ForgeDirection.SOUTH);
        boolean hasHorizontalConnection = west || east || north || south;

        // The central, vertical log only renders faces that are not covered by a branch.
        renderLogBox(
            world, x, y, z, block, renderer, smallLogBlock, ForgeDirection.UP,
            coreMin, coreMin, coreMin, coreMax, coreMax, coreMax,
            !west, !east, !down, !up, !north, !south, !hasHorizontalConnection);

        if (west) {
            renderLogBox(
                world, x, y, z, block, renderer, smallLogBlock, ForgeDirection.EAST,
                0.0, coreMin, coreMin, coreMin, coreMax, coreMax,
                false, false, true, true, true, true, false);
        }

        if (east) {
            renderLogBox(
                world, x, y, z, block, renderer, smallLogBlock, ForgeDirection.EAST,
                coreMax, coreMin, coreMin, 1.0, coreMax, coreMax,
                false, false, true, true, true, true, false);
        }

        if (down) {
            renderLogBox(
                world, x, y, z, block, renderer, smallLogBlock, ForgeDirection.UP,
                coreMin, 0.0, coreMin, coreMax, coreMin, coreMax,
                true, true, false, false, true, true, false);
        }

        if (up) {
            renderLogBox(
                world, x, y, z, block, renderer, smallLogBlock, ForgeDirection.UP,
                coreMin, coreMax, coreMin, coreMax, 1.0, coreMax,
                true, true, false, false, true, true, false);
        }

        if (north) {
            renderLogBox(
                world, x, y, z, block, renderer, smallLogBlock, ForgeDirection.SOUTH,
                coreMin, coreMin, 0.0, coreMax, coreMax, coreMin,
                true, true, true, true, false, false, false);
        }

        if (south) {
            renderLogBox(
                world, x, y, z, block, renderer, smallLogBlock, ForgeDirection.SOUTH,
                coreMin, coreMin, coreMax, coreMax, coreMax, 1.0,
                true, true, true, true, false, false, false);
        }

    }

    private boolean isConnected(IBlockAccess world, int x, int y, int z, ForgeDirection direction) {
        int neighborX = x + direction.offsetX;
        int neighborY = y + direction.offsetY;
        int neighborZ = z + direction.offsetZ;
        return world.isSideSolid(neighborX, neighborY, neighborZ, direction.getOpposite(), false)
            || world.getBlock(neighborX, neighborY, neighborZ) instanceof SmallLogBlock;
    }

    private void renderLogBox(
        IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer, SmallLogBlock smallLogBlock,
        ForgeDirection axis,
        double minX, double minY, double minZ, double maxX, double maxY, double maxZ,
        boolean renderWest, boolean renderEast, boolean renderDown, boolean renderUp, boolean renderNorth,
        boolean renderSouth, boolean overrideTopIcon) {

        IIcon topIcon = overrideTopIcon ? smallLogBlock.getTopIcon() : smallLogBlock.getSideIcon();
        IIcon sideIcon = smallLogBlock.getSideIcon();
        Tessellator tessellator = Tessellator.instance;

        renderer.setRenderBounds(minX, minY, minZ, maxX, maxY, maxZ);

        if (renderWest) {
            setFaceLighting(tessellator, world, x, y, z, block, ForgeDirection.WEST, minX, maxX, minY, maxY, minZ, maxZ);
            renderer.renderFaceXNeg(block, x, y, z, axis == ForgeDirection.EAST ? topIcon : sideIcon);
        }
        if (renderEast) {
            setFaceLighting(tessellator, world, x, y, z, block, ForgeDirection.EAST, minX, maxX, minY, maxY, minZ, maxZ);
            renderer.renderFaceXPos(block, x, y, z, axis == ForgeDirection.EAST ? topIcon : sideIcon);
        }
        if (renderDown) {
            setFaceLighting(tessellator, world, x, y, z, block, ForgeDirection.DOWN, minX, maxX, minY, maxY, minZ, maxZ);
            renderer.renderFaceYNeg(block, x, y, z, axis == ForgeDirection.UP ? topIcon : sideIcon);
        }
        if (renderUp) {
            setFaceLighting(tessellator, world, x, y, z, block, ForgeDirection.UP, minX, maxX, minY, maxY, minZ, maxZ);
            renderer.renderFaceYPos(block, x, y, z, axis == ForgeDirection.UP ? topIcon : sideIcon);
        }
        if (renderNorth) {
            setFaceLighting(tessellator, world, x, y, z, block, ForgeDirection.NORTH, minX, maxX, minY, maxY, minZ, maxZ);
            renderer.renderFaceZNeg(block, x, y, z, axis == ForgeDirection.SOUTH ? topIcon : sideIcon);
        }
        if (renderSouth) {
            setFaceLighting(tessellator, world, x, y, z, block, ForgeDirection.SOUTH, minX, maxX, minY, maxY, minZ, maxZ);
            renderer.renderFaceZPos(block, x, y, z, axis == ForgeDirection.SOUTH ? topIcon : sideIcon);
        }
    }

    private void setFaceLighting(
        Tessellator tessellator, IBlockAccess world, int x, int y, int z, Block block, ForgeDirection face,
        double minX, double maxX, double minY, double maxY, double minZ, double maxZ) {

        int lightX = x;
        int lightY = y;
        int lightZ = z;
        float shade;

        switch (face) {
            case DOWN:
                if (minY <= 0.0D) lightY--;
                shade = 0.5F;
                break;
            case UP:
                if (maxY >= 1.0D) lightY++;
                shade = 1.0F;
                break;
            case NORTH:
                if (minZ <= 0.0D) lightZ--;
                shade = 0.8F;
                break;
            case SOUTH:
                if (maxZ >= 1.0D) lightZ++;
                shade = 0.8F;
                break;
            case WEST:
                if (minX <= 0.0D) lightX--;
                shade = 0.6F;
                break;
            case EAST:
                if (maxX >= 1.0D) lightX++;
                shade = 0.6F;
                break;
            default:
                return;
        }

        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, lightX, lightY, lightZ));
        tessellator.setColorOpaque_F(shade, shade, shade);
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    @Override
    public int getRenderId() {
        return renderId;
    }


    private void renderInventoryCuboid(Block block, int metadata, RenderBlocks renderer, IIcon overrideIcon) {
        Tessellator tessellator = Tessellator.instance;

        tessellator.startDrawingQuads();

        tessellator.setNormal(0.0F, -1.0F, 0.0F);
        renderer.renderFaceYNeg(block, 0.0, 0.0, 0.0, overrideIcon == null ? block.getIcon(0, 0) : overrideIcon);

        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        renderer.renderFaceYPos(block, 0.0, 0.0, 0.0, overrideIcon == null ? block.getIcon(0, 0) : overrideIcon);

        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        renderer.renderFaceXNeg(block, 0.0, 0.0, 0.0, overrideIcon == null ? block.getIcon(2, 0) : overrideIcon);

        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        renderer.renderFaceXPos(block, 0.0, 0.0, 0.0, overrideIcon == null ? block.getIcon(2, 0) : overrideIcon);

        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        renderer.renderFaceZNeg(block, 0.0, 0.0, 0.0, overrideIcon == null ? block.getIcon(2, 0) : overrideIcon);

        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        renderer.renderFaceZPos(block, 0.0, 0.0, 0.0, overrideIcon == null ? block.getIcon(2, 0) : overrideIcon);

        tessellator.draw();
    }

}
