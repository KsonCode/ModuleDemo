package com.example.andfix.entity;

import com.example.kson.lib_net.network.BaseResponse;

public class PatchInfo extends BaseResponse {
    public String apkUrl; //不为空则表明有更新
    public int versionCode;

    public String versionName; //本次patch包的版本号

    public String patchMessage; //本次patch包含的相关信息，例如：主要做了那些改动
}
