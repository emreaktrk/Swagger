package com.oneframe.plugin.swagger.action.url;

import com.intellij.execution.ui.ConsoleView;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.oneframe.plugin.swagger.Action;
import com.oneframe.plugin.swagger.action.GenerateCommand;
import org.jetbrains.annotations.NotNull;

public class UrlAction extends Action implements UrlDialog.OnOKClickListener {

  private ConsoleView mConsole;

  @Override
  public void actionPerformed(@NotNull AnActionEvent e) {
    PsiClass psi = getPsiClass(e);
    mConsole = getConsole(e);

    if (psi != null) {
      UrlDialog dialog = new UrlDialog(psi, this);
      dialog.show();
    }
  }

  @Override
  public void update(@NotNull AnActionEvent e) {
    super.update(e);

    PsiClass psi = getPsiClass(e);
    e.getPresentation().setEnabled(psi != null);
  }

  @SuppressWarnings("deprecation")
  @Override
  public void onOKClick(PsiClass psi, String url, String target, String name) {
    Project project = psi.getProject();

    ProgressManager.getInstance()
        .run(
            new Task.Backgroundable(project, "Generating Networking Modules", true) {
              @Override
              public void run(@NotNull ProgressIndicator indicator) {
                GenerateCommand generate = new GenerateCommand(project, mConsole);
                generate.setInputs(url, target, name);
                generate.execute();

                project.getBaseDir().refresh(false, true);
              }

              @Override
              public void onSuccess() {
                super.onSuccess();

                new Notification("url", null, NotificationType.INFORMATION)
                    .setImportant(true)
                    .setTitle("Success")
                    .setContent("APIs and Unit Tests are generated")
                    .notify(project);
              }

              @Override
              public void onCancel() {
                super.onCancel();

                new Notification("url", null, NotificationType.ERROR)
                    .setImportant(true)
                    .setTitle("Cancelled")
                    .setContent("Operation is stopped")
                    .notify(project);
              }
            });
  }
}
