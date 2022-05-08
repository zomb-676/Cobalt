package com.github.zomb_676.cobalt.mixin;

import com.github.zomb_676.cobalt.window.ModEventHandle;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Supplier;

@Mixin(value = RenderSystem.class, remap = false)
public abstract class MixinRenderSystem {

    private static final int[] shaderTextures = new int[12];
    private static final float[] shaderColor = new float[]{1.0F, 1.0F, 1.0F, 1.0F};

    private static ShaderInstance IShader;

    @Inject(method = "isOnRenderThread", at = @At("HEAD"), cancellable = true)
    private static void mixinIsOnRenderThread(CallbackInfoReturnable<Boolean> cir) {
        if (Thread.currentThread() == ModEventHandle.getThread()) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "_setShaderTexture(ILnet/minecraft/resources/ResourceLocation;)V", at = @At("HEAD"), cancellable = true)
    private static void mixin_setShaderTexture(int pShaderTexture, ResourceLocation pTextureId, CallbackInfo ci) {
        if (Thread.currentThread() == ModEventHandle.getThread()) {
            if (pShaderTexture >= 0 && pShaderTexture < shaderTextures.length) {
                TextureManager texturemanager = Minecraft.getInstance().getTextureManager();
                AbstractTexture abstracttexture = texturemanager.getTexture(pTextureId);
                shaderTextures[pShaderTexture] = abstracttexture.getId();
            }
            ci.cancel();
        }
    }

    @Inject(method = "_setShaderTexture(II)V", at = @At("HEAD"), cancellable = true)
    private static void mixin_setShaderTexture(int pShaderTexture, int pTextureId, CallbackInfo ci) {
        if (Thread.currentThread() == ModEventHandle.getThread()) {
            if (pShaderTexture >= 0 && pShaderTexture < shaderTextures.length) {
                shaderTextures[pShaderTexture] = pTextureId;
            }
            ci.cancel();
        }
    }

    @Inject(method = "getShaderTexture(I)I", at = @At("HEAD"), cancellable = true)
    private static void mixinGetShaderTexture(int pShaderTexture, CallbackInfoReturnable<Integer> cir) {
        if (Thread.currentThread() == ModEventHandle.getThread()) {
            RenderSystem.assertOnRenderThread();
            cir.setReturnValue(
                    pShaderTexture >= 0 && pShaderTexture < shaderTextures.length ? shaderTextures[pShaderTexture] : 0
            );
        }
    }

    @Inject(method = "_setShaderColor", at = @At("HEAD"), cancellable = true)
    private static void mixin_setShaderColor(float pRed, float pGreen, float pBlue, float pAlpha, CallbackInfo ci) {
        if (Thread.currentThread() == ModEventHandle.getThread()) {
            shaderColor[0] = pRed;
            shaderColor[1] = pGreen;
            shaderColor[2] = pBlue;
            shaderColor[3] = pAlpha;
            ci.cancel();
        }
    }

    @Inject(method = "getShaderColor", at = @At("HEAD"), cancellable = true)
    private static void mixinGetShaderColor(CallbackInfoReturnable<float[]> cir) {
        if (Thread.currentThread() == ModEventHandle.getThread()) {
            cir.setReturnValue(shaderColor);
        }
    }

    @Inject(method = "getShader", at = @At("HEAD"), cancellable = true)
    private static void mixinGetShader(CallbackInfoReturnable<ShaderInstance> cir) {
        if (Thread.currentThread() == ModEventHandle.getThread()) {
            cir.setReturnValue(IShader);
        }
    }

    @Inject(method = "setShader", at = @At("HEAD"), cancellable = true)
    private static void mixinSetShader(Supplier<ShaderInstance> pShaderSupplier, CallbackInfo ci) {
        if (Thread.currentThread() == ModEventHandle.getThread()) {
            IShader = pShaderSupplier.get();
            ci.cancel();
        }
    }
}
