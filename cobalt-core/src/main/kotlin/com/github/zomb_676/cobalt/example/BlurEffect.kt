package com.github.zomb_676.cobalt.example

import com.github.zomb_676.cobalt.Cobalt
import com.mojang.blaze3d.vertex.DefaultVertexFormat
import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.ShaderInstance
import net.minecraft.resources.ResourceLocation
import net.minecraftforge.client.event.RegisterShadersEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo


@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
object BlurEffect {

    private val location = ResourceLocation(Cobalt.MOD_ID, "blur")

    @SubscribeEvent
    @JvmStatic
    fun registerShader(event: RegisterShadersEvent) {
//        val shader = ShaderInstance(
//            Minecraft.getInstance().resourceManager,
//            location,
//            DefaultVertexFormat.POSITION
//        )
//        event.registerShader(shader) {}
    }

//    private class RenderTypeHolder : RenderType("any", DefaultVertexFormat.POSITION_COLOR, VertexFormat.Mode.QUADS, 256, false, false, {}, {}) {
//        companion object {
//            @Suppress("INACCESSIBLE_TYPE")
//            val renderType: RenderType = create
//        }
//    }

    fun blur(partialTicks: Float, finshTimeNano: Long, poseStack: PoseStack, callbackInfo: CallbackInfo) {

    }

}