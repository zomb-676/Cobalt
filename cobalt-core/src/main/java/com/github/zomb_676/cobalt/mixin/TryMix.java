package com.github.zomb_676.cobalt.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Matrix4f;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.LightTexture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelRenderer.class)
public class TryMix {
    @Inject(method = "renderLevel", at = @At("HEAD"))
    public void render(PoseStack stack, float pPartialTick, long pFinishNanoTime, boolean pRenderBlockOutline,
                       Camera camera, GameRenderer gameRenderer, LightTexture lightTexture
            , Matrix4f ProjectionMatrix, CallbackInfo callbackInfo) {

    }
}
