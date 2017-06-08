package com.example.inyoung.teamapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.inyoung.teamapp.RetroFit.NetworkService;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONObject;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class RoomAddActivity extends AppCompatActivity {

    EditText roomName,roomSubject;
    Button roomAdd,roomCancel;
    String name,subject;
    ApplicationController application;
    private NetworkService networkService;
    JSONObject jsonObject;
    SharedPreferences sessDB;
    ListroomActivity listroomActivity;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_add);
        roomName=(EditText) findViewById(R.id.edt_roomName456);
        roomSubject=(EditText)findViewById(R.id.edt_roomSubject456);
        roomAdd=(Button)findViewById(R.id.btn_roomRegister);
        roomCancel=(Button)findViewById(R.id.btn_roomCancel);

        roomAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                application = ApplicationController.getInstance();
                application.buildNetworkService("52.78.39.253", 7530);
                networkService = ApplicationController.getInstance().getNetworkService();
                name=roomName.getText().toString();
                subject=roomSubject.getText().toString();
                sessDB = getSharedPreferences("sessDB",MODE_PRIVATE);
                Log.i("Mytag","getsess"+sessDB.getString("session","error"));
                Log.i("Mytag","getsess"+name);
                Log.i("Mytag","getsess"+subject);
                Call<ResponseBody> thum= networkService.post_add(sessDB.getString("session","error"),name,subject);
                thum.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                        if(response.isSuccess()){
                          intent = new Intent();
                            intent.setClass(getApplicationContext(),ListroomActivity.class);
                            intent.putExtra("value",true);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });





            }
        });
    }





}
