package com.example.inyoung.teamapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.inyoung.teamapp.ApplicationController;
import com.example.inyoung.teamapp.R;
import com.example.inyoung.teamapp.RetroFit.NetworkService;
import com.example.inyoung.teamapp.RetroFit.SharedPreferenceUtil;
import com.example.inyoung.teamapp.ViewPagerActivity;
import com.example.inyoung.teamapp.dto.RoomDTO;
import com.example.inyoung.teamapp.dto.UserListDTO;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

import static com.example.inyoung.teamapp.R.id.btn_enter;

public class RoomRecyclerViewAdapter extends RecyclerView.Adapter<RoomRecyclerViewAdapter.ViewHolder> implements View.OnClickListener {

    private ArrayList<RoomDTO> roomList;
    ArrayList<UserListDTO> userList ;
    String[] temp;
    private Context context;
    private NetworkService networkService;
    private ApplicationController application;
    TextView tv_title;
    JSONArray jsonArray1;
    JSONObject jsonObject1;
    static int itemNum;

    public RoomRecyclerViewAdapter(ArrayList<RoomDTO> roomList, Context context) {
        this.roomList = roomList;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements Serializable {
        private TextView title;
        private TextView chiefName;
        private Button btnAdd;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            chiefName = (TextView) itemView.findViewById(R.id.tv_managerName);
            btnAdd = (Button) itemView.findViewById(btn_enter);
        }
    }

    @Override
    public RoomRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room, parent, false);
        ViewHolder vholder = new ViewHolder(itemView);
        return vholder;
    }

    @Override
    public void onBindViewHolder(RoomRecyclerViewAdapter.ViewHolder holder,final int position) {
        holder.title.setText(roomList.get(holder.getAdapterPosition()).getRoom_Title());
        holder.chiefName.setText(roomList.get(holder.getAdapterPosition()).getManager_Name());
        Log.i("Mytag", "testbody:" + roomList.get(0));
        holder.btnAdd.setOnClickListener(this);
        itemNum = holder.btnAdd.getId();
    }

    @Override
    public int getItemCount() {
        return roomList.size();
     }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case btn_enter:
                tv_title = (TextView) v.findViewById(R.id.tv_title);
                //roomName12 = tv_roomName.getText().toString();
                application = ApplicationController.getInstance();
                application.buildNetworkService();
                networkService = ApplicationController.getInstance().getNetworkService();
                Call<ResponseBody> thumbnailCall = networkService.post_userList("공설팀플");
                thumbnailCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                        if (response.isSuccess()){
                            try {
                                jsonArray1= new JSONArray(response.body().string());
                                userList = new ArrayList<>();
                                for(int i=0;i<jsonArray1.length();i++){
                                    jsonObject1 = jsonArray1.getJSONObject(i);
                                    userList.add(new UserListDTO((String) jsonObject1.get("name")));
                                }
                                Intent intent = new Intent();
                                intent.setClass(context, ViewPagerActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.putExtra("test",userList);
                                context.startActivity(intent);

                                    Log.i("my","userlist:"+userList.get(0).getName());
                                    Log.i("mt","temp"+temp);
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                            catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                    }
                });
                break;

        }
    }
}
