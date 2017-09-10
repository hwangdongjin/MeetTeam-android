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

import com.bumptech.glide.Glide;
import com.example.inyoung.teamapp.ApplicationController;
import com.example.inyoung.teamapp.LoginActivity;
import com.example.inyoung.teamapp.ProfileActivity;
import com.example.inyoung.teamapp.R;
import com.example.inyoung.teamapp.RetroFit.NetworkService;
import com.example.inyoung.teamapp.RetroFit.SharedPreferenceUtil;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class SettingFragment extends Fragment {
    private static final int PICK_FROM_CAMERA = 0;
    View view;
    ImageView profile_image;
    private Uri mlmageCaptureUri;
    Button profile_button, logout_button;
    private Intent intent;

    SharedPreferenceUtil sessDB;
    private NetworkService networkService;
    private ApplicationController application;
    JSONObject jsonObject;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){


        view = inflater.inflate(R.layout.activity_listroom_fragment3, container, false);

        profile_image = (ImageView) view.findViewById(R.id.profile_image);
        application = ApplicationController.getInstance();
        application.buildNetworkService();
        networkService = ApplicationController.getInstance().getNetworkService();
        sessDB = new SharedPreferenceUtil(getContext());
        Call<ResponseBody> thumbnail = networkService.post_profile(sessDB.getSess());
        thumbnail.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                try {
                    jsonObject = new JSONObject(response.body().string());
                    Glide.with(getContext())
                            .load(jsonObject.get("photo").toString())
                            .fitCenter()
                            .into(profile_image);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });


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
