package com.github.zomb_676.cobalt.mixin.example.textureOverlay;

import net.minecraft.client.renderer.entity.TntMinecartRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/**
 * make fired tnt twinkle
 */
@Mixin(TntMinecartRenderer.class)
abstract class MixinTNTMinecartRenderer {
    @ModifyVariable(
            method = "renderWhiteSolidBlock",
            at = @At(
                    value = "LOAD",
                    opcode = Opcodes.ILOAD
            ),
            ordinal = 1
    )
    private static int interpolatedOverlay(int value) {
        return OverlayTexture.pack(OverlayTexture.u(getU()), 10);
    }

    private static float getU() {
        var time = (float) (System.currentTimeMillis() % 1000 / 1000.0);
        if (time>0.5){
            return 1-time;
        }else {
            return time;
        }
    }

}
