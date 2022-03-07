package zomb_676.cobalt.grahic

import org.lwjgl.opengl.GL43
import zomb_676.cobalt.grahic.Log.logger
import java.io.File
import kotlin.system.measureNanoTime

class Shader(
    val shaderType: ShaderType,
    private val supplier: () -> String,
    private val shaderName: String = "no_name"
) {
    var id = -1;
    private var shaderSrc: String? = null

    companion object{
         val shaderPath = File("./cobalt-graphic/src/main/resources/shader")

        fun of(shaderType: ShaderType,file: File)=
            Shader(shaderType,{file.readText()},file.name)

        fun of(shaderType: ShaderType,name:String): Shader {
            val file = File(shaderPath,name)
            return Shader(shaderType,{file.readText()},file.name)
        }
    }

    private fun retrieve(): String {
        shaderSrc = supplier.invoke()
        return shaderSrc!!
    }

    fun isValid(): Boolean = id != -1

    @Throws(RuntimeException::class)
    fun compile() {
        if (isValid()){
            return
        }
        id = GL43.glCreateShader(shaderType.shaderType)
        GL43.glShaderSource(id, shaderSrc ?: retrieve())
        val compileCostTime = measureNanoTime { GL43.glCompileShader(id) }
        val shaderInfoLog = GL43.glGetShaderInfoLog(id)
        if (shaderInfoLog.isNotBlank()) {
            throw RuntimeException("failed to compile ${toString()}\nreason:$shaderInfoLog")
        } else {
            logger.info("Compile ${toString()} , time:${compileCostTime}ms")
        }
    }

    @Throws(RuntimeException::class)
    fun attach(program: Program) {
        if (!program.isValid()) {
            throw RuntimeException("can't attach ${toString()} to un-init $program")
        }
        if (!isValid()){
            throw RuntimeException("can't attach un-compile ${toString()} to $program")
        }
        GL43.glAttachShader(program.id,this.id)
    }

    override fun toString(): String = "{Type:$shaderType,ShaderName:$shaderName,id:$id}"
}