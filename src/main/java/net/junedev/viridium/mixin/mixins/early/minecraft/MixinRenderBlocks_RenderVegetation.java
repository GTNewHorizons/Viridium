package net.junedev.viridium.mixin.mixins.early.minecraft;

import net.junedev.viridium.client.renderers.utils.RenderingUtils;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;

@Mixin(RenderBlocks.class)
public abstract class MixinRenderBlocks_RenderVegetation {

    @WrapOperation(
        method = { "renderCrossedSquares", "renderBlockDoublePlant" },
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/RenderBlocks;drawCrossedSquares(Lnet/minecraft/util/IIcon;DDDF)V"))
    private void viridium$renderXGrass(RenderBlocks renderer, IIcon icon, double x, double y, double z, float size,
        Operation<Void> original, @Coerce Block block, int bx, int by, int bz) {

        double centerX = x + 0.5D;
        double centerZ = z + 0.5D;
        double rotation = RenderingUtils.getRandomRotation(RenderingUtils.positionHash(bx, bz));
        double halfWidth = 0.5D * size;

        // Blocks.tallgrass also stores dead shrubs and ferns as metadata variants.
        boolean isGrass = block == Blocks.tallgrass && renderer.blockAccess.getBlockMetadata(bx, by, bz) == 1;
        float variant = isGrass ? RenderingUtils.hash01(bx, bz, by) : 1.0F;
        if (variant >= 0.2F && variant < 0.4F) {
            RenderingUtils.renderTriangularPlanes(centerX, by, centerZ, icon, halfWidth, size, 0.125D * size, rotation);
            return;
        }

        RenderingUtils.renderCrossedPlanes(bx, by, bz, icon, centerX - bx, centerZ - bz, halfWidth, rotation);
        if (variant < 0.2F) {
            RenderingUtils.renderOpeningPlanes(centerX, by, centerZ, icon, halfWidth, Math.PI / 4.0D, 0.1D, -rotation);
        }
    }

    @ModifyConstant(method = "renderBlockVine", constant = @Constant(doubleValue = 0.05000000074505806D))
    private double viridium$vineOffset(double original) {
        return 1D / 16;
    }

}
