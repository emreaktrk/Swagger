package com.oneframe.plugin.swagger.utils;

import org.jetbrains.annotations.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ValidationUtils {

  public static boolean email(@Nullable String email) {
    if (email == null) {
      return false;
    }

    Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$.");
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
  }

  public static boolean url(@Nullable String ur) {
    if (ur == null) {
      return false;
    }

    Pattern pattern =
        Pattern.compile(
            "/((([A-Za-z]{3,9}:(?:\\/\\/)?)(?:[-;:&=\\+\\$,\\w]+@)?[A-Za-z0-9.-]+|(?:www.|[-;:&=\\+\\$,\\w]+@)[A-Za-z0-9.-]+)((?:\\/[\\+~%\\/.\\w-_]*)?\\??(?:[-\\+=&;%@.\\w_]*)#?(?:[\\w]*))?)/");
    Matcher matcher = pattern.matcher(ur);
    return matcher.matches();
  }
}
