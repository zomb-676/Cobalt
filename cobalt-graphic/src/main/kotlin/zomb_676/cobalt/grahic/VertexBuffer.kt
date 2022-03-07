package zomb_676.cobalt.grahic

import org.lwjgl.opengl.GL43
import org.lwjgl.system.MemoryUtil

class VertexBuffer(val size:Int =1024) {

    private val buffer = MemoryUtil.memAllocFloat(size)

    val id = GL43.glGenBuffers()

    fun bind(){
        GL43.glBindBuffer(GL43.GL_ARRAY_BUFFER,id)
    }

    fun unbind(){
        GL43.glBindBuffer(GL43.GL_ARRAY_BUFFER,0)
    }

    fun put(data:Float): VertexBuffer {
        buffer.put(data)
        return this
    }

    fun bufferPos(pos:Int = 0){
        buffer.position(pos)
    }

    fun end(){
        MemoryUtil.memFree(buffer)
    }
}