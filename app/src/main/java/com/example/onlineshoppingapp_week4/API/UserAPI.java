package com.example.onlineshoppingapp_week4.API;

import com.example.onlineshoppingapp_week4.Model.ItemModel;
import com.example.onlineshoppingapp_week4.Model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserAPI {

    @GET("selectitem")
    Call<List<ItemModel>> getItems();

//    @FormUrlEncoded
//    @POST("registeruser")
//    Call<UserModel> registerUser(@Field("userFname") String uFname, @Field("userLname") String uLname, @Field("username") String uname, @Field("password") String pass);
//
//    @FormUrlEncoded
    @POST("registeruser")
    Call<Void> useradd(@Body UserModel userModel);



    @FormUrlEncoded
    @POST("login")
    Call<String> login(@Field("username") String uname, @Field("password") String password);

    @GET("selectitembyId/{id}")
    Call<ItemModel> getItembyID(@Path("id") int id);
}
