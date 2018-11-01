package com.example.kson.lib_net.utils;

import com.blankj.utilcode.util.SPUtils;
import com.example.kson.lib_net.utils.secretutil.AESOperator;
import com.example.kson.lib_net.utils.secretutil.RSAUtils;

public class KeyHelper {
    public static String AESKEY = "";
    public static String ivParams = "0392039203920300";

    public static String getPrivateKey() {
        try {
            return RSAUtils.encryptByPublicKey(AESKEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    public static String encry(String s, String ct) {
        if ("0".equals(ct)) {
            String key = AESOperator.generateKey();
            AESKEY = key;
            return s;
        } else {
            try {
                String key = AESOperator.generateKey();
                AESKEY = key;
                return AESOperator.Encrypt(s, AESKEY, ivParams).replaceAll("\\s*", "");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return s;
        }
    }

    public static String getHeadParams() {
        try {
            String token = SPUtils.getInstance().getString("token", "");
            String tk = getReq_Seq() + "," + token + "," + getNonce();
            return AESOperator.Encrypt(tk, AESKEY, ivParams).replaceAll("\\s*", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    public static String decry() {
        try {
            String pk = SPUtils.getInstance().getString("pk", "");
            return RSAUtils.decryptByPrivateKey(pk);//返回aes的秘钥
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    private static String getReq_Seq() {
        return SPUtils.getInstance().getString("req_seq", "");
    }

    private static String getNonce() {
        return SPUtils.getInstance().getString("nonce", "");
    }

}
