package com.oneframe.plugin.swagger.utils;

import org.apache.http.util.TextUtils;
import org.jetbrains.annotations.Nullable;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public final class ValidationUtils {

  public static boolean url(@Nullable String uri) {
    if (uri == null) {
      return false;
    }

    URL url;

    try {
      url = new URL(uri);
    } catch (MalformedURLException e) {
      return false;
    }

    try {
      url.toURI();
    } catch (URISyntaxException e) {
      return false;
    }

    return true;
  }

  public static boolean has(@Nullable String text) {
    if (text == null) {
      return false;
    }

    return !TextUtils.isEmpty(text.trim());
  }
}
