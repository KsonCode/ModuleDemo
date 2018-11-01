package com.example.kson.lib_core.utils;


/**
 * Author: 夜天子丶
 * *
 * Date: 2016-06-13 18:47
 * *
 * QQ: 363246266
 * *
 * Version: V1.0
 */
public class IsEmptyUtils {
    private static final String TAG = "IsEmptyUtils";

    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        }
        if (o instanceof String && StringUtils.isEmpty((String) o)) {
            return true;
        }

        return false;
    }

    public static boolean isEmpty(Object... o) {
        for (Object object : o) {
            if (isEmpty(object))
                return true;
        }
        return false;
    }

}
