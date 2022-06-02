package com.example.t3_android;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.t3_android.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    static final int REQUEST_GPS_PERMISSION = 1001;
    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    public float t1Longitud,t1Latitud;
//    public float t2Longitud,t2Latitud;
//    public float t3Longitud,t3Latitud;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent=getIntent();
        String t1=intent.getStringExtra("tienda1");



        Log.d("holaaaaaaaaaaa----------",String.valueOf(t1));

        String a="";
        String b="";
        a=t1.split(",")[0];
        b=t1.split(",")[1];

        float f1=Float.parseFloat(a);
        float f2=Float.parseFloat(b);
        t1Longitud=f1;
        t1Latitud=f2;

        Log.d("holaaaaaaaaaaa-----123123123-----",String.valueOf(a));
        Log.d("holaaaaaaaaaaa-----123123123-----",String.valueOf(b));



        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        if (!hasGPSPermissions()){
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
            }, REQUEST_GPS_PERMISSION);
        }

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,500,1,this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Log.d("holaaaaaaaaaaa---latitud------",String.valueOf(t1Latitud));
        Log.d("holaaaaaaaaaaa---longitud------",String.valueOf(t1Longitud));
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(t1Latitud, t1Longitud);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,15f));
    }
    private boolean hasGPSPermissions(){
        return checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }
}