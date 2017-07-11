package com.example.inyoung.teamapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.inyoung.teamapp.adapter.MainPagerAdapter;
import com.example.inyoung.teamapp.dto.UserListDTO;

import java.io.Serializable;
import java.util.ArrayList;

public class ViewPagerActivity extends AppCompatActivity implements Serializable{

    private ViewPager mainViewPagerActivity;
    private TabLayout tabs;
    private MainPagerAdapter mainPagerAdapter;
    ArrayList<UserListDTO> userDTO;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        Intent intent = getIntent();
        userDTO= (ArrayList<UserListDTO>) intent.getSerializableExtra("test");

        //String test = userList.get(0).getName();
       Log.i("mytag","test"+userDTO.get(0).getName());

        initViewPaging();
        initToolBar();
    }

    private void initToolBar() {
        toolbar =(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("CADI");
        toolbar.setTitleMarginStart(30);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.op_bookmark:
                Toast.makeText(ViewPagerActivity.this,"즐겨찾기", Toast.LENGTH_SHORT).show();
                break;
            case R.id.op_time:
                Toast.makeText(ViewPagerActivity.this,"최근", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu_items, menu);
        return super.onCreateOptionsMenu(menu);
    }

//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        return super.onPrepareOptionsMenu(menu);
//    }

    private void initViewPaging(){
        mainViewPagerActivity = (ViewPager)findViewById(R.id.mainViewPager);
        tabs = (TabLayout)findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("그룹").setIcon(R.drawable.memberiicon_black));
        tabs.addTab(tabs.newTab().setText("모임").setIcon(R.drawable.meetingicon_black));
        tabs.addTab(tabs.newTab().setText("진행률").setIcon(R.drawable.checklisticon_black));
        tabs.addTab(tabs.newTab().setText("설정").setIcon(R.drawable.settingicon_black));
        
        //tabs.setSelectedTabIndicatorColor(Color.BLACK);

        mainViewPagerActivity.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mainViewPagerActivity.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        initAdapter();
    }

    private void initAdapter() {
        mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), tabs.getTabCount(),userDTO,Integer.valueOf(userDTO.size()));
        mainViewPagerActivity.setAdapter(mainPagerAdapter);
    }
}
