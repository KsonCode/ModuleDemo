package com.example.kson.moduledemo;


import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Author:kson
 * E-mail:19655910@qq.com
 * Time:2018/10/31
 * Description:
 */
public interface UserApi {
    @POST("user/login")
    @FormUrlEncoded
    Observable<ResponseBody> login(@FieldMap HashMap<String,String> params);
}
