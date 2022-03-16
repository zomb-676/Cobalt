package zomb_676.cobalt.grahic.`object`

import org.lwjgl.opengl.GL43
import org.lwjgl.opengl.GL44


@JvmInline
value class BufferType private constructor(val bufferType: Int) {
    @Suppress("MemberVisibilityCanBePrivate")
    companion object {
        val GL_ARRAY_BUFFER = BufferType(GL43.GL_ARRAY_BUFFER)
        val GL_ATOMIC_COUNTER_BUFFER = BufferType(GL43.GL_ATOMIC_COUNTER_BUFFER)
        val GL_COPY_READ_BUFFER = BufferType(GL43.GL_COPY_READ_BUFFER)
        val GL_COPY_WRITE_BUFFER = BufferType(GL43.GL_COPY_WRITE_BUFFER)
        val GL_DISPATCH_INDIRECT_BUFFER = BufferType(GL43.GL_DISPATCH_INDIRECT_BUFFER)
        val GL_DRAW_INDIRECT_BUFFER = BufferType(GL43.GL_DRAW_INDIRECT_BUFFER)
        val GL_ELEMENT_ARRAY_BUFFER = BufferType(GL43.GL_ELEMENT_ARRAY_BUFFER)
        val GL_PIXEL_PACK_BUFFER = BufferType(GL43.GL_PIXEL_PACK_BUFFER)
        val GL_PIXEL_UNPACK_BUFFER = BufferType(GL43.GL_PIXEL_UNPACK_BUFFER)
        val GL_QUERY_BUFFER = BufferType(GL44.GL_QUERY_BUFFER)
        val GL_SHADER_STORAGE_BUFFER = BufferType(GL43.GL_SHADER_STORAGE_BUFFER)
        val GL_TEXTURE_BUFFER = BufferType(GL43.GL_TEXTURE_BUFFER)

        private val mapToDescription = mapOf(
            GL_ARRAY_BUFFER to "Vertex attributes",
            GL_ATOMIC_COUNTER_BUFFER to "Atomic counter storage",
            GL_COPY_READ_BUFFER to "Buffer copy source",
            GL_COPY_WRITE_BUFFER to "Buffer copy destination",
            GL_DISPATCH_INDIRECT_BUFFER to "Indirect compute dispatch commands",
            GL_DRAW_INDIRECT_BUFFER to "Indirect command arguments",
            GL_ELEMENT_ARRAY_BUFFER to "Vertex array indices",
            GL_PIXEL_PACK_BUFFER to "Pixel read target",
            GL_PIXEL_UNPACK_BUFFER to "Texture data source",
            GL_QUERY_BUFFER to "Query result buffer",
            GL_SHADER_STORAGE_BUFFER to "Read-write storage for shaders",
            GL_TEXTURE_BUFFER to "Texture data buffer"
        )
        private val mapToName = mapOf(
            GL_ARRAY_BUFFER to "GL_ARRAY_BUFFER",
            GL_ATOMIC_COUNTER_BUFFER to "GL_ATOMIC_COUNTER_BUFFER",
            GL_COPY_READ_BUFFER to "GL_COPY_READ_BUFFER",
            GL_COPY_WRITE_BUFFER to "GL_COPY_WRITE_BUFFER",
            GL_DISPATCH_INDIRECT_BUFFER to "GL_DISPATCH_INDIRECT_BUFFER",
            GL_DRAW_INDIRECT_BUFFER to "GL_DRAW_INDIRECT_BUFFER",
            GL_ELEMENT_ARRAY_BUFFER to "GL_ELEMENT_ARRAY_BUFFER",
            GL_PIXEL_PACK_BUFFER to "GL_PIXEL_PACK_BUFFER",
            GL_PIXEL_UNPACK_BUFFER to "GL_PIXEL_UNPACK_BUFFER",
            GL_QUERY_BUFFER to "GL_QUERY_BUFFER",
            GL_SHADER_STORAGE_BUFFER to "GL_SHADER_STORAGE_BUFFER",
            GL_TEXTURE_BUFFER to "GL_TEXTURE_BUFFER"
        )
        fun BufferType.getDescription() = mapToDescription[this]
        fun BufferType.getName() = mapToName[this]
    }
}
