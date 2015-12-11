package com.clabs.com.clabs.utils;

import com.clabs.models.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

public class Json {

    public final static Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX").create();

    public final static <A> Response<A> toResponsefromConnectionResultWrapper(ConnectionResultWrapper connectionResultWrapper, Type type) {
        return (connectionResultWrapper.isJson())
                ? (Response<A>) Json.GSON.fromJson(connectionResultWrapper.getBody(), type)
                : (Response<A>) nonJson(connectionResultWrapper);
    }

    public final static <A> Response<A> nonJson(ConnectionResultWrapper connectionResultWrapper){
        System.out.println("[Error] The response object is not json formatted. " + connectionResultWrapper.getBody());
        return new Response<A>();
    }
}
