package zomb_676.cobalt.grahic

import org.lwjgl.opengl.GL43
import zomb_676.cobalt.grahic.Log.logger
import kotlin.system.measureTimeMillis

class Program(val vertexShaderType: Shader, val fragmentShader: Shader, val programName: String = "no_name") {
    var id: Int = -1

    fun isValid(): Boolean = id != -1

    fun link(): Program {
        if (isValid()) {
            return this
        }
        val linkCostTime = measureTimeMillis {
            id = GL43.glCreateProgram()
            vertexShaderType.compile()
            vertexShaderType.attach(this)
            fragmentShader.compile()
            fragmentShader.attach(this)
        }
        GL43.glLinkProgram(id)
        val programLinkInfo = GL43.glGetProgramInfoLog(id)
        if (programLinkInfo.isNotBlank()) {
            logger.info("failed to link $programLinkInfo\nreason:$programLinkInfo")
        } else {
            logger.info("cost ${linkCostTime}ms link $this")
        }
        return this
    }

    @Throws(RuntimeException::class)
    fun use() {
        if (isValid()) {
            GL43.glUseProgram(id)
        } else {
            throw RuntimeException("can't use un-init $this")
        }
    }

    override fun toString(): String = "{program:$programName id:$id with $vertexShaderType and $fragmentShader}"
}