package com.clabs.examples;

import com.clabs.models.Error;
import com.clabs.models.Response;

abstract public class Common {

    public static <A> Response<A> printErrorsFromResponse(Response<A> response) {
        if(response != null && response.errors != null){
            for(Error error: response.errors) {
                System.out.println(error.toString());
            }
        }
        return response;
    }
}
