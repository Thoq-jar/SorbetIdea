@file:Suppress("unused")

package dev.thoq.sorbetidea.language

import com.intellij.psi.tree.IElementType
import com.intellij.psi.TokenType

object SorbetTokenTypes {
    val KEY = SorbetElementType("KEY")
    val SEPARATOR = SorbetElementType("SEPARATOR")
    val VALUE = SorbetElementType("VALUE")
    val CONTINUATION = SorbetElementType("CONTINUATION")
    val COMMENT = SorbetElementType("COMMENT")
    val LINE_BREAK = SorbetElementType("LINE_BREAK")
    val WHITE_SPACE: IElementType = TokenType.WHITE_SPACE

    class SorbetElementType(debugName: String) : IElementType(debugName, SorbetLanguage.INSTANCE)
}