package net.junedev.viridium.mixin.mixins.early.minecraft;

import net.junedev.viridium.client.renderers.world.water.WaterTint;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;

@Mixin(RenderBlocks.class)
public abstract class MixinRenderBlocks_WaterTint {

    @WrapOperation(
        method = "renderBlockLiquid",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/block/Block;colorMultiplier(Lnet/minecraft/world/IBlockAccess;III)I"))
    private int viridium$tintWater(Block block, IBlockAccess world, int x, int y, int z, Operation<Integer> original) {
        int color = original.call(block, world, x, y, z);
        if (block.getMaterial() != Material.water) {
            return color;
        }
        return WaterTint.apply(color, world, x, y, z);
    }
}
