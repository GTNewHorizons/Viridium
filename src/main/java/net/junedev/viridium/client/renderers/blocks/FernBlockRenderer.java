package net.junedev.viridium.client.renderers.blocks;

import net.junedev.viridium.blocks.FernBlock;
import net.junedev.viridium.client.renderers.utils.RenderingUtils;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

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

        long hash = RenderingUtils.positionHash(x, z);

        double offsetX = RenderingUtils.getRandomOffset(hash, 16, RANDOM_OFFSET);
        double offsetZ = RenderingUtils.getRandomOffset(hash, 24, RANDOM_OFFSET);

        double rotation = RenderingUtils.getRandomRotation(hash);

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
            plantBlock.getMinimumRadius(),
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
        double openingAngle, double minimumRadius, double rotation) {

        Tessellator tessellator = Tessellator.instance;
        double uMin = icon.getMinU();
        double uMax = icon.getMaxU();
        double vMin = icon.getMinV();
        double vMax = icon.getMaxV();

        double cos = Math.cos(rotation) * halfSize;
        double sin = Math.sin(rotation) * halfSize;

        // Angle above the ground: keep the frond length constant while tilting it.
        double length = halfSize * 2;
        // Normalized plane area
        double height = Math.sin(openingAngle) * length;
        double reach = Math.cos(openingAngle) * length;
        // Sine and Cosine taking into account their actual radius
        double innerOutwardCos = Math.cos(rotation) * minimumRadius;
        double innerOutwardSin = Math.sin(rotation) * minimumRadius;
        double outerOutwardCos = Math.cos(rotation) * (reach + minimumRadius);
        double outerOutwardSin = Math.sin(rotation) * (reach + minimumRadius);

        // Rotation matrix
        // ( c s )
        // ( -s c )

        RenderingUtils.addDoubleSidedQuad(
            tessellator,
            centerX - cos + innerOutwardSin,
            y,
            centerZ + sin + innerOutwardCos,
            uMin,
            vMax,
            centerX + cos + innerOutwardSin,
            y,
            centerZ - sin + innerOutwardCos,
            uMax,
            vMax,
            centerX + cos + outerOutwardSin,
            y + height,
            centerZ - sin + outerOutwardCos,
            uMax,
            vMin,
            centerX - cos + outerOutwardSin,
            y + height,
            centerZ + sin + outerOutwardCos,
            uMin,
            vMin);

        RenderingUtils.addDoubleSidedQuad(
            tessellator,
            centerX + sin + innerOutwardCos,
            y,
            centerZ + cos - innerOutwardSin,
            uMin,
            vMax,
            centerX - sin + innerOutwardCos,
            y,
            centerZ - cos - innerOutwardSin,
            uMax,
            vMax,
            centerX - sin + outerOutwardCos,
            y + height,
            centerZ - cos - outerOutwardSin,
            uMax,
            vMin,
            centerX + sin + outerOutwardCos,
            y + height,
            centerZ + cos - outerOutwardSin,
            uMin,
            vMin);

        RenderingUtils.addDoubleSidedQuad(
            tessellator,
            centerX + cos - innerOutwardSin,
            y,
            centerZ - sin - innerOutwardCos,
            uMin,
            vMax,
            centerX - cos - innerOutwardSin,
            y,
            centerZ + sin - innerOutwardCos,
            uMax,
            vMax,
            centerX - cos - outerOutwardSin,
            y + height,
            centerZ + sin - outerOutwardCos,
            uMax,
            vMin,
            centerX + cos - outerOutwardSin,
            y + height,
            centerZ - sin - outerOutwardCos,
            uMin,
            vMin);

        RenderingUtils.addDoubleSidedQuad(
            tessellator,
            centerX - sin - innerOutwardCos,
            y,
            centerZ - cos + innerOutwardSin,
            uMin,
            vMax,
            centerX + sin - innerOutwardCos,
            y,
            centerZ + cos + innerOutwardSin,
            uMax,
            vMax,
            centerX + sin - outerOutwardCos,
            y + height,
            centerZ + cos + outerOutwardSin,
            uMax,
            vMin,
            centerX - sin - outerOutwardCos,
            y + height,
            centerZ - cos + outerOutwardSin,
            uMin,
            vMin);

    }
}
