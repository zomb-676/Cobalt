package com.github.zomb_676.cobalt.example.itemModel

import com.github.zomb_676.cobalt.AllRegisters
import com.github.zomb_676.cobalt.Cobalt
import net.minecraft.client.Minecraft
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.renderer.item.ItemProperties
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.util.thread.SidedThreadGroups

class WeatherIndicator : Item(Properties().tab(AllRegisters.creativeTab)) {
    companion object{
        @JvmStatic
        fun setItemOverride(event: FMLClientSetupEvent) {
            ItemProperties.register(AllRegisters.whetherIndicator.get(), ResourceLocation(Cobalt.MOD_ID, "weather"))
            { itemStack: ItemStack, _: ClientLevel?, livingEntity: LivingEntity?, seed: Int ->
                val clientLevel = Minecraft.getInstance().level
                if (clientLevel == null) {
                    0f
                } else {
                    if (clientLevel.isRaining) {
                        1f
                    } else if (clientLevel.dayTime < 11000) {
                        2f
                    } else {
                        3f
                    }
                }
            }
        }
    }

    override fun isFoil(pStack: ItemStack): Boolean {
        return if (Thread.currentThread().threadGroup == SidedThreadGroups.SERVER || Minecraft.getInstance().level?.isThundering == true) {
            true
        } else {
            super.isFoil(pStack)
        }
    }

}