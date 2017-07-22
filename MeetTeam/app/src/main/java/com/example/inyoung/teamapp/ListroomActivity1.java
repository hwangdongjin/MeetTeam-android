package com.example.inyoung.teamapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.inyoung.teamapp.adapter.ListroomAdapter;

public class ListroomActivity1 extends AppCompatActivity {

    private ViewPager listroomViewPagerActivity;
    private TabLayout listroomtabs;
    private ListroomAdapter listroomAdapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listroom1);





        initViewPaging();
    }

    private void initViewPaging() {
        listroomViewPagerActivity = (ViewPager)findViewById(R.id.listroomViewPager);
        listroomtabs = (TabLayout)findViewById(R.id.listroomtabs);
        listroomtabs.addTab(listroomtabs.newTab().setIcon(R.drawable.teamlist));
        listroomtabs.addTab(listroomtabs.newTab().setIcon(R.drawable.plus));
        listroomtabs.addTab(listroomtabs.newTab().setIcon(R.drawable.settingicon));

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
}
