package com.github.zomb_676.cobalt.mixin.traceState;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Locale;

@Mixin(RenderSystem.class)
public class MixinRenderSystem {

    @Shadow
    static int[] shaderTextures;


    @Inject(
            method = "_setShaderTexture(ILnet/minecraft/resources/ResourceLocation;)V",
            at=@At(
                    value = "INVOKE_ASSIGN",
                    target = "Lnet/minecraft/client/renderer/texture/AbstractTexture;getId()I"
            )
    )
    private static void a(int pShaderTexture, ResourceLocation pTextureId, CallbackInfo ci){
        if (pShaderTexture >= 0 && pShaderTexture < shaderTextures.length) {

        }
    }

}
