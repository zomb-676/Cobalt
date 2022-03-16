package zomb_676.cobalt.grahic.`object`

import org.lwjgl.opengl.GL43
import zomb_676.cobalt.grahic.StateRecorder
import java.lang.ref.WeakReference

class VertexArrayObject {

    companion object{
        private val buffers = mutableListOf<WeakReference<VertexArrayObject>>()
        fun getById(id: Int) = buffers.find { it.get()?.id == id }!!.get()!!
    }

    init {
        buffers.add(WeakReference(this))
    }

    var id = 0
    fun generate(){
        id = GL43.glGenVertexArrays()
    }

    @Throws(RuntimeException::class)
    fun bind(){
        if (id !=0){
            StateRecorder.tryBindVertexArrayObject(this){
                GL43.glBindVertexArray(id)
            }
        }else{
            throw RuntimeException("can't bind un-init VertexArrayObject")
        }
    }

    fun unbind(){
        GL43.glBindVertexArray(0)
        StateRecorder.lastVertexArrayObject = 0
    }
}