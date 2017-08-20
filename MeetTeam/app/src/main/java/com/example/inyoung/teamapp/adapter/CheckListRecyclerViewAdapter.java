package com.example.inyoung.teamapp.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.inyoung.teamapp.R;
import com.example.inyoung.teamapp.RetroFit.SharedPreferenceUtil;
import com.example.inyoung.teamapp.dto.CheckAddDTO;
import com.example.inyoung.teamapp.dto.CheckListDTO;

import java.util.ArrayList;

/**
 * Created by MYpc on 2017-04-10.
 */

public class CheckListRecyclerViewAdapter extends RecyclerView.Adapter<CheckListRecyclerViewAdapter.ViewHolder> implements View.OnClickListener {
    private ArrayList<CheckListDTO> checkList;
    private ArrayList<CheckAddDTO> checkAddList;
    private Context context;
    private AlertDialog.Builder dlg;
    private CheckListViewAdapter checkListViewAdapter;
    private CheckListAddRecyclerViewAdapter checkListAddRecyclerViewAdapter;
    EditText av, as;
    SharedPreferenceUtil sessDB;
    static int progressNum=0;
    static int total=0;
    static double finish=0;
    static int pro;



    public CheckListRecyclerViewAdapter(ArrayList<CheckListDTO> checkList, Context context) {
        this.checkList = checkList;
        this.context = context;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView checkRoom_name;
        private Button manager_Button;
        private Button checkbox_add, btn_down, btn_up;
        private TextView manage_Name, manage_Do;
        ArrayList<CheckAddDTO> checkAddDTO;
        private ProgressBar progressBar;
        private ListView checkListView;
        private TextView progressNum;
        SharedPreferenceUtil sessDB;
        //private RecyclerView checkbox_view;

        public ViewHolder(View itemView) {
            super(itemView);
            checkRoom_name = (TextView) itemView.findViewById(R.id.CheckRoomName);
            manager_Button = (Button) itemView.findViewById(R.id.manager_button);
            manage_Name = (TextView) itemView.findViewById(R.id.manager_Name11);
            manage_Do = (TextView) itemView.findViewById(R.id.manager_do11);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
            checkListView = (ListView) itemView.findViewById(R.id.CheckListView);
            progressNum= (TextView) itemView.findViewById(R.id.progressNum);

        }
    }

    @Override
    public CheckListRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_check_list, parent, false);
        CheckListRecyclerViewAdapter.ViewHolder vholder = new CheckListRecyclerViewAdapter.ViewHolder(itemView);
        return vholder;
    }

    @Override
    public void onBindViewHolder(final CheckListRecyclerViewAdapter.ViewHolder holder, final int position) {

        sessDB = new SharedPreferenceUtil(context);
        checkAddList = new ArrayList<>();
        holder.checkRoom_name.setText(checkList.get(position).getCheck_RoomName());
        holder.manager_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                dlg = new AlertDialog.Builder(v.getContext());
                LayoutInflater inflater = (LayoutInflater) v.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view1 = inflater.inflate(R.layout.item_check_list_member2, null, false);
                final View addView=inflater.inflate(R.layout.item_check_add,null,false);
                dlg.setView(view1);
                final AlertDialog.Builder builder = dlg;
                final AlertDialog.Builder 확인 = builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        av = (EditText) ((AlertDialog) dialog).findViewById(R.id.editText4);
                        as = (EditText) ((AlertDialog) dialog).findViewById(R.id.editText5);
                        checkAddList.add(new CheckAddDTO(av.getText().toString(), as.getText().toString(),false));
                        initCheckListView(holder, checkAddList,addView);
                        if (progressNum!=0){
                            holder.progressBar.incrementProgressBy(pro);
                            holder.progressNum.setText(String.valueOf(pro));
                        }
                    }
                });
                builder.setNegativeButton("취소", null);
                dlg.show();
            }
        });

    }
    @Override
    public int getItemCount() {
        return checkList.size();
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {


        }
    }
    public void initCheckListView(CheckListRecyclerViewAdapter.ViewHolder holder, ArrayList<CheckAddDTO> checkAddList,View view) {

        checkListViewAdapter= new CheckListViewAdapter(checkAddList,context);
        checkListViewAdapter.notifyDataSetChanged();
        holder.checkListView.setAdapter(checkListViewAdapter);
        progressNum=checkListViewAdapter.getCheckNum();
        total=checkListViewAdapter.getCount();
        finish= (double) progressNum/total;
        pro= (int) (100*finish);
        Log.i("mytag","coco1:"+progressNum);
        Log.i("mytag","coco2:"+total);
        Log.i("mytag","coco3:"+finish);


    }



}
