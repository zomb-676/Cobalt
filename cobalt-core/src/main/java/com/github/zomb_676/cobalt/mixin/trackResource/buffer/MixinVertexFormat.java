package com.github.zomb_676.cobalt.mixin.trackResource.buffer;


import com.github.zomb_676.cobalt.window.ResourceMonitor;
import com.mojang.blaze3d.vertex.VertexFormat;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = VertexFormat.class, remap = false)
abstract class MixinVertexFormat {
    @Shadow
    int indexBufferObject;
    @Shadow
    int vertexBufferObject;

    @Redirect(
            method = "getOrCreateVertexBufferObject",
            at = @At(
                    value = "FIELD",
                    target = "Lcom/mojang/blaze3d/vertex/VertexFormat;" +
                            "vertexBufferObject:I",
                    opcode = Opcodes.PUTFIELD
            )
    )
    private void setVerBufferObjectId(VertexFormat instance, int value) {
        ((MixinVertexFormat) (Object) (instance)).vertexBufferObject = value;
        ResourceMonitor.Companion.switchMonitor().getBuffer().add(value);
    }

    @Redirect(
            method = "getOrCreateIndexBufferObject",
            at = @At(
                    value = "FIELD",
                    target = "Lcom/mojang/blaze3d/vertex/VertexFormat;" +
                            "indexBufferObject:I",
                    opcode = Opcodes.PUTFIELD
            )
    )
    private void setIndexBufferObjectId(VertexFormat instance, int value) {
        ((MixinVertexFormat) (Object) (instance)).indexBufferObject = value;
        ResourceMonitor.Companion.switchMonitor().getIndexBuffer().add(value);
    }

}
