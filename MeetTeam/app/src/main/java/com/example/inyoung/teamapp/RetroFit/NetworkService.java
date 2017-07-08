package com.example.inyoung.teamapp.RetroFit;

import com.example.inyoung.teamapp.dto.UserDTO;
import com.squareup.okhttp.ResponseBody;

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
    Call<ResponseBody> post_room(@Field("sess")String sess);
    @FormUrlEncoded
    @POST("/room/add/")
    Call<ResponseBody> post_add(@Field("sess")String sess,@Field("name")String roomName,@Field("subject")String subject);
    @FormUrlEncoded
    @POST("/room/addUser")
    Call<ResponseBody> post_addUser(@Field("sess")String sess,@Field("roomName")String roomName);
    @FormUrlEncoded
    @POST("/user/list")
    Call<ResponseBody> post_userList(@Field("roomName")String roomName);
}
