package com.oneframe.plugin.swagger.file;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.psi.PsiClass;
import com.oneframe.plugin.swagger.Action;
import org.jetbrains.annotations.NotNull;

public class FileAction extends Action {

  @Override
  public void actionPerformed(@NotNull AnActionEvent e) {
    PsiClass psi = getPsiClass(e);

    if (psi != null) {
      FileDialog dialog = new FileDialog(psi);
      dialog.show();
    }
  }

  @Override
  public void update(@NotNull AnActionEvent e) {
    super.update(e);

    PsiClass psi = getPsiClass(e);
    e.getPresentation().setEnabled(psi != null);
  }
}
