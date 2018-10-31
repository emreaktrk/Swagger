package com.oneframe.plugin.swagger.file;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.psi.PsiClass;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FileDialog extends DialogWrapper {

    FileDialog(PsiClass psi) {
        super(psi.getProject());

        init();
        setTitle("Select Swagger File");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select Swagger File");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON files", ".json", ".txt");
        chooser.addChoosableFileFilter(filter);

        int result = chooser.showOpenDialog(getRootPane());
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
        }

        return getRootPane();
    }
}
