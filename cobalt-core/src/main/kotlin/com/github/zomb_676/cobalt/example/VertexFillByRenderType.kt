package com.github.zomb_676.cobalt.example

import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.DefaultVertexFormat
import com.mojang.blaze3d.vertex.VertexFormat
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.RenderType
import net.minecraft.core.BlockPos
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.Blocks
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.client.event.RenderLevelLastEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import kotlin.math.floor

@Suppress("unused")
@Mod.EventBusSubscriber(Dist.CLIENT)
object VertexFillByRenderType {

    private class RenderTypeHolder : RenderType("any", DefaultVertexFormat.POSITION_COLOR, VertexFormat.Mode.QUADS, 256, false, false, {}, {}) {
        companion object {
            @Suppress("INACCESSIBLE_TYPE")
            val renderType: RenderType = create(
                "posColorRenderType", DefaultVertexFormat.POSITION_COLOR, VertexFormat.Mode.QUADS, 256, false, false,
                CompositeState.builder()
                    .setShaderState(POSITION_COLOR_SHADER)
                    .setCullState(NO_CULL)
                    .setDepthTestState(NO_DEPTH_TEST)
                    .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                    .createCompositeState(false)
            )
        }
    }

    @SubscribeEvent
    @JvmStatic
    fun renderLevelLastEvent(event: RenderLevelLastEvent) {
        if (Minecraft.getInstance().player!!.mainHandItem.item != Items.ANVIL) {
            return
        }
        val bufferSource = Minecraft.getInstance().renderBuffers().bufferSource()
        val buffer = bufferSource.getBuffer(RenderTypeHolder.renderType)
        dataFill(event,buffer,Blocks.ANVIL)
        RenderSystem.disableDepthTest()
        bufferSource.endBatch(RenderTypeHolder.renderType)
    }
}