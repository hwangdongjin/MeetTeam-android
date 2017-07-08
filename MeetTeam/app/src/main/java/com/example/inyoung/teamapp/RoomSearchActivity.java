package com.example.inyoung.teamapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.inyoung.teamapp.RetroFit.NetworkService;
import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class RoomSearchActivity extends AppCompatActivity {
    EditText edt_roomName,edt_password;
    String roomName,password;
    Button btn_search;
    ApplicationController application;
    private NetworkService networkService;
    SharedPreferences sessDB;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_search2);
        edt_roomName=(EditText) findViewById(R.id.edt_roomName789);
        edt_password=(EditText) findViewById(R.id.edt_roomSubject789);
        btn_search=(Button) findViewById(R.id.btn_roomSearch789);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                application = ApplicationController.getInstance();
                application.buildNetworkService();
                networkService = ApplicationController.getInstance().getNetworkService();
                roomName=edt_roomName.getText().toString();
                password=edt_password.getText().toString();
                sessDB = getSharedPreferences("sessDB",MODE_PRIVATE);
                Log.i("Mytag","getsess"+sessDB.getString("session","error"));
                Log.i("Mytag","getsess"+roomName);

                Call<ResponseBody> thum= networkService.post_addUser(sessDB.getString("session","error"),roomName);
                thum.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                        if(response.isSuccess()){
                            intent = new Intent();
                            intent.setClass(getApplicationContext(),ListroomActivity.class);
                            intent.putExtra("search",true);
                            startActivity(intent);
                        }
                        Log.i("Mytag","getsess"+response.body());
                            Toast.makeText(application, "찾는 방이 없습니다", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Throwable t) {
                    }
                });
            }
        });
    }
}
