package com.clabs.com.clabs.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Json {

    public final static Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX").create();
}
