package zomb_676.cobalt.grahic.`object`

import org.lwjgl.opengl.GL43

sealed interface TextureFilterParameter {
    val type: Int
    val typeName: String

    enum class TextureFilterType(override val type: Int, override val typeName: String) : TextureFilterParameter {
        NEAREST(GL43.GL_NEAREST, "Nearest Neighbor Filtering"),
        LINER(GL43.GL_LINEAR, "(Bi)linear Filtering");
    }

    /**
     * opengl naming convention
     *
     * first determines how to find the texture on the mipmap ,
     * similar to [TextureFilterType]
     *
     * second determines how to find the mipmap
     *
     * not apply this to GL_TEXTURE_MAG_FILTER
     */
    enum class TextureMipmapFilterType(override val type: Int, override val typeName: String) :
        TextureFilterParameter {
        MIPMAP_NEAREST_SAMPLER_NEAREST(GL43.GL_NEAREST_MIPMAP_NEAREST, "mipmap nearest sampler nearest"),
        MIPMAP_NEAREST_SAMPLER_LINEAR(GL43.GL_LINEAR_MIPMAP_NEAREST, "mipmap nearest sampler linear"),
        MIPMAP_LINEAR_SAMPLER_NEAREST(GL43.GL_NEAREST_MIPMAP_LINEAR, "mipmap linear sampler nearest"),
        MIPMAP_LINEAR_SAMPLER_LINEAR(GL43.GL_LINEAR_MIPMAP_LINEAR, "mipmap linear sampler linear");
    }
}