package com.example.inyoung.teamapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.inyoung.teamapp.fragment.CheckListFragment;
import com.example.inyoung.teamapp.fragment.ConfigureFragment;
import com.example.inyoung.teamapp.fragment.MemberlistFragment;
import com.example.inyoung.teamapp.fragment.TimeTableFragment;

/**
 * Created by inyoung on 2017-02-22.
 */

public class MainPagerAdapter extends FragmentStatePagerAdapter {
    private int pagerNum;

    public MainPagerAdapter(FragmentManager fm, int pagerNum) {
        super(fm);
        this.pagerNum = pagerNum;
    }

    public static Fragment getFragmentInstance(int pageNum){
        Fragment fragment = null;
        //pageNum은 ViewPagerActivity에서 tabs.getCount();
        switch (pageNum){
            case 0:
                fragment = MemberlistFragment.newInstance("first", "Memberlist");
                break;
            case 1:
                fragment = new TimeTableFragment();

                break;
            case 2:
                fragment = new CheckListFragment();
                break;
            case 3:
                fragment = ConfigureFragment.newInstance("forth", "Configure");

                break;


        }
        return fragment;
    }

    public MainPagerAdapter(FragmentManager fm) {

        super(fm);
    }

    //포지션에따라서 프래그먼트가 바뀌도록
    @Override
    public Fragment getItem(int position) {
        //return null;//profileList get(position)
        return getFragmentInstance(position);
    }

    @Override
    public int getCount() {
        return pagerNum;
    }
}
