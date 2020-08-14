package com.example.youloftstars.util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpUtils {
    public static RetrofitNetWork getFortuneInfo(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.85:3000/mock/471/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitNetWork netWork = retrofit.create(RetrofitNetWork.class);
        return  netWork;
    }
}
