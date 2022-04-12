package zomb_676.cobalt.grahic

import org.lwjgl.opengl.GL43

class VertexFormat(val formatName:String, vararg vertexAttribute: VertexAttribute) {

    companion object{
        val pos2Color3 = VertexFormat("pos2dColor3", VertexAttribute.pos2d, VertexAttribute.color3)
    }

    fun setupByAttributePointer(){

    }

}