package com.example.inyoung.teamapp.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.example.inyoung.teamapp.adapter.CheckListViewAdapter;
import com.example.inyoung.teamapp.dto.CheckAddDTO;
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

public class TaskFragment extends Fragment {

    View view;
    private RecyclerView chatView;
    static ArrayList<CheckListDTO> chatList;
    static ArrayList<CheckAddDTO> checkAddList, checkShowList;
    private CheckListRecyclerViewAdapter roAdapter;
    SharedPreferenceUtil sessDB;
    private Button btnAdd;
    private AlertDialog dlg;
    EditText edt_task;
    private NetworkService networkService;
    ApplicationController application;
    public JSONArray taskArray;
    public JSONObject taskObject;
    String taskName;
    Button btn_ok,btn_cancel;
    public JSONObject jsonObject;
    public JSONArray jsonArray;
    public JSONArray clistArray;
    public JSONObject clistObject;
    CheckListViewAdapter checkListViewAdapter;


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
                        taskArray= new JSONArray(response.body().string());
                        chatList= new ArrayList<>();
                        for(int i=0;i<taskArray.length();i++){
                            taskObject= taskArray.getJSONObject(i);
                            chatList.add(new CheckListDTO((String) taskObject.get("taskName")));
                        }
                        chatView = (RecyclerView) view.findViewById(R.id.chatView11);
                        chatView.setHasFixedSize(false);
                        chatView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
                        roAdapter = new CheckListRecyclerViewAdapter(chatList,getContext());
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
        btnAdd = (Button)view.findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View view = inflater.inflate(R.layout.item_task_add, null, false);
                dlg= new AlertDialog.Builder(getContext()).create();
                dlg.setView(view);
                dlg.show();
                edt_task= (EditText) view.findViewById(R.id.edt_title2);
                btn_ok= (Button) view.findViewById(R.id.btn_ok);
                btn_cancel= (Button) view.findViewById(R.id.btn_cancel);
                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if("".equals(edt_task.getText().toString())){
                            Toast.makeText(getContext(),"체크리스트를 입력하세요",Toast.LENGTH_LONG).show();
                        }
                        else {
                            taskName=edt_task.getText().toString();
                        }
                        if(taskName!=null) {
                            chatList.add(new CheckListDTO(edt_task.getText().toString()));
                            initTask(view,taskName);
                            dlg.cancel();
                        }
                    }
                });
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dlg.cancel();
                    }
                });
            }
        });
    }
    public void initTask(final View view,String taskName){
        String sess = sessDB.getSess();
        String roomTitle = sessDB.getRoomTitle();
        chatView= (RecyclerView) view.findViewById(R.id.chatView11);
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
