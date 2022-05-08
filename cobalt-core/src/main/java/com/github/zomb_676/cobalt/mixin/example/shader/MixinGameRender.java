package com.github.zomb_676.cobalt.mixin.example.shader;

import com.github.zomb_676.cobalt.example.BlurEffect;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class MixinGameRender {
    @Inject(
            method = "renderLevel",
            at = @At("TAIL")
    )
    private void blur(float pPartialTicks, long pFinishTimeNano, PoseStack pMatrixStack, CallbackInfo ci) {
        BlurEffect.INSTANCE.blur(pPartialTicks, pFinishTimeNano, pMatrixStack, ci);
    }
}
