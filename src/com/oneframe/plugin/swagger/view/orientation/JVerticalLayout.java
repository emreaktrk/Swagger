package com.oneframe.plugin.swagger.view.orientation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import javax.swing.*;
import java.awt.*;

public class JVerticalLayout extends JOrientationLayout {

  private JVerticalLayout(Builder builder) {
    super(builder);
  }

  @Override
  protected void populate(
      @NotNull GridBagConstraints constraints,
      @Range(from = 0, to = Integer.MAX_VALUE) int position) {
    constraints.gridy = position;
  }

  public static class Builder extends JOrientationLayout.Builder {

    @Override
    public JPanel build() {
      return new JVerticalLayout(this);
    }
  }
}
