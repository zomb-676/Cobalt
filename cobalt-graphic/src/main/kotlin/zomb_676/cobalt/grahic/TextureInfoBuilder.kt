package zomb_676.cobalt.grahic

import org.lwjgl.stb.STBImage
import org.lwjgl.system.MemoryUtil
import zomb_676.cobalt.grahic.`object`.TextureFilterParameter
import zomb_676.cobalt.grahic.`object`.TextureWrappingType
import java.io.*
import java.nio.ByteBuffer
import java.nio.channels.Channels

class TextureInfoBuilder {
    private var xWarpType: TextureWrappingType = TextureWrappingType.CLAMP_TO_BORDER
    private var yWarpType: TextureWrappingType = xWarpType
    private var minFilterType: TextureFilterParameter =
        TextureFilterParameter.TextureMipmapFilterType.MIPMAP_LINEAR_SAMPLER_LINEAR
    private var magFilterType: TextureFilterParameter.TextureFilterType =
        TextureFilterParameter.TextureFilterType.LINER

    private var isFroze: Boolean = false

    fun xWrapType(xWarpType: TextureWrappingType): TextureInfoBuilder {
        if (isFroze){
            return this
        }
        this.xWarpType = xWarpType
        return this
    }

    fun yWrapType(yWarpType: TextureWrappingType): TextureInfoBuilder {
        if (isFroze){
            return this
        }
        this.yWarpType = yWarpType
        return this
    }

    fun minFilterType(minFilterType: TextureFilterParameter): TextureInfoBuilder {
        if (isFroze){
            return this
        }
        this.minFilterType = minFilterType
        return this
    }

    fun magFilterType(magFilterType: TextureFilterParameter.TextureFilterType): TextureInfoBuilder {
        if (isFroze){
            return this
        }
        this.magFilterType = magFilterType
        return this
    }

    fun froze(): TextureInfoBuilder {
        if (isFroze){
            return this
        }
        this.isFroze = true
        return this
    }

    fun fromFile(file:File){
        val data = readResource(file.inputStream().buffered())
        val width : Int
        val height:Int
        val channel : Int
        Utils.memoryStack {
            val x = this.mallocInt(1)
            val y = this.mallocInt(1)
            val channel = this.mallocInt(1)
            STBImage.stbi_info_from_memory(data,x,y,channel)
        }
    }

    companion object{
        @Throws(IOException::class)
        fun readResource(pInputStream: InputStream?): ByteBuffer? {
            var bytebuffer: ByteBuffer
            if (pInputStream is FileInputStream) {
                val filechannel = pInputStream.channel
                bytebuffer = MemoryUtil.memAlloc(filechannel.size().toInt() + 1)
                while (filechannel.read(bytebuffer) != -1) {
                }
            } else {
                bytebuffer = MemoryUtil.memAlloc(8192)
                val readablebytechannel = Channels.newChannel(pInputStream)
                while (readablebytechannel.read(bytebuffer) != -1) {
                    if (bytebuffer.remaining() == 0) {
                        bytebuffer = MemoryUtil.memRealloc(bytebuffer, bytebuffer.capacity() * 2)
                    }
                }
            }
            return bytebuffer
        }
    }

}