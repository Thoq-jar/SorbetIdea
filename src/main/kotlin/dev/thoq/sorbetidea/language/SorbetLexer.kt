package dev.thoq.sorbetidea.language

import com.intellij.lexer.LexerBase
import com.intellij.psi.tree.IElementType
import com.intellij.psi.TokenType

class SorbetLexer : LexerBase() {
    private var buffer: CharSequence = ""
    private var bufferEnd: Int = 0
    private var bufferStart: Int = 0
    private var currentPosition: Int = 0
    private var tokenStart: Int = 0
    private var tokenType: IElementType? = null

    private var inContinuation = false
    private var afterSeparator = false

    override fun start(buffer: CharSequence, startOffset: Int, endOffset: Int, initialState: Int) {
        this.buffer = buffer
        this.bufferEnd = endOffset
        this.bufferStart = startOffset
        this.currentPosition = startOffset
        this.tokenStart = startOffset

        this.inContinuation = initialState and 1 != 0
        this.afterSeparator = initialState and 2 != 0

        advance()
    }

    override fun getState(): Int {
        var state = 0
        if (inContinuation) state = state or 1
        if (afterSeparator) state = state or 2
        return state
    }

    override fun getTokenType(): IElementType? = tokenType

    override fun getTokenStart(): Int = tokenStart

    override fun getTokenEnd(): Int = currentPosition

    override fun getBufferSequence(): CharSequence = buffer

    override fun getBufferEnd(): Int = bufferEnd

    override fun advance() {
        tokenStart = currentPosition

        if (currentPosition >= bufferEnd) {
            tokenType = null
            return
        }

        if (isWhitespace(buffer[currentPosition])) {
            val isLineStart = isStartOfLine()
            while (currentPosition < bufferEnd && isWhitespace(buffer[currentPosition])) {
                currentPosition++
            }

            if (isLineStart && currentPosition < bufferEnd && buffer[currentPosition] == '>') {
                return
            } else {
                tokenType = TokenType.WHITE_SPACE
                return
            }
        }

        if (buffer[currentPosition] == '\n' || buffer[currentPosition] == '\r') {
            currentPosition++
            if (currentPosition < bufferEnd &&
                buffer[currentPosition - 1] == '\r' &&
                buffer[currentPosition] == '\n') {
                currentPosition++
            }
            afterSeparator = false
            tokenType = SorbetTokenTypes.LINE_BREAK
            return
        }

        if (isStartOfLine() && buffer[currentPosition] == '>') {
            currentPosition++
            inContinuation = true
            afterSeparator = true
            tokenType = SorbetTokenTypes.CONTINUATION
            return
        }

        if (currentPosition + 1 < bufferEnd &&
            buffer[currentPosition] == '=' &&
            buffer[currentPosition + 1] == '>') {
            currentPosition += 2
            inContinuation = false
            afterSeparator = true
            tokenType = SorbetTokenTypes.SEPARATOR
            return
        }

        if (afterSeparator) {
            while (currentPosition < bufferEnd &&
                buffer[currentPosition] != '\n' &&
                buffer[currentPosition] != '\r') {
                currentPosition++
            }
            tokenType = SorbetTokenTypes.VALUE
            return
        }

        val separatorPos = findSeparator()
        if (separatorPos > 0) {
            while (currentPosition < separatorPos) {
                currentPosition++
            }
            tokenType = SorbetTokenTypes.KEY
            return
        }

        currentPosition++
        tokenType = TokenType.BAD_CHARACTER
    }

    private fun isWhitespace(c: Char): Boolean = c == ' ' || c == '\t'

    private fun findSeparator(): Int {
        var i = currentPosition
        while (i < bufferEnd - 1) {
            if (buffer[i] == '=' && buffer[i + 1] == '>') {
                return i
            }
            if (buffer[i] == '\n' || buffer[i] == '\r') {
                break
            }
            i++
        }
        return -1
    }

    private fun isStartOfLine(): Boolean {
        var i = tokenStart - 1
        while (i >= bufferStart) {
            if (buffer[i] == '\n' || buffer[i] == '\r') {
                return true
            }
            if (!isWhitespace(buffer[i])) {
                return false
            }
            i--
        }
        return true
    }
}