package com.example.inyoung.teamapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    private Retrofit retrofit;
    private ArrayList<RoomDTO> chatList;
    private RoomDTO roomDTO;
    private RoomRecyclerViewAdapter roAdapter;
    private RecyclerView.LayoutManager manager;
    private Button btnAdd;
    private Button btnSearch;
    private AlertDialog.Builder dlg;
    private Toolbar toolbar;
    private ActionBarDrawerToggle mainTogle;
    private DrawerLayout drawer;
    private NavigationView nav_view;
    private NetworkService networkService;
    private ApplicationController application;
    public JSONArray jsonArray;
    public JSONObject jsonObject;
    private AlertDialog.Builder dig;
    SharedPreferences sessDB;
    public String cheifName;
    public EditText roomName,roomSubject;
    View view;
    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("태그","onCreate");
        LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.activity_listroom, null, false);
        Intent intent2 = getIntent();

        initButton(view);
        initRecyclerView(view);
        setContentView(view);
        initToolBar();
        boolean respone_add=intent2.getBooleanExtra("add",false);
        boolean respone_search=intent2.getBooleanExtra("search",false);
        if(respone_add=true){
            initRecyclerView(view);
        }
        else{
            Toast.makeText(application, "방 만들기 실패", Toast.LENGTH_SHORT).show();
        }
        if (respone_search = true) {
            initRecyclerView(view);
        }
        else{
            Toast.makeText(application, "방 찾기 실패", Toast.LENGTH_SHORT).show();
        }


    }

    private void initRecyclerView(final View view)  {
        application = ApplicationController.getInstance();
        application.buildNetworkService();
        networkService = ApplicationController.getInstance().getNetworkService();
        Log.i("태그","initRecyclerView");
        sessDB = getSharedPreferences("sessDB",MODE_PRIVATE);
        SharedPreferences.Editor editor = sessDB.edit();

        Log.i("Mytag","getsess"+sessDB.getString("session","error"));
        Call<ResponseBody> thumbnailCall = networkService.post_room(sessDB.getString("session","error"));
        thumbnailCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                if (response.isSuccess()){
                    try {
                        jsonArray= new JSONArray(response.body().string());
                        chatList= new ArrayList<>();
                        for(int i=0;i<jsonArray.length();i++){
                            jsonObject = jsonArray.getJSONObject(i);
                            chatList.add(new RoomDTO((String) jsonObject.get("name"), (String) jsonObject.get("chiefName"),i));
                        }
                        Log.i("mytag","responseBody:"+jsonArray.getJSONObject(0));
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
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),RoomAddActivity.class);
                startActivity(intent);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),RoomSearchActivity.class);
                startActivity(intent);
            }
        });
    }

    /*private void initDialogBox(){
        dlg = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_adduser,null, false);
        dlg.setView(view);
    }*/

    private void initToolBar() {
        toolbar =(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleMarginStart(30);
        toolbar.setTitle("CADI");
        //getSupportActionBar().setHomeAsUpIndicator(and);
        //getSupportActionBar().setHomeAsUpIndicator(android.R.drawable.menu_frame);
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
