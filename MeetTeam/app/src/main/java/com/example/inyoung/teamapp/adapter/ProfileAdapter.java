package com.example.inyoung.teamapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.inyoung.teamapp.R;
import com.example.inyoung.teamapp.dto.MemberDTO;

import java.util.ArrayList;

/**
 * Created by inyoung on 2017-01-17.
 */
//BaseAdapter상속 받아서 3 + 1 오버라이드
public class ProfileAdapter extends BaseAdapter {
    private ArrayList<MemberDTO> profileList;
    private Context context;

    public ProfileAdapter(ArrayList<MemberDTO> profileList, Context context) {
        this.profileList = profileList;
        this.context = context;
    }

    public static ProfileAdapter profileInstance(ArrayList<MemberDTO> profileList, Context context){
        return new ProfileAdapter(profileList,context);
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
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //아이템 뷰에 인플레이터
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_profile, parent, false);

        //홀더에 뷰단 달아주기
        Holder holder = new Holder();
        holder.mImage = (ImageView) itemView.findViewById(R.id.mImage);
        holder.mName = (TextView) itemView.findViewById(R.id.mName);
        holder.mContent = (TextView) itemView.findViewById(R.id.mContent);

        //프로필리스트에서 이름이랑 정보 가져오기
        holder.mName.setText(profileList.get(position).getmName());
        holder.mContent.setText(profileList.get(position).getmData());

        //프로필리스트에서 이미지 가져오기
        Glide.with(context)
                .load(profileList.get(position).getmImage())
                .fitCenter()
                .into(holder.mImage);

        return itemView;
    }
}
