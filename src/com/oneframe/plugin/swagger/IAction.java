package com.oneframe.plugin.swagger;

import com.intellij.execution.ui.ConsoleView;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import org.jetbrains.annotations.Nullable;

public interface IAction {

  @Nullable
  PsiClass getPsiClass(AnActionEvent e);

  @Nullable
  Project getProject(AnActionEvent e);

  @Nullable
  ConsoleView getConsole(AnActionEvent e);
}
