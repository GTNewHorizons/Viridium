package net.junedev.viridium.mixin.mixins.early.minecraft;

import net.junedev.viridium.client.renderers.utils.RenderingUtils;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.spongepowered.asm.mixin.injection.Coerce;

@Mixin(RenderBlocks.class)
public abstract class MixinRenderBlocks_RenderXGrass {

    @WrapOperation(
        method = {"renderCrossedSquares", "renderBlockDoublePlant"},
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/RenderBlocks;drawCrossedSquares(Lnet/minecraft/util/IIcon;DDDF)V"))
    private void viridium$renderXGrass(RenderBlocks renderer, IIcon icon, double x, double y, double z, float size,
                                       Operation<Void> original, @Coerce Block block, int bx, int by, int bz) {

        double centerX = x + 0.5D;
        double centerZ = z + 0.5D;
        double rotation = RenderingUtils.getRandomRotation(RenderingUtils.positionHash(bx, bz));
        RenderingUtils.renderCrossedPlanes(bx, by, bz, icon, centerX - bx, centerZ - bz, 0.5D * size, rotation);

        if (block != Blocks.tallgrass) {
            return;
        }

        // Blocks.tallgrass also stores dead shrubs and ferns as metadata variants.
        if (RenderingUtils.hash01(bx, bz, by) < 0.2F && renderer.blockAccess.getBlockMetadata(bx, by, bz) == 1) {
            RenderingUtils.renderOpeningPlanes(
                centerX,
                by,
                centerZ,
                icon,
                0.5D * size,
                Math.PI / 4.0D,
                0.1D,
                -rotation);
        }
    }

}
