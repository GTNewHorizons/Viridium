package net.junedev.viridium.mixin.mixins.early.angelica;

import java.util.List;

import net.junedev.viridium.client.config.OptionPages;
import net.minecraft.client.gui.GuiScreen;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.flashyreese.mods.reeses_sodium_options.client.gui.ReeseSodiumVideoOptionsScreen;
import me.jellysquid.mods.sodium.client.gui.options.OptionPage;

@Mixin(value = ReeseSodiumVideoOptionsScreen.class, remap = false)
public abstract class MixinReeseSodiumVideoOptionsScreen_ViridiumPage {

    @Shadow
    @Final
    private List<OptionPage> pages;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void viridium$addPage(GuiScreen prevScreen, CallbackInfo ci) {
        pages.add(OptionPages.viridium());
    }
}
