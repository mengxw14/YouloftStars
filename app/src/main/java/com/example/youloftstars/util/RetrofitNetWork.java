package com.example.youloftstars.util;

import android.graphics.Bitmap;

import com.example.youloftstars.bean.ShouxiangPay;
import com.example.youloftstars.bean.UserPostData;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitNetWork {
    @GET("api/Main/GetUser")
    Call<ResponseBody> getUser();

    @POST("api/Main/CreateOrUpdateUser")
    @Headers("Content-Type:application/json")//提交的时候后端接收到json格式的数据
    Call<ResponseBody> UONUser(@Body UserPostData bean);

    @GET("api/Main/GetArticle")
    Call<ResponseBody> getArticle(@Query("id") int id);

    @GET("api/Main/GetAstroTb")
    Call<ResponseBody> getAstroTb(@Query("Astrold") String Astrold);

    @POST("api/Main/LuckPalmistryCreateOrder")
    Call<ResponseBody> postShouxiang(@Body ShouxiangPay bean);

    @GET("api/Main/GetProduct")
    Call<ResponseBody> getProductInfo();

    @POST("api/Main/PutFile")
    @FormUrlEncoded
    Call<ResponseBody> postPutFile(@Field("picture") Bitmap bitmap);

    @GET("api/Main/GetOrderList")
    Call<ResponseBody> getOrderList(@Query("UserID") int id, @Query("PayStatus") int status,
                                    @Query("Page") int page, @Query("Rows")int rows);
}
