package com.example.inyoung.teamapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.inyoung.teamapp.R;
import com.example.inyoung.teamapp.dto.MemberDTO;

import java.util.ArrayList;

/**
 * Created by inyoung on 2017-01-17.
 */
public class ProfileAdapter extends BaseAdapter {
    private ArrayList<MemberDTO> profileList;
    private Context context;

    public ProfileAdapter(ArrayList<MemberDTO> profileList, Context context) {
        this.profileList = profileList;
        this.context = context;
    }

    public static ProfileAdapter profileInstance(ArrayList<MemberDTO> profileList, Context context) {
        return new ProfileAdapter(profileList, context);
    }

    @Override
    public int getCount() {
        return profileList.size();
    }

    @Override
    public Object getItem(int position) {
        return profileList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // 깔끔하게 홀더
    class Holder {
        ImageView mImage;
        TextView mName;
        TextView mContent;
        Button btn_call;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        //아이템 뷰에 인플레이터
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_profile, parent, false);

        //홀더에 뷰단 달아주기
        Holder holder = new Holder();
        holder.mImage = (ImageView) itemView.findViewById(R.id.mImage);
        holder.mName = (TextView) itemView.findViewById(R.id.mName);
        holder.mContent = (TextView) itemView.findViewById(R.id.mContent);
        holder.btn_call = (Button) itemView.findViewById(R.id.btn_call);

        //프로필리스트에서 이름이랑 정보 가져오기
        holder.mName.setText(profileList.get(position).getmName());
        holder.mContent.setText(profileList.get(position).getmData());
        holder.btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String call="tel:"+profileList.get(position).getPhoneNum();
                context.startActivity(new Intent("android.intent.action.DIAL", Uri.parse(call)));
            }
        });
        Glide.with(context)
                .load(profileList.get(position).getmImage())
                .fitCenter()
                .into(holder.mImage);

        return itemView;
    }
}
