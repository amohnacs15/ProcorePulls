package com.amohnacs.common;

import java.util.List;

/**
 * Created by adrianmohnacs on 1/28/18.
 */

public class Utils {

    public static <E extends Object> boolean isEmpty(List<E> objectList) {
        if(objectList == null) {
            return true;
        } else if (objectList.size() <= 0) {
            return true;
        } else {
            //success scenario
            return false;
        }
    }

    public static boolean isEmpty(String string) {
        if(string == null) {
            return true;
        } else if (string.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
