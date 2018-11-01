package com.example.kson.lib_net.utils;

import android.text.TextUtils;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.example.kson.lib_net.NetApp;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

/**
 * Created by kson on 2017/12/12.
 */

public class EncryBody {
    public static String encryBody(HashMap<String, Object> map) {
        try {
            if (!isEmpty(map)) {
                putBody(map);
                StringBuffer sb = new StringBuffer();
                for (String key : map.keySet()) {
                    sb.append(map.get(key) + ",");
                }
                String body = sb.toString();
                if (!TextUtils.isEmpty(body)) {
                    String ct = SPUtils.getInstance().getString("CT", "0");
                    if ("0".equals(ct)) {
                        //不加密
                        Gson gson = new GsonBuilder()
                                .serializeNulls()
                                .create();
                        String s = gson.toJson(map);
                        String encry = KeyHelper.encry(s, ct);
                        LogUtils.e("beePayUploading", encry);
                        return encry;
                    } else {
                        Gson gson = new GsonBuilder()
                                .serializeNulls()
                                .create();
                        String s = gson.toJson(map);
                        LogUtils.e("beePayUploading","上传 加密前", s + "加密之前的");
                        String encry = KeyHelper.encry(s, ct);
                        LogUtils.e("beePayUploading","上传 加密后", encry + "加密之后的");
                        return encry;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getUrl(String url) {
        StringBuffer sb = new StringBuffer();
        String sign = NetApp.getSign();
        sb.append(url + "?" + sign);
        String urlString = sb.toString();
        return urlString;
    }

    private static void putBody(HashMap<String, Object> map) {
        map.put("reqSeq", NetApp.getSysyemTime());//获取系统时间
        map.put("nonce", NetApp.getBodyRandom());//获取6位的随机数
    }

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
