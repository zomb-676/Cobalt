package com.github.zomb_676.cobalt

import com.github.zomb_676.cobalt.example.itemModel.Chalk
import com.github.zomb_676.cobalt.example.itemModel.ColorfulChalk
import com.github.zomb_676.cobalt.example.itemModel.WeatherIndicator
import net.minecraft.client.Minecraft
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import net.minecraftforge.fml.javafmlmod.FMLModContainer
import net.minecraftforge.registries.ForgeRegistries

@Mod(value = Cobalt.MOD_ID)
class Cobalt {

    init {
        val modEventBus = FMLJavaModLoadingContext.get().modEventBus
        val forgeBuf = MinecraftForge.EVENT_BUS
        AllRegisters.register(modEventBus)
        modEventBus.addListener(Chalk::registerColorHandle)
        modEventBus.addListener(ColorfulChalk::registerColorHandle)
        modEventBus.addListener(WeatherIndicator::setItemOverride)
        forgeBuf.addListener(ColorfulChalk::registerCommand)
    }

    companion object {
        const val MOD_ID = "cobalt"
        const val MOD_NAME = "Cobalt"
    }
}