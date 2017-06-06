package com.example.inyoung.teamapp;

import android.content.Context;
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
    //public ArrayList<String> listRoomName,listRoomCheif;
    private ArrayList<RoomDTO> chatList;
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
    int number =0;
    JSONArray jsonArray;
    JSONObject jsonObject;
    SharedPreferences sessDB;
    String Room;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("태그","onCreate");
        LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_listroom, null, false);
        initRecyclerView(view);
        initButton(view);
        setContentView(view);
        initToolBar();

    }

    private void initRecyclerView(View view) {
        application = ApplicationController.getInstance();
        application.buildNetworkService("52.78.39.253", 7530);
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
                        //roomDTO.setRoomName(response.body().string());
                        //roomDTO.setChiefName(response.body().string());
                        //listRoom = new ArrayList();
                        //listRoom.add(0,roomDTO);
                        //jsonObject = new JSONObject(response.body().string());
                        //Room=jsonObject.get("name").toString();
                        jsonArray= new JSONArray(response.body().string());

                        //listRoomName = new ArrayList<>();
                        //listRoomCheif = new ArrayList<>();
                        chatList = new ArrayList<>();




                        //listRoomCheif= new ArrayList<String>();
                        //listRoomName= new ArrayList<String>();

                       for (int i=0;i<jsonArray.length();i++){

                            jsonObject= jsonArray.getJSONObject(i);
                           chatList.add(new RoomDTO(jsonObject.getString("name"),jsonObject.getString("chiefName")));

                           //listRoomName.add(i,jsonObject.getString("name"));
                           //listRoomCheif.add(i,jsonObject.getString("chiefName"));
                           number ++;


                        }






                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });


        chatView = (RecyclerView) view.findViewById(R.id.chatView);
        chatView.setHasFixedSize(true);
        iniiLayoutManager(view);

        roAdapter = new RoomRecyclerViewAdapter(chatList,getApplicationContext(),number);
        chatView.setAdapter(roAdapter);


    }

    private void iniiLayoutManager(View view) {
        Log.i("태그","iniiLayoutManager");
        manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        chatView.setLayoutManager(manager);


    }

    private void initButton(View view) {
        Log.i("태그","initAddRoom");
        btnAdd = (Button)view.findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chatList.add(0, new RoomDTO());

                roAdapter.notifyItemInserted(0);
                roAdapter.notifyDataSetChanged();


            }
        });


    }

    private void initDialogBox(){
        dlg = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_searchbox,null, false);
        dlg.setView(view);

    }

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
