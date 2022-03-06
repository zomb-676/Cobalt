package zomb_676.cobalt.grahic

import org.lwjgl.BufferUtils
import org.lwjgl.system.MemoryStack
import org.lwjgl.system.MemoryUtil

object Utils {
    inline fun memoryStack(function:MemoryStack.()->Unit){
        MemoryStack.stackPush().use {
            function.invoke(it)

        }
    }
}