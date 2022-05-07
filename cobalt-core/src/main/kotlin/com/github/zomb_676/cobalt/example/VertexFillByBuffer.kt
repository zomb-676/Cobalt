package com.github.zomb_676.cobalt.example

import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.BufferBuilder
import com.mojang.blaze3d.vertex.BufferUploader
import com.mojang.blaze3d.vertex.DefaultVertexFormat
import com.mojang.blaze3d.vertex.VertexFormat
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.GameRenderer
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.Blocks
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.client.event.RenderLevelLastEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber

@Suppress("unused")
@EventBusSubscriber(Dist.CLIENT)
object VertexFillByBuffer {

    private val buffer = BufferBuilder(256)

    @SubscribeEvent
    @JvmStatic
    fun renderLevelLastEvent(event: RenderLevelLastEvent) {
        if (Minecraft.getInstance().player!!.mainHandItem.item != Items.DIAMOND_BLOCK) {
            return
        }
        buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR)
        RenderSystem.setShader(GameRenderer::getPositionColorShader)
        RenderSystem.disableDepthTest()
        RenderSystem.enableBlend()
        RenderSystem.defaultBlendFunc()
        dataFill(event, buffer, Blocks.DIAMOND_BLOCK)
        buffer.end()
        BufferUploader.end(buffer)
        RenderSystem.enableDepthTest()
        RenderSystem.disableBlend()
    }

}

