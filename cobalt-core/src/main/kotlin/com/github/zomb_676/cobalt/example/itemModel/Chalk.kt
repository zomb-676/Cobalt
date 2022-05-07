package com.github.zomb_676.cobalt.example.itemModel

import com.github.zomb_676.cobalt.AllRegisters
import net.minecraft.world.level.material.MaterialColor
import net.minecraftforge.client.event.ColorHandlerEvent

object Chalk {
    @JvmStatic
    fun registerColorHandle(event: ColorHandlerEvent.Item) {
        event.itemColors.register({ pStack, pTintIndex ->
            when (pStack.item) {
                AllRegisters.redChalk.get() -> MaterialColor.COLOR_RED
                AllRegisters.greenChalk.get() -> MaterialColor.COLOR_GREEN
                AllRegisters.blueChalk.get() -> MaterialColor.COLOR_BLUE
                else -> MaterialColor.COLOR_BLACK
            }.col
        }, AllRegisters.redChalk.get(), AllRegisters.greenChalk.get(), AllRegisters.blueChalk.get())
    }
}