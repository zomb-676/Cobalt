package com.github.zomb_676.cobalt.mixin.trackResource.buffer;

import com.github.zomb_676.cobalt.window.ResourceMonitor;
import com.mojang.blaze3d.systems.RenderSystem;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = RenderSystem.AutoStorageIndexBuffer.class, remap = false)
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
    private void setName(RenderSystem.AutoStorageIndexBuffer instance, int value) {
        ((MixinAutoStorageIndexBuffer) (Object) instance).name = value;
        ResourceMonitor.Companion.switchMonitor().getIndexBuffer().add(value);
    }
}
