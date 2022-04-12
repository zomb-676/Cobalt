package zomb_676.cobalt.grahic

import zomb_676.cobalt.grahic.`object`.Program
import zomb_676.cobalt.grahic.`object`.Texture
import zomb_676.cobalt.grahic.`object`.VertexArrayObject
import zomb_676.cobalt.grahic.`object`.VertexBufferObject

object StateRecorder {
    val texturesSlots = mutableListOf<Texture?>()
    var lastVertexArrayObject : Int = 0
    var lastVertexBufferObject : Int = 0
    var lastProgram : Int = 0
    var lastTexture : Int = 0

     inline fun tryBindProgram(program: Program,block:(Program).() -> Unit){
        if (program.id != lastProgram){
            lastProgram = program.id
            block.invoke(program)
        }
    }

    fun tryBindBufferObject(vertexArrayBuffer: VertexBufferObject, block:(VertexBufferObject).()->Unit){
        if (vertexArrayBuffer.id != lastVertexBufferObject){
            lastVertexBufferObject = vertexArrayBuffer.id
            block.invoke(vertexArrayBuffer)
        }
    }

    fun tryBindVertexArrayObject(vertexArrayBuffer: VertexArrayObject, block:(VertexArrayObject).()->Unit){
        if (vertexArrayBuffer.id != lastVertexArrayObject){
            lastVertexArrayObject = vertexArrayBuffer.id
            block.invoke(vertexArrayBuffer)
        }
    }



}