package com.example.inyoung.teamapp.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.inyoung.teamapp.R;
import com.example.inyoung.teamapp.dto.CheckListDTO;

import java.util.ArrayList;

/**
 * Created by MYpc on 2017-04-10.
 */

public class CheckListRecyclerViewAdapter extends RecyclerView.Adapter<CheckListRecyclerViewAdapter.ViewHolder> implements View.OnClickListener {
    private ArrayList<CheckListDTO> checkList;
    private Context context;
    private int pageNumber;
    private AlertDialog.Builder dlg;
    Button manager_Button;
    Button member1,member2,member3,member4,member5,member6;
    TextView managerName;
    String memberSelect,memberFinal = "";


    public CheckListRecyclerViewAdapter(ArrayList<CheckListDTO> checkList, Context context) {
        this.checkList = checkList;
        this.context = context;

    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView checkRoom_name;
        private TextView manager_Name;
        private TextView manager_Do;
        private Button manager_Button;

        public ViewHolder(View itemView) {
            super(itemView);
            checkRoom_name=(TextView)itemView.findViewById(R.id.CheckRoomName);
            manager_Name=(TextView)itemView.findViewById(R.id.manager_Name);
            manager_Do=(TextView) itemView.findViewById(R.id.manager_do);
            manager_Button=(Button) itemView.findViewById(R.id.manager_button);

        }
    }
    @Override
    public CheckListRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_check_list, parent,false);
        CheckListRecyclerViewAdapter.ViewHolder vholder = new CheckListRecyclerViewAdapter.ViewHolder(itemView);
        return vholder;
    }

    @Override
    public void onBindViewHolder(CheckListRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.manager_Name.setText(checkList.get(position).getManager_Name());
        holder.checkRoom_name.setText(checkList.get(position).getCheck_RoomName());
        holder.manager_Do.setText(checkList.get(position).getManager_Do());
        holder.manager_Button.setOnClickListener(this);

    }

    @Override
    public int getItemCount() {
        return checkList.size();
    }



    @Override
    public void onClick(View v) {
       if(v.getId()==R.id.manager_button) {
            managerName = (TextView) v.findViewById(R.id.manager_Name);
            manager_Button = (Button) v.findViewById(R.id.manager_button);
           member1=(Button)v.findViewById(R.id.checkList_member);
            dlg = new AlertDialog.Builder(v.getContext());
            LayoutInflater inflater = (LayoutInflater) v.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view1 = inflater.inflate(R.layout.item_check_list_member, null, false);
            dlg.setView(view1);
            dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {




                }
            });
            dlg.setNegativeButton("취소", null);
            dlg.show();
        }

                }






    public String memberClick(View v){
        LayoutInflater inflater = (LayoutInflater)v.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view1 = inflater.inflate(R.layout.item_check_list_member,null, false);
        switch (v.getId()) {
            case R.id.checkList_member:
                member1 = (Button) view1.findViewById(R.id.checkList_member);
                memberSelect = member1.getText().toString();
                System.out.println(memberSelect);
                break;
            case R.id.checkList_member2:
                member2 = (Button) view1.findViewById(R.id.checkList_member2);
                memberSelect = member2.getText().toString();
                break;
            case R.id.checkList_member3:
                member3 = (Button) view1.findViewById(R.id.checkList_member3);
                memberSelect = member3.getText().toString();
                break;
            case R.id.checkList_member4:
                member4 = (Button) view1.findViewById(R.id.checkList_member4);
                memberSelect = member4.getText().toString();
                break;
            case R.id.checkList_member5:
                member5 = (Button) view1.findViewById(R.id.checkList_member5);
                memberSelect = member5.getText().toString();
                break;
            case R.id.checkList_member6:
                member6 = (Button) view1.findViewById(R.id.checkList_member6);
                memberSelect = member6.getText().toString();
                break;

        }

        return memberSelect;







    }



}
