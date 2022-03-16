package zomb_676.cobalt.grahic

class VertexAttribute(
    private val attributeName: String,
    private val type: TypeSize<*>,
    private val vertexNum: Int,
    private val normalized : Boolean = false
) {
    val vertexSize = type * vertexNum

    companion object{
        val pos2d = VertexAttribute("pos2", TypeSize.float,2)
        val pos3d = VertexAttribute("pos3", TypeSize.float,3)
        val uv0 = VertexAttribute("uv0", TypeSize.float,2,true)
        val uv1 = VertexAttribute("uv1", TypeSize.float,2,true)
        val color3 = VertexAttribute("color3", TypeSize.float,3,true)
        val color4 = VertexAttribute("color4", TypeSize.float,4,true)
    }
}