package com.example.inyoung.teamapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inyoung.teamapp.RetroFit.NetworkService;
import com.example.inyoung.teamapp.RetroFit.SharedPreferenceUtil;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class TtableActivity extends AppCompatActivity {

    private NetworkService networkService;
    ApplicationController application;
    Intent intent;
    private AlertDialog.Builder dlg;
    SharedPreferenceUtil sessDB;
    Button DateSelectButton;
    TextView DateSelectView;
    TextView text1;
    String sess,roomTitle,date;
    static int year, month, dayOfMonth;
    public JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ttable);

        DateSelectButton=(Button)findViewById(R.id.btn_ttablecal);
        DateSelectView = (TextView)findViewById(R.id.tv_ttablecal);

        for(int i=0; i<28; i++){
            int timeId= getResources().getIdentifier("TableText"+i, "id", getPackageName());
            findViewById(timeId).setOnClickListener(onClick);
        }

        sessDB = new SharedPreferenceUtil(getApplicationContext());
        sess=sessDB.getSess();
        roomTitle=sessDB.getRoomTitle();

        final DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int fix_month=month+1;
                int last_date = dayOfMonth + 6;
                DateSelectView.setText(year+"년 "+fix_month+"월 "+dayOfMonth+"일" +" ~ " + year+"년 "+fix_month+"월 "+last_date+"일");
                date= String.valueOf(year)+"-"+String.valueOf(fix_month)+"-"+String.valueOf(dayOfMonth);
                initTableShow(roomTitle,date);
            }
        };

        DateSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                DatePickerDialog dlg = new DatePickerDialog(TtableActivity.this , listener, c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
                dlg.show();
            }
        });
    }

    TextView.OnClickListener onClick =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.TableText0:
                    initTableAdd("0");
                    break;
                case R.id.TableText1:
                    initTableAdd("1");
                    break;
                case R.id.TableText2:
                    initTableAdd("2");
                    break;
                case R.id.TableText3:
                    initTableAdd("3");
                    break;
                case R.id.TableText4:
                    initTableAdd("4");
                    break;
                case R.id.TableText5:
                    initTableAdd("5");
                    break;
                case R.id.TableText6:
                    initTableAdd("6");
                    break;
                case R.id.TableText7:
                    initTableAdd("7");
                    break;
                case R.id.TableText8:
                    initTableAdd("8");
                    break;
                case R.id.TableText9:
                    initTableAdd("9");
                    break;
                case R.id.TableText10:
                    initTableAdd("10");
                    break;
                case R.id.TableText11:
                    initTableAdd("11");
                    break;
                case R.id.TableText12:
                    initTableAdd("12");
                    break;
                case R.id.TableText13:
                    initTableAdd("13");
                    break;
                case R.id.TableText14:
                    initTableAdd("14");
                    break;
                case R.id.TableText15:
                    initTableAdd("15");
                    break;
                case R.id.TableText16:
                    initTableAdd("16");
                    break;
                case R.id.TableText17:
                    initTableAdd("17");
                    break;
                case R.id.TableText18:
                    initTableAdd("18");
                    break;
                case R.id.TableText19:
                    initTableAdd("19");
                    break;
                case R.id.TableText20:
                    initTableAdd("20");
                    break;
                case R.id.TableText21:
                    initTableAdd("21");
                    break;
                case R.id.TableText22:
                    initTableAdd("22");
                    break;
                case R.id.TableText23:
                    initTableAdd("23");
                    break;
                case R.id.TableText24:
                    initTableAdd("24");
                    break;
                case R.id.TableText25:
                    initTableAdd("25");
                    break;
                case R.id.TableText26:
                    initTableAdd("26");
                    break;
                case R.id.TableText27:
                    initTableAdd("27");
                    break;
            }
        }
    };


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
                        JSONArray tables = new JSONArray(jsonObject.get("tables").toString());
                        for(int i=0; i<tables.length();i++){
                            String userNames = "";
                            JSONObject table = tables.getJSONObject(i);
                            int time = Integer.parseInt((String)table.get("time"));
                            JSONArray userNamesArray = new JSONArray(table.get("userNames").toString());
                            initSetText(time, userNamesArray);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"가능한 시간을 선택하세요.",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public void initSetTextNull(){
        for(int i=0; i<28; i++){
            int timeId= getResources().getIdentifier("TableText"+i, "id", getPackageName());
            TextView timeView = (TextView)findViewById(timeId);
            timeView.setText("");
        }
    }

    public void initSetText(int time, JSONArray userNamesArray) throws JSONException {
        for(int i=0;i<userNamesArray.length();i++) {
            String userName= userNamesArray.get(i).toString() + " ";
            switch (time) {
                case 0:
                    text1 = (TextView) findViewById(R.id.TableText0);
                    text1.append(userName);
                    break;
                case 1:
                    text1 = (TextView) findViewById(R.id.TableText1);
                    text1.append(userName);
                    break;
                case 2:
                    text1 = (TextView) findViewById(R.id.TableText2);
                    text1.append(userName);
                    break;
                case 3:
                    text1 = (TextView) findViewById(R.id.TableText3);
                    text1.append(userName);
                    break;
                case 4:
                    text1 = (TextView) findViewById(R.id.TableText4);
                    text1.append(userName);
                    break;
                case 5:
                    text1 = (TextView) findViewById(R.id.TableText5);
                    text1.append(userName);
                    break;
                case 6:
                    text1 = (TextView) findViewById(R.id.TableText6);
                    text1.append(userName);
                    break;
                case 7:
                    text1 = (TextView) findViewById(R.id.TableText7);
                    text1.append(userName);
                    break;
                case 8:
                    text1 = (TextView) findViewById(R.id.TableText8);
                    text1.append(userName);
                    break;
                case 9:
                    text1 = (TextView) findViewById(R.id.TableText9);
                    text1.append(userName);
                    break;
                case 10:
                    text1 = (TextView) findViewById(R.id.TableText10);
                    text1.append(userName);
                    break;
                case 11:
                    text1 = (TextView) findViewById(R.id.TableText11);
                    text1.append(userName);
                    break;
                case 12:
                    text1 = (TextView) findViewById(R.id.TableText12);
                    text1.append(userName);
                    break;
                case 13:
                    text1 = (TextView) findViewById(R.id.TableText13);
                    text1.append(userName);
                    break;
                case 14:
                    text1 = (TextView) findViewById(R.id.TableText14);
                    text1.append(userName);
                    break;
                case 15:
                    text1 = (TextView) findViewById(R.id.TableText15);
                    text1.append(userName);
                    break;
                case 16:
                    text1 = (TextView) findViewById(R.id.TableText16);
                    text1.append(userName);
                    break;
                case 17:
                    text1 = (TextView) findViewById(R.id.TableText17);
                    text1.append(userName);
                    break;
                case 18:
                    text1 = (TextView) findViewById(R.id.TableText18);
                    text1.append(userName);
                    break;
                case 19:
                    text1 = (TextView) findViewById(R.id.TableText19);
                    text1.append(userName);
                    break;
                case 20:
                    text1 = (TextView) findViewById(R.id.TableText20);
                    text1.append(userName);
                    break;
                case 21:
                    text1 = (TextView) findViewById(R.id.TableText21);
                    text1.append(userName);
                    break;
                case 22:
                    text1 = (TextView) findViewById(R.id.TableText22);
                    text1.append(userName);
                    break;
                case 23:
                    text1 = (TextView) findViewById(R.id.TableText23);
                    text1.append(userName);
                    break;
                case 24:
                    text1 = (TextView) findViewById(R.id.TableText24);
                    text1.append(userName);
                    break;
                case 25:
                    text1 = (TextView) findViewById(R.id.TableText25);
                    text1.append(userName);
                    break;
                case 26:
                    text1 = (TextView) findViewById(R.id.TableText26);
                    text1.append(userName);
                    break;
                case 27:
                    text1 = (TextView) findViewById(R.id.TableText27);
                    text1.append(userName);
                    break;
            }
        }
    }

    public void initTableAdd(String time){
        sess=sessDB.getSess();
        roomTitle=sessDB.getRoomTitle();

        application = ApplicationController.getInstance();
        application.buildNetworkService();
        networkService = ApplicationController.getInstance().getNetworkService();

        Call<ResponseBody> thumbnailcall = networkService.post_ttableAdd(sess, roomTitle, date, time);
        thumbnailcall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                if(response.isSuccess()){
                    initSetTextNull();
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

}