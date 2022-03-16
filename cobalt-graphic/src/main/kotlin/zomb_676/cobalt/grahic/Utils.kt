package zomb_676.cobalt.grahic

import org.lwjgl.system.MemoryStack

object Utils {
    inline fun memoryStack(function:MemoryStack.()->Unit){
        MemoryStack.stackPush().use {
            function.invoke(it)
        }
    }

    @Throws(NotImplementedError::class)
    fun noImplementation():Nothing{
        throw NotImplementedError()
    }
}