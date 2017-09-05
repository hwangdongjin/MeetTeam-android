package com.example.inyoung.teamapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.inyoung.teamapp.ApplicationController;
import com.example.inyoung.teamapp.R;
import com.example.inyoung.teamapp.RetroFit.FragmentReplaceAble;
import com.example.inyoung.teamapp.RetroFit.NetworkService;
import com.example.inyoung.teamapp.RetroFit.SharedPreferenceUtil;
import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class RoomAddFragment extends Fragment{
    View view;
    private Button btnAdd;
    private Button btnSearch;
    //private AlertDialog.Builder dlg;
    private AlertDialog alter;
    String title, subject,session,search_title;
    EditText edt_title, edt_subject, edt_title2;
    Button edt_ok,edt_cancel;
    SharedPreferenceUtil sessDB;
    private NetworkService networkService;
    private ApplicationController application;
    public RoomAddFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        view = inflater.inflate(R.layout.activity_listroom_fragment2, container, false);
        initButton(view);
        return view;
    }
    private void initButton(final View view) {
        Log.i("태그","initAddRoom");
        btnAdd = (Button)view.findViewById(R.id.btn_add);
        btnSearch= (Button) view.findViewById(R.id.btn_search);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roomAddDlg();
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roomEnterDlg();
            }
        });

    }

    private void roomAddDlg(){
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_room_add, null, false);
        alter= new AlertDialog.Builder(getContext()).create();
        alter.setView(view);
        alter.show();
        edt_title = (EditText)view.findViewById(R.id.edt_title);
        edt_subject = (EditText)view.findViewById(R.id.edt_subject);
        edt_ok= (Button) view.findViewById(R.id.btn_ok);
        edt_cancel= (Button) view.findViewById(R.id.btn_cancel);
        edt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(edt_title.getText().toString())) {
                    Toast.makeText(getContext(), "방이름을 입력하세요", Toast.LENGTH_LONG).show();
                } else {
                    title = edt_title.getText().toString();
                }
                if ("".equals(edt_subject.getText().toString())) {
                    Toast.makeText(getContext(), "과목을 입력하세요", Toast.LENGTH_LONG).show();
                } else {
                    subject = edt_subject.getText().toString();
                }
                if (title != null && subject != null){
                    sessDB = new SharedPreferenceUtil(getContext());
                session = sessDB.getSess();
                networkService = ApplicationController.getInstance().getNetworkService();
                Call<ResponseBody> thum = networkService.post_roomAdd(session, title, subject);
                thum.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                        if (response.isSuccess()) {
                            Toast.makeText(getContext(),"성공하였습니다",Toast.LENGTH_LONG).show();
                            ((FragmentReplaceAble) getActivity()).replaceFragment(2);
                            alter.cancel();
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

   private void roomEnterDlg(){
       sessDB= new SharedPreferenceUtil(getContext());
       session=sessDB.getSess();
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_room_enter, null, false);
        alter= new AlertDialog.Builder(getContext()).create();
        alter.setView(view);
        alter.show();
        edt_title2 = (EditText)view.findViewById(R.id.edt_title2);
        edt_ok= (Button) view.findViewById(R.id.btn_ok);
        edt_cancel= (Button) view.findViewById(R.id.btn_cancel2);
        edt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(edt_title2.getText().toString())) {
                    Toast.makeText(getContext(), "방이름을 입력하세요", Toast.LENGTH_LONG).show();
                } else {
                    title = edt_title2.getText().toString();
                }
                if (title != null){
                    application = ApplicationController.getInstance();
                    application.buildNetworkService();
                    networkService = ApplicationController.getInstance().getNetworkService();
                    Call<ResponseBody> thumnailcall = networkService.post_roomAddUser(session, search_title);
                    thumnailcall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                        if (response.isSuccess()) {
                            Toast.makeText(getContext(),"성공하였습니다",Toast.LENGTH_LONG).show();
                            ((FragmentReplaceAble) getActivity()).replaceFragment(2);
                            alter.cancel();
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
