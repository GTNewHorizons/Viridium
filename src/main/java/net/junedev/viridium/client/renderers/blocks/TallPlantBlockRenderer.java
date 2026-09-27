package net.junedev.viridium.client.renderers.blocks;

import net.junedev.viridium.blocks.TallPlantBlock;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class TallPlantBlockRenderer implements ISimpleBlockRenderingHandler {

    private static final double RANDOM_OFFSET = 0.4D;

    private final int renderId;

    public TallPlantBlockRenderer(int renderId) {
        this.renderId = renderId;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {

    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
        RenderBlocks renderer) {
        TallPlantBlock plantBlock = (TallPlantBlock) block;
        double halfSize = plantBlock.getPixelWidth() / (32D);

        long hash = TallPlantBlock.getPositionHash(x, z);

        double offsetX = TallPlantBlock.getRandomOffset(hash, 16, RANDOM_OFFSET);
        double offsetZ = TallPlantBlock.getRandomOffset(hash, 24, RANDOM_OFFSET);

        double rotation = TallPlantBlock.getRandomRotation(hash);

        Tessellator tessellator = Tessellator.instance;

        resetLightAndColor(world, tessellator, block, x, y, z);

        renderCrossedPlanes(
            x,
            y,
            z,
            plantBlock.getIcon(0, world.getBlockMetadata(x, y, z)),
            0.5D + offsetX,
            0.5D + offsetZ,
            halfSize,
            rotation);

        renderer.setRenderBoundsFromBlock(block);

        return true;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    @Override
    public int getRenderId() {
        return renderId;
    }

    // Needs to be done every time a block is placed, standard renderer techniques already implement it
    private void resetLightAndColor(IBlockAccess world, Tessellator tessellator, Block block, int x, int y, int z) {
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));

        tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);
    }

    private void renderCrossedPlanes(int x, int y, int z, IIcon icon, double centerX, double centerZ, double halfSize,
        double rotation) {

        Tessellator tessellator = Tessellator.instance;
        double uMin = icon.getMinU();
        double uMax = icon.getMaxU();
        double vMin = icon.getMinV();
        double vMax = icon.getMaxV();
        double cos = Math.cos(rotation);
        double sin = Math.sin(rotation);

        // Rotation matrix
        // ( c -s )
        // ( s c )
        // Og positions relative to center (-h, -h) -> (h, h), (-h, h) -> (h, -h)
        double firstMinX = centerX - halfSize * cos + halfSize * sin;
        double firstMinZ = centerZ - halfSize * sin - halfSize * cos;
        double firstMaxX = centerX + halfSize * cos - halfSize * sin;
        double firstMaxZ = centerZ + halfSize * sin + halfSize * cos;

        addDoubleSidedQuad(
            tessellator,
            x + firstMinX,
            y,
            z + firstMinZ,
            uMin,
            vMax,
            x + firstMaxX,
            y,
            z + firstMaxZ,
            uMax,
            vMax,
            x + firstMaxX,
            y + 1,
            z + firstMaxZ,
            uMax,
            vMin,
            x + firstMinX,
            y + 1,
            z + firstMinZ,
            uMin,
            vMin);

        double secondMinX = centerX - halfSize * cos - halfSize * sin;
        double secondMinZ = centerZ - halfSize * sin + halfSize * cos;
        double secondMaxX = centerX + halfSize * cos + halfSize * sin;
        double secondMaxZ = centerZ + halfSize * sin - halfSize * cos;

        addDoubleSidedQuad(
            tessellator,
            x + secondMaxX,
            y,
            z + secondMaxZ,
            uMax,
            vMax,
            x + secondMinX,
            y,
            z + secondMinZ,
            uMin,
            vMax,
            x + secondMinX,
            y + 1,
            z + secondMinZ,
            uMin,
            vMin,
            x + secondMaxX,
            y + 1,
            z + secondMaxZ,
            uMax,
            vMin);
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

}
