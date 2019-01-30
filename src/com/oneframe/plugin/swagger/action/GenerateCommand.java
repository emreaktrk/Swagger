package com.oneframe.plugin.swagger.action;

import com.intellij.execution.ui.ConsoleView;
import com.intellij.openapi.project.Project;
import com.oneframe.plugin.swagger.command.Command;

public class GenerateCommand extends Command {

  private String mUrl;
  private String mTarget;
  private String mName;

  public GenerateCommand(Project project, ConsoleView consoleView) {
    super(project, consoleView);
  }

  public void setInputs(String url, String target, String name) {
    mUrl = url;
    mTarget = target;
    mName = name;
  }

  @Override
  public String command() {
    return "networking-swagger-java" + " " + mUrl + " " + mTarget + " " + mName;
  }
}
