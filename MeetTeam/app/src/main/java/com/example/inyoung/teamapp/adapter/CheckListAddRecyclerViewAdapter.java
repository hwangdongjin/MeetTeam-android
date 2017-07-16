package com.example.inyoung.teamapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.inyoung.teamapp.R;
import com.example.inyoung.teamapp.dto.CheckAddDTO;

import java.util.ArrayList;

/**
 * Created by MYpc on 2017-07-11.
 */

public class CheckListAddRecyclerViewAdapter extends RecyclerView.Adapter<CheckListAddRecyclerViewAdapter.ViewHolder> implements View.OnClickListener {

    private ArrayList<CheckAddDTO> checkList;
    private Context context;


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
    public void onBindViewHolder(final CheckListAddRecyclerViewAdapter.ViewHolder holder, int position) {

        holder.manager_Do.setText(checkList.get(position).getManager_Do());
        holder.manager_Name.setText(checkList.get(position).getManager_Name());
        Log.i("mytag","size"+checkList.size());
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.checkBox.isChecked() == true)
                    holder.progressBar.setProgress(100);
                else
                    holder.progressBar.setProgress(0);
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


}
