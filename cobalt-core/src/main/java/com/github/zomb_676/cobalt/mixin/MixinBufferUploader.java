package com.github.zomb_676.cobalt.mixin;

import com.github.zomb_676.cobalt.window.ModEventHandle;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.VertexFormat;
import org.jetbrains.annotations.Contract;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = BufferUploader.class, remap = false)
public abstract class MixinBufferUploader {
    private static int ILastVertexArrayObject;
    private static int ILastVertexBufferObject;
    private static int ILastIndexBufferObject;
    private static VertexFormat ILastVertexFormat;

    @Shadow
    static int lastVertexArrayObject;
    @Shadow
    static int lastVertexBufferObject;
    @Shadow
    static int lastIndexBufferObject;
    @Shadow
    static VertexFormat lastFormat;


    @Redirect(
            method = "*",
            at = @At(value = "FIELD",
                    target = "Lcom/mojang/blaze3d/vertex/BufferUploader;" +
                            "lastVertexArrayObject:I",
                    opcode = Opcodes.PUTSTATIC)
    )
    private static void redirectSetLastVertexArrayObject(int value) {
        if (Thread.currentThread() == ModEventHandle.getThread()) {
            ILastVertexArrayObject = value;
        } else {
            lastVertexArrayObject=value;
        }
    }

    @Redirect(
            method = "*",
            at = @At(value = "FIELD",
                    target = "Lcom/mojang/blaze3d/vertex/BufferUploader;" +
                            "lastVertexArrayObject:I",
                    opcode = Opcodes.GETSTATIC)
    )
    private static int redirectGetLastVertexArrayObject() {
        if (Thread.currentThread() == ModEventHandle.getThread()) {
            return ILastVertexArrayObject;
        } else {
            return lastVertexArrayObject;
        }
    }

    @Redirect(
            method = "*",
            at = @At(value = "FIELD",
                    target = "Lcom/mojang/blaze3d/vertex/BufferUploader;" +
                            "lastVertexBufferObject:I",
                    opcode = Opcodes.PUTSTATIC)
    )
    private static void redirectSetLastVertexBufferObject(int value) {
        if (Thread.currentThread() == ModEventHandle.getThread()) {
            ILastVertexBufferObject = value;
        } else {
            lastVertexBufferObject = value;
        }
    }

    @Redirect(
            method = "*",
            at = @At(value = "FIELD",
                    target = "Lcom/mojang/blaze3d/vertex/BufferUploader;" +
                            "lastVertexBufferObject:I",
                    opcode = Opcodes.GETSTATIC)
    )
    private static int redirectGetLastVertexBufferObject() {
        if (Thread.currentThread() == ModEventHandle.getThread()) {
            return ILastVertexBufferObject;
        } else {
            return lastVertexBufferObject;
        }
    }

    @Redirect(
            method = "*",
            at = @At(value = "FIELD",
                    target = "Lcom/mojang/blaze3d/vertex/BufferUploader;" +
                            "lastIndexBufferObject:I",
                    opcode = Opcodes.PUTSTATIC)
    )
    private static void redirectSetLastIndexBufferObject(int value) {
        if (Thread.currentThread() == ModEventHandle.getThread()) {
            ILastIndexBufferObject = value;
        } else {
            lastIndexBufferObject = value;
        }
    }

    @Redirect(
            method = "*",
            at = @At(value = "FIELD",
                    target = "Lcom/mojang/blaze3d/vertex/BufferUploader;" +
                            "lastIndexBufferObject:I",
                    opcode = Opcodes.GETSTATIC)
    )
    private static int redirectGetLastIndexBufferObject() {
        if (Thread.currentThread() == ModEventHandle.getThread()) {
            return ILastIndexBufferObject;
        } else {
            return lastIndexBufferObject;
        }
    }

    @Redirect(
            method = "*",
            at = @At(value = "FIELD",
                    target = "Lcom/mojang/blaze3d/vertex/BufferUploader;" +
                            "lastFormat:Lcom/mojang/blaze3d/vertex/VertexFormat;",
                    opcode = Opcodes.PUTSTATIC)
    )
    private static void redirectSetLastVertexFormat(VertexFormat vertexFormat) {
        if (Thread.currentThread() == ModEventHandle.getThread()) {
            ILastVertexFormat = vertexFormat;
        } else {
            lastFormat=vertexFormat;
        }
    }

    @Redirect(
            method = "*",
            at = @At(value = "FIELD",
                    target = "Lcom/mojang/blaze3d/vertex/BufferUploader;" +
                            "lastFormat:Lcom/mojang/blaze3d/vertex/VertexFormat;",
                    opcode = Opcodes.GETSTATIC)
    )
    private static VertexFormat redirectGetLastVertexFormat() {
        if (Thread.currentThread() == ModEventHandle.getThread()) {
            return ILastVertexFormat;
        } else {
            return lastFormat;
        }
    }


}
