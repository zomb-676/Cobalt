package com.github.zomb_676.cobalt.example.itemModel

import com.github.zomb_676.cobalt.Cobalt
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonObject
import com.mojang.datafixers.util.Pair
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.renderer.block.model.BlockModel
import net.minecraft.client.renderer.block.model.ItemOverrides
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.resources.model.*
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.resources.ResourceManager
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemStack
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.client.model.BakedModelWrapper
import net.minecraftforge.client.model.IModelConfiguration
import net.minecraftforge.client.model.IModelLoader
import net.minecraftforge.client.model.ModelLoaderRegistry
import net.minecraftforge.client.model.geometry.IModelGeometry
import java.util.function.Function

class OverrideModelLoader : IModelLoader<OverrideModelGeometry> {
    companion object {
        @JvmStatic
        fun registerModelLoader(event: ModelRegistryEvent) {
            ModelLoaderRegistry.registerLoader(
                ResourceLocation(Cobalt.MOD_ID, "override_loader"),
                OverrideModelLoader()
            )
        }
    }

    override fun onResourceManagerReload(pResourceManager: ResourceManager) {

    }

    override fun read(
        deserializationContext: JsonDeserializationContext,
        modelContents: JsonObject
    ): OverrideModelGeometry {
        modelContents.remove("loader")
        val model = deserializationContext.deserialize<BlockModel>(modelContents, BlockModel::class.java)
        return OverrideModelGeometry(model)
    }

}

class OverrideModelGeometry(val delegate: BlockModel) : IModelGeometry<OverrideModelGeometry> {
    override fun bake(
        owner: IModelConfiguration?,
        bakery: ModelBakery?,
        spriteGetter: Function<Material, TextureAtlasSprite>?,
        modelTransform: ModelState?,
        overrides: ItemOverrides?,
        modelLocation: ResourceLocation?
    ): BakedModel {

        val delegateModel = delegate.bake(
            bakery, delegate, spriteGetter, modelTransform, modelLocation, delegate.guiLight.lightLikeBlock()
        )
        return OverrideWrappedBakedModel(delegateModel, OverrideItemOverrides())
    }

    override fun getTextures(
        owner: IModelConfiguration?,
        modelGetter: Function<ResourceLocation, UnbakedModel>?,
        missingTextureErrors: MutableSet<Pair<String, String>>?
    ): MutableCollection<Material> {
        return delegate.getMaterials(modelGetter, missingTextureErrors)
    }

}

class OverrideWrappedBakedModel(originalModel: BakedModel, private val overrides: OverrideItemOverrides) :
    BakedModelWrapper<BakedModel>(originalModel) {
    override fun getOverrides(): ItemOverrides = overrides
}

class OverrideItemOverrides : ItemOverrides() {

    override fun resolve(
        pModel: BakedModel,
        pStack: ItemStack,
        pLevel: ClientLevel?,
        pEntity: LivingEntity?,
        pSeed: Int
    ): BakedModel? {
        return super.resolve(pModel, pStack, pLevel, pEntity, pSeed)
    }
}