package com.github.zomb_676.cobalt

import com.github.zomb_676.cobalt.example.itemModel.ColorfulChalk
import com.github.zomb_676.cobalt.example.itemModel.WeatherIndicator
import net.minecraft.client.Minecraft
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.Blocks
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.fml.util.thread.SidedThreadGroups
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries

object AllRegisters {
    val BLOCK = DeferredRegister.create(ForgeRegistries.BLOCKS, Cobalt.MOD_ID)
    val ITEM = DeferredRegister.create(ForgeRegistries.ITEMS, Cobalt.MOD_ID)

    fun register(bus: IEventBus) {
        BLOCK.register(bus)
        ITEM.register(bus)
    }

    val creativeTab = object : CreativeModeTab("tab") {
        override fun makeIcon(): ItemStack = ItemStack(Blocks.IRON_BLOCK)
    }

    val redChalk = ITEM.register("red_chalk") { Item(Item.Properties().tab(creativeTab)) }
    val greenChalk = ITEM.register("green_chalk") { Item(Item.Properties().tab(creativeTab)) }
    val blueChalk = ITEM.register("blue_chalk") { Item(Item.Properties().tab(creativeTab)) }

    val colorfulChalk = ITEM.register("colorful_chalk") { ColorfulChalk.ColorfulChalk() }

    val whetherIndicator = ITEM.register("weather_indicator"){WeatherIndicator()}
}