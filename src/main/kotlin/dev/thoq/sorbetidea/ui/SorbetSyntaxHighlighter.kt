package dev.thoq.sorbetidea.ui

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import dev.thoq.sorbetidea.language.SorbetLexer
import dev.thoq.sorbetidea.language.SorbetTokenTypes

@Suppress("unused")
class SorbetSyntaxHighlighter : SyntaxHighlighterBase() {
    companion object {
        val KEY = TextAttributesKey.createTextAttributesKey("SORBET_KEY", DefaultLanguageHighlighterColors.KEYWORD)
        val SEPARATOR = TextAttributesKey.createTextAttributesKey("SORBET_SEPARATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN)
        val CONTINUATION = TextAttributesKey.createTextAttributesKey("SORBET_CONTINUATION", DefaultLanguageHighlighterColors.OPERATION_SIGN)
        val VALUE = TextAttributesKey.createTextAttributesKey("SORBET_VALUE", DefaultLanguageHighlighterColors.STRING)
        val COMMENT = TextAttributesKey.createTextAttributesKey("SORBET_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
        val BAD_CHARACTER = TextAttributesKey.createTextAttributesKey("SORBET_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)
    }

    override fun getHighlightingLexer(): Lexer = SorbetLexer()

    override fun getTokenHighlights(tokenType: IElementType?): Array<TextAttributesKey> {
        return when (tokenType) {
            SorbetTokenTypes.KEY -> arrayOf(KEY)
            SorbetTokenTypes.SEPARATOR -> arrayOf(SEPARATOR)
            SorbetTokenTypes.CONTINUATION -> arrayOf(CONTINUATION)
            SorbetTokenTypes.VALUE -> arrayOf(VALUE)
            SorbetTokenTypes.COMMENT -> arrayOf(COMMENT)
            else -> emptyArray()
        }
    }
}
