package com.oneframe.plugin.swagger.command;

public interface ICommand {

  String command();

  boolean execute();

  void executeAsync();
}
