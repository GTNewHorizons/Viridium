package net.junedev.viridium.client.renderers.blocks;

import net.junedev.viridium.blocks.SmallLogBlock;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class SmallLogBlockRenderer implements ISimpleBlockRenderingHandler {

    private final int renderId;

    public SmallLogBlockRenderer(int renderId) {
        this.renderId = renderId;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        SmallLogBlock smallLogBlockRenderer = (SmallLogBlock) block;

        double coreMin = smallLogBlockRenderer.getCoreMin();
        double coreMax = smallLogBlockRenderer.getCoreMax();

        GL11.glPushMatrix();
        GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

        renderInventoryCuboid(
            block,
            metadata,
            renderer,
            smallLogBlockRenderer,
            ForgeDirection.UP,
            coreMin,
            0.0625,
            coreMin,
            coreMax,
            0.9375,
            coreMax,
            null);

        renderer.setRenderBoundsFromBlock(block);
        GL11.glPopMatrix();
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
        RenderBlocks renderer) {

        renderBranchesCuboid(world, x, y, z, block, renderer);

        renderer.setRenderBoundsFromBlock(block);

        return true;
    }

    private void renderBranchesCuboid(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer) {
        SmallLogBlock smallLogBlock = (SmallLogBlock) block;

        double coreMin = smallLogBlock.getCoreMin();
        double coreMax = smallLogBlock.getCoreMax();

        boolean down = isConnected(world, x, y, z, ForgeDirection.DOWN);
        boolean up = isConnected(world, x, y, z, ForgeDirection.UP);

        boolean west = isConnected(world, x, y, z, ForgeDirection.WEST) && smallLogBlock.doSidesConnect();
        boolean east = isConnected(world, x, y, z, ForgeDirection.EAST) && smallLogBlock.doSidesConnect();
        boolean north = isConnected(world, x, y, z, ForgeDirection.NORTH) && smallLogBlock.doSidesConnect();
        boolean south = isConnected(world, x, y, z, ForgeDirection.SOUTH) && smallLogBlock.doSidesConnect();
        boolean hasHorizontalConnection = west || east || north || south;

        // The central, vertical log only renders faces that are not covered by a branch.
        renderLogBox(
            world,
            x,
            y,
            z,
            block,
            renderer,
            smallLogBlock,
            ForgeDirection.UP,
            coreMin,
            coreMin,
            coreMin,
            coreMax,
            coreMax,
            coreMax,
            !west,
            !east,
            !down,
            !up,
            !north,
            !south,
            !hasHorizontalConnection);

        if (down) {
            renderLogBox(
                world,
                x,
                y,
                z,
                block,
                renderer,
                smallLogBlock,
                ForgeDirection.UP,
                coreMin,
                0.0,
                coreMin,
                coreMax,
                coreMin,
                coreMax,
                true,
                true,
                false,
                false,
                true,
                true,
                false);
        }

        if (up) {
            renderLogBox(
                world,
                x,
                y,
                z,
                block,
                renderer,
                smallLogBlock,
                ForgeDirection.UP,
                coreMin,
                coreMax,
                coreMin,
                coreMax,
                1.0,
                coreMax,
                true,
                true,
                false,
                false,
                true,
                true,
                false);
        }

        if (!smallLogBlock.doSidesConnect()) return;

        if (west) {
            renderLogBox(
                world,
                x,
                y,
                z,
                block,
                renderer,
                smallLogBlock,
                ForgeDirection.EAST,
                0.0,
                coreMin,
                coreMin,
                coreMin,
                coreMax,
                coreMax,
                false,
                false,
                true,
                true,
                true,
                true,
                false);
        }

        if (east) {
            renderLogBox(
                world,
                x,
                y,
                z,
                block,
                renderer,
                smallLogBlock,
                ForgeDirection.EAST,
                coreMax,
                coreMin,
                coreMin,
                1.0,
                coreMax,
                coreMax,
                false,
                false,
                true,
                true,
                true,
                true,
                false);
        }

        if (north) {
            renderLogBox(
                world,
                x,
                y,
                z,
                block,
                renderer,
                smallLogBlock,
                ForgeDirection.SOUTH,
                coreMin,
                coreMin,
                0.0,
                coreMax,
                coreMax,
                coreMin,
                true,
                true,
                true,
                true,
                false,
                false,
                false);
        }

        if (south) {
            renderLogBox(
                world,
                x,
                y,
                z,
                block,
                renderer,
                smallLogBlock,
                ForgeDirection.SOUTH,
                coreMin,
                coreMin,
                coreMax,
                coreMax,
                coreMax,
                1.0,
                true,
                true,
                true,
                true,
                false,
                false,
                false);
        }

    }

    private boolean isConnected(IBlockAccess world, int x, int y, int z, ForgeDirection direction) {
        int neighborX = x + direction.offsetX;
        int neighborY = y + direction.offsetY;
        int neighborZ = z + direction.offsetZ;
        return world.isSideSolid(neighborX, neighborY, neighborZ, direction.getOpposite(), false)
            || world.getBlock(neighborX, neighborY, neighborZ) instanceof SmallLogBlock;
    }

    private void renderLogBox(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer,
        SmallLogBlock smallLogBlock, ForgeDirection axis, double minX, double minY, double minZ, double maxX,
        double maxY, double maxZ, boolean renderWest, boolean renderEast, boolean renderDown, boolean renderUp,
        boolean renderNorth, boolean renderSouth, boolean overrideTopIcon) {

        IIcon topIcon = overrideTopIcon ? smallLogBlock.getTopIcon() : smallLogBlock.getSideIcon();
        IIcon sideIcon = smallLogBlock.getSideIcon();
        Tessellator tessellator = Tessellator.instance;

        if (renderWest) {
            setFaceRenderBounds(renderer, smallLogBlock, axis, ForgeDirection.WEST, minX, minY, minZ, maxX, maxY, maxZ);
            setFaceLighting(
                tessellator,
                world,
                x,
                y,
                z,
                block,
                ForgeDirection.WEST,
                minX,
                maxX,
                minY,
                maxY,
                minZ,
                maxZ);
            renderer.renderFaceXNeg(block, x, y, z, axis == ForgeDirection.EAST ? topIcon : sideIcon);
        }
        if (renderEast) {
            setFaceRenderBounds(renderer, smallLogBlock, axis, ForgeDirection.EAST, minX, minY, minZ, maxX, maxY, maxZ);
            setFaceLighting(
                tessellator,
                world,
                x,
                y,
                z,
                block,
                ForgeDirection.EAST,
                minX,
                maxX,
                minY,
                maxY,
                minZ,
                maxZ);
            renderer.renderFaceXPos(block, x, y, z, axis == ForgeDirection.EAST ? topIcon : sideIcon);
        }
        if (renderDown) {
            setFaceRenderBounds(renderer, smallLogBlock, axis, ForgeDirection.DOWN, minX, minY, minZ, maxX, maxY, maxZ);
            setFaceLighting(
                tessellator,
                world,
                x,
                y,
                z,
                block,
                ForgeDirection.DOWN,
                minX,
                maxX,
                minY,
                maxY,
                minZ,
                maxZ);
            renderer.renderFaceYNeg(block, x, y, z, axis == ForgeDirection.UP ? topIcon : sideIcon);
        }
        if (renderUp) {
            setFaceRenderBounds(renderer, smallLogBlock, axis, ForgeDirection.UP, minX, minY, minZ, maxX, maxY, maxZ);
            setFaceLighting(tessellator, world, x, y, z, block, ForgeDirection.UP, minX, maxX, minY, maxY, minZ, maxZ);
            renderer.renderFaceYPos(block, x, y, z, axis == ForgeDirection.UP ? topIcon : sideIcon);
        }
        if (renderNorth) {
            setFaceRenderBounds(
                renderer,
                smallLogBlock,
                axis,
                ForgeDirection.NORTH,
                minX,
                minY,
                minZ,
                maxX,
                maxY,
                maxZ);
            setFaceLighting(
                tessellator,
                world,
                x,
                y,
                z,
                block,
                ForgeDirection.NORTH,
                minX,
                maxX,
                minY,
                maxY,
                minZ,
                maxZ);
            renderer.renderFaceZNeg(block, x, y, z, axis == ForgeDirection.SOUTH ? topIcon : sideIcon);
        }
        if (renderSouth) {
            setFaceRenderBounds(
                renderer,
                smallLogBlock,
                axis,
                ForgeDirection.SOUTH,
                minX,
                minY,
                minZ,
                maxX,
                maxY,
                maxZ);
            setFaceLighting(
                tessellator,
                world,
                x,
                y,
                z,
                block,
                ForgeDirection.SOUTH,
                minX,
                maxX,
                minY,
                maxY,
                minZ,
                maxZ);
            renderer.renderFaceZPos(block, x, y, z, axis == ForgeDirection.SOUTH ? topIcon : sideIcon);
        }
    }

    private void setFaceRenderBounds(RenderBlocks renderer, SmallLogBlock smallLogBlock, ForgeDirection logAxis,
        ForgeDirection face, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {

        boolean faceX = face.offsetX != 0;
        boolean faceY = face.offsetY != 0;
        boolean faceZ = face.offsetZ != 0;
        boolean axisX = logAxis.offsetX != 0;
        boolean axisY = logAxis.offsetY != 0;
        boolean axisZ = logAxis.offsetZ != 0;

        // face || axis => location bounds (min max), and NOT expanded bounds (face min max)
        // face and axis cross product determine the expanding direction
        double renderMinX = faceX || axisX ? minX : smallLogBlock.getFaceMin();
        double renderMaxX = faceX || axisX ? maxX : smallLogBlock.getFaceMax();
        double renderMinY = faceY || axisY ? minY : smallLogBlock.getFaceMin();
        double renderMaxY = faceY || axisY ? maxY : smallLogBlock.getFaceMax();
        double renderMinZ = faceZ || axisZ ? minZ : smallLogBlock.getFaceMin();
        double renderMaxZ = faceZ || axisZ ? maxZ : smallLogBlock.getFaceMax();

        renderer.setRenderBounds(renderMinX, renderMinY, renderMinZ, renderMaxX, renderMaxY, renderMaxZ);
    }

    private void setFaceLighting(Tessellator tessellator, IBlockAccess world, int x, int y, int z, Block block,
        ForgeDirection face, double minX, double maxX, double minY, double maxY, double minZ, double maxZ) {

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

    private void renderInventoryCuboid(Block block, int metadata, RenderBlocks renderer, SmallLogBlock smallLogBlock,
        ForgeDirection axis, double minX, double minY, double minZ, double maxX, double maxY, double maxZ,
        IIcon overrideIcon) {

        Tessellator tessellator = Tessellator.instance;

        tessellator.startDrawingQuads();

        tessellator.setNormal(0.0F, -1.0F, 0.0F);
        setFaceRenderBounds(renderer, smallLogBlock, axis, ForgeDirection.DOWN, minX, minY, minZ, maxX, maxY, maxZ);
        renderer.renderFaceYNeg(block, 0.0, 0.0, 0.0, overrideIcon == null ? block.getIcon(0, 0) : overrideIcon);

        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        setFaceRenderBounds(renderer, smallLogBlock, axis, ForgeDirection.UP, minX, minY, minZ, maxX, maxY, maxZ);
        renderer.renderFaceYPos(block, 0.0, 0.0, 0.0, overrideIcon == null ? block.getIcon(0, 0) : overrideIcon);

        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        setFaceRenderBounds(renderer, smallLogBlock, axis, ForgeDirection.WEST, minX, minY, minZ, maxX, maxY, maxZ);
        renderer.renderFaceXNeg(block, 0.0, 0.0, 0.0, overrideIcon == null ? block.getIcon(2, 0) : overrideIcon);

        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        setFaceRenderBounds(renderer, smallLogBlock, axis, ForgeDirection.EAST, minX, minY, minZ, maxX, maxY, maxZ);
        renderer.renderFaceXPos(block, 0.0, 0.0, 0.0, overrideIcon == null ? block.getIcon(2, 0) : overrideIcon);

        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        setFaceRenderBounds(renderer, smallLogBlock, axis, ForgeDirection.NORTH, minX, minY, minZ, maxX, maxY, maxZ);
        renderer.renderFaceZNeg(block, 0.0, 0.0, 0.0, overrideIcon == null ? block.getIcon(2, 0) : overrideIcon);

        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        setFaceRenderBounds(renderer, smallLogBlock, axis, ForgeDirection.SOUTH, minX, minY, minZ, maxX, maxY, maxZ);
        renderer.renderFaceZPos(block, 0.0, 0.0, 0.0, overrideIcon == null ? block.getIcon(2, 0) : overrideIcon);

        tessellator.draw();
    }

}
