package com.example.inyoung.teamapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inyoung.teamapp.RetroFit.NetworkService;
import com.example.inyoung.teamapp.RetroFit.SharedPreferenceUtil;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class TtableActivity extends AppCompatActivity {

    private AlertDialog.Builder dlg;
    static int textId;
    SharedPreferenceUtil sessDB;
    Button DateSelectButton;
    TextView DateSelectView;
    TextView text1;
    String sess,roomTitle,date;
    static int year1,month1,dayOfMonth1;
    ArrayList<String> times;
    private NetworkService networkService;
    ApplicationController application;
    public Object jsonArray;
    public JSONObject jsonObject;
    Intent intent;
    String[] tablePos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ttable);
        DateSelectButton=(Button)findViewById(R.id.DateSelectButton);
        DateSelectView = (TextView) findViewById(R.id.DateSelectView);
        sessDB = new SharedPreferenceUtil(getApplicationContext());
        times= new ArrayList<>();
        intent = getIntent();
        year1=intent.getIntExtra("year",0);
        month1=intent.getIntExtra("month",0);
        dayOfMonth1=intent.getIntExtra("day",0);
        findViewById(R.id.TableText0).setOnClickListener(onClick);
        findViewById(R.id.TableText1).setOnClickListener(onClick);
        findViewById(R.id.TableText2).setOnClickListener(onClick);
        findViewById(R.id.TableText3).setOnClickListener(onClick);
        findViewById(R.id.TableText4).setOnClickListener(onClick);
        findViewById(R.id.TableText5).setOnClickListener(onClick);
        findViewById(R.id.TableText6).setOnClickListener(onClick);
        findViewById(R.id.TableText7).setOnClickListener(onClick);
        findViewById(R.id.TableText8).setOnClickListener(onClick);
        findViewById(R.id.TableText9).setOnClickListener(onClick);
        findViewById(R.id.TableText10).setOnClickListener(onClick);
        findViewById(R.id.TableText11).setOnClickListener(onClick);
        findViewById(R.id.TableText12).setOnClickListener(onClick);
        findViewById(R.id.TableText13).setOnClickListener(onClick);
        findViewById(R.id.TableText14).setOnClickListener(onClick);
        findViewById(R.id.TableText15).setOnClickListener(onClick);
        findViewById(R.id.TableText16).setOnClickListener(onClick);
        findViewById(R.id.TableText17).setOnClickListener(onClick);
        findViewById(R.id.TableText18).setOnClickListener(onClick);
        findViewById(R.id.TableText19).setOnClickListener(onClick);
        findViewById(R.id.TableText20).setOnClickListener(onClick);
        findViewById(R.id.TableText21).setOnClickListener(onClick);
        findViewById(R.id.TableText22).setOnClickListener(onClick);
        findViewById(R.id.TableText23).setOnClickListener(onClick);
        findViewById(R.id.TableText24).setOnClickListener(onClick);
        findViewById(R.id.TableText25).setOnClickListener(onClick);
        findViewById(R.id.TableText26).setOnClickListener(onClick);
        findViewById(R.id.TableText27).setOnClickListener(onClick);

    }


    TextView.OnClickListener onClick =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.TableText0:

                    times.add("0");
                    initTableAdd(times);

                    break;
                case R.id.TableText1:

                    times.add("1");
                    initTableAdd(times);

                    break;
                case R.id.TableText2:

                    times.add("2");
                    initTableAdd(times);

                    break;
                case R.id.TableText3:

                    times.add("3");
                    initTableAdd(times);

                    break;

                case R.id.TableText4:

                    times.add("4");
                    initTableAdd(times);

                    break;
                case R.id.TableText5:

                    times.add("5");
                    initTableAdd(times);

                    break;
                case R.id.TableText6:

                    times.add("6");
                    initTableAdd(times);

                    break;

                case R.id.TableText7:

                    times.add("7");
                    initTableAdd(times);

                    break;
                case R.id.TableText8:

                    times.add("8");
                    initTableAdd(times);

                    break;
                case R.id.TableText9:

                    times.add("9");
                    initTableAdd(times);

                    break;
                case R.id.TableText10:

                    times.add("10");
                    initTableAdd(times);

                    break;
                case R.id.TableText11:

                    times.add("11");
                    initTableAdd(times);

                    break;
                case R.id.TableText12:

                    times.add("12");
                    initTableAdd(times);

                    break;
                case R.id.TableText13:

                    times.add("13");
                    initTableAdd(times);

                    break;
                case R.id.TableText14:

                    times.add("14");
                    initTableAdd(times);

                    break;
                case R.id.TableText15:

                    times.add("15");
                    initTableAdd(times);

                    break;
                case R.id.TableText16:

                    times.add("16");
                    initTableAdd(times);

                    break;
                case R.id.TableText17:

                    times.add("17");
                    initTableAdd(times);

                    break;
                case R.id.TableText18:

                    times.add("18");
                    initTableAdd(times);

                    break;
                case R.id.TableText19:

                    times.add("19");
                    initTableAdd(times);

                    break;
                case R.id.TableText20:

                    times.add("20");
                    initTableAdd(times);

                    break;
                case R.id.TableText21:

                    times.add("21");
                    initTableAdd(times);


                    break;
                case R.id.TableText22:

                    times.add("22");
                    initTableAdd(times);

                    break;
                case R.id.TableText23:

                    times.add("23");
                    initTableAdd(times);

                    break;
                case R.id.TableText24:

                    times.add("24");
                    initTableAdd(times);

                    break;
                case R.id.TableText25:

                    times.add("25");
                    initTableAdd(times);

                    break;
                case R.id.TableText26:

                    times.add("26");
                    initTableAdd(times);

                    break;
                case R.id.TableText27:

                    times.add("27");
                    initTableAdd(times);

                    break;


            }

        }
    };


    public void initTableAdd(ArrayList<String> times){
        sess=sessDB.getSess();
        roomTitle=sessDB.getRoomTitle();
        date= String.valueOf(year1)+"-"+String.valueOf(month1)+"-"+String.valueOf(dayOfMonth1);
        Log.i("mytag","datess:"+year1);
        application = ApplicationController.getInstance();
        application.buildNetworkService();
        networkService = ApplicationController.getInstance().getNetworkService();
        Call<ResponseBody> thumbnailcall = networkService.post_ttableAdd(sess,roomTitle,date,times);
        thumbnailcall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {

                if(response.isSuccess()){

                    initTableShow(roomTitle,date);
                }
                else
                    Toast.makeText(getApplicationContext(),"실패하였습니다",Toast.LENGTH_LONG).show();


            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }

    public void initTableShow(String roomTitle,String date){

        application = ApplicationController.getInstance();
        application.buildNetworkService();
        networkService = ApplicationController.getInstance().getNetworkService();
        Call<ResponseBody> thumnailcall = networkService.post_ttableShow(roomTitle,date);
        thumnailcall.enqueue(new Callback<ResponseBody>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {

                if(response.isSuccess()){
                    try {

                        jsonObject= new JSONObject(response.body().string());
                        JSONArray jsonArray=new JSONArray(jsonObject.get("tables").toString());
                        for(int i=0;i<jsonArray.length();i++){

                            JSONObject json= jsonArray.getJSONObject(i);
                            JSONObject json2 = new JSONObject(json.toString());
                            JSONArray jsonArray1 = new JSONArray(json2.get("times").toString());
                            String userName = (String) json2.get("userName");
                            initSetText(jsonArray1,userName);

                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }


                else
                    Toast.makeText(getApplicationContext(),"실패하였습니다",Toast.LENGTH_LONG).show();


            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }




    public void initSetText(JSONArray jsonArray, String userName) throws JSONException {


        for(int i=0;i<jsonArray.length();i++) {

            int number= Integer.parseInt(jsonArray.get(i).toString());
            Log.i("mytag", "number22:" + number);
            Log.i("mytag","text1:"+userName);

            switch (number) {

                case 0:

                    textId = R.id.TableText0;
                    text1 = (TextView) findViewById(textId);
                    text1.append(userName+",");
                    int z =text1.getText().toString().indexOf(",");
                    String temp= text1.getText().toString().substring(0,z);


                    break;
                case 1:

                    textId = R.id.TableText1;
                    text1 = (TextView) findViewById(textId);
                    text1.setText(null);
                    text1.append(userName);

                    break;


                case 2:
                    textId = R.id.TableText2;
                    text1 = (TextView) findViewById(textId);
                    text1.setText(null);
                    text1.append(userName);

                    break;
                case 3:
                    textId = R.id.TableText3;
                    text1 = (TextView) findViewById(textId);
                    text1.setText(null);
                    text1.append(userName);

                    break;

                case 4:
                    textId = R.id.TableText4;
                    text1 = (TextView) findViewById(textId);
                    text1.setText(null);
                    text1.append(userName);

                    break;
                case 5:
                    textId = R.id.TableText5;
                    text1 = (TextView) findViewById(textId);
                    text1.setText(null);
                    text1.append(userName);

                    break;
                case 6:
                    textId = R.id.TableText6;
                    text1 = (TextView) findViewById(textId);
                    text1.setText(null);
                    text1.append(userName);

                    break;

                case 7:
                    textId = R.id.TableText7;
                    text1 = (TextView) findViewById(textId);
                    text1.setText(null);
                    text1.append(userName);

                    break;
                case 8:
                    textId = R.id.TableText8;
                    text1 = (TextView) findViewById(textId);
                    text1.setText(null);
                    text1.append(userName);

                    break;
                case 9:
                    textId = R.id.TableText9;
                    text1 = (TextView) findViewById(textId);
                    text1.setText(null);
                    text1.append(userName);

                    break;
                case 10:
                    textId = R.id.TableText10;
                    text1 = (TextView) findViewById(textId);
                    text1.setText(null);
                    text1.append(userName);

                    break;
                case 11:
                    textId = R.id.TableText11;
                    text1 = (TextView) findViewById(textId);
                    text1.setText(null);
                    text1.append(userName);

                    break;
                case 12:
                    textId = R.id.TableText12;
                    text1 = (TextView) findViewById(textId);
                    text1.setText(null);
                    text1.append(userName);

                    break;
                case 13:
                    textId = R.id.TableText13;
                    text1 = (TextView) findViewById(textId);
                    text1.setText(null);
                    text1.append(userName);
                    break;
                case 14:
                    textId = R.id.TableText14;
                    text1 = (TextView) findViewById(textId);
                    text1.setText(null);
                    text1.append(userName);
                    break;
                case 15:
                    textId = R.id.TableText15;
                    text1 = (TextView) findViewById(textId);
                    text1.setText(null);
                    text1.append(userName);

                    break;
                case 16:
                    textId = R.id.TableText16;
                    text1 = (TextView) findViewById(textId);
                    text1.setText(null);
                    text1.append(userName);

                    break;
                case 17:
                    textId = R.id.TableText17;
                    text1 = (TextView) findViewById(textId);
                    text1.setText(null);
                    text1.append(userName);

                    break;
                case 18:
                    textId = R.id.TableText18;
                    text1 = (TextView) findViewById(textId);
                    text1.setText(null);
                    text1.append(userName);

                    break;
                case 19:
                    textId = R.id.TableText19;
                    text1 = (TextView) findViewById(textId);
                    text1.setText(null);
                    text1.append(userName);

                    break;
                case 20:
                    textId = R.id.TableText20;
                    text1 = (TextView) findViewById(textId);
                    text1.setText(null);
                    text1.append(userName);
                    break;
                case 21:
                    textId = R.id.TableText21;
                    text1 = (TextView) findViewById(textId);
                    text1.setText(null);
                    text1.append(userName);


                    break;
                case 22:
                    textId = R.id.TableText22;
                    text1 = (TextView) findViewById(textId);
                    text1.setText(null);
                    text1.append(userName);

                    break;
                case 23:
                    textId = R.id.TableText23;
                    text1 = (TextView) findViewById(textId);
                    text1.setText(null);
                    text1.append(userName);
                    break;
                case 24:
                    textId = R.id.TableText24;
                    text1 = (TextView) findViewById(textId);
                    text1.setText(null);
                    text1.append(userName);

                    break;
                case 25:
                    textId = R.id.TableText25;
                    text1 = (TextView) findViewById(textId);
                    text1.setText(null);
                    text1.append(userName);

                    break;
                case 26:
                    textId = R.id.TableText26;
                    text1 = (TextView) findViewById(textId);
                    text1.setText(null);
                    text1.append(userName);

                    break;
                case 27:
                    textId = R.id.TableText27;
                    text1 = (TextView) findViewById(textId);
                    text1.setText(null);
                    text1.append(userName);

                    break;

            }
        }

    }

}
