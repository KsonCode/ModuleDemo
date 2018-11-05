package com.example.kson.moduledemo.common;

import com.example.kson.moduledemo.BuildConfig;

/**
 * Author:kson
 * E-mail:19655910@qq.com
 * Time:2018/11/01
 * Description:
 */
public class Constants {
    public static String BASE_URL = BuildConfig.DEBUG?"http://www.zhaoapi.cn/":"https://www.zhaoapi.cn/";
    public static String LOGIN_URL = "techApi/user/v1/login";
    public static String CHECK_URL = "techApi/user/v1/checkPhone";
    public static String NEWS_URL = "user/login";
}
