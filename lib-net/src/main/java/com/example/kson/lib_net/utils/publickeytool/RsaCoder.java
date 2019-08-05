package com.example.kson.lib_net.utils.publickeytool;



import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by 樊彦龙 on 2018/9/1.
 * RSA公钥处理工具类
 */
public class RsaCoder {

    //非对称密钥算法
    public static final String KEY_ALGORITHM="RSA";

    //公钥
    private static PublicKey publicKey;

    //初始化
    static {
        publicKey = getPublicKey(KEY_ALGORITHM);
    }


    /**
     *  获取公钥
     * @param algorithm
     * @return
     */
    private static PublicKey getPublicKey(String algorithm) {
        try {
            Base64 b64 = new Base64();
            byte [] decoded = b64.decode(Key.key);
            KeyFactory keyFactory=KeyFactory.getInstance(algorithm);
            X509EncodedKeySpec x509KeySpec=new X509EncodedKeySpec(decoded);
            return keyFactory.generatePublic(x509KeySpec);
        }catch (Exception e) {
            return null;
        }
    }

    /**
     *  公钥解密
     * @param data
     * @return
     * @throws Exception
     */
    public static String decryptByPublicKey(String data) throws Exception{
        try {

            if(null == data || "".equals(data))
            {
                return null;
            }

            byte[] key = publicKey.getEncoded();
            byte[] datas = Base64.decode(data);
            //实例化密钥工厂
            KeyFactory keyFactory=KeyFactory.getInstance(KEY_ALGORITHM);
            //初始化公钥
            //密钥材料转换
            X509EncodedKeySpec x509KeySpec=new X509EncodedKeySpec(key);
            //产生公钥
            PublicKey pubKey=keyFactory.generatePublic(x509KeySpec);
            //数据解密
            String algorithm = keyFactory.getAlgorithm();

            Cipher cipher=Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, pubKey);
            String s = new String(cipher.doFinal(datas));

            return s;
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    /**
     *  公钥加密
     * @param data
     * @return
     * @throws Exception
     */
    public static String encryptByPublicKey(String data) {
        try {

            if(null == data || "".equals(data))
            {
                return null;
            }

            byte[] key = publicKey.getEncoded();
            byte[] datas = data.getBytes();
            //实例化密钥工厂
            KeyFactory keyFactory=KeyFactory.getInstance(KEY_ALGORITHM);
            //初始化公钥
            X509EncodedKeySpec x509KeySpec=new X509EncodedKeySpec(key);
            //产生公钥
            PublicKey pubKey=keyFactory.generatePublic(x509KeySpec);

            //数据加密
            Cipher cipher=Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            return Base64.encode(cipher.doFinal(datas));
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * @param
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {


        //公钥加密数据
        String str2 = encryptByPublicKey("111111");
        System.out.println(str2);
        System.out.println(decryptByPublicKey(str2));

    }
}
