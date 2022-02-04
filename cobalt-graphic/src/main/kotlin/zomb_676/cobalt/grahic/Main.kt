package zomb_676.cobalt.grahic

import org.lwjgl.glfw.GLFW
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11
import org.lwjgl.system.MemoryUtil
import kotlin.concurrent.thread

fun main() {
    init()
    val window = createWindow(900, 900, "rua")
    GLFW.glfwMakeContextCurrent(window)
    GL.createCapabilities()
    val id = GLFW.glfwGetCurrentContext()
    GLFW.glfwMakeContextCurrent(id)

    while (!GLFW.glfwWindowShouldClose(window)) {

        GL11.glBegin(GL11.GL_TRIANGLES)
        GL11.glColor3f(1f, 0f, 0f)
        GL11.glVertex2f(0f, 0.5f)
        GL11.glColor3f(0f, 1f, 0f)
        GL11.glVertex2f(-0.5f, -0.5f)
        GL11.glColor3f(0f, 0f, 1f)
        GL11.glVertex2f(0.5f, -0.5f)
        GL11.glEnd()
        thread {  }.start()

        GLFW.glfwSwapBuffers(window)
        GLFW.glfwPollEvents()
    }
    GLFW.glfwDestroyWindow(window)
    GLFW.glfwTerminate()
    GLFW.glfwSetErrorCallback(null)?.free()
}

@Throws(RuntimeException::class)
fun init() {
    if (!GLFW.glfwInit()) {
        throw RuntimeException("failed to init glfw")
    }
}

fun createWindow(width: Int, height: Int, title: String): Long =
    GLFW.glfwCreateWindow(width, height, title, MemoryUtil.NULL, MemoryUtil.NULL)
