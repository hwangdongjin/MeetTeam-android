package com.example.inyoung.teamapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.inyoung.teamapp.RetroFit.NetworkService;
import com.example.inyoung.teamapp.RetroFit.SharedPreferenceUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

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

    static int year1,month1,dayOfMonth1;
    Intent intent;

    Button DateSelectButton;
    TextView DateSelectView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        //mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        DateSelectButton=(Button)findViewById(R.id.btn_mapcal);
        DateSelectView = (TextView)findViewById(R.id.tv_mapcal);

        final DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int fix_month=month+1;
                int last_date = dayOfMonth + 6;
                DateSelectView.setText(year+"년 "+fix_month+"월 "+dayOfMonth+"일");
                year1 = year;
                month1= fix_month;
                dayOfMonth1= dayOfMonth;
                go();
            }
        };

        DateSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                DatePickerDialog dlg = new DatePickerDialog(MapActivity.this , listener, c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
                dlg.show();
            }
        });
//        intent = getIntent();
//        year1 = intent.getIntExtra("year",0);
//        month1=intent.getIntExtra("month",0);
//        dayOfMonth1=intent.getIntExtra("day",0);

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
        Call<ResponseBody> thumbnail = networkService.post_mapshow(sessDB.getRoomTitle(),/*sessDB.getDate()*/year1+"-"+month1+"-"+dayOfMonth1);
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
                        JSONObject place = jsonArray.getJSONObject(i);
                        JSONObject loc = new JSONObject(place.get("loc").toString());
                        JSONArray  coordinates = new JSONArray(loc.get("coordinates").toString());

                        LatLng latLng = new LatLng(Double.parseDouble(coordinates.get(1).toString()), Double.parseDouble(coordinates.get(0).toString()));
                        MarkerOptions opt = new MarkerOptions();
                        opt.position(latLng);
                        opt.title((String) place.get("userName"));
                        mMap.addMarker(opt).showInfoWindow();

//                        for(int j=0;j<1;j++) {
//                            LatLng latLng = new LatLng(Double.parseDouble(coordinates.get(j + 1).toString()), Double.parseDouble(coordinates.get(j).toString()));
//                            MarkerOptions opt = new MarkerOptions();
//                            opt.position(latLng);
//                            opt.title((String) place.get("userName"));
//                            mMap.addMarker(opt).showInfoWindow();
//                        }
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
                application = ApplicationController.getInstance();
                application.buildNetworkService();
                networkService = ApplicationController.getInstance().getNetworkService();
                sessDB = new SharedPreferenceUtil(getApplicationContext());
                Call<ResponseBody> thumbnail = networkService.post_mapshow(sessDB.getRoomTitle(),/*sessDB.getDate()*/year1+"-"+month1+"-"+dayOfMonth1);

                final ArrayList<Double> array1= new ArrayList<>();
                final ArrayList<Double> array2= new ArrayList<>();

                thumbnail.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                        try {
                            if(response.isSuccess()) {
                                jsonObject = new JSONObject(response.body().string());
                                jsonArray = new JSONArray(jsonObject.get("places").toString());
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject place = jsonArray.getJSONObject(i);
                                    JSONObject loc = new JSONObject(place.get("loc").toString());
                                    JSONArray coordinates = new JSONArray(loc.get("coordinates").toString());

                                    array1.add(Double.parseDouble((String) coordinates.get(1)));
                                    array2.add(Double.parseDouble((String) coordinates.get(0)));
                                    //Double q = Double.parseDouble((String) coordinates.get(1));
                                    //String k = String.format("%.8f",q);
                                    //array1.add(Double.parseDouble(k));
                                    Log.i("mytag2", "arr1:" + array1);
                                }

                                double sum1=0;
                                double sum2=0;
                                for(int i=0;i<jsonArray.length();i++){
                                    sum1 += array1.get(i);
                                    sum2 += array2.get(i);
                                }
                                LatLng latLng = new LatLng(sum1/(double)jsonArray.length(), sum2/jsonArray.length());
                                Log.i("mytag3", "arr1:" + latLng);
                                MarkerOptions opt = new MarkerOptions();
                                opt.title("중간지점");
                                opt.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                                opt.position(latLng);
                                mMap.addMarker(opt).showInfoWindow();

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
        //String text = "latitude ="+ marker.getPosition().latitude + "\n"+"longitude ="+ marker.getPosition().longitude;
        //String text = "위치가 등록되었습니다.";
        //Toast.makeText(getApplicationContext(),text, Toast.LENGTH_LONG).show();

//        application = ApplicationController.getInstance();
//        application.buildNetworkService();
//        networkService = ApplicationController.getInstance().getNetworkService();
//
//        double longitude = marker.getPosition().longitude;
//        double latitude = marker.getPosition().latitude;
//
//        sessDB = new SharedPreferenceUtil(getApplicationContext());
//        Call<ResponseBody> thumbnalCall = networkService.post_map(sessDB.getSess(),sessDB.getRoomTitle(),/*sessDB.getDate()*/year1+"-"+month1+"-"+dayOfMonth1,longitude,latitude);
//        thumbnalCall.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
//                if(response.isSuccess()){
//                    go();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//
//            }
//        });
        return false;
    }
    @Override
    public void onMapLongClick(LatLng latLng) {
        mMap.clear();
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.addMarker(markerOptions);

        application = ApplicationController.getInstance();
        application.buildNetworkService();
        networkService = ApplicationController.getInstance().getNetworkService();

        double longitude = latLng.longitude;
        double latitude = latLng.latitude;

        sessDB = new SharedPreferenceUtil(getApplicationContext());
        Call<ResponseBody> thumbnalCall = networkService.post_map(sessDB.getSess(),sessDB.getRoomTitle(),/*sessDB.getDate()*/year1+"-"+month1+"-"+dayOfMonth1,longitude,latitude);
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
    }
}