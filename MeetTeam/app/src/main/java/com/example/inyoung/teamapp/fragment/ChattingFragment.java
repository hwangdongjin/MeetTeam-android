package com.example.inyoung.teamapp.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inyoung.teamapp.ApplicationController;
import com.example.inyoung.teamapp.R;
import com.example.inyoung.teamapp.RetroFit.NetworkService;
import com.example.inyoung.teamapp.RetroFit.SharedPreferenceUtil;
import com.example.inyoung.teamapp.adapter.ChatShowRecyclerViewAdapter;
import com.example.inyoung.teamapp.dto.ChatShowDTO;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by MYpc on 2017-09-10.
 */

public class ChattingFragment extends Fragment {
    View view;
    private NetworkService networkService;
    private ApplicationController application;
    TextView txt_name;
    EditText edt_message;
    Button btn_res;
    SharedPreferenceUtil sessDB;
    String sess, title, message;
    public JSONArray chatJSONArray;
    public JSONObject chatJSONObject;
    static ArrayList<ChatShowDTO> chatShowList;
    private RecyclerView chat_recyclerview;
    private ChatShowRecyclerViewAdapter chatAdapter;
    RecyclerView.LayoutManager manager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_chatting, container, false);
        sessDB = new SharedPreferenceUtil(getContext());
        application = ApplicationController.getInstance();
        application.buildNetworkService();
        networkService = ApplicationController.getInstance().getNetworkService();
        txt_name = (TextView) view.findViewById(R.id.txt_name12);
        txt_name.setText(sessDB.getName().toString() + ":");
        sessDB = new SharedPreferenceUtil(getContext());
        initRecyclerView(view);
        Button(view);
        return view;
    }

    public void initRecyclerView(final View view) {
        sessDB = new SharedPreferenceUtil(getContext());
        Call<ResponseBody> call = networkService.post_roomChatShow(sessDB.getRoomTitle());
        call.enqueue(new Callback<ResponseBody>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    try {
                        chatJSONObject = new JSONObject(response.body().toString());
                        chatJSONArray = new JSONArray((String) chatJSONObject.get("chat"));
                        chatShowList = new ArrayList<ChatShowDTO>();
                        for (int i = 0; i < chatJSONArray.length(); i++) {
                            JSONObject jsonObject = chatJSONArray.getJSONObject(i);
                            chatShowList.add(new ChatShowDTO((String) jsonObject.get("message"), (String) jsonObject.get("userName")));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    ArrayList<ChatShowDTO> arr = new ArrayList<ChatShowDTO>();
                    arr.add(new ChatShowDTO("안녕하세요","조인영"));
                    arr.add(new ChatShowDTO("안녕하세요","조인영"));
                    arr.add(new ChatShowDTO("안녕하세요","조인영"));
                    arr.add(new ChatShowDTO("안녕하세요","조인영"));
                    chat_recyclerview = (RecyclerView) view.findViewById(R.id.chatview);
                    chat_recyclerview.setHasFixedSize(true);
                    chat_recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                    chatAdapter = new ChatShowRecyclerViewAdapter(arr, getContext());
                    chatAdapter.notifyItemInserted(0);
                    chatAdapter.notifyDataSetChanged();
                    chat_recyclerview.setAdapter(chatAdapter);
                    Toast.makeText(getContext(),"성공",Toast.LENGTH_SHORT).show();
                    for (int i=0;i<chatShowList.size();i++){
                        Log.e("tag","name:"+chatShowList.get(i).getUserName());
                    }

                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public void Button(View view) {
        btn_res = (Button) view.findViewById(R.id.btn_res);
        edt_message = (EditText) view.findViewById(R.id.edt_message);
        btn_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sess = sessDB.getSess();
                title = sessDB.getRoomTitle();
                message = edt_message.getText().toString();
                Call<ResponseBody> call = networkService.post_addChat(sess, title, message);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                        if (response.isSuccess()) {
                            Toast.makeText(getContext(), "성공", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                    }
                });
            }
        });

    }

}
