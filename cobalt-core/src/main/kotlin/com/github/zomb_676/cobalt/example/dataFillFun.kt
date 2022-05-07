package com.github.zomb_676.cobalt.example

import com.mojang.blaze3d.vertex.VertexConsumer
import net.minecraft.client.Minecraft
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.Block
import net.minecraftforge.client.event.RenderLevelLastEvent
import kotlin.math.floor

fun dataFill(event: RenderLevelLastEvent, buffer: VertexConsumer,block:Block) {
    val stack = event.poseStack
    val cameraPos = Minecraft.getInstance().gameRenderer.mainCamera.position
    stack.translate(-cameraPos.x, -cameraPos.y, -cameraPos.z)
    val playerPos = Minecraft.getInstance().player!!.blockPosition()
    val x = playerPos.x
    val y = playerPos.y
    val z = playerPos.z
    val pos = BlockPos.MutableBlockPos()
    for (dx in (x - 15)..(x + 15)) {
        pos.x = dx
        for (dy in (y - 15)..(y + 15)) {
            pos.y = dy
            for (dz in (z - 15)..(z + 15)) {
                pos.z = dz
                val blockState = Minecraft.getInstance().level!!.getBlockState(pos)
                if (blockState.block == block) {
                    stack.pushPose()
                    stack.translate(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble())
                    val lastPose = stack.last().pose()

                    buffer.vertex(lastPose, 0f, 0f, 0f).color(1f, 0f, 0f, 0.75f).endVertex()
                    buffer.vertex(lastPose, 0f, 1f, 0f).color(0f, 1f, 0f, 0.75f).endVertex()
                    buffer.vertex(lastPose, 1f, 1f, 0f).color(1f, 1f, 1f, 0.75f).endVertex()
                    buffer.vertex(lastPose, 1f, 0f, 0f).color(1f, 1f, 1f, 0.75f).endVertex()

                    //                        buffer.vertex(lastPose.pose(),1f,0f,0f).color(1f,1f,1f,1f).endVertex()
                    //                        buffer.vertex(lastPose.pose(),1f,1f,0f).color(1f,1f,1f,1f).endVertex()
                    //                        buffer.vertex(lastPose.pose(),0f,1f,0f).color(1f,1f,1f,1f).endVertex()
                    //                        buffer.vertex(lastPose.pose(),0f,0f,0f).color(1f,0f,0f,1f).endVertex()
                    stack.popPose()
                }
            }
        }
    }
}