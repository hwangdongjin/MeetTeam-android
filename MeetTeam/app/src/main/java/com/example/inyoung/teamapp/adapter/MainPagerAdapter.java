package com.example.inyoung.teamapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.inyoung.teamapp.dto.UserListDTO;
import com.example.inyoung.teamapp.fragment.AppFragment;
import com.example.inyoung.teamapp.fragment.ChattingFragment;
import com.example.inyoung.teamapp.fragment.MemberlistFragment;
import com.example.inyoung.teamapp.fragment.TaskFragment;

import java.util.ArrayList;

/**
 * Created by inyoung on 2017-02-22.
 */

public class MainPagerAdapter extends FragmentStatePagerAdapter {
    private static ArrayList<UserListDTO> userList = null;
    private static int Num = 0;
    private int pagerNum;

    public MainPagerAdapter(FragmentManager fm, int pagerNum, ArrayList<UserListDTO> userList,int Num) {
        super(fm);
        this.pagerNum = pagerNum;
        this.userList=userList;
        this.Num=Num;
    }

    public static Fragment getFragmentInstance(int pageNum){
        Fragment fragment = null;
        //pageNum은 ViewPagerActivity에서 tabs.getCount();
        switch (pageNum){
            case 0:
                fragment = MemberlistFragment.newInstance(userList,Num);
                break;
            case 1:
                fragment = new ChattingFragment();
                break;
            case 2:
                fragment = new TaskFragment();
                break;
            case 3:
                fragment = new AppFragment();
                break;
        }
        return fragment;
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
