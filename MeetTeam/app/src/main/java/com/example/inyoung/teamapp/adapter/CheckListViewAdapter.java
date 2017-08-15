package com.example.inyoung.teamapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.inyoung.teamapp.R;
import com.example.inyoung.teamapp.dto.CheckAddDTO;

import java.util.ArrayList;

/**
 * Created by MYpc on 2017-08-11.
 */

public class CheckListViewAdapter extends BaseAdapter {


    private ArrayList<CheckAddDTO> checkList;
    private Context context;
    CheckListRecyclerViewAdapter checkListRecyclerViewAdapter;
    static int count;

    public CheckListViewAdapter(ArrayList<CheckAddDTO> checkList, Context context) {

        this.checkList = checkList;
        this.context = context;
    }


    @Override
    public int getCount() { return checkList.size(); }

    @Override
    public Object getItem(int position) { return checkList.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    public int getCheckCount(){return count;}

    class Holder {
        CheckBox checkBox;
        TextView manager_Do;
        TextView manager_Name;
        boolean checkBoxState;
        ProgressBar progressBar;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final View itemView = LayoutInflater.from(context).inflate(R.layout.item_check_add, parent, false);
        final View itemView2 = LayoutInflater.from(context).inflate(R.layout.fragement_check_list, parent, false);
        final Holder holder = new Holder();
        //프로필리스트에서 이름이랑 정보 가져오기
        holder.checkBox= (CheckBox) itemView.findViewById(R.id.manager_checkbox);
        holder.checkBox.setChecked(false);
        Log.i("mytag","state:"+holder.checkBox.isChecked());
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CheckBox)v).isChecked()){


                }
            }
        });
        holder.manager_Name= (TextView) itemView.findViewById(R.id.manager_Name11);
        holder.manager_Do= (TextView) itemView.findViewById(R.id.manager_do11);
        holder.manager_Do.setText(checkList.get(position).getManager_Do());
        holder.manager_Name.setText(checkList.get(position).getManager_Name());

        return itemView;
    }




    }

