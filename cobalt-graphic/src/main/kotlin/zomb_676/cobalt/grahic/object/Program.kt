package zomb_676.cobalt.grahic.`object`

import org.lwjgl.opengl.GL43
import zomb_676.cobalt.grahic.Log.logger
import zomb_676.cobalt.grahic.StateRecorder
import kotlin.system.measureTimeMillis

class Program(
    private val vertexShaderType: Shader,
    private val fragmentShader: Shader,
    private val programName: String = "no_name"
) {
    var id: Int = -1

    fun isValid(): Boolean = id != -1

    fun generate(): Program {
        if (isValid()) {
            return this
        }
        val linkCostTime = measureTimeMillis {
            id = GL43.glCreateProgram()
            vertexShaderType.generate()
            vertexShaderType.attach(this)
            fragmentShader.generate()
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
    fun bind(): Program {
        if (isValid()) {
            StateRecorder.tryBindProgram(this){
                GL43.glUseProgram(id)
            }
        } else {
            throw RuntimeException("can't bind un-init $this")
        }
        return this
    }

    fun unbind() {
        GL43.glUseProgram(0)
        StateRecorder.lastProgram = 0
    }


    override fun toString(): String = "{program:$programName id:$id with $vertexShaderType and $fragmentShader}"
}