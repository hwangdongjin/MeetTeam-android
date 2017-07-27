package com.example.inyoung.teamapp.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inyoung.teamapp.ApplicationController;
import com.example.inyoung.teamapp.R;
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

/**
 * Created by MYpc on 2017-04-05.
 */

public class TimeTableFragment extends Fragment {
    private AlertDialog.Builder dlg;
    EditText name,edt_tableName;
    TextView text1;
    TextView timeText;
    TextView placeText;
    View view;
    static int textId;
    SharedPreferenceUtil sessDB;
     Button DateSelectButton;
    TextView DateSelectView;
    String sess,roomTitle,date;
    static int year1,month1,dayOfMonth1;
    ArrayList<String> times,timeTablePos,timeTableUser;
    private NetworkService networkService;
    ApplicationController application;
    public Object jsonArray;
    public JSONObject jsonObject;
    String[] tablePos;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        view = inflater.inflate(R.layout.fragment_time_table, container, false);
        timeText=(TextView) view.findViewById(R.id.textViewTimeOk);
        placeText=(TextView)view.findViewById(R.id.textViewPlaceOk) ;
        DateSelectButton=(Button) view.findViewById(R.id.DateSelectButton);
        DateSelectView = (TextView) view.findViewById(R.id.DateSelectView);
        sessDB = new SharedPreferenceUtil(getContext());
        times= new ArrayList<>();
        view.findViewById(R.id.TableText0).setOnClickListener(onClick);
        view.findViewById(R.id.TableText1).setOnClickListener(onClick);
        view.findViewById(R.id.TableText2).setOnClickListener(onClick);
        view.findViewById(R.id.TableText3).setOnClickListener(onClick);
        view.findViewById(R.id.TableText4).setOnClickListener(onClick);
        view.findViewById(R.id.TableText5).setOnClickListener(onClick);
        view.findViewById(R.id.TableText6).setOnClickListener(onClick);
        view.findViewById(R.id.TableText7).setOnClickListener(onClick);
        view.findViewById(R.id.TableText8).setOnClickListener(onClick);
        view.findViewById(R.id.TableText9).setOnClickListener(onClick);
        view.findViewById(R.id.TableText10).setOnClickListener(onClick);
        view.findViewById(R.id.TableText11).setOnClickListener(onClick);
        view.findViewById(R.id.TableText12).setOnClickListener(onClick);
        view.findViewById(R.id.TableText13).setOnClickListener(onClick);
        view.findViewById(R.id.TableText14).setOnClickListener(onClick);
        view.findViewById(R.id.TableText15).setOnClickListener(onClick);
        view.findViewById(R.id.TableText16).setOnClickListener(onClick);
        view.findViewById(R.id.TableText17).setOnClickListener(onClick);
        view.findViewById(R.id.TableText18).setOnClickListener(onClick);
        view.findViewById(R.id.TableText19).setOnClickListener(onClick);
        view.findViewById(R.id.TableText20).setOnClickListener(onClick);
        view.findViewById(R.id.TableText21).setOnClickListener(onClick);
        view.findViewById(R.id.TableText22).setOnClickListener(onClick);
        view.findViewById(R.id.TableText23).setOnClickListener(onClick);
        view.findViewById(R.id.TableText24).setOnClickListener(onClick);
        view.findViewById(R.id.TableText25).setOnClickListener(onClick);
        view.findViewById(R.id.TableText26).setOnClickListener(onClick);
        view.findViewById(R.id.TableText27).setOnClickListener(onClick);



        final DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                DateSelectView.setText(year+"년"+month+"월"+dayOfMonth+"일");
                year1=year;
                month1=month;
                dayOfMonth1=dayOfMonth;
            }
        };

        DateSelectButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(getContext(),listener,2017,07,03);
                dialog.show();
            }



        });

        return  view;



    }

    public void initDialog(View view,int textId){

        dlg = new AlertDialog.Builder(view.getContext());
        text1= (TextView) view.findViewById(textId);
        LayoutInflater inflater = (LayoutInflater)view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = inflater.inflate(R.layout.item_time_selecter,null, false);
        name = (EditText) view1.findViewById(R.id.edt_tableName);
        dlg.setView(view1);
        dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                text1.append(name.getText().toString()+"\n");

            }
        });

        dlg.setNegativeButton("취소", null);
        dlg.show();

    }

    TextView.OnClickListener onClick =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.TableText0:

                    textId=R.id.TableText0;
                    text1= (TextView) v.findViewById(textId);
                    text1.append(sessDB.getName());
                    times.add("0");
                    initTableAdd(times);
                    break;
                case R.id.TableText1:
                    textId=R.id.TableText1;
                    text1= (TextView) v.findViewById(textId);
                    text1.append(sessDB.getName());
                    times.add("1");
                    initTableAdd(times);

                    break;
                case R.id.TableText2:
                    textId=R.id.TableText2;
                    text1= (TextView) v.findViewById(textId);
                    text1.append(sessDB.getName());
                    times.add("2");
                    initTableAdd(times);

                    break;
                case R.id.TableText3:
                    textId=R.id.TableText3;
                    text1= (TextView) v.findViewById(textId);
                    text1.append(sessDB.getName());
                    times.add("3");
                    initTableAdd(times);

                    break;

                case R.id.TableText4:
                    textId=R.id.TableText4;
                    text1= (TextView) v.findViewById(textId);
                    text1.append(sessDB.getName());
                    times.add("4");
                    initTableAdd(times);

                    break;
                case R.id.TableText5:
                    textId=R.id.TableText5;
                    text1= (TextView) v.findViewById(textId);
                    text1.append(sessDB.getName());
                    times.add("5");
                    initTableAdd(times);

                    break;
                case R.id.TableText6:
                    textId=R.id.TableText6;
                    text1= (TextView) v.findViewById(textId);
                    text1.append(sessDB.getName());
                    times.add("6");
                    initTableAdd(times);

                    break;

                case R.id.TableText7:
                    textId=R.id.TableText7;
                    text1= (TextView) v.findViewById(textId);
                    text1.append(sessDB.getName());
                    times.add("7");
                    initTableAdd(times);

                    break;
                case R.id.TableText8:
                    textId=R.id.TableText8;
                    text1= (TextView) v.findViewById(textId);
                    text1.append(sessDB.getName());
                    times.add("8");
                    initTableAdd(times);

                    break;
                case R.id.TableText9:
                    textId=R.id.TableText9;
                    text1= (TextView) v.findViewById(textId);
                    text1.append(sessDB.getName());
                    times.add("9");
                    initTableAdd(times);

                    break;
                case R.id.TableText10:
                    textId=R.id.TableText10;
                    text1= (TextView) v.findViewById(textId);
                    text1.append(sessDB.getName());
                    times.add("10");
                    initTableAdd(times);

                    break;
                case R.id.TableText11:
                    textId=R.id.TableText11;
                    text1= (TextView) v.findViewById(textId);
                    text1.append(sessDB.getName());
                    times.add("11");
                    initTableAdd(times);

                    break;
                case R.id.TableText12:
                    textId=R.id.TableText12;
                    text1= (TextView) v.findViewById(textId);
                    text1.append(sessDB.getName());
                    times.add("12");
                    initTableAdd(times);

                    break;
                case R.id.TableText13:
                    textId=R.id.TableText13;
                    text1= (TextView) v.findViewById(textId);
                    text1.append(sessDB.getName());
                    times.add("13");
                    initTableAdd(times);

                    break;
                case R.id.TableText14:
                    textId=R.id.TableText14;
                    text1= (TextView) v.findViewById(textId);
                    text1.append(sessDB.getName());
                    times.add("14");
                    initTableAdd(times);

                    break;
                case R.id.TableText15:
                    textId=R.id.TableText15;
                    text1= (TextView) v.findViewById(textId);
                    text1.append(sessDB.getName());
                    times.add("15");
                    initTableAdd(times);

                    break;
                case R.id.TableText16:
                    textId=R.id.TableText16;
                    text1= (TextView) v.findViewById(textId);
                    text1.append(sessDB.getName());
                    times.add("16");
                    initTableAdd(times);

                    break;
                case R.id.TableText17:
                    textId=R.id.TableText17;
                    text1= (TextView) v.findViewById(textId);
                    text1.append(sessDB.getName());
                    times.add("17");
                    initTableAdd(times);

                    break;
                case R.id.TableText18:
                    textId=R.id.TableText18;
                    text1= (TextView) v.findViewById(textId);
                    text1.append(sessDB.getName());
                    times.add("18");
                    initTableAdd(times);

                    break;
                case R.id.TableText19:
                    textId=R.id.TableText19;
                    text1= (TextView) v.findViewById(textId);
                    text1.append(sessDB.getName());
                    times.add("19");
                    initTableAdd(times);

                    break;
                case R.id.TableText20:
                    textId=R.id.TableText20;
                    text1= (TextView) v.findViewById(textId);
                    text1.append(sessDB.getName());
                    times.add("20");
                    initTableAdd(times);

                    break;
                case R.id.TableText21:
                    textId=R.id.TableText21;
                    text1= (TextView) v.findViewById(textId);
                    text1.append(sessDB.getName());
                    times.add("21");
                    initTableAdd(times);


                    break;
                case R.id.TableText22:
                    textId=R.id.TableText22;
                    text1= (TextView) v.findViewById(textId);
                    text1.append(sessDB.getName());
                    times.add("22");
                    initTableAdd(times);

                    break;
                case R.id.TableText23:
                    textId=R.id.TableText23;
                    text1= (TextView) v.findViewById(textId);
                    text1.append(sessDB.getName());
                    times.add("23");
                    initTableAdd(times);

                    break;
                case R.id.TableText24:
                    textId=R.id.TableText24;
                    text1= (TextView) v.findViewById(textId);
                    text1.append(sessDB.getName());
                    times.add("24");
                    initTableAdd(times);

                    break;
                case R.id.TableText25:
                    textId=R.id.TableText25;
                    text1= (TextView) v.findViewById(textId);
                    text1.append(sessDB.getName());
                    times.add("25");
                    initTableAdd(times);

                    break;
                case R.id.TableText26:
                    textId=R.id.TableText26;
                    text1= (TextView) v.findViewById(textId);
                    text1.append(sessDB.getName());
                    times.add("26");
                    initTableAdd(times);

                    break;
                case R.id.TableText27:
                    textId=R.id.TableText27;
                    text1= (TextView) v.findViewById(textId);
                    text1.append(sessDB.getName());
                    times.add("27");
                    initTableAdd(times);

                    break;


            }

        }
    };

    public void initTableAdd(ArrayList<String> times){
        sessDB= new SharedPreferenceUtil(getContext());
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
                    Toast.makeText(getContext(),"실패하였습니다",Toast.LENGTH_LONG).show();


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
                            for(int j=0;j<jsonArray1.length();j++) {
                                initSetText(Integer.parseInt(jsonArray1.get(j).toString()), userName);
                            }
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }


                else
                    Toast.makeText(getContext(),"실패하였습니다",Toast.LENGTH_LONG).show();


            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }

    public void initSetText(int arrTimes, String userName) throws JSONException {




            int number = arrTimes;
            Log.i("mytag","number22:"+number);
            switch (number) {

                case 0:
                    textId = R.id.TableText0;
                    text1 = (TextView) view.findViewById(textId);
                    text1.append(userName);

                    break;
                case 1:

                    textId = R.id.TableText1;
                    text1 = (TextView) view.findViewById(textId);
                    text1.append(userName);

                    break;


                case 2:
                    textId = R.id.TableText2;
                    text1 = (TextView) view.findViewById(textId);
                    text1.append(userName);

                    break;
                case 3:
                    textId = R.id.TableText3;
                    text1 = (TextView) view.findViewById(textId);
                    text1.append(userName);

                    break;

                case 4:
                    textId = R.id.TableText4;
                    text1 = (TextView) view.findViewById(textId);
                    text1.append(userName);

                    break;
                case 5:
                    textId = R.id.TableText5;
                    text1 = (TextView) view.findViewById(textId);
                    text1.append(userName);

                    break;
                case 6:
                    textId = R.id.TableText6;
                    text1 = (TextView) view.findViewById(textId);
                    text1.append(userName);

                    break;

                case 7:
                    textId = R.id.TableText7;
                    text1 = (TextView) view.findViewById(textId);
                    text1.append(userName);

                    break;
                case 8:
                    textId = R.id.TableText8;
                    text1 = (TextView) view.findViewById(textId);
                    text1.append(userName);

                    break;
                case 9:
                    textId = R.id.TableText9;
                    text1 = (TextView) view.findViewById(textId);
                    text1.append(userName);

                    break;
                case 10:
                    textId = R.id.TableText10;
                    text1 = (TextView) view.findViewById(textId);
                    text1.append(userName);

                    break;
                case 11:
                    textId = R.id.TableText11;
                    text1 = (TextView) view.findViewById(textId);
                    text1.append(userName);

                    break;
                case 12:
                    textId = R.id.TableText12;
                    text1 = (TextView) view.findViewById(textId);
                    text1.append(userName);

                    break;
                case 13:
                    textId = R.id.TableText13;
                    text1 = (TextView) view.findViewById(textId);
                    text1.append(userName);
                    break;
                case 14:
                    textId = R.id.TableText14;
                    text1 = (TextView) view.findViewById(textId);
                    text1.append(userName);
                    break;
                case 15:
                    textId = R.id.TableText15;
                    text1 = (TextView) view.findViewById(textId);
                    text1.append(userName);

                    break;
                case 16:
                    textId = R.id.TableText16;
                    text1 = (TextView) view.findViewById(textId);
                    text1.append(userName);

                    break;
                case 17:
                    textId = R.id.TableText17;
                    text1 = (TextView) view.findViewById(textId);
                    text1.append(userName);

                    break;
                case 18:
                    textId = R.id.TableText18;
                    text1 = (TextView) view.findViewById(textId);
                    text1.append(userName);

                    break;
                case 19:
                    textId = R.id.TableText19;
                    text1 = (TextView) view.findViewById(textId);
                    text1.append(userName);

                    break;
                case 20:
                    textId = R.id.TableText20;
                    text1 = (TextView) view.findViewById(textId);
                    text1.append(userName);
                    break;
                case 21:
                    textId = R.id.TableText21;
                    text1 = (TextView) view.findViewById(textId);
                    text1.append(userName);


                    break;
                case 22:
                    textId = R.id.TableText22;
                    text1 = (TextView) view.findViewById(textId);
                    text1.append(userName);

                    break;
                case 23:
                    textId = R.id.TableText23;
                    text1 = (TextView) view.findViewById(textId);
                    text1.append(userName);
                    break;
                case 24:
                    textId = R.id.TableText24;
                    text1 = (TextView) view.findViewById(textId);
                    text1.append(userName);

                    break;
                case 25:
                    textId = R.id.TableText25;
                    text1 = (TextView) view.findViewById(textId);
                    text1.append(userName);

                    break;
                case 26:
                    textId = R.id.TableText26;
                    text1 = (TextView) view.findViewById(textId);
                    text1.append(userName);

                    break;
                case 27:
                    textId = R.id.TableText27;
                    text1 = (TextView) view.findViewById(textId);
                    text1.append(userName);

                    break;






        }









    }








    }







