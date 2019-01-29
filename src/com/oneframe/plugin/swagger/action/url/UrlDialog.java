package com.oneframe.plugin.swagger.action.url;

import com.intellij.ide.projectView.ProjectView;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.psi.PsiClass;
import com.intellij.util.ui.JBUI;
import com.oneframe.plugin.swagger.action.GenerateCommand;
import com.oneframe.plugin.swagger.utils.ValidationUtils;
import com.oneframe.plugin.swagger.view.JImage;
import com.oneframe.plugin.swagger.view.JInput;
import com.oneframe.plugin.swagger.view.orientation.JHorizontalLayout;
import com.oneframe.plugin.swagger.view.orientation.JVerticalLayout;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class UrlDialog extends DialogWrapper implements JInput.TextListener {

  private JInput mUrl;
  private JInput mTarget;
  private JInput mName;

  UrlDialog(PsiClass psi) {
    super(psi.getProject());

    init();
    setTitle("Networking Swagger Generator");
    setOKActionEnabled(false);
  }

  @Override
  protected JComponent createCenterPanel() {
    mUrl = new JInput("Enter your swagger url");
    mUrl.setListener(this);

    mTarget = new JInput("Enter your package");
    mTarget.setListener(this);

    mName = new JInput("Enter your manager name");
    mName.setListener(this);

    JPanel inputs = new JVerticalLayout.Builder().add(mUrl).add(mTarget).add(mName).build();

    JImage image = new JImage("icons/starforce.png");
    image.setPreferredSize(JBUI.size(180, 100));

    return new JHorizontalLayout.Builder().add(image).add(inputs).build();
  }

  @Override
  public void onTextUpdate() {
    if (!ValidationUtils.url(mUrl.field().getText())) {
      setOKActionEnabled(false);
      return;
    }

    if (!ValidationUtils.has(mTarget.field().getText())) {
      setOKActionEnabled(false);
      return;
    }

    if (!ValidationUtils.has(mName.field().getText())) {
      setOKActionEnabled(false);
      return;
    }

    setOKActionEnabled(true);
  }

  @Override
  protected void doOKAction() {
    super.doOKAction();

    Project project = ProjectManager.getInstance().getOpenProjects()[0];

    ProgressManager.getInstance()
        .run(
            new Task.Backgroundable(project, "Generating apis...", false) {
              @Override
              public void run(@NotNull ProgressIndicator progressIndicator) {
                String url = mUrl.field().getText();
                String target = mTarget.field().getText();
                String name = mName.field().getText();
                GenerateCommand generate = new GenerateCommand(url, target, name);
                generate.execute();
              }
            });

    ProjectView.getInstance(project).refresh();
  }
}
