package dev.thoq.sorbetidea.actions

import com.intellij.ide.actions.CreateFileFromTemplateAction
import com.intellij.ide.actions.CreateFileFromTemplateDialog
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory
import dev.thoq.sorbetidea.ui.SorbetIcons

class NewSorbetFileAction : CreateFileFromTemplateAction(
    "Sorbet File",
    "Create a new Sorbet configuration file",
    SorbetIcons.FILE_ICON
), DumbAware {

    override fun buildDialog(project: Project, directory: PsiDirectory, builder: CreateFileFromTemplateDialog.Builder) {
        builder
            .setTitle("New Sorbet File")
            .addKind("SorbetFile", SorbetIcons.FILE_ICON, "SorbetFile")
    }

    override fun getActionName(directory: PsiDirectory, newName: String, templateName: String): String {
        return "Create Sorbet File"
    }
}
