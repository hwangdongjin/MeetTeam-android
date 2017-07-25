package com.example.inyoung.teamapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class ListroomActivity extends AppCompatActivity {

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
    View view;
    String title, subject;

    Intent intent;

    SharedPreferenceUtil sessDB;
    private NetworkService networkService;
    private ApplicationController application;
    public JSONArray jsonArray;
    public JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        application = ApplicationController.getInstance();
        application.buildNetworkService();
        networkService = ApplicationController.getInstance().getNetworkService();

        LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.activity_listroom, null, false);
        //Intent intent2 = getIntent();

        initButton(view);
        initRecyclerView(view);
        setContentView(view);
        initToolBar();

    }

    private void initRecyclerView(final View view)  {


        sessDB = new SharedPreferenceUtil(getApplicationContext());

        Call<ResponseBody> thumbnailCall = networkService.post_roomList(sessDB.getSess());
        thumbnailCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                if (response.isSuccess()){
                    try {

                        jsonArray= new JSONArray(response.body().string());
                        chatList= new ArrayList<>();

                        for(int i=0; i<jsonArray.length(); i++){
                            jsonObject = jsonArray.getJSONObject(i);
                            chatList.add(new RoomDTO((String) jsonObject.get("title"), (String) jsonObject.get("chiefName"),i));
                        }

                        chatView = (RecyclerView) view.findViewById(R.id.chatView);
                        chatView.setHasFixedSize(true);
                        iniiLayoutManager(view);

                        roAdapter = new RoomRecyclerViewAdapter(chatList,getApplicationContext());
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
        manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        chatView.setLayoutManager(manager);
    }

    private void initButton(View view) {
        Log.i("태그","initAddRoom");
        btnAdd = (Button)view.findViewById(R.id.btn_add);
        btnSearch=(Button)view.findViewById(R.id.btn_search);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                roomAddDlg();

                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        title = edt_title.getText().toString();
                        subject = edt_subject.getText().toString();
                        sessDB= new SharedPreferenceUtil(getApplicationContext());

                        Call<ResponseBody> thum= networkService.post_roomAdd(sessDB.getSess(),title, subject);
                        thum.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                                if(response.isSuccess()){

                                    intent = new Intent();
                                    intent.setClass(getApplicationContext(),ListroomActivity.class);
                                    intent.putExtra("add",true);
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
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roomEnterDlg();
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        title = edt_title.getText().toString();
                        sessDB= new SharedPreferenceUtil(getApplicationContext());

                        Call<ResponseBody> thum= networkService.post_roomAddUser(sessDB.getSess(),title);
                        thum.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                                if(response.isSuccess()){
                                    intent = new Intent();
                                    intent.setClass(getApplicationContext(),ListroomActivity.class);
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
        });
    }

    private void roomAddDlg(){
        dlg = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_room_add, null, false);
        edt_title = (EditText)view.findViewById(R.id.edt_title);
        edt_subject = (EditText)view.findViewById(R.id.edt_subject);
        dlg.setView(view);
    }

    private void roomEnterDlg(){
        dlg = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_room_enter, null, false);
        edt_title = (EditText)view.findViewById(R.id.edt_title);
        dlg.setView(view);
    }

    private void initToolBar() {
        toolbar =(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleMarginStart(30);
        toolbar.setTitle("MeetTeam");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initTogggleBtn();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mainTogle.onOptionsItemSelected(item))
            return true;
        switch (item.getItemId()){
            case R.id.op_bookmark:
                Toast.makeText(ListroomActivity.this,"즐겨찾기", Toast.LENGTH_SHORT).show();
                break;
            case R.id.op_time:
                Toast.makeText(ListroomActivity.this,"최근", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu_items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    private void initTogggleBtn() {
        drawer =(DrawerLayout)findViewById(R.id.drawer);
        nav_view =(NavigationView)findViewById(R.id.nav_view);
        mainTogle = new ActionBarDrawerToggle(ListroomActivity.this, drawer, R.string.open_drawer, R.string.close_drawer);
        drawer.addDrawerListener(mainTogle);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawer.closeDrawer(Gravity.LEFT);
                switch (item.getItemId()){
                    case R.id.nav_map:
                        Toast.makeText(ListroomActivity.this,"지도", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_calendar:
                        Toast.makeText(ListroomActivity.this,"달력", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_notice:
                        Toast.makeText(ListroomActivity.this,"공지", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onPostCreate( Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mainTogle.syncState();
    }
}
