package com.example.inyoung.teamapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener, GoogleMap.OnMarkerClickListener,GoogleMap.OnMapLongClickListener{

    GoogleMap mMap;


    String mylatitude="36.444445";
    String mylongitude="127.444555";

    Button map_findbutton;
    Button map_plusbutton;
    Button map_minusbutton;

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
        return false;
    }


    @Override
    public void onMapLongClick(LatLng latLng) {
        mMap.clear();
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);

        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.addMarker(markerOptions);
    }
}
