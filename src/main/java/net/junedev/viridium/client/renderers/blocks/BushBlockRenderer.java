package net.junedev.viridium.client.renderers.blocks;

import net.junedev.viridium.blocks.BushBlock;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class BushBlockRenderer implements ISimpleBlockRenderingHandler {

    private static final int HALF_WIDTH = 1;

    private final int renderId;

    public BushBlockRenderer(int renderId) {
        this.renderId = renderId;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        BushBlock bushBlock = (BushBlock) block;

        double coreMin = 0.5 - HALF_WIDTH * 0.0625;
        double coreMax = 0.5 + HALF_WIDTH * 0.0625;

        GL11.glPushMatrix();
        GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

        // renderInventoryCrossedBranch(
        // bushBlock.getBranchIcon(),
        // coreMin, 0.0, coreMin,
        // coreMax, 1.0, coreMax);

        renderer.setRenderBounds(coreMin, 0.0625, coreMin, coreMax, 0.9375, coreMax);
        renderInventoryCuboid(block, metadata, renderer, bushBlock.getBranchIcon());

        renderer.setRenderBounds(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
        renderInventoryCuboid(block, metadata, renderer, bushBlock.getLeaveIcon());

        renderer.setRenderBoundsFromBlock(block);
        GL11.glPopMatrix();
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
        RenderBlocks renderer) {
        BushBlock bushBlock = (BushBlock) block;
        // Vanilla renders destroy progress by calling this with a temporary override texture,
        // so we must be careful when overriding the texture.
        boolean hasOverrideTexture = renderer.hasOverrideBlockTexture();

        if (!hasOverrideTexture) {
            renderBranchesCuboid(world, x, y, z, block, renderer, bushBlock);
        }

        // Leaves
        if (!hasOverrideTexture) {
            renderer.setOverrideBlockTexture(bushBlock.getLeaveIcon());
        }

        renderer.setRenderBounds(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
        renderer.renderStandardBlock(block, x, y, z);

        if (!hasOverrideTexture) {
            renderer.clearOverrideBlockTexture();
        }

        renderer.setRenderBoundsFromBlock(block);

        return true;
    }

    private void renderBranches(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer,
        BushBlock bushBlock) {
        double coreMin = 0.5 - HALF_WIDTH * 0.0625;
        double coreMax = 0.5 + HALF_WIDTH * 0.0625;

        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            if (direction.offsetX < 0 || direction.offsetY < 0 || direction.offsetZ < 0) continue;

            double min = coreMin;
            double max = coreMax;
            if (isBlockConnected(world, x, y, z, direction)) max = 1.0;
            if (isBlockConnected(world, x, y, z, direction.getOpposite())) min = 0.0;

            if (max == coreMax && min == coreMin) continue;

            switch (direction) {
                case EAST:
                    renderCrossedBranch(
                        world,
                        x,
                        y,
                        z,
                        block,
                        bushBlock.getBranchIcon(),
                        min,
                        coreMin,
                        coreMin,
                        max,
                        coreMax,
                        coreMax,
                        direction);
                    break;

                case UP:
                    renderCrossedBranch(
                        world,
                        x,
                        y,
                        z,
                        block,
                        bushBlock.getBranchIcon(),
                        coreMin,
                        min,
                        coreMin,
                        coreMax,
                        max,
                        coreMax,
                        direction);
                    break;

                case SOUTH:
                    renderCrossedBranch(
                        world,
                        x,
                        y,
                        z,
                        block,
                        bushBlock.getBranchIcon(),
                        coreMin,
                        coreMin,
                        min,
                        coreMax,
                        coreMax,
                        max,
                        direction);
                    break;

                default:
                    continue;
            }
        }

    }

    private void renderBranchesCuboid(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer,
        BushBlock bushBlock) {
        double coreMin = 0.5 - HALF_WIDTH * 0.0625;
        double coreMax = 0.5 + HALF_WIDTH * 0.0625;

        renderer.setRenderBounds(coreMin, coreMin, coreMin, coreMax, coreMax, coreMax);
        renderer.renderStandardBlock(block, x, y, z);

        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

            if (!world.isSideSolid(
                x + direction.offsetX,
                y + direction.offsetY,
                z + direction.offsetZ,
                direction.getOpposite(),
                false)
                && !(world.getBlock(
                    x + direction.offsetX,
                    y + direction.offsetY,
                    z + direction.offsetZ) instanceof BushBlock))
                continue;

            switch (direction) {
                case WEST:
                    renderer.setRenderBounds(0.0, coreMin, coreMin, coreMin, coreMax, coreMax);
                    break;

                case EAST:
                    renderer.setRenderBounds(coreMax, coreMin, coreMin, 1.0, coreMax, coreMax);
                    break;

                case DOWN:
                    renderer.setRenderBounds(coreMin, 0.0, coreMin, coreMax, coreMin, coreMax);
                    break;

                case UP:
                    renderer.setRenderBounds(coreMin, coreMax, coreMin, coreMax, 1.0, coreMax);
                    break;

                case NORTH:
                    renderer.setRenderBounds(coreMin, coreMin, 0.0, coreMax, coreMax, coreMin);
                    break;

                case SOUTH:
                    renderer.setRenderBounds(coreMin, coreMin, coreMax, coreMax, coreMax, 1.0);
                    break;

                default:
                    continue;
            }
            renderer.renderStandardBlock(block, x, y, z);
        }

    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    @Override
    public int getRenderId() {
        return renderId;
    }

    private void renderInventoryCuboid(Block block, int metadata, RenderBlocks renderer, IIcon icon) {
        Tessellator tessellator = Tessellator.instance;

        tessellator.startDrawingQuads();

        tessellator.setNormal(0.0F, -1.0F, 0.0F);
        renderer.renderFaceYNeg(block, 0.0, 0.0, 0.0, icon);

        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        renderer.renderFaceYPos(block, 0.0, 0.0, 0.0, icon);

        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        renderer.renderFaceXNeg(block, 0.0, 0.0, 0.0, icon);

        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        renderer.renderFaceXPos(block, 0.0, 0.0, 0.0, icon);

        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        renderer.renderFaceZNeg(block, 0.0, 0.0, 0.0, icon);

        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        renderer.renderFaceZPos(block, 0.0, 0.0, 0.0, icon);

        tessellator.draw();
    }

    private void renderInventoryCrossedBranch(IIcon icon, double minX, double minY, double minZ, double maxX,
        double maxY, double maxZ) {

        Tessellator tessellator = Tessellator.instance;
        double uMin = icon.getInterpolatedU(minX * 16.0D);
        double uMax = icon.getInterpolatedU(maxX * 16.0D);
        double vMin = icon.getInterpolatedV(minY * 16.0D);
        double vMax = icon.getInterpolatedV(maxY * 16.0D);

        tessellator.startDrawingQuads();

        // The inventory branch grows along Y, so the cards intersect along Y.
        addDoubleSidedQuad(
            tessellator,
            minX,
            minY,
            minZ,
            uMin,
            vMax,
            maxX,
            minY,
            maxZ,
            uMax,
            vMax,
            maxX,
            maxY,
            maxZ,
            uMax,
            vMin,
            minX,
            maxY,
            minZ,
            uMin,
            vMin);
        addDoubleSidedQuad(
            tessellator,
            maxX,
            minY,
            minZ,
            uMax,
            vMax,
            minX,
            minY,
            maxZ,
            uMin,
            vMax,
            minX,
            maxY,
            maxZ,
            uMin,
            vMin,
            maxX,
            maxY,
            minZ,
            uMax,
            vMin);

        tessellator.draw();
    }

    private void renderCrossedBranch(IBlockAccess world, int x, int y, int z, Block block, IIcon icon, double minX,
        double minY, double minZ, double maxX, double maxY, double maxZ, ForgeDirection direction) {

        Tessellator tessellator = Tessellator.instance;

        int brightness = block.getMixedBrightnessForBlock(world, x, y, z);
        tessellator.setBrightness(brightness);
        tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);

        boolean usesZForU = direction.offsetZ != 0;

        double uStart = usesZForU ? minZ : minX;
        double uEnd = usesZForU ? maxZ : maxX;

        double uMin = icon.getInterpolatedU(uStart * 16.0D);
        double uMax = icon.getInterpolatedU(uEnd * 16.0D);

        double vMin = icon.getInterpolatedV(minY * 16.0D);
        double vMax = icon.getInterpolatedV(maxY * 16.0D);

        switch (direction) {
            case EAST:
            case WEST:
                // The cards intersect along the X axis.
                addDoubleSidedQuad(
                    tessellator,
                    x + minX,
                    y + minY,
                    z + minZ,
                    uMin,
                    vMax,
                    x + minX,
                    y + maxY,
                    z + maxZ,
                    uMin,
                    vMin,
                    x + maxX,
                    y + maxY,
                    z + maxZ,
                    uMax,
                    vMin,
                    x + maxX,
                    y + minY,
                    z + minZ,
                    uMax,
                    vMax);
                addDoubleSidedQuad(
                    tessellator,
                    x + minX,
                    y + minY,
                    z + maxZ,
                    uMin,
                    vMax,
                    x + minX,
                    y + maxY,
                    z + minZ,
                    uMin,
                    vMin,
                    x + maxX,
                    y + maxY,
                    z + minZ,
                    uMax,
                    vMin,
                    x + maxX,
                    y + minY,
                    z + maxZ,
                    uMax,
                    vMax);
                break;

            case UP:
            case DOWN:
                // The cards intersect along the Y axis.
                addDoubleSidedQuad(
                    tessellator,
                    x + minX,
                    y + minY,
                    z + minZ,
                    uMin,
                    vMax,
                    x + maxX,
                    y + minY,
                    z + maxZ,
                    uMax,
                    vMax,
                    x + maxX,
                    y + maxY,
                    z + maxZ,
                    uMax,
                    vMin,
                    x + minX,
                    y + maxY,
                    z + minZ,
                    uMin,
                    vMin);
                addDoubleSidedQuad(
                    tessellator,
                    x + maxX,
                    y + minY,
                    z + minZ,
                    uMax,
                    vMax,
                    x + minX,
                    y + minY,
                    z + maxZ,
                    uMin,
                    vMax,
                    x + minX,
                    y + maxY,
                    z + maxZ,
                    uMin,
                    vMin,
                    x + maxX,
                    y + maxY,
                    z + minZ,
                    uMax,
                    vMin);
                break;

            case NORTH:
            case SOUTH:
                // The cards intersect along the Z axis.
                addDoubleSidedQuad(
                    tessellator,
                    x + minX,
                    y + minY,
                    z + minZ,
                    uMin,
                    vMax,
                    x + maxX,
                    y + maxY,
                    z + minZ,
                    uMin,
                    vMin,
                    x + maxX,
                    y + maxY,
                    z + maxZ,
                    uMax,
                    vMin,
                    x + minX,
                    y + minY,
                    z + maxZ,
                    uMax,
                    vMax);
                addDoubleSidedQuad(
                    tessellator,
                    x + maxX,
                    y + minY,
                    z + minZ,
                    uMin,
                    vMax,
                    x + minX,
                    y + maxY,
                    z + minZ,
                    uMin,
                    vMin,
                    x + minX,
                    y + maxY,
                    z + maxZ,
                    uMax,
                    vMin,
                    x + maxX,
                    y + minY,
                    z + maxZ,
                    uMax,
                    vMax);
                break;

            default:
                break;
        }
    }

    private void addDoubleSidedQuad(Tessellator tessellator, double x1, double y1, double z1, double u1, double v1,
        double x2, double y2, double z2, double u2, double v2, double x3, double y3, double z3, double u3, double v3,
        double x4, double y4, double z4, double u4, double v4) {

        tessellator.addVertexWithUV(x1, y1, z1, u1, v1);
        tessellator.addVertexWithUV(x2, y2, z2, u2, v2);
        tessellator.addVertexWithUV(x3, y3, z3, u3, v3);
        tessellator.addVertexWithUV(x4, y4, z4, u4, v4);

        tessellator.addVertexWithUV(x4, y4, z4, u4, v4);
        tessellator.addVertexWithUV(x3, y3, z3, u3, v3);
        tessellator.addVertexWithUV(x2, y2, z2, u2, v2);
        tessellator.addVertexWithUV(x1, y1, z1, u1, v1);
    }

    private boolean isBlockConnected(IBlockAccess world, int x, int y, int z, ForgeDirection direction) {
        return world.isSideSolid(
            x + direction.offsetX,
            y + direction.offsetY,
            z + direction.offsetZ,
            direction.getOpposite(),
            false)
            || (world
                .getBlock(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ) instanceof BushBlock);
    }
}
