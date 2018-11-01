package com.example.kson.lib_core.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

//import com.sina.weibo.sdk.auth.Oauth2AccessToken;

/**
 * Created by DELL on 2016/5/9.
 */
public class SharedPreferencesUtil {

    private static final String KEY_REFRESH_TOKEN = "refresh_token";
    private static final String PREFERENCES_NAME = "com_weibo_sdk_android";
    private static final String KEY_UID = "uid";
    private static final String KEY_ACCESS_TOKEN = "access_token";
    private static final String KEY_EXPIRES_IN = "expires_in";
    private static final String SharedPreferencesName = "BeePay";
    private static SharedPreferences mSharedPreferences;

    /**
     * 存储boolean值的方法
     */
    public static void putBoolean(Context context, String key, Boolean value) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SharedPreferencesName, context.MODE_PRIVATE);
        }
        mSharedPreferences.edit().putBoolean(key, value).commit();
    }

    /**
     * 获取boolean值的方法
     */
    public static boolean getBoolean(Context context, String key, Boolean defValue) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SharedPreferencesName, context.MODE_PRIVATE);
        }
        return mSharedPreferences.getBoolean(key, defValue);
    }

    /**
     * 存储String值的方法
     */
    public static void putString(Context context, String key, String value) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SharedPreferencesName, context.MODE_PRIVATE);
        }
        mSharedPreferences.edit().putString(key, value).commit();
    }

    /**
     * 获取String值的方法
     */
    public static String getString(Context context, String key, String defValue) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SharedPreferencesName, context.MODE_PRIVATE);
        }
        return mSharedPreferences.getString(key, defValue);
    }

    /**
     * 存储Set集合的方法
     */
    public static void putStringSet(Context context, String key, Set<String> value) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SharedPreferencesName, context.MODE_PRIVATE);
        }
        mSharedPreferences.edit().putStringSet(key, value).commit();
    }

    /**
     * 获取Set集合的方法
     */
    public static Set<String> getStringSet(Context context, String key, Set<String> defValue) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SharedPreferencesName, context.MODE_PRIVATE);
        }
        return mSharedPreferences.getStringSet(key, defValue);
    }

    /**
     * 存入int值得方法
     */
    public static void putInt(Context context, String key, int value) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SharedPreferencesName, context.MODE_PRIVATE);
        }
        mSharedPreferences.edit().putInt(key, value).commit();
    }

    /**
     * 存入Long类型的值
     */
    public static void putLong(Context context, String key, long value) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SharedPreferencesName, context.MODE_PRIVATE);
        }
        mSharedPreferences.edit().putLong(key, value).commit();


    }

    /**
     * 获取int值得方法
     */
    public static int getInt(Context context, String key, int defValue) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SharedPreferencesName, context.MODE_PRIVATE);
        }
        return mSharedPreferences.getInt(key, defValue);
    }

    /**
     * 获取long
     */
    public static Long getLong(Context context, String key, long defValue) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SharedPreferencesName, context.MODE_PRIVATE);
        }
        return mSharedPreferences.getLong(key, defValue);
    }

    /**
     * 保存 Token 对象到 SharedPreferences。
     *
     * @param context 应用程序上下文环境
     * @param token   Token 对象
     */
//    public static void writeAccessToken(Context context, Oauth2AccessToken token) {
//        if (null == context || null == token) {
//            return;
//        }
//
//        SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_APPEND);
//        SharedPreferences.Editor editor = pref.edit();
//        editor.putString(KEY_UID, token.getUid());
//        editor.putString(KEY_ACCESS_TOKEN, token.getToken());
//        editor.putString(KEY_REFRESH_TOKEN, token.getRefreshToken());
//        editor.putLong(KEY_EXPIRES_IN, token.getExpiresTime());
//        editor.commit();
//    }

    /**
     * 从 SharedPreferences 读取 Token 信息。
     *
     * @param context 应用程序上下文环境
     *
     * @return 返回 Token 对象
     */
//    public static Oauth2AccessToken readAccessToken(Context context) {
//        if (null == context) {
//            return null;
//        }
//
//        Oauth2AccessToken token = new Oauth2AccessToken();
//        SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_APPEND);
//        token.setUid(pref.getString(KEY_UID, ""));
//        token.setToken(pref.getString(KEY_ACCESS_TOKEN, ""));
//        token.setRefreshToken(pref.getString(KEY_REFRESH_TOKEN, ""));
//        token.setExpiresTime(pref.getLong(KEY_EXPIRES_IN, 0));
//
//        return token;
//    }

    /**
     * 清空 SharedPreferences 中 Token信息。
     *
     * @param context 应用程序上下文环境
     */
    public static void clear(Context context) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SharedPreferencesName, context.MODE_PRIVATE);
        }
        mSharedPreferences.edit().clear().commit();
    }


}
