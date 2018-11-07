package com.example.kson.lib_net.network.rx;


import android.text.TextUtils;

import com.blankj.utilcode.util.LogUtils;

import io.reactivex.functions.Function;
import retrofit2.Response;


/**
 * <pre>
 * </pre>
 */
public class HttpResultFunc implements Function<Response<String>, String> {

    @Override
    public String apply(Response<String> response) throws Exception {
        return getData(response);

    }

    /**
     *  解密数据
     * @param response
     * @return
     */
//    public String getData(Response<String> response){
//        if (response.raw().headers().get("pk") != null) {
//            SPUtils.getInstance().put("pk", response.raw().headers().get("pk").replaceAll("\\s*", ""));
//        }
//        String tk = response.headers().get("tk");
//        if (!TextUtils.isEmpty(tk)) {
//            String decry = KeyHelper.decry();
//            try {
//                String tktrim = tk.replaceAll("\\s*", "");
//                String tkdecry = AESOperator.decrypt(tktrim, decry, ivParams).replaceAll("\\s*", "");
//                String[] split = tkdecry.split(",");
//                String token = split[1];
//                SPUtils.getInstance().put("token", token);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        if (!TextUtils.isEmpty(response.body())) {
////            String ct = SharedPreferencesUtil.getString(MyApplication.getContext(), "CT", "");
//            String ct = response.raw().headers().get("ct");
//            if (ct == null) {
//                ct = "0";
//            }
//            if ("0".equals(ct)) {
//                //返回的没有加密的结果
//                String result = response.body();
//                LogUtils.e("KsonResponse",result);
//                return result;
//            } else {
//                //得到的是密钥  返回的是加密的字符串
//                try {
////                    String decry = KeyHelper.decry();
//                    String decry = RSAUtils.decryptByPrivateKey(response.raw().headers().get("pk").replaceAll("\\s*", ""));
//                    String result = AESOperator.decrypt(response.body(), decry, ivParams);
//                     LogUtils.e("Ksonn","返回 解密后的数据",result);
//                    return result;
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    return null;
//                }
//            }
//        } else {
////            ToastUtil.show(MyApplication.getContext(), "服务器繁忙");
//        }
//        return null;
//    }
    /**
     *  处理数据
     * @param response
     * @return
     */
    public String getData(Response<String> response){

        String result = response.body();

        if (!TextUtils.isEmpty(result)) {
//
                //返回的没有加密的结果
                LogUtils.e("KsonResponse",result);
                return result;

        } else {
//            ToastUtil.show(MyApplication.getContext(), "服务器繁忙");
        }
        return null;
    }
}
