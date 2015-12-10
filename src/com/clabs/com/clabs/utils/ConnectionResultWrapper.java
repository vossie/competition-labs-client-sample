package com.clabs.com.clabs.utils;

public class ConnectionResultWrapper {

    private String body;

    private int httpStatusCode;

    public ConnectionResultWrapper setBody(String body) {
        this.body = body;

        return this;
    }

    public ConnectionResultWrapper setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;

        return this;
    }

    public String getBody() {
        return body;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }
}
