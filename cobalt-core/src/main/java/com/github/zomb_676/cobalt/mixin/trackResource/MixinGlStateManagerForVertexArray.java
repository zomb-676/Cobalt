package com.github.zomb_676.cobalt.mixin.trackResource;

import com.github.zomb_676.cobalt.window.ResourceMonitor;
import com.mojang.blaze3d.platform.GlStateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = GlStateManager.class, remap = false)
abstract class MixinGlStateManagerForVertexArray {
    @Inject(method = "_glGenVertexArrays", at = @At("RETURN"))
    private static void retrieveVertexArrayId(CallbackInfoReturnable<Integer> cir) {
        ResourceMonitor.Companion.switchMonitor().getVertexArray().add(cir.getReturnValue());
    }

    @Inject(method = "_glDeleteVertexArrays", at = @At("TAIL"))
    private static void deleteVertexArrayId(int pArrayId, CallbackInfo ci) {
        ResourceMonitor.Companion.switchMonitor().getVertexArray().remove(pArrayId);
    }

}
