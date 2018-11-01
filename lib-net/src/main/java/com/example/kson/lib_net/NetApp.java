package com.example.kson.lib_net;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.SPUtils;
import com.example.kson.lib_net.utils.CharacterUtils;
import com.example.kson.lib_net.utils.Sha256Utils;
import com.facebook.stetho.Stetho;

/**
 * <pre>
 * </pre>
 */
public class NetApp {
    //    private static ACache cache;
    public static String appSercet = "xWZIWMmc4PjTbbS2C1P4atKQ58DAdcqe";
    private static Application app;
    public static String baseUrl="http://www.zhaoapi.cn/";

    public static void init(Application context,String Url) {
        app = context;
        baseUrl=Url;
//        cache = ACache.get(getCacheFile());
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(context);
        }
//        DiskCache.openCache(this);
    }

    public static Context getAppContext() {
        return app;
    }

    public static String getSign() {
        StringBuffer sb = new StringBuffer();
        sb.append(getNonce() + "," + appSercet + "," + getReq_Seq());
        String sign = sb.toString();
        String sha256StrJava = Sha256Utils.getSHA256StrJava(sign);
        return sha256StrJava;
    }

    public static String getNonce() {
        String nonce = SPUtils.getInstance().getString("nonce", "");
        return nonce;
    }

    public static String getReq_Seq() {
        String req_seq = SPUtils.getInstance().getString("req_seq", "");
        return req_seq;
    }
    public static String getBodyRandom() {
        String randomString = CharacterUtils.createRandomCharData(6);
        SPUtils.getInstance().put( "nonce", randomString);
        return randomString;
    }

    public static String getSysyemTime() {
        long time = System.currentTimeMillis();
        String symtime = String.valueOf(time);
        SPUtils.getInstance().put("req_seq", symtime);
        return symtime;
    }


//    public static ACache getCache() {
//        return cache;
//    }

    //获取缓存目录
   /* private File getCacheFile() {
        File file = new File(getExternalCacheDir() + "/http_exception_data");
        if (!(file.exists() && file.isDirectory())) {
            file.mkdirs();
        }
        return file;
    }*/

}
