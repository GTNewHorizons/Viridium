package net.junedev.viridium.client.renderers.blocks;

import net.junedev.viridium.client.renderers.utils.RenderingUtils;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class XShapedPlantBlockRenderer implements ISimpleBlockRenderingHandler {

    private static final double RANDOM_OFFSET = 0.4D;

    private final int renderId;

    public XShapedPlantBlockRenderer(int renderId) {
        this.renderId = renderId;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {

    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
        RenderBlocks renderer) {

        return RenderingUtils.renderXShapedPlant(
            world,
            x,
            y,
            z,
            block,
            block.getIcon(0, world.getBlockMetadata(x, y, z)),
            1 / 2D,
            renderer,
            RANDOM_OFFSET);
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
