package dev.thoq.sorbetidea.ui

import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

class SorbetSyntaxHighlighterFactory : SyntaxHighlighterFactory() {
    override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?): SyntaxHighlighter {
        // Only apply the highlighter to Sorbet files to prevent stacktraces in non-Sorbet files
        if (virtualFile != null) {
            val extension = virtualFile.extension?.lowercase()
            if (extension == "srb" || extension == "sorbet") {
                return SorbetSyntaxHighlighter()
            }
        }
        // Default to SorbetSyntaxHighlighter for null files or when called from the settings dialog
        return SorbetSyntaxHighlighter()
    }
}
