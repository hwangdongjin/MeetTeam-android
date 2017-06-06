package com.example.inyoung.teamapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inyoung.teamapp.R;

/**
 * Created by MYpc on 2017-04-10.
 */

public class NaverFragment extends NMapFragment {
    private static final    String NAVER_CLIENT_ID ="AJK1tte0Z6e5hN9tClDD";
    public NaverFragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        return inflater.inflate(R.layout.fragment_naver,container,false);
    }
}
