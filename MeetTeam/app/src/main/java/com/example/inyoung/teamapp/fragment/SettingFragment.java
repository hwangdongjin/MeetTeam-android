package com.example.inyoung.teamapp.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.inyoung.teamapp.LoginActivity;
import com.example.inyoung.teamapp.ProfileActivity;
import com.example.inyoung.teamapp.R;

import java.io.File;

public class SettingFragment extends Fragment {
    private static final int PICK_FROM_CAMERA = 0;
    View view;
    ImageView profile_image;
    private Uri mlmageCaptureUri;
    Button profile_button, logout_button;
    private Intent intent;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){


        view = inflater.inflate(R.layout.activity_listroom_fragment3, container, false);





        profile_image = (ImageView) view.findViewById(R.id.profile_image);

        profile_button = (Button) view.findViewById(R.id.profile_button);
        profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), ProfileActivity.class);
                startActivity(intent);
            }
        });

        logout_button = (Button) view.findViewById(R.id.logout_button);
        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(),LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });



        return view;
    }

    public void doTakePhotoAction(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String url = "tmp_" + String.valueOf(System.currentTimeMillis())+".jpg";
        mlmageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),url));

        intent.putExtra(MediaStore.EXTRA_OUTPUT,mlmageCaptureUri);
        startActivityForResult(intent,PICK_FROM_CAMERA);

    }

}
