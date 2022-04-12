package zomb_676.cobalt.grahic.`object`

import org.lwjgl.opengl.GL43

/**
 * if you use [TextureWrappingType.CLAMP_TO_BORDER]
 *
 * you need to specific border color manually
 *
 * like : GL43.glTexParameterfv(GL43.GL_TEXTURE_2D,GL43.GL_TEXTURE_BORDER_COLOR,clolor data)
 */
enum class TextureWrappingType(val type: Int, val typeName: String) {
    REPEAT(GL43.GL_REPEAT, "repeat"),
    MIRRORED_REPEAT(GL43.GL_MIRRORED_REPEAT, "mirrored repeat"),
    CLAM_TO_EDGE(GL43.GL_CLAMP_TO_EDGE, "clamp to edge"),
    CLAMP_TO_BORDER(GL43.GL_CLAMP_TO_BORDER, "clamp to border")
}