package com.example.inyoung.teamapp.adapter;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inyoung.teamapp.ApplicationController;
import com.example.inyoung.teamapp.R;
import com.example.inyoung.teamapp.RetroFit.NetworkService;
import com.example.inyoung.teamapp.RetroFit.SharedPreferenceUtil;
import com.example.inyoung.teamapp.dto.CheckAddDTO;
import com.example.inyoung.teamapp.dto.CheckListDTO;
import com.squareup.okhttp.ResponseBody;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by MYpc on 2017-04-10.
 */

public class CheckListRecyclerViewAdapter extends RecyclerView.Adapter<CheckListRecyclerViewAdapter.ViewHolder> implements View.OnClickListener {
    private ArrayList<CheckListDTO> checkList;
    private ArrayList<CheckAddDTO> checkAddList, checkShowList;
    private Context context;
    private AlertDialog dlg;
    int size;
    CheckListAddRecyclerViewAdapter roAdapter;
    private NetworkService networkService;
    ApplicationController application;
    EditText edt_do, edt_name;
    SharedPreferenceUtil sessDB;
    static int progressNum = 0;
    static int total = 0;
    static double finish = 0;
    static int pro;
    String st_name, st_do;
    Button btn_ok, btn_cancel;
    public CheckListRecyclerViewAdapter(ArrayList<CheckListDTO> checkList, ArrayList<CheckAddDTO> checkAddList, int size, Context context) {
        this.checkList = checkList;
        this.checkAddList = checkAddList;
        this.context = context;
        this.size = size;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView checkRoom_name;
        private Button manager_Button;
        private ProgressBar progressBar;
        private TextView progressNum;
        private Button btn_up, btn_down;
        private RecyclerView checkListView;

        public ViewHolder(View itemView) {
            super(itemView);
            checkRoom_name = (TextView) itemView.findViewById(R.id.CheckRoomName);
            manager_Button = (Button) itemView.findViewById(R.id.manager_button);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
            progressNum = (TextView) itemView.findViewById(R.id.progressNum);
            btn_up = (Button) itemView.findViewById(R.id.btn_up);
            btn_down = (Button) itemView.findViewById(R.id.btn_down);
            checkListView = (RecyclerView) itemView.findViewById(R.id.clistRecyclerView);
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
        application = ApplicationController.getInstance();
        application.buildNetworkService();
        networkService = ApplicationController.getInstance().getNetworkService();
        holder.checkRoom_name.setText(checkList.get(position).getCheck_RoomName());
        Log.i("mytag", "position:" + position);
        switch (position) {
            case 0:
                 initClistShow(holder,position);
                break;
            case 1:
                 initClistShow(holder,position);
                break;
            case 2:
                initClistShow(holder,position);
                break;
            case 3:
                initClistShow(holder,position);
                break;
            case 4:
                initClistShow(holder,position);
                break;
            case 5:
                initClistShow(holder,position);
                break;
            case 6:
                initClistShow(holder,position);
                break;
            case 7:
                initClistShow(holder,position);
                break;
            case 8:
                initClistShow(holder,position);
                break;
            case 9:
                initClistShow(holder,position);
                break;
            case 10:
                initClistShow(holder,position);
                break;
        }

        holder.btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        holder.manager_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View view = inflater.inflate(R.layout.item_check_list_member2, null, false);
                dlg = new AlertDialog.Builder(context).create();
                dlg.setView(view);
                dlg.show();
                edt_name = (EditText) view.findViewById(R.id.edt_name);
                edt_do = (EditText) view.findViewById(R.id.edt_do);
                btn_ok = (Button) view.findViewById(R.id.btn_ok);
                btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        if ("".equals(edt_name.getText().toString())) {
                            Toast.makeText(context, "팀원을 입력해주세요", Toast.LENGTH_LONG).show();
                        } else {
                            st_name = edt_name.getText().toString();
                        }
                        if ("".equals(edt_do.getText().toString())) {
                            Toast.makeText(context, "할일을 입력해주세요", Toast.LENGTH_LONG).show();
                        } else {
                            st_do = edt_do.getText().toString();
                        }
                        if (st_name != null && st_do != null) {
                            Call<ResponseBody> call = networkService.post_taskClistAdd(sessDB.getRoomTitle(), checkList.get(position).getCheck_RoomName(), st_do, st_name);
                            call.enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                                    if (response.isSuccess()) {
                                        Toast.makeText(context, "성공하였습니다", Toast.LENGTH_LONG).show();
                                        
                                        if (progressNum != 0) {
                                            holder.progressBar.incrementProgressBy(pro);
                                            holder.progressNum.setText(String.valueOf(pro));
                                        }
                                        dlg.cancel();
                                    }
                                }

                                @Override
                                public void onFailure(Throwable t) {
                                }
                            });
                        }

                    }
                });
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dlg.cancel();
                    }
                });

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

   /* public void initCheckListView(CheckListRecyclerViewAdapter.ViewHolder holder, ArrayList<CheckAddDTO> checkAddList) {
        checkListViewAdapter = new CheckListViewAdapter(checkAddList, context);
        checkListViewAdapter.notifyDataSetChanged();
        holder.checkListView.setAdapter(checkListViewAdapter);
        progressNum = checkListViewAdapter.getCheckNum();
        total = checkListViewAdapter.getCount();
        finish = (double) progressNum / total;
        pro = (int) (100 * finish);
    }*/


    public void initClistShow(CheckListRecyclerViewAdapter.ViewHolder holder, final int position) {

        checkShowList = new ArrayList<>();
        for (int i = 0; i < checkAddList.size(); i++) {
            if (checkAddList.get(i).getClistNum() == position) {
                checkShowList.add(new CheckAddDTO(checkAddList.get(i).getManager_Name(), checkAddList.get(i).getManager_Do(), false, checkAddList.get(i).getClistNum()));
            }
        }
        holder.checkListView.setHasFixedSize(false);
        holder.checkListView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        roAdapter = new CheckListAddRecyclerViewAdapter(checkShowList, context);
        roAdapter.notifyDataSetChanged();
        holder.checkListView.setAdapter(roAdapter);
        progressNum = roAdapter.getCheckNum();
        total = roAdapter.getItemCount();
        finish = (double) progressNum / total;
        pro = (int) (100 * finish);
    }
}
