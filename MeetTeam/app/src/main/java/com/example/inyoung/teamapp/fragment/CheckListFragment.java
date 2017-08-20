package com.example.inyoung.teamapp.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.inyoung.teamapp.ApplicationController;
import com.example.inyoung.teamapp.R;
import com.example.inyoung.teamapp.RetroFit.NetworkService;
import com.example.inyoung.teamapp.RetroFit.SharedPreferenceUtil;
import com.example.inyoung.teamapp.adapter.CheckListRecyclerViewAdapter;
import com.example.inyoung.teamapp.dto.CheckListDTO;
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
 * Created by MYpc on 2017-04-10.
 */

public class CheckListFragment extends Fragment {

    View view;
    private RecyclerView chatView;
    private ArrayList<CheckListDTO> chatList;
    private CheckListDTO checkListDTO;
    private CheckListRecyclerViewAdapter roAdapter;
    SharedPreferenceUtil sessDB;
    private Button btnAdd;
    private Button btnSearch;
    private AlertDialog.Builder dlg;
    EditText edt_task;
    private NetworkService networkService;
    ApplicationController application;
    public JSONArray taskArray;
    public JSONObject taskObject;
    String taskName;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragement_check_list, container, false);
        sessDB = new SharedPreferenceUtil(getContext());
        initRecyclerView(view);
        initButton(view);
        return view;
    }

    public void initRecyclerView(final View view) {
        String roomTitle = sessDB.getRoomTitle();
        application = ApplicationController.getInstance();
        application.buildNetworkService();
        networkService = ApplicationController.getInstance().getNetworkService();
        Call<ResponseBody> call = networkService.post_taskShow(roomTitle);
        call.enqueue(new Callback<ResponseBody>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                if(response.isSuccess()){

                    try {

                        chatList= new ArrayList<>();
                        taskArray= new JSONArray(response.body().string());
                        for(int i=0;i<taskArray.length();i++){
                            taskObject= taskArray.getJSONObject(i);
                            chatList.add(new CheckListDTO((String) taskObject.get("taskName")));
                        }
                        chatView = (RecyclerView) view.findViewById(R.id.chatView11);
                        chatView.setHasFixedSize(false);
                        chatView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
                        roAdapter = new CheckListRecyclerViewAdapter(chatList, getContext());
                        roAdapter.notifyItemInserted(0);
                        roAdapter.notifyDataSetChanged();
                        chatView.setAdapter(roAdapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }

            }
            @Override
            public void onFailure(Throwable t) {

            }
        });



    }

    private void initButton(View view) {
        Log.i("태그","initAddRoom");
        btnAdd = (Button)view.findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg = new AlertDialog.Builder(v.getContext());
                LayoutInflater inflater = (LayoutInflater)v.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View view1 = inflater.inflate(R.layout.item_task_add,null, false);
                dlg.setView(view1);
                dlg.setPositiveButton("확인",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edt_task= (EditText) view1.findViewById(R.id.edt_task);
                        chatList.add(new CheckListDTO(edt_task.getText().toString()));
                        initTask(view1);
                    }
                });
                dlg.setNegativeButton("취소",null);
                dlg.show();
            }
        });

        btnSearch = (Button)view.findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg = new AlertDialog.Builder(v.getContext());
                LayoutInflater inflater = (LayoutInflater)v.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view1 = inflater.inflate(R.layout.item_room_enter,null, false);

                dlg.setView(view1);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dlg.setNegativeButton("취소", null);
                dlg.show();
            }
        });
    }

    public void initTask(final View view){
        String sess = sessDB.getSess();
        String roomTitle = sessDB.getRoomTitle();
        edt_task= (EditText) view.findViewById(R.id.edt_task);
        chatView= (RecyclerView) view.findViewById(R.id.chatView11);
        taskName =  edt_task.getText().toString();
        application = ApplicationController.getInstance();
        application.buildNetworkService();
        networkService = ApplicationController.getInstance().getNetworkService();
        Call<ResponseBody> call = networkService.post_taskAdd(sess,roomTitle,taskName);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {


                if(response.isSuccess()){

                    Toast.makeText(getContext(),"추가되었습니다",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });







    }
}
