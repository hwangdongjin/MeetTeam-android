package com.example.inyoung.teamapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    int idNum;
    String id,pw,name,phoneNum,addr,email;
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
        edt_professor = (EditText)findViewById(R.id.edt_professor);
        ApplicationController application = ApplicationController.getInstance();
        application.buildNetworkService();
        networkService = ApplicationController.getInstance().getNetworkService();
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("mytag","tse:"+edt_id.getText().toString());
                Log.i("mytag","tse:"+edt_pw.getText().toString());


                    if ("".equals(edt_id.getText().toString()))
                        Toast.makeText(getApplicationContext(), "아이디를 입력 해 주세요", Toast.LENGTH_LONG).show();
                    else
                        id = edt_id.getText().toString();
                    if ("".equals(edt_pw.getText().toString()))
                        Toast.makeText(getApplicationContext(), "비밀번호를 입력 해 주세요", Toast.LENGTH_LONG).show();
                    else
                        pw = edt_pw.getText().toString();
                    if ("".equals(edt_name.getText().toString()))
                        Toast.makeText(getApplicationContext(), "이름을 입력 해 주세요", Toast.LENGTH_LONG).show();
                    else
                        name = edt_name.getText().toString();
                    if ("".equals(edt_idNum.getText().toString()))
                        Toast.makeText(getApplicationContext(), "학번을 입력 해 주세요", Toast.LENGTH_LONG).show();
                    else
                        idNum = Integer.parseInt(edt_idNum.getText().toString());
                    if ("".equals(edt_phoneNum.getText().toString()))
                        Toast.makeText(getApplicationContext(), "전화번호를 입력 해 주세요", Toast.LENGTH_LONG).show();
                    else
                        phoneNum = edt_phoneNum.getText().toString();
                    if ("".equals(edt_addr.getText().toString()))
                        Toast.makeText(getApplicationContext(), "주소를 입력 해 주세요", Toast.LENGTH_LONG).show();
                    else
                        addr = edt_addr.getText().toString();
                    if ("".equals(edt_email.getText().toString()))
                        Toast.makeText(getApplicationContext(), "메일을 입력 해 주세요", Toast.LENGTH_LONG).show();
                    else
                        email = edt_email.getText().toString();
                    if ("".equals(edt_professor.getText().toString()))
                        Toast.makeText(getApplicationContext(), "신분을 입력 해 주세요", Toast.LENGTH_LONG).show();
                    else
                        professor = Boolean.parseBoolean(edt_professor.getText().toString());

                if (id != null && pw != null && name != null && idNum != 0 && phoneNum != null && addr != null && email != null) {
                    UserDTO user = new UserDTO();
                    user.setId(id);
                    user.setPassword(pw);
                    user.setName(name);
                    user.setIdNum(idNum);
                    user.setPhoneNum(phoneNum);
                    user.setAddr(addr);
                    user.setEmail(email);
                    user.setProfessor(professor);
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
                }
            }

        });
    }
}
