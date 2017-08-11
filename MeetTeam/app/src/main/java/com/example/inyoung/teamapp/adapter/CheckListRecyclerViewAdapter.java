package com.example.inyoung.teamapp.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
    private int checkNum;
    private AlertDialog.Builder dlg;
    LinearLayoutManager linearLayoutManager;
    SharedPreferenceUtil db;

    private CheckListAddRecyclerViewAdapter roAdapter;
    EditText av,as;
    String teamName,teamDo;

    SharedPreferenceUtil sessDB;



    int checkboxcount = 0;

    public CheckListRecyclerViewAdapter(ArrayList<CheckListDTO> checkList, Context context) {
        this.checkList = checkList;
        this.context = context;
    }
    public CheckListRecyclerViewAdapter(){}


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView checkRoom_name;
        private Button manager_Button;
        private Button checkbox_add,btn_down,btn_up;
        private TextView manage_Name,manage_Do;
        private RecyclerView check11;

        ProgressBar progressBar;
        SharedPreferenceUtil sessDB;
        private RecyclerView checkbox_view;

        public ViewHolder(View itemView) {
            super(itemView);
            checkRoom_name=(TextView)itemView.findViewById(R.id.CheckRoomName);
            check11=(RecyclerView) itemView.findViewById(R.id.checkbox_view);
            manager_Button=(Button) itemView.findViewById(R.id.manager_button);
            manage_Name= (TextView) itemView.findViewById(R.id.manager_Name11);
            manage_Do= (TextView) itemView.findViewById(R.id.manager_do11);
            btn_down= (Button) itemView.findViewById(R.id.btn_down);
            btn_up= (Button) itemView.findViewById(R.id.btn_up);
            progressBar= (ProgressBar) itemView.findViewById(R.id.progressBar);

            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
            checkbox_view = (RecyclerView) itemView.findViewById(R.id.checkbox_view);
        }
    }

    @Override
    public CheckListRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_check_list, parent,false);
        CheckListRecyclerViewAdapter.ViewHolder vholder = new CheckListRecyclerViewAdapter.ViewHolder(itemView);

        return vholder;
    }

    @Override
    public void onBindViewHolder(final CheckListRecyclerViewAdapter.ViewHolder holder, final int position) {

        checkAddList = new ArrayList<>();
        db= new SharedPreferenceUtil(context);
        checkNum=db.getCheckNum();
        CheckListAddRecyclerViewAdapter check = new CheckListAddRecyclerViewAdapter();

        sessDB = new SharedPreferenceUtil(context);


        /*holder.progressBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                holder.progressBar.setProgress(0);
                int num=sessDB.getCheckboxcount();
                num--;
                Log.i("mytag", "titii:" + num);
                if (num!=0){
                    int k = 100/num;
                    holder.progressBar.setProgress(k);}
                return false;
            }
        });*/




        holder.checkRoom_name.setText(checkList.get(position).getCheck_RoomName());
        holder.btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.check11.scrollToPosition(0);
            }
        });
        holder.btn_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.check11.scrollToPosition(roAdapter.getItemCount()-1);
            }
        });

        holder.manager_Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View v) {
                dlg = new AlertDialog.Builder(v.getContext());
                LayoutInflater inflater = (LayoutInflater) v.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view1 = inflater.inflate(R.layout.item_check_list_member2, null, false);
                dlg.setView(view1);
                final AlertDialog.Builder builder = dlg;
                final AlertDialog.Builder 확인 = builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        av = (EditText) ((AlertDialog) dialog).findViewById(R.id.editText4);
                        as = (EditText) ((AlertDialog) dialog).findViewById(R.id.editText5);
                        initRecycleView(holder);
                        //checkboxcount++;
                        //sessDB.setCheckboxcount(checkboxcount);
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
        switch (v.getId()){

            //case R.id.checkbox_add:
        }
    }
    public void initRecycleView(ViewHolder holder){
        checkAddList.add(new CheckAddDTO(av.getText().toString(), as.getText().toString()));
        holder.check11.setHasFixedSize(true);
        holder.check11.setFocusable(false);

        roAdapter = new CheckListAddRecyclerViewAdapter(checkAddList, context);
        linearLayoutManager= new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        linearLayoutManager.setAutoMeasureEnabled(true);
        holder.check11.setLayoutManager(linearLayoutManager);
        holder.check11.setAdapter(roAdapter);
    }

    public void setProgressBar(int checkNum){
        this.checkNum=checkNum;
    }

}
