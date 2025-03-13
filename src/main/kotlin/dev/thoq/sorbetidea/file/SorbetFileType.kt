package dev.thoq.sorbetidea.file

import com.intellij.openapi.fileTypes.LanguageFileType
import dev.thoq.sorbetidea.language.SorbetLanguage
import dev.thoq.sorbetidea.ui.SorbetIcons
import javax.swing.Icon

@Suppress("CompanionObjectInExtension", "unused")
class SorbetFileType private constructor() : LanguageFileType(SorbetLanguage.INSTANCE) {
    override fun getName(): String {
        return "Sorbet File"
    }

    override fun getDescription(): String {
        return "Sorbet file"
    }

    override fun getDefaultExtension(): String {
        return "srb"
    }

    override fun getIcon(): Icon {
        return FILE_ICON
    }

    companion object {
        val INSTANCE = SorbetFileType()

        private val FILE_ICON = SorbetIcons.FILE_ICON
    }
}
