package com.oneframe.plugin.swagger;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.psi.PsiClass;

import javax.annotation.Nullable;

public interface IAction {

    @Nullable
    PsiClass getPsiClass(AnActionEvent e);
}
