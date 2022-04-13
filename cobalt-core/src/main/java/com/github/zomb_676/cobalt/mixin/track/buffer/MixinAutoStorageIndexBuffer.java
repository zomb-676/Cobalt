package com.github.zomb_676.cobalt.mixin.track.buffer;

import com.github.zomb_676.cobalt.window.ResourceMonitor;
import com.mojang.blaze3d.systems.RenderSystem;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.Area;
import org.lwjgl.opengl.GL43;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import javax.annotation.RegEx;

@Mixin(value = RenderSystem.AutoStorageIndexBuffer.class,remap = false)
abstract class MixinAutoStorageIndexBuffer {
    @Shadow
    private int name;

    @Redirect(
            method = "ensureStorage",
            at = @At(
                    value = "FIELD",
                    target = "Lcom/mojang/blaze3d/systems/RenderSystem$AutoStorageIndexBuffer;" +
                            "name:I",
                    opcode = Opcodes.PUTFIELD
            )
    )
    private static void setName(RenderSystem.AutoStorageIndexBuffer instance,int value){
        ((MixinAutoStorageIndexBuffer)(Object)instance).name = value;
        ResourceMonitor.Companion.switchMonitor().getIndexBuffer().add(value);
    }
}
