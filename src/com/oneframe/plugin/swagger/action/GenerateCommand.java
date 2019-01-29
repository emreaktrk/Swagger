package com.oneframe.plugin.swagger.action;

import com.oneframe.plugin.swagger.command.Command;

public class GenerateCommand extends Command {

  private String mUrl;
  private String mTarget;
  private String mName;

  public GenerateCommand(String url, String target, String name) {
    mUrl = url;
    mTarget = target;
    mName = name;
  }

  @Override
  public String command() {
    return "networking-swagger-java" + " " + mUrl + " " + mTarget + " " + mName;
  }
}
