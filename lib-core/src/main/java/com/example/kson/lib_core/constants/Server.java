package com.example.kson.lib_core.constants;

public class Server {
    public static boolean isRelease = false;
    public static final String BASE_URL = isRelease?"http://www.com":"http://172.19";
}
