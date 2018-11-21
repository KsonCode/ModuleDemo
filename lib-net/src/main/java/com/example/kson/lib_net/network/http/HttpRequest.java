package com.example.kson.lib_net.network.http;


import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.Response;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Url;

public interface HttpRequest {
    void post(String url, HashMap<String, Object> params, ICallback callback);
    void get(String url, HashMap<String, Object> params, ICallback callback);
    void uploadFile(@Url String fileUrl, @PartMap HashMap<String,String> content,
                                            @Part() List<MultipartBody.Part> parts,ICallback callback);

}
