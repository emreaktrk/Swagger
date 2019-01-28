package com.oneframe.plugin.swagger.view;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

public class JImage extends JLabel {

  public JImage(String asset) {
    super();

    Icon icon = IconLoader.getIcon(asset);
    setIcon(icon);
  }
}
