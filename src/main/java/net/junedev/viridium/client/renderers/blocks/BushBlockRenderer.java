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

        if (!bushBlock.isOpaqueCube()) {
            renderer.setRenderBounds(coreMin, 0.0625, coreMin, coreMax, 0.9375, coreMax);
            renderInventoryCuboid(block, metadata, renderer, bushBlock.getBranchIcon());
        }

        renderer.setRenderBounds(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
        renderInventoryCuboid(
            block,
            metadata,
            renderer,
            bushBlock.isOpaqueCube() ? bushBlock.getFastLeaveIcon() : bushBlock.getLeaveIcon());

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
            if (!bushBlock.isOpaqueCube()) renderBranchesCuboid(world, x, y, z, block, renderer, bushBlock);
        }

        // Leaves
        if (!hasOverrideTexture) {
            renderer.setOverrideBlockTexture(
                bushBlock.isOpaqueCube() ? bushBlock.getFastLeaveIcon() : bushBlock.getLeaveIcon());
        }

        renderer.setRenderBounds(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
        renderer.renderStandardBlock(block, x, y, z);

        if (!hasOverrideTexture) {
            renderer.clearOverrideBlockTexture();
        }

        renderer.setRenderBoundsFromBlock(block);

        return true;
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

}
