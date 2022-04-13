package com.github.zomb_676.cobalt.window

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ResourceMonitor() {

    companion object {

        val logger: Logger = LoggerFactory.getLogger("ResourceMonitor")

        val mc = ResourceMonitor()
        val mod = ResourceMonitor()
        val unknown = ResourceMonitor()

        @Throws(RuntimeException::class)
        fun switchMonitor() = unknown
//            when (Thread.currentThread()) {
//                MixinRenderSystem.renderThread, MixinRenderSystem.gameThread -> mc
//                ModEventHandle.thread -> mod
//                else -> throw RuntimeException("error thread called,name:${Thread.currentThread().name}")
//            }

        fun autoSwitch(id:Int)= switchMonitor().auto(id)
    }

    fun auto(id: Int):ResourceWrapper =
        if (ResourceWrapper.textureCheck(id)){
            textures
        }else if (ResourceWrapper.vertexArrayCheck(id)){
            vertexArray
        }else if (ResourceWrapper.programCheck(id)){
            program
        }else if (ResourceWrapper.frameBufferCheck(id)){
            frameBuffer
        }else{
            throw UnsupportedOperationException()
        }

    val textures = ResourceWrapper("texture",ResourceWrapper.textureCheck)
    val buffer = ResourceWrapper("dataBuffer",ResourceWrapper.bufferCheck)
    val indexBuffer = ResourceWrapper("indexBuffer",ResourceWrapper.bufferCheck)
    val vertexArray = ResourceWrapper("vertexArray",ResourceWrapper.vertexArrayCheck)
    val program = ResourceWrapper("program",ResourceWrapper.programCheck)
    val frameBuffer = ResourceWrapper("frameBuffer",ResourceWrapper.frameBufferCheck)
}