package net.junedev.viridium.client.renderers.blocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.junedev.viridium.blocks.FernBlock;
import net.junedev.viridium.blocks.TallPlantBlock;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class FernBlockRenderer implements ISimpleBlockRenderingHandler {
    private static final double RANDOM_OFFSET = 0.4D;

    private final int renderId;

    public FernBlockRenderer(int renderId) {
        this.renderId = renderId;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {

    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
                                    RenderBlocks renderer) {
        FernBlock plantBlock = (FernBlock) block;

        long hash = TallPlantBlock.getPositionHash(x, z);

        double offsetX = TallPlantBlock.getRandomOffset(hash, 16, RANDOM_OFFSET);
        double offsetZ = TallPlantBlock.getRandomOffset(hash, 24, RANDOM_OFFSET);

        double rotation = TallPlantBlock.getRandomRotation(hash);

        Tessellator tessellator = Tessellator.instance;

        resetLightAndColor(world, tessellator, block, x, y, z);

        renderOpeningPlanes(
            x,
            y,
            z,
            plantBlock.getIcon(0, world.getBlockMetadata(x, y, z)),
            0.5D + offsetX + x,
            0.5D + offsetZ + z,
            (double) plantBlock.getHalfSize() / 16,
            Math.toRadians(plantBlock.getOpeningAngle()),
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

    private void renderOpeningPlanes(int x, int y, int z, IIcon icon, double centerX, double centerZ, double halfSize,
                                     double openingAngle, double rotation) {

        Tessellator tessellator = Tessellator.instance;
        double uMin = icon.getMinU();
        double uMax = icon.getMaxU();
        double vMin = icon.getMinV();
        double vMax = icon.getMaxV();

        double cos = Math.cos(rotation) * halfSize;
        double sin = Math.sin(rotation) * halfSize;

        // Angle above the ground: keep the frond length constant while tilting it.
        double length = halfSize * 2;
        double height = Math.sin(openingAngle) * length;
        double reach = Math.cos(openingAngle) * length;
        double outwardCos = Math.cos(rotation) * reach;
        double outwardSin = Math.sin(rotation) * reach;

        // Rotation matrix
        // (  c s )
        // ( -s c )

        addDoubleSidedQuad(
            tessellator,
            centerX - cos,
            y,
             centerZ + sin,
            uMin,
            vMax,
            centerX + cos,
            y,
             centerZ - sin,
            uMax,
            vMax,
            centerX + cos + outwardSin,
            y + height,
             centerZ - sin + outwardCos,
            uMax,
            vMin,
            centerX - cos + outwardSin,
            y + height,
             centerZ + sin + outwardCos,
            uMin,
            vMin);

        addDoubleSidedQuad(
            tessellator,
            centerX + sin,
            y,
             centerZ + cos,
            uMin,
            vMax,
            centerX - sin,
            y,
             centerZ - cos,
            uMax,
            vMax,
            centerX - sin + outwardCos,
            y + height,
             centerZ - cos - outwardSin,
            uMax,
            vMin,
            centerX + sin + outwardCos,
            y + height,
             centerZ + cos - outwardSin,
            uMin,
            vMin);

        addDoubleSidedQuad(
            tessellator,
            centerX + cos,
            y,
             centerZ - sin,
            uMin,
            vMax,
            centerX - cos,
            y,
             centerZ + sin,
            uMax,
            vMax,
            centerX - cos - outwardSin,
            y + height,
             centerZ + sin - outwardCos,
            uMax,
            vMin,
            centerX + cos - outwardSin,
            y + height,
             centerZ - sin - outwardCos,
            uMin,
            vMin);

        addDoubleSidedQuad(
            tessellator,
            centerX - sin,
            y,
             centerZ - cos,
            uMin,
            vMax,
            centerX + sin,
            y,
             centerZ + cos,
            uMax,
            vMax,
            centerX + sin - outwardCos,
            y + height,
             centerZ + cos + outwardSin,
            uMax,
            vMin,
            centerX - sin - outwardCos,
            y + height,
             centerZ - cos + outwardSin,
            uMin,
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
