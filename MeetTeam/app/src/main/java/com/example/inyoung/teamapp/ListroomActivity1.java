package com.example.inyoung.teamapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.inyoung.teamapp.RetroFit.FragmentReplaceAble;
import com.example.inyoung.teamapp.RetroFit.NetworkService;
import com.example.inyoung.teamapp.RetroFit.SharedPreferenceUtil;
import com.example.inyoung.teamapp.adapter.ListroomAdapter;
import com.example.inyoung.teamapp.fragment.ListroomFragment1;
import com.example.inyoung.teamapp.fragment.ListroomFragment2;

import org.json.JSONArray;
import org.json.JSONObject;

public class ListroomActivity1 extends AppCompatActivity implements FragmentReplaceAble{

    private ViewPager listroomViewPagerActivity;
    private TabLayout listroomtabs;
    private ListroomAdapter listroomAdapter;
    ListroomFragment1 listroomFragment1;
    ListroomFragment2 listroomFragment2;
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
        listroomtabs.addTab(listroomtabs.newTab().setText("리스트").setIcon(android.R.drawable.ic_dialog_info));
        listroomtabs.addTab(listroomtabs.newTab().setText("방찾기"));
        listroomtabs.addTab(listroomtabs.newTab().setText("설정"));
        listroomtabs.setSelectedTabIndicatorColor(Color.BLACK);


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
