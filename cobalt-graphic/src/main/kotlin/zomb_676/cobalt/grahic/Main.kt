package zomb_676.cobalt.grahic

import org.lwjgl.glfw.GLFW
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL43
import zomb_676.cobalt.grahic.`object`.Program
import zomb_676.cobalt.grahic.`object`.Shader
import zomb_676.cobalt.grahic.`object`.ShaderType

fun main() {

    Log.configureLogger()

    Init.init()
    Init.initGLFWHint()
    val window = Init.createWindow(900, 900, "rua")
    GLFW.glfwMakeContextCurrent(window)
    GL.createCapabilities()

    Log.enableDebug()

    val vbo: Int = GL43.glGenBuffers().also { GL43.glBindBuffer(GL43.GL_ARRAY_BUFFER, it) }
    GL43.glBindBuffer(GL43.GL_ARRAY_BUFFER,0)

    val vao = GL43.glGenVertexArrays().also { GL43.glBindVertexArray(it) }

//    val vertexBuilder = VertexBuilder(listOf(VertexFormat("pos",2),VertexFormat("color",3)))
//    GL43.glGenVertexArrays().run { GL43.glBindVertexArray(this) }

//    val vbo2: Int =GL43.glGenBuffers().also { GL43.glBindBuffer(GL43.GL_ARRAY_BUFFER, it) }
//
//    val vbo = MemoryStack.stackPush().bind {
//        val point = it.mallocInt(1)
//        GL43.glGenBuffers(point)
//        val id = point.get(0)
//        GL43.glBindBuffer(GL43.GL_ARRAY_BUFFER,id)
//        id
//    }
//    GL43.glVertexAttribPointer(0, 2, GL43.GL_FLOAT, true, TypeSize.float * 5, 0)
//    GL43.glEnableVertexAttribArray(0)
//    GL43.glVertexAttribPointer(1, 3, GL43.GL_FLOAT, true, TypeSize.float * 5, (TypeSize.float * 2).toLong())
//    GL43.glEnableVertexAttribArray(1)

    GL43.glEnableVertexAttribArray(0)
    GL43.glVertexAttribFormat(0,2,GL43.GL_FLOAT,true,0)
    GL43.glVertexAttribBinding(0,0)

    GL43.glEnableVertexAttribArray(1)
    GL43.glVertexAttribFormat(1,3,GL43.GL_FLOAT,true,TypeSize.float * 2)
    GL43.glVertexAttribBinding(1,0)

    GL43.glBindVertexBuffer(0,vao,0,TypeSize.float * 5)

    GL43.glBindBuffer(GL43.GL_ARRAY_BUFFER,vbo)

//    GL43.glBindVertexBuffer(0,vbo, (TypeSize.float * 2).toLong(),TypeSize.float * 5)
//    GL43.glBindVertexBuffer(1,vbo,0,TypeSize.float * 3)

    val data: FloatArray = floatArrayOf(
        0f, 0.5f, 1f, 0f, 0f,
        -0.5f, -0.5f, 0f, 1f, 0f,
        0.5f, -0.5f, 0f, 0f, 1f
    )
//    val data: FloatBuffer = MemoryUtil.memAllocFloat(15).apply {
//        put(0f).put(0.5f).put(1f).put(0f).put(0f)
//        put(-0.5f).put(-0.5f).put(0f).put(1f).put(0f)
//        put(0.5f).put(-0.5f).put(0f).put(0f).put(1f)
//        flip()
//    }
    GL43.glBufferData(GL43.GL_ARRAY_BUFFER, data, GL43.GL_STATIC_DRAW)
    val vsh = Shader.of(ShaderType.vertex, "pos.vsh")
//    val vsh : Int = GL43.glCreateShader(GL43.GL_VERTEX_SHADER).also {
//        GL43.glShaderSource(it, File(Shader.shaderPath,"pos.vsh").readText())
//        GL43.glCompileShader(it)
//
//    }
    val fsh = Shader.of(ShaderType.fragment, "pos.fsh")
    val program = Program(vsh, fsh, "pos").generate()

    while (!GLFW.glfwWindowShouldClose(window)) {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT or GL11.GL_DEPTH_BUFFER_BIT)

        program.bind()
//        vertexBuilder.begin()
//        vertexBuilder.pos(0f,0.5f).color(1f,0f,0f)
//            .pos(-0.5f,-0.5f).color(0f,1f,0f)
//            .pos(0.5f,-0.5f).color(0f,0f,1f)
//        vertexBuilder.upload()
//        vertexBuilder.bind()
        GL43.glDrawArrays(GL43.GL_TRIANGLES, 0, 3)

        GLFW.glfwSwapBuffers(window)
        GLFW.glfwPollEvents()
    }
    GLFW.glfwDestroyWindow(window)
    GLFW.glfwTerminate()
    GLFW.glfwSetErrorCallback(null)?.free()
}