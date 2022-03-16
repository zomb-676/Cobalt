package zomb_676.cobalt.grahic.`object`

import org.lwjgl.opengl.GL43
import zomb_676.cobalt.grahic.StateRecorder
import java.lang.ref.WeakReference

class VertexBufferObject(private val bufferType: BufferType = BufferType.GL_ARRAY_BUFFER) {

    companion object{
        val buffers = mutableListOf<WeakReference<VertexBufferObject>>()
        fun getById(id: Int) = buffers.find { it.get()?.id == id }!!.get()!!
    }

    init {
        buffers.add(WeakReference(this))
    }

    var id = 0

    fun generate(){
        id = GL43.glGenBuffers()
    }

    @Throws(RuntimeException::class)
    fun bind() {
        if (id != 0) {
            StateRecorder.tryBindBufferObject(this) {
                GL43.glBindBuffer(bufferType.bufferType, id)
            }
        } else {
            throw RuntimeException("can't bind un-init VertexBufferObject")
        }
    }

    fun unbind(){
        GL43.glBindBuffer(bufferType.bufferType,0)
        StateRecorder.lastVertexBufferObject = 0
    }

}