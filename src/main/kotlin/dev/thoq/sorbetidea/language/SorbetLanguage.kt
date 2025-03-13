package dev.thoq.sorbetidea.language

import com.intellij.lang.Language

object SorbetLanguage : Language("Sorbet") {
    private fun readResolve(): Any = SorbetLanguage
    val INSTANCE: SorbetLanguage = SorbetLanguage
}
