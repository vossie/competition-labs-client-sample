package com.clabs.models;

import java.util.ArrayList;

final public class Error {

    public String message;

    public Integer code;

    public Integer status;

    public ArrayList<Error> children;

    @Override
    public String toString() {
        String out = "[Error] status:" + status + ", code: " + code + ", message: " + message;

        if(children!=null)
            for(Error child: children)
                out = out + "\n> [Child]" + child.toString();

        return out;
    }
}
