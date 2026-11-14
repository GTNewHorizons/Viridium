package net.junedev.viridium.mixin.mixins.early.minecraft;

import net.minecraft.block.BlockLiquid;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(BlockLiquid.class)
public abstract class MixinBlockLiquid_WaterTextures {

    @ModifyArg(
        method = "registerBlockIcons",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/texture/IIconRegister;registerIcon(Ljava/lang/String;)Lnet/minecraft/util/IIcon;"),
        index = 0)
    private String viridium$waterTextureName(String name) {
        if ("water_still".equals(name)) {
            return "viridium:water/water_still";
        }
        if ("water_flow".equals(name)) {
            return "viridium:water/water_flow";
        }
        return name;
    }
}
