package com.example.dlarb.vacationproject2018;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GoogleMap extends AppCompatActivity implements OnMapReadyCallback {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private static Context baseContext;
    private com.google.android.gms.maps.GoogleMap mMap;

    double mlat;
    double mlng;
    SharedPreferences mPrefs;

    private GpsInfo gpsinfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_googlemap);
        gpsinfo = new GpsInfo(this);
        mlat = gpsinfo.getLatitude();
        mlng = gpsinfo.getLongitude();

        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        tb.setSubtitle("Your Location");

        Log.e("GoogleMapCreated", "OnComplete");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    @Override
    protected void onDestroy() {
        gpsinfo.stopUsingGPS();
        super.onDestroy();
    }

    @Override
    public void onMapReady(com.google.android.gms.maps.GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMyLocationButtonClickListener(onMyLocationButtonClickListener);
        mMap.setOnMyLocationClickListener(onMyLocationClickListener);
        enableMyLocationIfPermitted();

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMinZoomPreference(11);
    }


    private void enableMyLocationIfPermitted() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else if (mMap != null) {
            mMap.setMyLocationEnabled(true);
        }
    }

    private void showDefaultLocation() {
        Toast.makeText(this, "Location permission not granted, " + "showing default location", Toast.LENGTH_SHORT).show();
        LatLng redmond = new LatLng(37.542766, 126.967056);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(redmond));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    enableMyLocationIfPermitted();
                } else {
                    showDefaultLocation();
                }
                return;
            }

        }
    }

    private com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener onMyLocationButtonClickListener =
            new com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener() {
                @Override
                public boolean onMyLocationButtonClick() {
                    mMap.setMinZoomPreference(15);
                    return false;
                }
            };


    private com.google.android.gms.maps.GoogleMap.OnMyLocationClickListener onMyLocationClickListener =
            new com.google.android.gms.maps.GoogleMap.OnMyLocationClickListener() {
                @Override
                public void onMyLocationClick(Location location) {
                    mMap.setMinZoomPreference(12);

                    CircleOptions circleOptions = new CircleOptions();
                    circleOptions.center(new LatLng(location.getLatitude(), location.getLongitude()));

                    circleOptions.radius(200);
                    circleOptions.fillColor(Color.RED);
                    circleOptions.strokeWidth(6);

                    mMap.addCircle(circleOptions);

                }
            };


    public static String getAddress(Context mContext, double lat, double lng) {
        String nowAddress = "현재 위치를 확인 할 수 없습니다.";
        Geocoder geocoder = new Geocoder(mContext, Locale.KOREA);
        List<Address> address;
        try {
            //세번째 파라미터는 좌표에 대해 주소를 리턴 받는 갯수로
            //한좌표에 대해 두개이상의 이름이 존재할수있기에 주소배열을 리턴받기 위해 최대갯수 설정
            address = geocoder.getFromLocation(lat, lng, 1);

            if (address != null && address.size() > 0) {
                // 주소 받아오기
                nowAddress = address.get(0).getAddressLine(0);

            }

        } catch (IOException e) {
            Toast.makeText(baseContext, "주소를 가져 올 수 없습니다.", Toast.LENGTH_LONG).show();

            e.printStackTrace();
        }
        return nowAddress;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("Address", getAddress(this, mlat, mlng));
        gpsinfo.stopUsingGPS();
        setResult(334, intent);
        finish();
    }


}