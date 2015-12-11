package com.clabs.models;

final public class Error {

    public String message;

    public Integer code;

    public Integer status;

    @Override
    public String toString() {
        return "[Error] status:" + status + ", code: " + code + ", message: " + message;
    }
}
