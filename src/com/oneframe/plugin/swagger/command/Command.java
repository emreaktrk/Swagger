package com.oneframe.plugin.swagger.command;

import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;

public abstract class Command implements ICommand {

  private boolean isExecuting;
  private StringBuilder mOutput;
  private boolean mResult;
  private Project mProject;
  private ConsoleView mConsoleView;

  public Command(Project project, ConsoleView consoleView) {
    mProject = project;
    mConsoleView = consoleView;
  }

  @Override
  public void execute() {
    if (isExecuting) {
      return;
    }

    isExecuting = true;

    mOutput = new StringBuilder();

    try {
      String[] exec = command().split(" ");

      GeneralCommandLine commandline = new GeneralCommandLine(exec);
      commandline.setWorkDirectory(mProject.getBasePath());

      OSProcessHandler handler = new OSProcessHandler(commandline);
      handler.startNotify();
      handler.waitFor();

      Integer code = handler.getExitCode();
      if (code != null) {
        mResult = code == 0;
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      isExecuting = false;
    }
  }

  public @Nullable String getOutput() {
    return mOutput.toString();
  }

  @Override
  public boolean isSuccess() {
    if (!isExecuting) {
      return false;
    }

    return mResult;
  }
}
