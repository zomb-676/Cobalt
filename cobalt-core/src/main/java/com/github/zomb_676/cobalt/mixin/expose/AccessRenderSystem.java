package com.github.zomb_676.cobalt.mixin.expose;

import com.mojang.blaze3d.systems.RenderSystem;
import org.jetbrains.annotations.Contract;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(RenderSystem.class)
public interface AccessRenderSystem {
    @Contract(pure = true)
    @Accessor("gameThread")
    static Thread getGameThread() {
        throw new RuntimeException();
    }

    @Contract(pure = true)
    @Accessor("renderThread")
    static Thread getRenderThread() {
        throw new RuntimeException();
    }

}
