package com.oneframe.plugin.swagger;

import com.intellij.execution.ui.ConsoleView;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.Nullable;

public abstract class Action extends AnAction implements IAction {

  @Nullable
  @Override
  public PsiClass getPsiClass(AnActionEvent e) {
    PsiFile file = e.getData(LangDataKeys.PSI_FILE);
    Editor editor = e.getData(PlatformDataKeys.EDITOR_EVEN_IF_INACTIVE);
    if (file == null || editor == null) {
      return null;
    }

    int offset = editor.getCaretModel().getOffset();
    PsiElement element = file.findElementAt(offset);

    return PsiTreeUtil.getParentOfType(element, PsiClass.class);
  }

  @Nullable
  @Override
  public Project getProject(AnActionEvent e) {
    return e.getData(PlatformDataKeys.PROJECT);
  }

  @Nullable
  @Override
  public ConsoleView getConsole(AnActionEvent e) {
    return e.getData(LangDataKeys.CONSOLE_VIEW);
  }
}
