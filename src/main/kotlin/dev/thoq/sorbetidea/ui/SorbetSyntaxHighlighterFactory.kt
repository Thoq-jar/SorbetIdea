package dev.thoq.sorbetidea.ui

import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

class SorbetSyntaxHighlighterFactory : SyntaxHighlighterFactory() {
    override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?): SyntaxHighlighter {
        if (virtualFile != null) {
            val extension = virtualFile.extension?.lowercase()
            if (extension == "srb" || extension == "sorbet") {
                return SorbetSyntaxHighlighter()
            }
        }

        return SorbetSyntaxHighlighter()
    }
}
