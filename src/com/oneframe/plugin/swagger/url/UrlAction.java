package com.oneframe.plugin.swagger.url;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.psi.PsiClass;
import com.oneframe.plugin.swagger.Action;
import org.jetbrains.annotations.NotNull;

public class UrlAction extends Action {

  @Override
  public void actionPerformed(@NotNull AnActionEvent e) {
    PsiClass psi = getPsiClass(e);

    if (psi != null) {
      UrlDialog dialog = new UrlDialog(psi);
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
