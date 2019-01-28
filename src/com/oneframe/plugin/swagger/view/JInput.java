package com.oneframe.plugin.swagger.view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class JInput extends JPanel implements DocumentListener {

  private JTextField mField;
  private TextListener mListener;

  public JInput(String hint) {
    super(new GridBagLayout());

    GridBagConstraints constraints = new GridBagConstraints();
    constraints.anchor = GridBagConstraints.NORTHWEST;

    constraints.gridx = 0;
    constraints.gridy = 0;
    JLabel label = new JLabel(hint);
    add(label, constraints);

    constraints.gridx = 0;
    constraints.gridy = 1;
    mField = new JTextField(30);
    mField.getDocument().addDocumentListener(this);
    add(mField, constraints);
  }

  public JTextField field() {
    return mField;
  }

  @Override
  public void insertUpdate(DocumentEvent e) {
    if (mListener != null) {
      mListener.onTextUpdate();
    }
  }

  @Override
  public void removeUpdate(DocumentEvent e) {
    if (mListener != null) {
      mListener.onTextUpdate();
    }
  }

  @Override
  public void changedUpdate(DocumentEvent e) {
    if (mListener != null) {
      mListener.onTextUpdate();
    }
  }

  public void setListener(TextListener listener) {
    mListener = listener;
  }

  public interface TextListener {
    void onTextUpdate();
  }
}
