package com.example.inyoung.teamapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.inyoung.teamapp.RetroFit.NetworkService;
import com.example.inyoung.teamapp.dto.UserDTO;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class JoinActivity extends AppCompatActivity {
    EditText edt_id,edt_pw,edt_name,edt_idNum,edt_phoneNum,edt_addr,edt_email,edt_professor;
    Button btn_register;
    String id,pw,name,idNum,phoneNum,addr,email;
    Boolean professor;

    private NetworkService networkService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        btn_register = (Button)findViewById(R.id.btn_register);
        edt_id = (EditText)findViewById(R.id.edt_id);
        edt_pw = (EditText)findViewById(R.id.edt_pw);
        edt_name = (EditText)findViewById(R.id.edt_name);
        edt_idNum = (EditText)findViewById(R.id.edt_idNum);
        edt_phoneNum = (EditText)findViewById(R.id.edt_phoneNum);
        edt_addr = (EditText)findViewById(R.id.edt_addr);
        edt_email = (EditText)findViewById(R.id.edt_email);
        
        ApplicationController application = ApplicationController.getInstance();
        application.buildNetworkService();
        networkService = ApplicationController.getInstance().getNetworkService();
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("mytag","tse:"+edt_id.getText().toString());
                Log.i("mytag","tse:"+edt_pw.getText().toString());


                if (!TextUtils.isEmpty(edt_id.getText()) && !TextUtils.isEmpty(edt_pw.getText()) && !TextUtils.isEmpty(edt_name.getText())&&
                        !TextUtils.isEmpty(edt_idNum.getText()) && !TextUtils.isEmpty(edt_phoneNum.getText()) && !TextUtils.isEmpty(edt_addr.getText())
                        && !TextUtils.isEmpty(edt_email.getText())) {

                    id = edt_id.getText().toString();
                    pw = edt_pw.getText().toString();
                    name = edt_name.getText().toString();
                    idNum = edt_idNum.getText().toString();
                    phoneNum = edt_phoneNum.getText().toString();
                    addr = edt_addr.getText().toString();
                    email = edt_email.getText().toString();

                    UserDTO user = new UserDTO();
                    user.setId(id);
                    user.setPassword(pw);
                    user.setName(name);
                    user.setIdNum(idNum);
                    user.setPhoneNum(phoneNum);
                    user.setAddr(addr);
                    user.setEmail(email);
                    Call<UserDTO> thumbnailCall = networkService.post_userAdd(user);
                    thumbnailCall.enqueue(new Callback<UserDTO>() {
                        @Override
                        public void onResponse(Response<UserDTO> response, Retrofit retrofit) {
                            if (response.isSuccess()) {
                                Toast.makeText(getBaseContext(), "등록되었습니다", Toast.LENGTH_SHORT).show();
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
                }else{
                    Toast.makeText(getApplicationContext(), "모든 칸을 입력해주세요", Toast.LENGTH_LONG).show();
                }
            }

        });
    }
}
