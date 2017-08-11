package com.example.inyoung.teamapp.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    private int checkNum;
    SharedPreferenceUtil DB;
    CheckListRecyclerViewAdapter roAdapter;
    LinearLayoutManager linearLayoutManager;
    SharedPreferenceUtil sessDB;
    private int gValue;
    int checkboxcount=0;


   public CheckListAddRecyclerViewAdapter(){}

    public CheckListAddRecyclerViewAdapter(ArrayList<CheckAddDTO> checkList,Context context){

        this.checkList=checkList;
        this.context=context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        protected TextView manager_Name,manager_Do;
        protected CheckBox checkBox;
        protected ProgressBar progressBar;
        RecyclerView check11;
        RecyclerView chatView;

    
      
      public ViewHolder(View itemView) {

            super(itemView);
            manager_Do=(TextView)itemView.findViewById(R.id.manager_do11);
            manager_Name=(TextView) itemView.findViewById(R.id.manager_Name11);
            checkBox= (CheckBox) itemView.findViewById(R.id.manager_checkbox);
            progressBar= (ProgressBar) itemView.findViewById(R.id.progressBar);
            check11= (RecyclerView) itemView.findViewById(R.id.chatView);
            chatView= (RecyclerView) itemView.findViewById(R.id.chatView);

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
        
        DB= new SharedPreferenceUtil(context);
        sessDB= new SharedPreferenceUtil(context);
        holder.manager_Do.setText(checkList.get(position).getManager_Do());
        holder.manager_Name.setText(checkList.get(position).getManager_Name());
        Log.i("mytag","size"+checkList.size());


        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CheckListRecyclerViewAdapter check = new CheckListRecyclerViewAdapter();
                check.setProgressBar(4);

                if(holder.checkBox.isChecked() == true) {

                    //Log.i("mytag", "titi:" + sessDB.getCheckboxcount());
                    checkboxcount++;
                    //sessDB.setCheckboxcount(checkboxcount);

                    //holder.progressBar.setProgress(100 / sessDB.getCheckboxcount());





                }
                else
                    holder.progressBar.setProgress(0);
            }
        });
        //holder.progressBar.setProgress(100);

        Log.i("mytag","1526"+DB.getCheckNum());



    }

    @Override
    public int getItemCount() {


        return checkList.size();
    }

    @Override
    public void onClick(View v) {

    }


}
