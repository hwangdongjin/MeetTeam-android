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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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
    public JSONObject jsonObject;
    public JSONArray jsonArray;
    public JSONArray clistArray;
    public JSONObject clistObject;
    public CheckListRecyclerViewAdapter(ArrayList<CheckListDTO> checkList,Context context) {
        this.checkList = checkList;
        this.context = context;

    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView checkRoom_name;
        private Button manager_Button;
        private ProgressBar progressBar;
        private RecyclerView checkListView;
        private TextView progressNum;
        private TextView manage_Name, manage_Do;
        private Button btn_up, btn_down;

        public ViewHolder(View itemView) {
            super(itemView);
            checkRoom_name = (TextView) itemView.findViewById(R.id.CheckRoomName);
            manager_Button = (Button) itemView.findViewById(R.id.manager_button);
            manage_Name = (TextView) itemView.findViewById(R.id.manager_Name11);
            manage_Do = (TextView) itemView.findViewById(R.id.manager_do11);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
            checkListView = (RecyclerView) itemView.findViewById(R.id.CheckListView);
            progressNum = (TextView) itemView.findViewById(R.id.progressNum);
            btn_up = (Button) itemView.findViewById(R.id.btn_up);
            btn_down = (Button) itemView.findViewById(R.id.btn_down);

        }
    }

    @Override
    public CheckListRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_check_list, parent, false);
        CheckListRecyclerViewAdapter.ViewHolder vholder = new CheckListRecyclerViewAdapter.ViewHolder(itemView);
        initClistShow(vholder,viewType);
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
        holder.btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        holder.manager_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
                    public void onClick(View v) {
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


   public void initClistShow(final CheckListRecyclerViewAdapter.ViewHolder holder, final int position) {
        sessDB = new SharedPreferenceUtil(context);
        application = ApplicationController.getInstance();
        application.buildNetworkService();
        networkService = ApplicationController.getInstance().getNetworkService();
        Call<ResponseBody> call = networkService.post_taskShow(sessDB.getRoomTitle());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    try {
                        jsonArray = new JSONArray(response.body().string());
                        checkAddList = new ArrayList<>();
                        Log.i("mytag","jsonleng:"+jsonArray.length());
                            JSONObject jsonObject2 = jsonArray.getJSONObject(position);
                            JSONArray clistArray2 = new JSONArray(jsonObject2.get("clist").toString());
                            for (int j = 0; j < clistArray2.length(); j++) {
                                JSONObject clistObject2 = clistArray2.getJSONObject(j);
                                checkAddList.add(new CheckAddDTO((String) clistObject2.get("name"), (String) clistObject2.get("list"), (Boolean) clistObject2.get("isCheck"),clistArray2.length()));
                                Log.i("mytag", "length22:" + ((String) clistObject2.get("name")));
                                Log.i("mytag", "length22:" + ((String) clistObject2.get("list")));
                            }

                        for(int k=0;k<checkAddList.size();k++){
                            Log.i("mytag","clistarr:"+checkAddList.get(k).getClistNum());
                        }
                        holder.checkListView.setHasFixedSize(false);
                        holder.checkListView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
                        roAdapter = new CheckListAddRecyclerViewAdapter(checkAddList,context);
                        roAdapter.notifyItemInserted(0);
                        roAdapter.notifyDataSetChanged();
                        holder.checkListView.setAdapter(roAdapter);
                        progressNum = roAdapter.getCheckNum();
                        total = roAdapter.getItemCount();
                        finish = (double) progressNum / total;
                        pro = (int) (100 * finish);



                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}

