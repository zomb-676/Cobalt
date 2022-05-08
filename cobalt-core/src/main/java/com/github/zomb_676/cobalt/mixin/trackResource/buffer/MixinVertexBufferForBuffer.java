package com.github.zomb_676.cobalt.mixin.trackResource.buffer;

import com.github.zomb_676.cobalt.window.ResourceMonitor;
import com.mojang.blaze3d.vertex.VertexBuffer;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = VertexBuffer.class, remap = false)
abstract class MixinVertexBufferForBuffer {
    @Shadow
    public int vertextBufferId;
    @Shadow
    public int indexBufferId;

    @Redirect(
            method = "lambda$new$0",
            at = @At(
                    value = "FIELD",
                    target = "Lcom/mojang/blaze3d/vertex/VertexBuffer;vertextBufferId:I",
                    opcode = Opcodes.PUTFIELD
            )
    )
    private void retrieveVertexBufferId(VertexBuffer instance, int value) {
        ((MixinVertexBufferForBuffer) (Object) instance).vertextBufferId = value;
        ResourceMonitor.Companion.switchMonitor().getBuffer().add(value);
    }

    @Redirect(
            method = "close",
            at = @At(
                    value = "FIELD",
                    target = "Lcom/mojang/blaze3d/vertex/VertexBuffer;" +
                            "indexBufferId:I",
                    opcode = Opcodes.PUTFIELD
            )
    )
    private void resetIndexBuffer(VertexBuffer instance, int value) {
        ((MixinVertexBufferForBuffer) (Object) instance).indexBufferId = -1;
        ResourceMonitor.Companion.switchMonitor().getIndexBuffer().remove(value);
    }

    @Redirect(
            method = "close",
            at = @At(
                    value = "FIELD",
                    target = "Lcom/mojang/blaze3d/vertex/VertexBuffer;" +
                            "vertextBufferId:I",
                    opcode = Opcodes.PUTFIELD
            )
    )
    private void resetVertexBuffer(VertexBuffer instance, int value) {
        ((MixinVertexBufferForBuffer) (Object) instance).indexBufferId = -1;
        ResourceMonitor.Companion.switchMonitor().getBuffer().remove(value);
    }


}
