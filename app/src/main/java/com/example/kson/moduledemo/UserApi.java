package com.example.kson.moduledemo;


import com.example.kson.moduledemo.entity.UploadEntity;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

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

    /**
     * 上传文件
     *
     * @param fileUrl
     * @return
     */
    @POST
    @Multipart
    Observable<UploadEntity> uploadFile(@Url String fileUrl, @Part("content") String content,
                                        @Part() List<MultipartBody.Part> parts);

}
