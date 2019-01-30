package com.oneframe.plugin.swagger.action.url;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.psi.PsiClass;
import com.intellij.util.ui.JBUI;
import com.oneframe.plugin.swagger.utils.ValidationUtils;
import com.oneframe.plugin.swagger.view.JImage;
import com.oneframe.plugin.swagger.view.JInput;
import com.oneframe.plugin.swagger.view.orientation.JHorizontalLayout;
import com.oneframe.plugin.swagger.view.orientation.JVerticalLayout;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class UrlDialog extends DialogWrapper implements JInput.TextListener {

  private JInput mUrl;
  private JInput mTarget;
  private JInput mName;
  private PsiClass mPsi;

  private OnOKClickListener mListener;

  UrlDialog(PsiClass psi, @Nullable OnOKClickListener listener) {
    super(psi.getProject());

    mPsi = psi;

    init();
    setTitle("Networking Swagger Generator");
    setOKActionEnabled(false);

    mListener = listener;
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

    if (mListener != null) {
      String url = mUrl.field().getText();
      String target = mTarget.field().getText();
      String name = mName.field().getText();

      mListener.onOKClick(mPsi, url, target, name);
    }
  }

  public interface OnOKClickListener {
    void onOKClick(PsiClass psi, String url, String target, String name);
  }
}
