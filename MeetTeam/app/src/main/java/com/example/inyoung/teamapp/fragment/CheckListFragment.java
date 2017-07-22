package com.example.inyoung.teamapp.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.inyoung.teamapp.R;
import com.example.inyoung.teamapp.adapter.CheckListRecyclerViewAdapter;
import com.example.inyoung.teamapp.dto.CheckListDTO;

import java.util.ArrayList;

/**
 * Created by MYpc on 2017-04-10.
 */

public class CheckListFragment extends Fragment {

    View view;
    private RecyclerView chatView;
    private ArrayList<CheckListDTO> chatList;
    private CheckListDTO checkListDTO;
    private CheckListRecyclerViewAdapter roAdapter;
    private Button btnAdd;
    private Button btnSearch;
    private AlertDialog.Builder dlg;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        view = inflater.inflate(R.layout.fragement_check_list, container, false);
        initRecyclerView(view);
        initButton(view);
        return view;
    }
    private void initRecyclerView(View view) {
        Log.i("태그","initRecyclerView");
        chatView = (RecyclerView) view.findViewById(R.id.chatView);
        chatView.setHasFixedSize(true);
        chatList= new ArrayList<>();
        chatList.add(new CheckListDTO("야호"));
        roAdapter = new CheckListRecyclerViewAdapter(chatList, getContext());
        chatView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        chatView.setAdapter(roAdapter);

    }

    private void initButton(View view) {
        Log.i("태그","initAddRoom");
        btnAdd = (Button)view.findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chatList.add(0,new CheckListDTO("CheckList"));
                roAdapter.notifyItemInserted(0);
                roAdapter.notifyDataSetChanged();


            }
        });

        btnSearch = (Button)view.findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg = new AlertDialog.Builder(v.getContext());
                LayoutInflater inflater = (LayoutInflater)v.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view1 = inflater.inflate(R.layout.item_room_enter,null, false);

                dlg.setView(view1);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dlg.setNegativeButton("취소", null);
                dlg.show();
            }
        });
    }
}
