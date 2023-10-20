package com.assignment.demo.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtils {

  private static Gson gson;
  private static GsonBuilder gsonBuilder = new GsonBuilder();

  static {
    gson = gsonBuilder.create();
  }

  public static String serialize(Object object) {
    return gson.toJson(object);
  }

  public static <T> T deserialize(String json, Class<T> jsonClass) {
    return gson.fromJson(json, jsonClass);
  }

}
