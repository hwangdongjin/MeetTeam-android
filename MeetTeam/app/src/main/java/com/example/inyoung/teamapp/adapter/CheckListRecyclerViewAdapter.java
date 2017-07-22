package com.example.inyoung.teamapp.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.inyoung.teamapp.R;
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
    private int pageNumber;
    private AlertDialog.Builder dlg;

    private CheckListAddRecyclerViewAdapter roAdapter;
    EditText av,as;
    String teamName,teamDo;

    public CheckListRecyclerViewAdapter(ArrayList<CheckListDTO> checkList, Context context) {
        this.checkList = checkList;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView checkRoom_name;

        private Button manager_Button;
        private Button checkbox_add;
        private TextView manage_Name,manage_Do;
        private RecyclerView check11;


        public ViewHolder(View itemView) {
            super(itemView);
            checkRoom_name=(TextView)itemView.findViewById(R.id.CheckRoomName);
            check11=(RecyclerView) itemView.findViewById(R.id.checkbox_view);
            manager_Button=(Button) itemView.findViewById(R.id.manager_button);
            checkbox_add=(Button) itemView.findViewById(R.id.checkbox_add);
            manage_Name= (TextView) itemView.findViewById(R.id.manager_Name11);
            manage_Do= (TextView) itemView.findViewById(R.id.manager_do11);
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

        holder.checkRoom_name.setText(checkList.get(position).getCheck_RoomName());
        holder.checkbox_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAddList.add(0,new CheckAddDTO("안녕","안녕"));
                roAdapter.notifyItemInserted(0);
                roAdapter.notifyDataSetChanged();
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
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        av = (EditText) ((AlertDialog) dialog).findViewById(R.id.editText4);
                        as = (EditText) ((AlertDialog) dialog).findViewById(R.id.editText5);
                        checkAddList = new ArrayList<>();
                        checkAddList.add(new CheckAddDTO(av.getText().toString(),as.getText().toString()));
                        Log.i("mytag","titi:"+checkAddList.get(0).getManager_Name());
                        roAdapter = new CheckListAddRecyclerViewAdapter(checkAddList,context);
                        holder.check11.setHasFixedSize(true);
                        holder.check11.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
                        holder.check11.setAdapter(roAdapter);
                        roAdapter.notifyDataSetChanged();





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

/*
            case  R.id.manager_button:
            managerName = (TextView) v.findViewById(R.id.manager_Name);
            manager_Button = (Button) v.findViewById(R.id.manager_button);
                managerdo = (TextView) v.findViewById(R.id.manager_do);
                editText5 = (EditText) v.findViewById(R.id.editText5);
                editText4 = (EditText) v.findViewById(R.id.editText4);

            //member1 = (Button) v.findViewById(R.id.checkList_member);
            dlg = new AlertDialog.Builder(v.getContext());
            LayoutInflater inflater = (LayoutInflater) v.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view1 = inflater.inflate(R.layout.item_check_list_member2, null, false);
           dlg.setView(view1);
            dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Editable str = editText4.getText();
                   // str = editText4.getText();
                    managerName.setText(str.toString());
                    String ac = (String)editText4.getText().toString();
                    Toast.makeText(v.getContext(),ac, Toast.LENGTH_SHORT).show();
                    //managerName.setText(editText4.getText().toString());
                    //managerdo.setText(editText5.getText().toString());

                }
            });
            dlg.setNegativeButton("취소", null);
            dlg.show();
                break;*/
            case R.id.checkbox_add:
        }
    }

/*
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
    }*/
}
