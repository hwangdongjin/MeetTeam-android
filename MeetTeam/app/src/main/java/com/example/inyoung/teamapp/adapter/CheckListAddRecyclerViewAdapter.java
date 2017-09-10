package com.example.inyoung.teamapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.inyoung.teamapp.R;
import com.example.inyoung.teamapp.RetroFit.SharedPreferenceUtil;
import com.example.inyoung.teamapp.dto.CheckAddDTO;

import java.util.ArrayList;

/**
 * Created by MYpc on 2017-07-11.
 */

public class CheckListAddRecyclerViewAdapter extends RecyclerView.Adapter<CheckListAddRecyclerViewAdapter.ViewHolder> implements View.OnClickListener {

    private ArrayList<CheckAddDTO> checkList;
    private Context context;
    SharedPreferenceUtil DB;
    SharedPreferenceUtil sessDB;
    static int progressNum,total;


    public CheckListAddRecyclerViewAdapter(ArrayList<CheckAddDTO> checkList,Context context){
        this.checkList=checkList;
        this.context=context;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        protected TextView manager_Name,manager_Do;
        protected CheckBox checkBox;
        protected ProgressBar progressBar;


        public ViewHolder(View itemView) {
            super(itemView);
            manager_Do=(TextView)itemView.findViewById(R.id.manager_do11);
            manager_Name=(TextView) itemView.findViewById(R.id.manager_Name11);
            checkBox= (CheckBox) itemView.findViewById(R.id.manager_checkbox);
            progressBar= (ProgressBar) itemView.findViewById(R.id.progressBar);
        }
    }

    @Override
    public CheckListAddRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_check_add, parent,false);
        CheckListAddRecyclerViewAdapter.ViewHolder vholder = new CheckListAddRecyclerViewAdapter.ViewHolder(itemView);
        return vholder;

    }

    @Override
    public void onBindViewHolder(final CheckListAddRecyclerViewAdapter.ViewHolder holder, final int position) {
        
        DB= new SharedPreferenceUtil(context);
        sessDB= new SharedPreferenceUtil(context);
        holder.manager_Do.setText(checkList.get(position).getManager_Do());
        holder.manager_Name.setText(checkList.get(position).getManager_Name());
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkList.get(position).setCheck();
                holder.checkBox.setChecked(checkList.get(position).getCheck());
                total=0;
                for(int i=0;i<checkList.size();i++){
                    if(checkList.get(i).getCheck()==true){
                        total++;
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return checkList.size();
    }

    @Override
    public void onClick(View v) {
    }
    public int getCheckNum(){
        total=0;
        for(int i=0;i<checkList.size();i++){
            if(checkList.get(i).getCheck()==true){
                total++;
            }
        }
        return total;
    }


}
