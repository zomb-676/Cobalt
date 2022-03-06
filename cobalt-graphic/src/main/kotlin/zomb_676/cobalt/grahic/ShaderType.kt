package zomb_676.cobalt.grahic

import org.lwjgl.opengl.GL43

@JvmInline
value class ShaderType private constructor(val shaderType: Int) {
    companion object {
        val vertex = ShaderType(GL43.GL_VERTEX_SHADER)
        val tessellationControl = ShaderType(GL43.GL_TESS_CONTROL_SHADER)
        val tessellationEvaluation = ShaderType(GL43.GL_TESS_EVALUATION_SHADER)
        val geometry = ShaderType(GL43.GL_GEOMETRY_SHADER)
        val fragment = ShaderType(GL43.GL_FRAGMENT_SHADER)
        val compute = ShaderType(GL43.GL_COMPUTE_SHADER)
        private val TypeToNameMap = mapOf(
            vertex to "Vertex",
            tessellationControl to "Tessellation Control",
            tessellationEvaluation to "Tessellation Evaluation Control",
            geometry to "Geometry",
            fragment to "Fragment",
            compute to "Compute"
        )
    }

    override fun toString(): String = TypeToNameMap[this]!!
}