package net.junedev.viridium.client.renderers.blocks;

import net.junedev.viridium.blocks.TallPlantBlock;
import net.junedev.viridium.client.renderers.utils.RenderingUtils;
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

        RenderingUtils.resetLightAndColor(world, tessellator, block, x, y, z);

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

        RenderingUtils.addDoubleSidedQuad(
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

        RenderingUtils.addDoubleSidedQuad(
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

}
