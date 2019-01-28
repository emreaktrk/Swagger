package com.oneframe.plugin.swagger.url;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.psi.PsiClass;
import com.intellij.util.ui.JBUI;
import com.oneframe.plugin.swagger.utils.ValidationUtils;
import com.oneframe.plugin.swagger.view.JImage;
import com.oneframe.plugin.swagger.view.JInput;
import com.oneframe.plugin.swagger.view.orientation.JHorizontalLayout;
import com.oneframe.plugin.swagger.view.orientation.JVerticalLayout;

import javax.swing.*;
import java.io.IOException;

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
  protected void doOKAction() {
    super.doOKAction();

    String command =
        new StringBuilder()
            .append("networking-swagger-java")
            .append(" ")
            .append(mUrl.field().getText())
            .append(" ")
            .append(mTarget.field().getText())
            .append(" ")
            .append(mName.field().getText())
            .toString();
    try {
      Process process = new ProcessBuilder().command(command).start();
    } catch (IOException e) {
      e.printStackTrace();
    }
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
}
