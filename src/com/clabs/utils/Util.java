package com.clabs.utils;

import java.util.List;

public class Util {

    public static String listToCommaSeparatedList(String prefix, List<String> stringList) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(prefix);

        for (int i = 0; i < stringList.size(); i++) {
            stringBuilder.append(stringList.get(i));

            if((i+1) < stringList.size())
                stringBuilder.append(",");
        }

        return stringBuilder.toString();
    }
}
