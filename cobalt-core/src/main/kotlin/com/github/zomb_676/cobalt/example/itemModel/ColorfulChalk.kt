package com.github.zomb_676.cobalt.example.itemModel

import com.github.zomb_676.cobalt.AllRegisters
import com.github.zomb_676.cobalt.HexArgumentType
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands
import net.minecraft.nbt.IntTag
import net.minecraft.network.chat.TextComponent
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraftforge.client.event.ColorHandlerEvent
import net.minecraftforge.event.RegisterCommandsEvent

object ColorfulChalk {



    class ColorfulChalk : Item(Properties().tab(AllRegisters.creativeTab)) {
        fun setColor(itemStack: ItemStack, color: Int) {
            val nbt = IntTag.valueOf(color)
            itemStack.addTagElement("color", nbt)
        }

        fun getColor(itemStack: ItemStack): Int {
            val nbt = itemStack.tag?.get("color") as? IntTag
            return nbt?.asInt ?: 0xffffff
        }
    }

    @JvmStatic
    fun registerCommand(event: RegisterCommandsEvent) {
        event.dispatcher.register(
            LiteralArgumentBuilder.literal<CommandSourceStack?>("setColor").then(
            Commands.argument("color", HexArgumentType(0,0xffffff))
                .executes { ctx ->
                    val color = ctx.getArgument("color", Int::class.java)
                    val source = ctx.source
                    val entity = source.entity
                    if (entity is Player) {
                        val itemStack = entity.mainHandItem
                        if (itemStack.item is ColorfulChalk) {
                            (itemStack.item as ColorfulChalk).setColor(itemStack, color)
                            source.sendSuccess(TextComponent("successfully set color"), true)
                        } else {
                            source.sendFailure(TextComponent("main hand isn't holding colorfulChalk"))
                        }
                    }else{
                        source.sendFailure(TextComponent("sender is not a player"))
                    }
                    0
                }
        ))
    }

    @JvmStatic
    fun registerColorHandle(event: ColorHandlerEvent.Item) {
        event.itemColors.register({pStack,_ ->
            (pStack.item as ColorfulChalk).getColor(pStack)
        }, AllRegisters.colorfulChalk.get())
    }
}