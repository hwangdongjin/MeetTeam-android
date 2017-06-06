package com.example.inyoung.teamapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.inyoung.teamapp.R;
import com.example.inyoung.teamapp.RetroFit.SharedPreferenceUtil;
import com.example.inyoung.teamapp.ViewPagerActivity;
import com.example.inyoung.teamapp.dto.RoomDTO;

import java.util.ArrayList;

/**
 * Created by inyoung on 2017-02-02.
 */

public class RoomRecyclerViewAdapter extends RecyclerView.Adapter<RoomRecyclerViewAdapter.ViewHolder> implements View.OnClickListener {

    private ArrayList<RoomDTO> roomList;
    //private ArrayList<String> roomListName,roomListCheif;
    private Context context;
    private int number;
    SharedPreferenceUtil roomDB;



    public RoomRecyclerViewAdapter(ArrayList<RoomDTO> roomList,Context context,int number) {
        this.roomList = roomList;
        this.context = context;
        this.number= number;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView roomName;
        private TextView chiefName;
        private Button btnAdd;



        public ViewHolder(View itemView) {
            super(itemView);
            roomName = (TextView) itemView.findViewById(R.id.roomName);
            chiefName = (TextView) itemView.findViewById(R.id.managerName);
            btnAdd = (Button) itemView.findViewById(R.id.btn_enter);

        }
    }

    @Override
    public RoomRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room, parent,false);
        ViewHolder vholder = new ViewHolder(itemView);
        return vholder;
    }

    @Override
    public void onBindViewHolder(RoomRecyclerViewAdapter.ViewHolder holder, final int position) {


               //holder.roomName.setText(roomListName.get(0).toString());
               //holder.chiefName.setText(roomListCheif.get(0).toString());
        //Log.i("Mytag","dkdkdk:"+roomListName.get(0).toString());
                  holder.roomName.setText(roomList.get(0).getRoom_Name());


                 //holder.roomName.setText(roomList.get(0).);

        //holder.chiefName.setText(roomList.get(0).getChiefName());
                   holder.btnAdd.setOnClickListener(this);







    }

    @Override
    public int getItemCount() {
        //return roomListName.size();
        return 3;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_enter:
                Intent intent = new Intent();
                intent.setClass(context, ViewPagerActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

                break;

        }

    }

    /*@Override
    public int getItemCount() {
        return roomList.size();
    }
*/

}
