package zomb_676.cobalt.grahic

import com.sun.jdi.FloatType
import org.lwjgl.opengl.GL43
import org.lwjgl.system.MemoryUtil
import java.nio.Buffer

class VertexBuilder(private val  vertexFormats: List<VertexFormat>) {

    private val vaoId = GL43.glGenVertexArrays()
    private val vbo = VertexBuffer()

    init {
        attribute()
    }

    private fun attribute(){
        vbo.bind()
        GL43.glBindVertexArray(vaoId)
        val stride = vertexFormats.sumOf { it.num  } * Float.SIZE_BYTES
        var point = 0L
        vertexFormats.mapIndexed{index, vertexFormat ->
            GL43.glVertexAttribPointer(index,Float.SIZE_BYTES,GL43.GL_FLOAT,false,stride,point)
            point+=vertexFormat.num * Float.SIZE_BYTES
            GL43.glEnableVertexAttribArray(index)
        }
        vbo.unbind()
        GL43.glBindVertexArray(0)
    }

    fun bind(){
        GL43.glBindVertexArray(vaoId)
    }

    var buffer : VertexBuffer ? = null

    fun begin(vertexBuffer: VertexBuffer){
       vertexBuffer.bufferPos()
    }

    fun pos(x:Float,y:Float): VertexBuilder {
        buffer!!.put(x).put(y)
        return this
    }

    fun pos(x:Float,y:Float,z:Float): VertexBuilder{
        buffer!!.put(x).put(y).put(z)
        return this
    }

    fun color(r:Float,g:Float,b:Float): VertexBuilder {
        buffer!!.put(r).put(g).put(b)
        return this
    }

    fun color(r:Float,g:Float,b:Float,alpha:Float): VertexBuilder {
        buffer!!.put(r).put(g).put(b).put(alpha)
        return this
    }

    fun u(u:Float): VertexBuilder {
        buffer!!.put(u)
        return this
    }
    fun v(v:Float): VertexBuilder {
        buffer!!.put(v)
        return this
    }

    fun uv(u:Float,v:Float): VertexBuilder {
        buffer!!.put(u).put(v)
        return this
    }

}