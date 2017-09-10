package com.example.inyoung.teamapp.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
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
import com.example.inyoung.teamapp.MapActivity;
import com.example.inyoung.teamapp.R;
import com.example.inyoung.teamapp.RetroFit.FragmentReplaceAble;
import com.example.inyoung.teamapp.RetroFit.NetworkService;
import com.example.inyoung.teamapp.RetroFit.SharedPreferenceUtil;
import com.example.inyoung.teamapp.TtableActivity;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by MYpc on 2017-08-11.
 */

public class AppFragment extends Fragment {
    View view;
    Button btn_table,btn_map,btn_store;
    Intent intent;
    TextView DateSelectView;
    static int year1,month1,dayOfMonth1;
    SharedPreferenceUtil sessDB;
    Button DateSelectButton;
    private NetworkService networkService;
    private ApplicationController application;

    String roomTitle, date;

    TextView tv_decTime, tv_decPlace;

    EditText edt_time, edt_place;
    String decTime, decPlace;

    private AlertDialog alter;

    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_option, container, false);
        sessDB = new SharedPreferenceUtil(getContext());
        DateSelectButton=(Button) view.findViewById(R.id.DateSelectButton);
        DateSelectView = (TextView) view.findViewById(R.id.DateSelectView);
        btn_table= (Button) view.findViewById(R.id.btn_table);

        tv_decTime = (TextView) view.findViewById(R.id.tv_decTime);
        tv_decPlace = (TextView) view.findViewById(R.id.tv_decPlace);

        btn_table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                     if("".equals(DateSelectView.getText().toString())){
                         Toast.makeText(getContext(),"날짜를 입력하세요",Toast.LENGTH_LONG).show();
                     }
                     else {
                         intent = new Intent(getContext(), TtableActivity.class);
                         intent.putExtra("year", year1);
                         intent.putExtra("month", month1);
                         intent.putExtra("day", dayOfMonth1);
                         startActivity(intent);
                     }

            }
        });
        final DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int fix_month=month+1;
                DateSelectView.setText(year+"년 "+fix_month+"월 "+dayOfMonth+"일");
                year1=year;
                month1=month+1;
                dayOfMonth1=dayOfMonth;

                showApp();
            }
        };

        DateSelectButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(getContext(),listener,2017,07,03);
                dialog.show();
            }



        });

        btn_map = (Button) view.findViewById(R.id.btn_map);
        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), MapActivity.class);
                intent.putExtra("year", year1);
                intent.putExtra("month", month1);
                intent.putExtra("day", dayOfMonth1);
                startActivity(intent);
            }
        });

        tv_decTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAddDlg();
            }
        });

        tv_decPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAddDlg();
            }
        });
        return view;
    }

    private void showApp(){
        date = String.valueOf(year1)+"-"+String.valueOf(month1)+"-"+String.valueOf(dayOfMonth1);
        roomTitle= sessDB.getRoomTitle();

        application = ApplicationController.getInstance();
        application.buildNetworkService();
        networkService = ApplicationController.getInstance().getNetworkService();
        Call<ResponseBody> thumnailcall = networkService.post_appShow(roomTitle,date);

        thumnailcall.enqueue(new Callback<ResponseBody>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                if(response.isSuccess()){
                    try {
                        JSONObject jsonObject= new JSONObject(response.body().string());
                        String decTime = jsonObject.get("decTime").toString();
                        String decPlace = jsonObject.get("decPlace").toString();

                        tv_decTime.setText(decTime);
                        tv_decPlace.setText(decPlace);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    Toast.makeText(getContext(),"시간과 장소를 정해주세요",Toast.LENGTH_LONG).show();
                    tv_decTime.setText("");
                    tv_decPlace.setText("");
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    private void addAddDlg(){
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_app_add, null, false);
        alter= new AlertDialog.Builder(getContext()).create();
        alter.setView(view);
        alter.show();

        edt_time = (EditText)view.findViewById(R.id.edt_time);
        edt_place = (EditText)view.findViewById(R.id.edt_place);

        Button edt_ok= (Button) view.findViewById(R.id.btn_ok);
        Button edt_cancel= (Button) view.findViewById(R.id.btn_cancel);
        edt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(edt_time.getText().toString()) || "".equals(edt_place.getText().toString())) {
                    Toast.makeText(getContext(), "입력칸을 모두 입력하세요", Toast.LENGTH_LONG).show();
                } else {
                    decPlace = edt_place.getText().toString();
                    decTime = edt_time.getText().toString();

                    sessDB = new SharedPreferenceUtil(getContext());
                    String sess = sessDB.getSess();
                    networkService = ApplicationController.getInstance().getNetworkService();

                    Call<ResponseBody> thum = networkService.post_appAdd(sess, roomTitle, date, decTime, decPlace);
                    thum.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                            if (response.isSuccess()) {
                                Toast.makeText(getContext(),"성공하였습니다",Toast.LENGTH_LONG).show();
                                alter.dismiss();
                                showApp();
                            }
                        }
                        @Override
                        public void onFailure(Throwable t) {

                        }
                    });
                }
            }
        });
        edt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alter.cancel();
            }
        });
    }

}
