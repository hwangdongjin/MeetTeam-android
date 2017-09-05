package com.example.inyoung.teamapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.TextView;

import com.example.inyoung.teamapp.RetroFit.NetworkService;
import com.example.inyoung.teamapp.RetroFit.SharedPreferenceUtil;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ProfileActivity extends AppCompatActivity {
    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_iMAGE=2;
    EditText   profile_email, profile_PhoneNum, profile_pw, profile_pwc, profile_add;
    TextView profile_name, profile_id, profile_idNum;
    Button profile_change;
    ImageView profile_image2;
    private Uri mlmageCaptureUri;
    private Intent intent;
    private String absolutePath;

    SharedPreferenceUtil sessDB;
    private NetworkService networkService;
    private ApplicationController application;
    public JSONArray jsonArray;
    JSONObject jsonObject;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profile_name = (TextView) findViewById(R.id.profile_name);
        profile_id = (TextView) findViewById(R.id.profile_id);
        profile_email = (EditText) findViewById(R.id.profile_email);
        profile_PhoneNum = (EditText) findViewById(R.id.profile_PhoneNum);
        profile_pw = (EditText) findViewById(R.id.profile_pw);
        profile_pwc = (EditText) findViewById(R.id.profile_pwc);
        profile_change = (Button) findViewById(R.id.profile_change);
        profile_image2 = (ImageView) findViewById(R.id.profile_image2);
        profile_idNum = (TextView) findViewById(R.id.profile_idNum);
        profile_add = (EditText) findViewById(R.id.profile_add);





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




        application = ApplicationController.getInstance();
        application.buildNetworkService();
        networkService = ApplicationController.getInstance().getNetworkService();

        sessDB = new SharedPreferenceUtil(getApplicationContext());
        Call<ResponseBody> thumbnail = networkService.post_profile(sessDB.getSess());
        thumbnail.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                try {
                    jsonObject = new JSONObject(response.body().string());
                    profile_name.setText(jsonObject.get("name").toString());
                    profile_id.setText(jsonObject.get("id").toString());
                    profile_idNum.setText(jsonObject.get("idNum").toString());
                    profile_email.setText(jsonObject.get("email").toString());
                    profile_PhoneNum.setText(jsonObject.get("phoneNum").toString());
                    profile_add.setText(jsonObject.get("addr").toString());

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


        profile_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ResponseBody> thumbnail2 = networkService.post_profileup(sessDB.getSess(), profile_pw.getText().toString(),
                        profile_PhoneNum.getText().toString(), profile_add.getText().toString(), profile_email.getText().toString());
                thumbnail2.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                        intent = new Intent(getApplicationContext(), ListroomActivity.class);
                        startActivity(intent);
                        //Fragment fr = new SettingFragment();
                        //FragmentManager fm = getFragmentManager();
                        //FragmentTransaction fragmentTransaction = fm.beginTransaction();

                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });
            }
        });



    }//oncreate

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

                intent.putExtra("outputX",200);
                intent.putExtra("outputY",200);
                intent.putExtra("aspectX",1);
                intent.putExtra("aspectY",1);
                intent.putExtra("scale",true);
                intent.putExtra("return-data",true);
                startActivityForResult(intent,CROP_FROM_iMAGE);
                break;
            }
            case CROP_FROM_iMAGE:{
                if (resultCode!=RESULT_OK){
                    return;
                }
                final Bundle extras = data.getExtras();

                String filePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SmartWheel/"+System.currentTimeMillis()+".jpg";

                if(extras!=null)
                {
                    Bitmap photo = extras.getParcelable("data");
                    profile_image2.setImageBitmap(photo);

                    storeCropImage(photo,filePath);
                    absolutePath = filePath;
                    break;
                }
                File f = new File(mlmageCaptureUri.getPath());
                if (f.exists())
                {
                    f.delete();
                }
            }
        }
    }


    private void storeCropImage(Bitmap bitmap,String filePath){
        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SmartWheel";
        File directory_SmartWheel = new File(dirPath);

        if(!directory_SmartWheel.exists())
            directory_SmartWheel.mkdir();

        File copyFile = new File(filePath);
        BufferedOutputStream out = null;

        try{
            copyFile.createNewFile();
            out = new BufferedOutputStream(new FileOutputStream(copyFile));
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,out);
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE));
            Uri.fromFile(copyFile);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
