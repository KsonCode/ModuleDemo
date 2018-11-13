package com.example.kson.lib_net;

import java.io.File;

/**
 *
 */
public class NetConstants
{
    //http请求成功
    public static final String HTTP_SUCCESS = "0";

    //token过期了
    public static final int EXPIRED_TOKEN = 1001;

    //================= PATH ====================
    public static final String PATH_DATA = NetApp.getAppContext().getExternalCacheDir().getAbsolutePath() + File.separator +
            "data";
    public static final String PATH_CACHE = PATH_DATA + File.separator + "NetCache";


    public static final int PAUSE_DOWNLOAD = 5;
    public static final int CANCEL_DOWNLOAD = 6;

    //cookie
    public static final String COOKIES = "cookies";

    //存储权限
    public static final int WRITE_REQUEST_CODE = 101;
    /**
     * 申请权限 sp 的key
     */
    public static final String REQUEST_CODE_PERMISSION = "request_code_permission";

    public static final String SHOW_WEBVIEW = "show_webview";

}
