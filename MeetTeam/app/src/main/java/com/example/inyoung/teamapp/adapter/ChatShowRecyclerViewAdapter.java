package com.example.inyoung.teamapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.inyoung.teamapp.R;
import com.example.inyoung.teamapp.dto.ChatShowDTO;

import java.util.ArrayList;

/**
 * Created by MYpc on 2017-09-10.
 */

public class ChatShowRecyclerViewAdapter extends RecyclerView.Adapter<ChatShowRecyclerViewAdapter.ViewHolder> implements View.OnClickListener {
    ArrayList<ChatShowDTO> chatShowList;
    Context context;

    public ChatShowRecyclerViewAdapter(ArrayList<ChatShowDTO> chatShowList, Context context) {
        this.chatShowList = chatShowList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView userName;
        private TextView message;

        public ViewHolder(View itemView) {
            super(itemView);
            userName = (TextView) itemView.findViewById(R.id.txt_userName);
            message = (TextView) itemView.findViewById(R.id.txt_message);
        }
    }

    @Override
    public ChatShowRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_container, parent, false);
        ChatShowRecyclerViewAdapter.ViewHolder vholder = new ChatShowRecyclerViewAdapter.ViewHolder(itemView);
        return vholder;
    }

    @Override
    public void onBindViewHolder(ChatShowRecyclerViewAdapter.ViewHolder holder, final int position) {
        holder.userName.setText(chatShowList.get(position).getUserName() + ":");
        holder.message.setText("" + "" + "" + "" + "" + "" + "" + "" + chatShowList.get(position).getMessage());

    }

    @Override
    public int getItemCount() {
        return chatShowList.size();
    }

    @Override
    public void onClick(View v) {

    }


}
