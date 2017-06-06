package com.example.inyoung.teamapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.inyoung.teamapp.RetroFit.NetworkService;
import com.example.inyoung.teamapp.dto.LoginDTO;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    private Intent fIntent;
    EditText edt_id, edt_pw;
    private NetworkService networkService;
    LoginDTO login;
    SharedPreferences sessDB;
    Retrofit retrofit;
    ApplicationController application;
    JSONObject jsonObject;

    String result;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt_id = (EditText) findViewById(R.id.edt_id);
        edt_pw = (EditText) findViewById(R.id.edt_pw);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        if (id != null) {
            Toast.makeText(getApplicationContext(), "반갑습니다. " + id + "님 로그인 해주세요.", Toast.LENGTH_SHORT).show();
        }

        initView();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initView() {
        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.btn_join).setOnClickListener(this);
        fIntent = new Intent(LoginActivity.this, JoinActivity.class);
        fIntent.putExtra("HI", "123");
    }


    @Override
    protected void onStart() {
        setResult(RESULT_OK, fIntent);
        super.onStart();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:

                application = ApplicationController.getInstance();
                application.buildNetworkService("52.78.39.253", 7530);
                networkService = ApplicationController.getInstance().getNetworkService();
                final String id = edt_id.getText().toString();
                String pw = edt_pw.getText().toString();
                sessDB = getSharedPreferences("sessDB",MODE_PRIVATE);
                final SharedPreferences.Editor editor = sessDB.edit();
                Call<ResponseBody> thumbnailCall = networkService.post_login(id, pw);
                thumbnailCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                        if (response.isSuccess()) {


                            try {

                                jsonObject= new JSONObject(response.body().string());
                                result=jsonObject.get("sess").toString();
                                editor.putString("session",result);
                                editor.commit();
                                Log.i("Mytag","testsess:"+result);
                                Log.i("Mytag", "testroom:" + result);





                            } catch (IOException e) {
                                e.printStackTrace();


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                            Intent intent = new Intent();
                            intent.setClass(getApplicationContext(), ListroomActivity.class);
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
                Intent intent2 = new Intent();
                intent2.setClass(this, JoinActivity.class);
                startActivity(intent2);
                break;
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Login Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
