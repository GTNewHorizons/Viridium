package net.junedev.viridium.client.renderers.blocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.junedev.viridium.blocks.BushBlock;
import net.junedev.viridium.blocks.TallPlantBlock;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

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

        long hash = TallPlantBlock.positionHash(x, z);

        double offsetX = TallPlantBlock.offset(hash, 16, RANDOM_OFFSET);
        double offsetZ = TallPlantBlock.offset(hash, 24, RANDOM_OFFSET);

        Tessellator tessellator = Tessellator.instance;

        tessellator.setBrightness(
            block.getMixedBrightnessForBlock(world, x, y, z)
        );

        tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);

        renderCrossedPlanes(x, y, z, block, plantBlock.getIcon(0, world.getBlockMetadata(x, y, z)),
            0.5D - halfSize + offsetX, 0, 0.5D - halfSize + offsetZ,
            0.5D + halfSize + offsetX, 1, 0.5D + halfSize  + offsetZ);

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

    private void renderCrossedPlanes(int x, int y, int z, Block block, IIcon icon, double minX,
                                              double minY, double minZ, double maxX, double maxY, double maxZ) {

        Tessellator tessellator = Tessellator.instance;
        double uMin = icon.getMinU();
        double uMax = icon.getMaxU();
        double vMin = icon.getMinV();
        double vMax = icon.getMaxV();

        addDoubleSidedQuad(
            tessellator,
            x + minX, y + minY, z + minZ, uMin, vMax,
            x + maxX, y + minY, z + maxZ, uMax, vMax,
            x + maxX, y + maxY, z + maxZ, uMax, vMin,
            x + minX, y + maxY, z + minZ, uMin, vMin);
        addDoubleSidedQuad(
            tessellator,
            x + maxX, y + minY, z + minZ, uMax, vMax,
            x + minX, y + minY, z + maxZ, uMin, vMax,
            x + minX, y + maxY, z + maxZ, uMin, vMin,
            x + maxX, y + maxY, z + minZ, uMax, vMin);
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
