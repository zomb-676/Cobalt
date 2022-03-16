package zomb_676.cobalt.grahic

import org.lwjgl.glfw.GLFW
import org.lwjgl.system.MemoryUtil

object Init {

    @Throws(RuntimeException::class)
    fun init() {
        if (!GLFW.glfwInit()) {
            throw RuntimeException("failed to init glfw")
        }
    }


    fun initGLFWHint(){
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR,4)
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR,6)
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_DEBUG_CONTEXT,GLFW.GLFW_TRUE)
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE)
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GLFW.GLFW_FALSE)
    }

    fun createWindow(width: Int, height: Int, title: String): Long =
        GLFW.glfwCreateWindow(width, height, title, MemoryUtil.NULL, MemoryUtil.NULL)

}