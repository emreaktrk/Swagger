package com.oneframe.plugin.swagger.view.orientation;

import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class JOrientationLayout extends JPanel {

  private final GridBagConstraints mConstraints;

  JOrientationLayout(Builder builder) {
    super(new GridBagLayout());

    mConstraints = new GridBagConstraints();
    mConstraints.anchor = GridBagConstraints.NORTHWEST;
    mConstraints.insets = JBUI.insets(8);

    for (int index = 0; index < builder.mComponents.size(); index++) {
      populate(mConstraints, index);

      JComponent component = builder.mComponents.get(index);
      add(component, mConstraints);
    }
  }

  protected abstract void populate(
      @NotNull GridBagConstraints constraints,
      @Range(from = 0, to = Integer.MAX_VALUE) int position);

  public GridBagConstraints constraints() {
    return mConstraints;
  }

  public abstract static class Builder {

    private List<JComponent> mComponents;

    Builder() {
      mComponents = new ArrayList<>();
    }

    public Builder add(JComponent component) {
      mComponents.add(component);

      return this;
    }

    public abstract JPanel build();
  }
}
