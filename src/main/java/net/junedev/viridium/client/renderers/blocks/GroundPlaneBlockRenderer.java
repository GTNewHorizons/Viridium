package net.junedev.viridium.client.renderers.blocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.junedev.viridium.client.renderers.utils.RenderingUtils;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class GroundPlaneBlockRenderer implements ISimpleBlockRenderingHandler {

    private final int renderId;

    public GroundPlaneBlockRenderer(int renderId) {
        this.renderId = renderId;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {

    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        Tessellator tessellator = Tessellator.instance;
        int i1 = world.getBlockMetadata(x, y, z);
        IIcon iicon = block.getIcon(0, i1);

//        if (renderer.hasOverrideBlockTexture())
//        {
//            iicon = this.overrideBlockTexture;
//        }

        RenderingUtils.resetLightAndColor(world, tessellator, block, x, y, z);

        double d3 = iicon.getMinU();
        double d4 = iicon.getMinV();
        double d0 = iicon.getMaxU();
        double d1 = iicon.getMaxV();
        double d2 = 0.05000000074505806D;

        RenderingUtils.addSingleQuad(tessellator,
            x, y + d2, z, d0, d4,
            x, y + d2, z + 1, d0, d1,
             x + 1, y + d2, z + 1, d3, d1,
            x + 1, y + d2, z, d3, d4
        );

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
