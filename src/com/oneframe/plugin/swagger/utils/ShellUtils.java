package com.oneframe.plugin.swagger.utils;

import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringJoiner;

public class ShellUtils {

  public static @Nullable String run(String... commands) {
    ProcessBuilder builder = new ProcessBuilder(commands);
    Process process;
    String output = "";

    try {
      process = builder.start();

      InputStream input = process.getInputStream();
      InputStreamReader reader = new InputStreamReader(input);
      BufferedReader buffer = new BufferedReader(reader);

      StringJoiner joiner = new StringJoiner(System.getProperty("line.separator"));
      buffer.lines().iterator().forEachRemaining(joiner::add);
      output = joiner.toString();

      process.waitFor();
      process.destroy();
    } catch (Exception e) {
      return null;
    }

    return output;
  }
}
