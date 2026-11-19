package net.junedev.viridium.client.renderers.blocks;

import net.junedev.viridium.blocks.FernBlock;
import net.junedev.viridium.client.renderers.utils.RenderingUtils;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
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

        RenderingUtils.resetLightAndColor(world, tessellator, block, x, y, z);

        RenderingUtils.renderOpeningPlanes(
            0.5D + offsetX + x,
            y,
            0.5D + offsetZ + z,
            plantBlock.getIcon(0, world.getBlockMetadata(x, y, z)),
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
}
