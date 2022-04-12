package zomb_676.cobalt.grahic.`object`

import org.lwjgl.opengl.GL43
import org.lwjgl.opengl.GL43.*
import zomb_676.cobalt.grahic.StateRecorder

abstract class Texture(var width: Int, var height: Int) {
    var id = 0

    abstract fun getTextureBuffer(): IntArray

    @Throws(RuntimeException::class)
    fun generate(
        xWarpType: TextureWrappingType,
        yWarpType: TextureWrappingType = xWarpType,
        minFilterType: TextureFilterParameter,
        magFilterType: TextureFilterParameter.TextureFilterType,
        enableMipmap: Boolean = true
    ) {
        id = glGenTextures()
        glBindTexture(GL_TEXTURE_2D, id)
        StateRecorder.lastTexture = id
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, width, height, 0, GL_RGB, GL_UNSIGNED_BYTE, getTextureBuffer())
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, xWarpType.type)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, yWarpType.type)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, minFilterType.type)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, magFilterType.type)
        glGenerateMipmap(GL_TEXTURE_2D)
        glBindTexture(GL_TEXTURE_2D, 0)
        StateRecorder.lastTexture = 0
    }
}