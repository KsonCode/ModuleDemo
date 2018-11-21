package com.example.kson.lib_net;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface ApiService {


    //公用的接口
    @POST
    @FormUrlEncoded
    Observable<Response<String>> getData(@HeaderMap HashMap<String, String> headers, @Url String url, @FieldMap HashMap<String, Object> body);

    //公用的接口
    @GET
    Observable<Response<String>> doGetData(@HeaderMap HashMap<String, String> headers, @Url String url, @QueryMap HashMap<String, Object> body);

    //公用的接口
    @POST
    Observable<Response<String>> getData(@Url String url, @Body String body);

    //下载文件
    //如果文件非常大，必须要使用Streaming注解。否则retrofit会默认将整个文件读取到内存中
    //造成OOM
    @Streaming
    @GET
    Observable<ResponseBody> downLoadFile(@Url String fileUrl);

    /**
     * 上传文件
     *
     * @param fileUrl
     * @return
     */
    @POST
    @Multipart
    Observable<Response<String>> uploadFile(@HeaderMap HashMap<String,String> header,@Url String fileUrl, @PartMap HashMap<String,String> content,
                                            @Part() List<MultipartBody.Part> parts);

}
