package zomb_676.cobalt.grahic

import org.lwjgl.glfw.GLFW
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL43
import org.lwjgl.opengl.GL46
import org.lwjgl.system.MemoryUtil
import kotlin.concurrent.thread

fun main() {
    init()
    GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR,4)
    GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR,6)
    GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE,GLFW.GLFW_OPENGL_FORWARD_COMPAT)
    GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT,GLFW.GLFW_FALSE)

    val window = createWindow(900, 900, "rua")
    GLFW.glfwMakeContextCurrent(window)
    GLFW.glfwMakeContextCurrent(window)
    GL.createCapabilities()

    while (!GLFW.glfwWindowShouldClose(window)) {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT)

        GL11.glBegin(GL11.GL_TRIANGLES)
        GL11.glColor3f(1f, 0f, 0f)
        GL11.glVertex2f(0f, 0.5f)
        GL11.glColor3f(0f, 1f, 0f)
        GL11.glVertex2f(-0.5f, -0.5f)
        GL11.glColor3f(0f, 0f, 1f)
        GL11.glVertex2f(0.5f, -0.5f)
        GL11.glEnd()

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
