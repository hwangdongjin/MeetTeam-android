package com.example.inyoung.teamapp.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.inyoung.teamapp.ApplicationController;
import com.example.inyoung.teamapp.R;
import com.example.inyoung.teamapp.RetroFit.NetworkService;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONArray;
import org.json.JSONObject;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ListroomFragment2 extends Fragment {
    View view;


    private Button btnAdd;
    private Button btnSearch;
    private AlertDialog.Builder dlg;
    String title, subject,session;
    Intent intent;
    EditText edt_title, edt_subject;

    SharedPreferences sessDB;
    private NetworkService networkService;
    private ApplicationController application;
    public JSONArray jsonArray;
    public JSONObject jsonObject;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){


        view = inflater.inflate(R.layout.activity_listroom_fragment2, container, false);


        initButton(view);

        return view;
    }


    private void initButton(final View view) {
        Log.i("태그","initAddRoom");
        btnAdd = (Button)view.findViewById(R.id.btn_add);
        //btnSearch=(Button)view.findViewById(R.id.btn_search);
        //sessDB = this.getActivity().getSharedPreferences("sessDB", Context.MODE_PRIVATE);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roomAddDlg();

                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        title = edt_title.getText().toString();
                        subject = edt_subject.getText().toString();
                       sessDB=getActivity().getSharedPreferences("sessDB",Context.MODE_PRIVATE);
                        session=sessDB.getString("session","error");
                        Log.i("mtage","titi:"+session);
                        networkService=ApplicationController.getInstance().getNetworkService();
                        Call<ResponseBody> thum= networkService.post_roomAdd(session,title,subject);
                        thum.enqueue(new Callback<ResponseBody>() {
                                @Override
                            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                                if(response.isSuccess()){
                                    /*intent = new Intent();
                                    intent.setClass(view.getContext(),ListroomFragment1.class);
                                    intent.putExtra("add",true);
                                    startActivity(intent);*/

                                    Fragment fragment = new ListroomFragment1();
                                    FragmentManager fragmentManager = getFragmentManager();
                                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                    fragmentTransaction.attach(fragment);
                                    //fragmentTransaction.replace(R.id.listroomViewPager,fragment);

                                    Bundle args = new Bundle();
                                    args.putBoolean("add",true);
                                    //args.putString("true","add");
                                    //args.putString("add","true");


                                    fragment.setArguments(args);

                                    fragmentTransaction.commit();

                                }
                            }

                            @Override
                            public void onFailure(Throwable t) {
                            }
                        });
                    }
                });
                dlg.setNegativeButton("취소", null);
                dlg.show();
            }
        });

        /*btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roomEnterDlg();
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        title = edt_title.getText().toString();
                        //sessDB = this.getActivity().getSharedPreferences("sessDB", Context.MODE_PRIVATE);

                        Call<ResponseBody> thum= networkService.post_roomAddUser(sessDB.getString("session","error"),title);
                        thum.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                                if(response.isSuccess()){
                                    intent = new Intent();
                                    intent.setClass(getContext(),ListroomActivity.class);
                                    intent.putExtra("search",true);
                                    startActivity(intent);
                                }
                            }
                            @Override
                            public void onFailure(Throwable t) {
                            }
                        });
                    }
                });
                dlg.setNegativeButton("취소", null);
                dlg.show();
            }
        });*/
    }

    private void roomAddDlg(){
        dlg = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_room_add, null, false);
        edt_title = (EditText)view.findViewById(R.id.edt_title);
        edt_subject = (EditText)view.findViewById(R.id.edt_subject);
        dlg.setView(view);
    }

    private void roomEnterDlg(){
        dlg = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_room_enter, null, false);
        edt_title = (EditText)view.findViewById(R.id.edt_title);
        dlg.setView(view);
    }


}
