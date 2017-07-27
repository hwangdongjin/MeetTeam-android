package com.example.inyoung.teamapp.RetroFit;

import com.example.inyoung.teamapp.dto.UserDTO;
import com.squareup.okhttp.ResponseBody;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;


/**
 * Created by MYpc on 2017-05-14.
 */

public interface NetworkService {
    @POST("/user/add")
    Call<UserDTO> post_userAdd(@Body UserDTO userDTO);
    @FormUrlEncoded
    @POST("/user/login/")
    Call<ResponseBody> post_login(@Field("id")String id,@Field("password")String password);
    @FormUrlEncoded
    @POST("/room/list/")
    Call<ResponseBody> post_roomList(@Field("sess")String sess);
    @FormUrlEncoded
    @POST("/room/add/")
    Call<ResponseBody> post_roomAdd(@Field("sess")String sess,@Field("title")String title,@Field("subject")String subject);
    @FormUrlEncoded
    @POST("/room/addUser")
    Call<ResponseBody> post_roomAddUser(@Field("sess")String sess,@Field("title")String title);
    @FormUrlEncoded
    @POST("/user/list")
    Call<ResponseBody> post_userList(@Field("title")String title);
    @FormUrlEncoded
    @POST("/ttable/add")
    Call<ResponseBody> post_ttableAdd(@Field("sess")String sess, @Field("roomTitle")String title, @Field("date")String date, @Field("times")ArrayList arrayList);
    @FormUrlEncoded
    @POST("/ttable/show")
    Call<ResponseBody> post_ttableShow(@Field("roomTitle")String roomTitle,@Field("date")String date);




    @FormUrlEncoded
    @POST("/map/show")
    Call<ResponseBody> post_mapshow(@Field("roomTitle")String roomTitle,@Field("date")String date);

    @FormUrlEncoded
    @POST("/map/add")
    Call<ResponseBody> post_map(@Field("sess")String sess, @Field("roomTitle")String title, @Field("date")String date, @Field("longitude")double longitude, @Field("latitude")double latitude);


}
