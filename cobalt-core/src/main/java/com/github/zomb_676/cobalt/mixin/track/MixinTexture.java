package com.github.zomb_676.cobalt.mixin.track;

import com.github.zomb_676.cobalt.window.ResourceMonitor;
import com.mojang.blaze3d.platform.TextureUtil;
import org.checkerframework.checker.units.qual.A;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = TextureUtil.class, remap = false)
public class MixinTexture {
    @Inject(method = "generateTextureId", at = @At("RETURN"))
    private static void getGeneratedTextureID(CallbackInfoReturnable<Integer> cir) {
        var id = cir.getReturnValue();
        ResourceMonitor.Companion.switchMonitor().getTextures().add(id);
    }

    @Inject(method = "releaseTextureId",at = @At("HEAD"))
    private static void getReleaseTextureID(int pTextureId, CallbackInfo ci)
    {
        ResourceMonitor.Companion.switchMonitor().getTextures().remove(pTextureId);
    }

}
