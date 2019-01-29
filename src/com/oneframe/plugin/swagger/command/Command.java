package com.oneframe.plugin.swagger.command;

import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessEvent;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.process.ProcessListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.util.Key;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class Command implements ICommand {

  private boolean isExecuting;
  private StringBuilder mOutput;

  @Override
  public boolean execute() {
    if (isExecuting) {
      return false;
    }

    isExecuting = true;

    boolean result = true;
    mOutput = new StringBuilder();

    try {
      String[] exec = command().split(" ");
      GeneralCommandLine commandline = new GeneralCommandLine(exec);

      Project project = ProjectManager.getInstance().getOpenProjects()[0];
      commandline.setWorkDirectory(project.getBasePath());

      ProcessHandler handler = new OSProcessHandler(commandline);
      handler.startNotify();
      handler.waitFor();

      Integer code = handler.getExitCode();
      if (code != null) {
        result = code == 0;
      }
    } catch (Exception e) {
      result = false;
    } finally {
      // TODO Close streams
    }

    return result;
  }

  @Override
  public void executeAsync() {
    if (isExecuting) {
      return;
    }

    isExecuting = true;

    mOutput = new StringBuilder();

    try {
      String[] exec = command().split(" ");
      GeneralCommandLine commandline = new GeneralCommandLine(exec);

      Project project = ProjectManager.getInstance().getOpenProjects()[0];
      commandline.setWorkDirectory(project.getBasePath());

      ProcessHandler handler = new OSProcessHandler(commandline);
      handler.startNotify();
      handler.addProcessListener(new ProcessListener() {
        @Override
        public void startNotified(@NotNull ProcessEvent processEvent) {

        }

        @Override
        public void processTerminated(@NotNull ProcessEvent processEvent) {

        }

        @Override
        public void processWillTerminate(@NotNull ProcessEvent processEvent, boolean b) {

        }

        @Override
        public void onTextAvailable(@NotNull ProcessEvent processEvent, @NotNull Key key) {

        }
      });

    } catch (Exception e) {
    } finally {
      // TODO Close streams
    }
  }

  public @Nullable String getOutput() {
    return mOutput.toString();
  }
}
