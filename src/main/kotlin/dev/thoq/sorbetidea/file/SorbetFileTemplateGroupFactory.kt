package dev.thoq.sorbetidea.file

import com.intellij.ide.fileTemplates.FileTemplateDescriptor
import com.intellij.ide.fileTemplates.FileTemplateGroupDescriptor
import com.intellij.ide.fileTemplates.FileTemplateGroupDescriptorFactory
import dev.thoq.sorbetidea.ui.SorbetIcons

class SorbetFileTemplateGroupFactory : FileTemplateGroupDescriptorFactory {
    override fun getFileTemplatesDescriptor(): FileTemplateGroupDescriptor {
        val group = FileTemplateGroupDescriptor("Sorbet", SorbetIcons.FILE_ICON)
        group.addTemplate(FileTemplateDescriptor("SorbetFile.srb", SorbetIcons.FILE_ICON))
        return group
    }
}