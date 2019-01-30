package com.oneframe.plugin.swagger.command;

public interface ICommand {

  String command();

  void execute();

  boolean isSuccess();
}
