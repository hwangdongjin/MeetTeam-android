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
    private NetworkService networkService;
    Integer idNum;

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
        application.buildNetworkService("52.78.39.253", 7530);
        networkService = ApplicationController.getInstance().getNetworkService();
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = edt_id.getText().toString();
                String pw = edt_pw.getText().toString();
                String name = edt_name.getText().toString();
                //idNum= new Integer(edt_idNum.getText().toString());
                Log.i("MyTAG","idnum:"+idNum);
                String phoneNum  = edt_phoneNum.getText().toString();
                String addr = edt_addr.getText().toString();
                String email = edt_email.getText().toString();
                boolean professor = Boolean.parseBoolean(edt_professor.getText().toString());

                UserDTO user = new UserDTO();
                user.setId(id);
                user.setPassword(pw);
                user.setName(name);
                //user.setIdNum(idNum);
                user.setPhoneNum(phoneNum);
                user.setAddr(addr);
                user.setEmail(email);
                user.setProfessor(professor);

                Call<UserDTO> thumbnailCall = networkService.post_food(user);

                thumbnailCall.enqueue(new Callback<UserDTO>(){
                    @Override
                    public void onResponse(Response<UserDTO> response, Retrofit retrofit) {
                        if(response.isSuccess()){
                            Toast.makeText(getBaseContext(),"등록되었습니다",Toast.LENGTH_SHORT).show();
                            //성공시 변수thumbnailCall 에 성공한데이터담김
                            //성공시 토스트메시지 출력
                        }else{
                            int statusCode = response.code();
                            Log.i("MyTag","응답코드:"+statusCode);
                            //서버상에 문제 있을경우 응답코드뜸

                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.i("MyTag","서버 onFailure 에러내용 : "+t.getMessage());
                        //서버에 아얘 연결이 안된경우
                    }
                });
            }
        });
    }
}
