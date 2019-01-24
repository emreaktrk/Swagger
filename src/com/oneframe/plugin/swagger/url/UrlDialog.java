package com.oneframe.plugin.swagger.url;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.util.IconLoader;
import com.intellij.psi.PsiClass;
import com.intellij.util.ui.JBUI;
import com.oneframe.plugin.swagger.utils.ValidationUtils;

import javax.swing.*;
import java.awt.*;

public class UrlDialog extends DialogWrapper {

  private JTextField mUrl;

  UrlDialog(PsiClass psi) {
    super(psi.getProject());

    init();
    setTitle("Write Swagger Url");
  }

  @Override
  protected JComponent createCenterPanel() {
    JPanel panel = new JPanel(new GridBagLayout());

    GridBagConstraints constraints = new GridBagConstraints();
    constraints.anchor = GridBagConstraints.NORTHWEST;

    JLabel image = new JLabel();
    image.setPreferredSize(JBUI.size(180, 100  ));
    image.setIcon(IconLoader.getIcon("icons/starforce.png"));
    constraints.gridx = 0;
    constraints.gridy = 0;
    panel.add(image, constraints);

    JLabel header = new JLabel("Networking Swagger Generator");
    header.setPreferredSize(JBUI.size(240, 60));
    constraints.gridx = 1;
    constraints.gridy = 0;
    panel.add(header, constraints);

    JLabel label = new JLabel("Enter your swagger url");
    constraints.gridx = 1;
    constraints.gridy = 1;
    panel.add(label, constraints);

    mUrl = new JTextField(20);
    constraints.gridx = 1;
    constraints.gridy = 2;
    panel.add(mUrl, constraints);
    return panel;
  }

  @Override
  protected void doOKAction() {
    super.doOKAction();

    String url = mUrl.getText();
    if (ValidationUtils.url(url)) {}
  }
}
