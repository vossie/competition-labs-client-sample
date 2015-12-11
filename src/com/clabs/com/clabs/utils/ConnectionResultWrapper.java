package com.clabs.com.clabs.utils;

public class ConnectionResultWrapper {

    private String body;

    private int httpStatusCode;

    private String contentType;

    public ConnectionResultWrapper setBody(String body) {
        this.body = body;

        return this;
    }

    public ConnectionResultWrapper setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;

        return this;
    }

    public ConnectionResultWrapper setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public String getBody() {
        return body;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public String getContentType() {
        return contentType;
    }

    public boolean isJson() {
        return getContentType().toLowerCase().contains("json");
    }
}
