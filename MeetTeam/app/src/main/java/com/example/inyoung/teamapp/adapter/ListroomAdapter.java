package com.example.inyoung.teamapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.inyoung.teamapp.fragment.ListroomFragment1;
import com.example.inyoung.teamapp.fragment.ListroomFragment2;
import com.example.inyoung.teamapp.fragment.ListroomFragment3;

public class ListroomAdapter extends FragmentStatePagerAdapter {

    private int pagerNum;

    public ListroomAdapter(FragmentManager fm, int pagerNum) {

        super(fm);
        this.pagerNum = pagerNum;
    }

    public static Fragment getFragmentInstance(int pageNum){
        Fragment fragment = null;
        //pageNum은 ViewPagerActivity에서 tabs.getCount();
        switch (pageNum){
            case 0:
                fragment = new ListroomFragment1();
                break;
            case 1:
                fragment = new ListroomFragment2();
                break;
            case 2:
                fragment = new ListroomFragment3();
                break;

        }
        return fragment;
    }

    public ListroomAdapter(FragmentManager fm) {

        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        return getFragmentInstance(position);
    }

    @Override
    public int getCount() {
        return pagerNum;
    }
}
