package com.clabs.models;

import java.util.List;
import java.util.Map;

final public class Response<A> {

    public A data;

    public Map<String,String> meta;

    public List<Error> errors;

    public Integer getTotalRecordsFound(){
        if(meta.containsKey("totalRecordsFound"))
            return Integer.parseInt(meta.get("totalRecordsFound"));
        else
            return -1;
    }

    public Integer getSkip(){
        if(meta.containsKey("skip"))
            return Integer.parseInt(meta.get("skip"));
        else
            return -1;
    }

    public Integer getLimit(){
        if(meta.containsKey("limit"))
            return Integer.parseInt(meta.get("limit"));
        else
            return -1;
    }
}
