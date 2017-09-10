package com.example.inyoung.teamapp.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

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
    private ArrayList<ChatShowDTO> chatShowList;
    private RecyclerView chat_recyclerview;
    private ChatShowRecyclerViewAdapter chatAdapter;
    TimerTask timerTask;
    Timer timer;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chatting, container, false);
        sessDB = new SharedPreferenceUtil(getContext());
        application = ApplicationController.getInstance();
        application.buildNetworkService();
        networkService = ApplicationController.getInstance().getNetworkService();
        sessDB = new SharedPreferenceUtil(getContext());
        Button(view);
        timerTask= new TimerTask() {
            @Override
            public void run() {
                initRecyclerView(view);
            }
        };
        timer = new Timer();
        timer.schedule(timerTask,1000,1000);
        return view;
    }

    public void initRecyclerView(final View view) {
        Call<ResponseBody> call = networkService.post_roomChatShow(sessDB.getRoomTitle());
        call.enqueue(new Callback<ResponseBody>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    try {
                        chatJSONObject = new JSONObject(response.body().string());
                        chatJSONArray = new JSONArray(chatJSONObject.get("chat").toString());
                        chatShowList = new ArrayList<ChatShowDTO>();
                        for (int i = 0; i < chatJSONArray.length(); i++) {
                            JSONObject jsonObject = chatJSONArray.getJSONObject(i);
                            chatShowList.add(new ChatShowDTO((String) jsonObject.get("message"), (String) jsonObject.get("userName")));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    chat_recyclerview = (RecyclerView) view.findViewById(R.id.chatview);
                    chat_recyclerview.setHasFixedSize(true);
                    chat_recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                    chatAdapter = new ChatShowRecyclerViewAdapter(chatShowList, getContext());
                    chatAdapter.notifyItemInserted(0);
                    chatAdapter.notifyDataSetChanged();
                    chat_recyclerview.setAdapter(chatAdapter);
                    chat_recyclerview.scrollToPosition(chatAdapter.getItemCount()-1);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public void Button(final View view) {
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
                            edt_message.setText("");
                            InputMethodManager mInputMethodManager = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            mInputMethodManager.hideSoftInputFromWindow(edt_message.getWindowToken(), 0);

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
