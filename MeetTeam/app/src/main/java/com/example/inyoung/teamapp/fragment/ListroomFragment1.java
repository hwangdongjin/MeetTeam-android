package com.example.inyoung.teamapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.inyoung.teamapp.ApplicationController;
import com.example.inyoung.teamapp.R;
import com.example.inyoung.teamapp.RetroFit.NetworkService;
import com.example.inyoung.teamapp.RetroFit.SharedPreferenceUtil;
import com.example.inyoung.teamapp.adapter.RoomRecyclerViewAdapter;
import com.example.inyoung.teamapp.dto.RoomDTO;
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

public class ListroomFragment1 extends Fragment {
    View view;

    private RecyclerView chatView;
    private ArrayList<RoomDTO> chatList;
    private RoomRecyclerViewAdapter roAdapter;
    private RecyclerView.LayoutManager manager;



    private Button btnAdd, btnSearch;
    private Toolbar toolbar;
    private ActionBarDrawerToggle mainTogle;
    private DrawerLayout drawer;
    private NavigationView nav_view;
    private AlertDialog.Builder dlg;
    EditText edt_title, edt_subject;
    String title, subject;

    Intent intent;


    SharedPreferenceUtil sessDB;
    private NetworkService networkService;
    private ApplicationController application;
    public JSONArray jsonArray;
    public JSONObject jsonObject;

    public ListroomFragment1(){



    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        view = inflater.inflate(R.layout.activity_listroom_fragment1, container, false);
        application = ApplicationController.getInstance();
        application.buildNetworkService();
        networkService = ApplicationController.getInstance().getNetworkService();
        initRecyclerView(view);
        return view;
    }

    private void initRecyclerView(final View view) {

        sessDB= new SharedPreferenceUtil(getContext());
        Call<ResponseBody> thumbnailCall = networkService.post_roomList(sessDB.getSess());
        thumbnailCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                if (response.isSuccess()){
                    try {
                        jsonArray = new JSONArray(response.body().string());
                        chatList = new ArrayList<>();

                        for(int i=0; i<jsonArray.length(); i++){
                            jsonObject = jsonArray.getJSONObject(i);
                            chatList.add(new RoomDTO((String) jsonObject.get("title"), (String) jsonObject.get("chiefName"),i));
                        }

                        chatView = (RecyclerView) view.findViewById(R.id.chatView);
                        chatView.setHasFixedSize(true);
                        iniiLayoutManager(view);

                        roAdapter = new RoomRecyclerViewAdapter(chatList,view.getContext());
                        chatView.setAdapter(roAdapter);
                    }

                    catch (IOException e) {
                        e.printStackTrace();
                    }

                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
            @Override
            public void onFailure(Throwable t) {
            }

        });

    }

    private void iniiLayoutManager(View view) {
        Log.i("태그","iniiLayoutManager");
        manager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        chatView.setLayoutManager(manager);
    }


}
