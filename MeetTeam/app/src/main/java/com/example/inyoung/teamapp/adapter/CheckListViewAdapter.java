package com.example.inyoung.teamapp.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.inyoung.teamapp.R;
import com.example.inyoung.teamapp.dto.CheckAddDTO;

import java.util.ArrayList;

/**
 * Created by MYpc on 2017-08-11.
 */

public class CheckListViewAdapter extends BaseAdapter{


    private ArrayList<CheckAddDTO> checkList;
    private Context context;
    static int progressNum,total;
    SharedPreferences pref ;
    SharedPreferences.Editor editor;

    public CheckListViewAdapter(ArrayList<CheckAddDTO> checkList,Context context) {
        this.checkList=checkList;
        this.context = context;
    }

    @Override
    public int getCount() { return checkList.size(); }

    @Override
    public Object getItem(int position) { return checkList.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    public int getCheckNum(){
        total=0;
        for(int i=0;i<checkList.size();i++){
            if(checkList.get(i).getCheck()==true){
                total++;
            }
        }
        return total;
    }

    class Holder {
        CheckBox checkBox;
        TextView manager_Do;
        TextView manager_Name;

    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        pref=context.getSharedPreferences("pref",Context.MODE_PRIVATE);
        editor=pref.edit();
        final View itemView = LayoutInflater.from(context).inflate(R.layout.item_check_add, parent, false);
        final Holder holder = new Holder();
        holder.checkBox= (CheckBox) itemView.findViewById(R.id.manager_checkbox);
        holder.manager_Name= (TextView) itemView.findViewById(R.id.manager_Name11);
        holder.manager_Do= (TextView) itemView.findViewById(R.id.manager_do11);
        holder.manager_Do.setText(checkList.get(position).getManager_Do());

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
        return itemView;
    }

}

