package com.example.inyoung.teamapp;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapCompassManager;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapLocationManager;
import com.nhn.android.maps.NMapView;
import com.nhn.android.mapviewer.overlay.NMapMyLocationOverlay;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;

/**
 * Created by MYpc on 2017-04-11.
 */

public class NaverMapActivity extends NMapActivity /*implements NMapView.OnMapStateChangeListener, NMapOverlayManager.OnCalloutOverlayListener*/{


    NMapOverlayManager mOverlayManager;
    // API-KEY

    public static final String API_KEY = "eyaWUe9Imj";

    // 네이버 맵 객체

    NMapView mMapView = null;
    private final String CLIENT_ID = "AJK1tte0Z6e5hN9tClDD";

    // 맵 컨트롤러

    NMapController mMapController = null;

    // 맵을 추가할 레이아웃

    LinearLayout MapContainer;


    // 오버레이의 리소스를 제공하기 위한 객체


    // 오버레이 관리자

    private NMapMyLocationOverlay mMyLocationOverlay;

    private NMapLocationManager mMapLocationManager;

    private NMapCompassManager mMapCompassManager;


    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMapView = new NMapView(this);

        setContentView(mMapView);

        //MapContainer = (LinearLayout) findViewById(R.id.activity_map);

        //MapContainer.addView(mMapView);

        mMapView.setClientId(CLIENT_ID);
        mMapView.setClickable(true);
        mMapView.setEnabled(true);
        mMapView.setFocusable(true);
        mMapView.setFocusableInTouchMode(true);
        mMapView.requestFocus();






        mMapView.setApiKey(API_KEY);




        mMapController = mMapView.getMapController();

        mMapView.setClickable(true);

        mMapView.setBuiltInZoomControls(true, null);

        //super.setMapDataProviderListener(onDataProviderListener);

        //mMapView.setOnMapStateChangeListener(this);






    }




/*

    @Override

    public void onMapInitHandler(NMapView mapview, NMapError errorInfo) {

        if (errorInfo == null) { // success

            startMyLocation();//현재위치로 이동

            mMapController.setMapCenter(new NGeoPoint(126.978371,

            37.5666091), 11);

        } else { // fail

            android.util.Log.e("NMAP", "onMapInitHandler: error=" + errorInfo.toString());

        }

    }






    @Override
    public void onMapCenterChange(NMapView nMapView, NGeoPoint nGeoPoint) {

    }

    @Override
    public void onMapCenterChangeFine(NMapView nMapView) {

    }

    @Override
    public void onZoomLevelChange(NMapView nMapView, int i) {

    }

    @Override
    public void onAnimationStateChange(NMapView nMapView, int i, int i1) {

    }

    @Override
    public NMapCalloutOverlay onCreateCalloutOverlay(NMapOverlay nMapOverlay, NMapOverlayItem nMapOverlayItem, Rect rect) {
        return null;
    }

    private void startMyLocation() {



        mMapLocationManager = new NMapLocationManager(this);

        mMapLocationManager

                .setOnLocationChangeListener(onMyLocationChangeListener);



        boolean isMyLocationEnabled = mMapLocationManager

                .enableMyLocation(true);

        if (!isMyLocationEnabled) {

            Toast.makeText(

                    getApplicationContext(),

                    "Please enable a My Location source in system settings",

                    Toast.LENGTH_LONG).show();



            Intent goToSettings = new Intent(

                    Settings.ACTION_LOCATION_SOURCE_SETTINGS);

            startActivity(goToSettings);

            finish();

        }else{



        }





mMapCompassManager = new NMapCompassManager(this);

        mMyLocationOverlay= mOverlayManager.createMyLocationOverlay(

mMapLocationManager, mMapCompassManager);



if (mMyLocationOverlay != null) {

if (!mOverlayManager.hasOverlay(mMyLocationOverlay)) {

mOverlayManager.addOverlay(mMyLocationOverlay);

}

if (mMapLocationManager.isMyLocationEnabled()) {

if (!mMapView.isAutoRotateEnabled()) {

mMyLocationOverlay.setCompassHeadingVisible(true);

mMapCompassManager.enableCompass();

mMapView.setAutoRotateEnabled(true, false);

MapContainer.requestLayout();

} else {

stopMyLocation();

}

mMapView.postInvalidate();

} else {
    isMyLocationEnabled=  mMapLocationManager.enableMyLocation(true);
if (!isMyLocationEnabled) {

					Toast.makeText(

							getApplicationContext(),

							"Please enable a My Location source in system settings",

							Toast.LENGTH_LONG).show();



					Intent goToSettings = new Intent(

							Settings.ACTION_LOCATION_SOURCE_SETTINGS);

					startActivity(goToSettings);



					return;

				}

		}

		}

    }



    private void stopMyLocation(){

        if (mMyLocationOverlay != null) {

            mMapLocationManager.disableMyLocation();



            if (mMapView.isAutoRotateEnabled()) {

                mMyLocationOverlay.setCompassHeadingVisible(false);



                mMapCompassManager.disableCompass();



                mMapView.setAutoRotateEnabled(false, false);



                MapContainer.requestLayout();

            }

        }

    }



    private final NMapActivity.OnDataProviderListener onDataProviderListener = new NMapActivity.OnDataProviderListener() {




        @Override

        public void onReverseGeocoderResponse(NMapPlacemark placeMark, NMapError errInfo) {



            if (errInfo != null) {

                Log.e("myLog", "Failed to findPlacemarkAtLocation: error=" + errInfo.toString());

                Toast.makeText(getApplicationContext(), errInfo.toString(), Toast.LENGTH_LONG).show();

                return;

            }else{

                Toast.makeText(getApplicationContext(), placeMark.toString(), Toast.LENGTH_LONG).show();

            }



        }



    };





    private final NMapLocationManager.OnLocationChangeListener onMyLocationChangeListener = new NMapLocationManager.OnLocationChangeListener() {



        @Override

        public boolean onLocationChanged(NMapLocationManager locationManager,

                                         NGeoPoint myLocation) {



		if (mMapController != null) {

		mMapController.animateTo(myLocation);

	}

            Log.d("myLog", "myLocation  lat " + myLocation.getLatitude());

            Log.d("myLog", "myLocation  lng " + myLocation.getLongitude());





            findPlacemarkAtLocation(myLocation.getLongitude(), myLocation.getLatitude());

            //위도경도를 주소로 변환



            return true;

        }



        @Override

        public void onLocationUpdateTimeout(NMapLocationManager locationManager) {





            Runnable runnable = new Runnable() {

            public void run() {

            stopMyLocation();

            }

            };

            runnable.run();



            Toast.makeText(getApplicationContext(),

                    "Your current location is temporarily unavailable.",

                    Toast.LENGTH_LONG).show();

        }



        @Override

        public void onLocationUnavailableArea(

                NMapLocationManager locationManager, NGeoPoint myLocation) {



            Toast.makeText(getApplicationContext(),

                    "Your current location is unavailable area.",

                    Toast.LENGTH_LONG).show();



            stopMyLocation();

        }
        */



    }









