package com.example.inyoung.teamapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;

public class ProfileActivity extends AppCompatActivity {
    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int PICK_FROM_iMAGE=2;
    EditText profile_name, profile_email, profile_PhoneNum, profile_pw, profile_pwc;
    Button profile_change;
    ImageView profile_image2;
    private Uri mlmageCaptureUri;
    private Intent intent;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode != RESULT_OK)
            return;

        switch(requestCode)
        {
            case PICK_FROM_ALBUM:
            {
                mlmageCaptureUri = data.getData();
            }
            case PICK_FROM_CAMERA:
            {
                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(mlmageCaptureUri,"image/");

                //startActivityForResult(Intent,CROP_FROM_iMAGE);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        profile_name = (EditText) findViewById(R.id.profile_name);
        profile_email = (EditText) findViewById(R.id.profile_email);
        profile_PhoneNum = (EditText) findViewById(R.id.profile_PhoneNum);
        profile_pw = (EditText) findViewById(R.id.profile_pw);
        profile_pwc = (EditText) findViewById(R.id.profile_pwc);
        profile_change = (Button) findViewById(R.id.profile_change);
        profile_image2 = (ImageView) findViewById(R.id.profile_image2);


        profile_image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        doTakePhotoAction();
                    }
                };
                DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        doTakeAlbumAction();
                    }
                };


                new AlertDialog.Builder(v.getContext())
                        .setTitle("업로드할 이미지 선택")
                        .setPositiveButton("사진촬영",cameraListener)
                        .setNeutralButton("앨범선택",albumListener)
                        .setNegativeButton("취소",null)
                        .show();
            }
        });


    }

    public void doTakePhotoAction(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String url = "tmp_" + String.valueOf(System.currentTimeMillis())+".jpg";
        mlmageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),url));

        intent.putExtra(MediaStore.EXTRA_OUTPUT,mlmageCaptureUri);
        startActivityForResult(intent,PICK_FROM_CAMERA);

    }

    public void doTakeAlbumAction(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent,PICK_FROM_ALBUM);
    }
}
