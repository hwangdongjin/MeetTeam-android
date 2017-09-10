package com.example.inyoung.teamapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.inyoung.teamapp.RetroFit.FragmentReplaceAble;
import com.example.inyoung.teamapp.RetroFit.NetworkService;
import com.example.inyoung.teamapp.RetroFit.SharedPreferenceUtil;
import com.example.inyoung.teamapp.adapter.ListroomAdapter;
import com.example.inyoung.teamapp.fragment.RoomAddFragment;
import com.example.inyoung.teamapp.fragment.RoomListFragment;

import org.json.JSONArray;
import org.json.JSONObject;

public class ListroomActivity extends AppCompatActivity implements FragmentReplaceAble{
    private ViewPager listroomViewPagerActivity;
    private TabLayout listroomtabs;
    private ListroomAdapter listroomAdapter;
    RoomListFragment listroomFragment1;
    RoomAddFragment listroomFragment2;
    static int getTab;
    SharedPreferenceUtil sessDB;
    private NetworkService networkService;
    private ApplicationController application;
    public JSONArray jsonArray;
    public JSONObject jsonObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listroom1);
        initViewPaging();

    }

    private void initViewPaging() {

        listroomViewPagerActivity = (ViewPager)findViewById(R.id.listroomViewPager);
        getTab=R.id.listroomViewPager;
        listroomtabs = (TabLayout)findViewById(R.id.listroomtabs);
        listroomtabs.addTab(listroomtabs.newTab().setIcon(R.drawable.teamlist));
        listroomtabs.addTab(listroomtabs.newTab().setIcon(R.drawable.plus));
        listroomtabs.addTab(listroomtabs.newTab().setIcon(R.drawable.settingicon));
        listroomtabs.setSelectedTabIndicatorHeight(0);
        listroomViewPagerActivity.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(listroomtabs));
        listroomtabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                listroomViewPagerActivity.setCurrentItem(tab.getPosition());

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

        listroomAdapter = new ListroomAdapter(getSupportFragmentManager(), listroomtabs.getTabCount());
        listroomViewPagerActivity.setAdapter(listroomAdapter);
    }
    /*public void setDefaultFragment(){

        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.listroomViewPager,listroomFragment1);
        transaction.commit();

    }*/
    @Override
    public void replaceFragment(int fragmentId){

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();



        if(fragmentId==1){



        }
        if(fragmentId==2){

            listroomViewPagerActivity.setCurrentItem(1);
            listroomViewPagerActivity.setCurrentItem(0);



        }

        transaction.commit();

    }
}
