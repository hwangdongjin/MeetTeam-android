package com.example.inyoung.teamapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.inyoung.teamapp.RetroFit.NetworkService;
import com.example.inyoung.teamapp.RetroFit.SharedPreferenceUtil;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Intent intent;
    EditText edt_id, edt_pw;
    SharedPreferenceUtil sessDB;
    String result,name;
    String id,pw;
    private NetworkService networkService;
    ApplicationController application;
    JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt_id = (EditText) findViewById(R.id.edt_id);
        edt_pw = (EditText) findViewById(R.id.edt_pw);
        initView();
    }

    private void initView() {
        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.btn_join).setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                application = ApplicationController.getInstance();
                application.buildNetworkService();
                networkService = ApplicationController.getInstance().getNetworkService();
                if("".equals(edt_id.getText().toString())){
                    Toast.makeText(getApplicationContext(),"아이디를 입력하세요",Toast.LENGTH_LONG).show();
                }
                else {
                    id = edt_id.getText().toString();
                }
                if("".equals(edt_pw.getText().toString())){
                    Toast.makeText(getApplicationContext(),"비밀번호를 입력하세요",Toast.LENGTH_LONG).show();
                }
                else{
                    pw = edt_pw.getText().toString();
                }

                    sessDB = new SharedPreferenceUtil(this);
                    Call<ResponseBody> thumbnailCall = networkService.post_login(id, pw);
                    thumbnailCall.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                            if (response.isSuccess()) {
                                try {
                                    jsonObject = new JSONObject(response.body().string());
                                    result = jsonObject.get("sess").toString();
                                    name = jsonObject.get("name").toString();
                                    sessDB.setSess(result);
                                    sessDB.setName(name);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                intent = new Intent(getApplicationContext(), ListroomActivity1.class);
                                startActivity(intent);
                            } else {
                                int statusCode = response.code();
                                Log.i("MyTag", "응답코드:" + statusCode);
                            }
                        }
                        @Override
                        public void onFailure(Throwable t) {
                            Log.i("MyTag", "서버 onFailure 에러내용 : " + t.getMessage());
                        }
                    });
                    break;


            case R.id.btn_join:
                intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
                break;
        }
    }



}
