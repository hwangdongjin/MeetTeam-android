package com.example.inyoung.teamapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.inyoung.teamapp.RetroFit.NetworkService;
import com.example.inyoung.teamapp.RetroFit.SharedPreferenceUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener, GoogleMap.OnMarkerClickListener,GoogleMap.OnMapLongClickListener{

    GoogleMap mMap;
    String mylatitude="36.444445";
    String mylongitude="127.444555";
    Button map_findbutton;
    Button map_plusbutton;
    Button map_minusbutton;



    SharedPreferenceUtil sessDB;
    private NetworkService networkService;
    private ApplicationController application;
    public JSONArray jsonArray;
    JSONObject jsonObject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        //mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        map_plusbutton = (Button)findViewById(R.id.mapfr_plus);
        map_plusbutton.setOnClickListener(this);
        map_minusbutton = (Button)findViewById(R.id.mapfr_minus);
        map_minusbutton.setOnClickListener(this);
        map_findbutton = (Button) findViewById(R.id.mapfr_find);
        map_findbutton.setOnClickListener(this);


        go();
    }

    private void go() {
        application = ApplicationController.getInstance();
        application.buildNetworkService();
        networkService = ApplicationController.getInstance().getNetworkService();


        sessDB = new SharedPreferenceUtil(getApplicationContext());
        Call<ResponseBody> thumbnail = networkService.post_mapshow(sessDB.getRoomTitle(),sessDB.getDate());
        thumbnail.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                //좌표찍힘
                //LatLng latlng = new LatLng(36.444445,127.444555);
                //MarkerOptions opt = new MarkerOptions();
                //opt.position(latlng);
                //mMap.addMarker(opt);
                try {
                    if(response.isSuccess()){
                    jsonObject = new JSONObject(response.body().string());
                        Log.i("mytag","reason:"+jsonObject.get("places").toString());
                    jsonArray = new JSONArray(jsonObject.get("places").toString());
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject json = jsonArray.getJSONObject(i);
                        JSONObject json2 = new JSONObject(json.toString());
                        JSONObject json3 = new JSONObject(json2.get("loc").toString());
                        JSONObject json4 = new JSONObject(json3.toString());
                        JSONArray  jsonArray1 = new JSONArray(json4.get("coordinates").toString());
                        for(int j=0;j<1;j++) {
                            LatLng latLng = new LatLng(Double.parseDouble(jsonArray1.get(j + 1).toString()), Double.parseDouble(jsonArray1.get(j).toString()));
                            MarkerOptions opt = new MarkerOptions();
                            opt.position(latLng);
                            opt.title((String) json2.get("userName"));
                            mMap.addMarker(opt);
                        }
                        }
                    }
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


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng startingPoint = new LatLng(37.567174, 126.978158);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(startingPoint,10));
        mMap.setOnMarkerClickListener(this);
        mMap.setOnMapLongClickListener(this);





    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mapfr_find:
                break;
            case R.id.mapfr_plus:
                LatLng cameracenter2 = mMap.getProjection().getVisibleRegion().latLngBounds.getCenter();
                float zoom2 = mMap.getCameraPosition().zoom;
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cameracenter2,zoom2+1));
                break;
            case  R.id.mapfr_minus:
                LatLng cameracenter1 = mMap.getProjection().getVisibleRegion().latLngBounds.getCenter();
                float zoom = mMap.getCameraPosition().zoom;
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cameracenter1,zoom-1));
                break;
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        String text = "latitude ="+ marker.getPosition().latitude + "\n"+"longitude ="+ marker.getPosition().longitude;
        Toast.makeText(getApplicationContext(),text, Toast.LENGTH_LONG).show();

        application = ApplicationController.getInstance();
        application.buildNetworkService();
        networkService = ApplicationController.getInstance().getNetworkService();

        double longitude = marker.getPosition().longitude;
        double latitude = marker.getPosition().latitude;

        sessDB = new SharedPreferenceUtil(getApplicationContext());
        Call<ResponseBody> thumbnalCall = networkService.post_map(sessDB.getSess(),sessDB.getRoomTitle(),sessDB.getDate(),longitude,latitude);
        thumbnalCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                if(response.isSuccess()){



                    go();
                }

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });


        return false;
    }
    @Override
    public void onMapLongClick(LatLng latLng) {
        mMap.clear();
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.addMarker(markerOptions);
        Toast.makeText(getApplicationContext(),"위치를 확정하시려면 마커를 한번 더 클릭해주세요",Toast.LENGTH_LONG).show();



    }
}