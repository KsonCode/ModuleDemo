package com.example.kson.moduledemo.entity;

import com.example.kson.lib_net.network.BaseResponse;

import java.util.List;

/**
 * Author:kson
 * E-mail:19655910@qq.com
 * Time:2018/10/31
 * Description:
 */
public class ContactsEntity extends BaseResponse {
    public String sellerName;
    public String currentNumber;
    public String customize;
    public String groupId;
    public String groupName;
    public List<Lists> list;

    public class Lists{
       public String  bargainPrice;
    }
}
