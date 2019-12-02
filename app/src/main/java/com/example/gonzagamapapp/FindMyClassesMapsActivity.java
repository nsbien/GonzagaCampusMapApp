package com.example.gonzagamapapp;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class FindMyClassesMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    static final String TAG = "FindMyClass";
    static final int MY_LOCATION_REQUEST_CODE = 1;
    private GoogleMap mMap;

    private String course1, building1, room1;
    private String course2, building2, room2;
    private String course3, building3, room3;
    private String course4, building4, room4;
    private String course5, building5, room5;
    private String course6, building6, room6;
    private String course7, building7, room7;

    /**
     Starts google maps and displays every class that the user had inputted
     *
     * @param Bundle savedInstanceState
     * @return n/a
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_my_classes_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        course1 = intent.getStringExtra("course1");
        building1 = intent.getStringExtra("building1");
        room1 = intent.getStringExtra("room1");
        course2 = intent.getStringExtra("course2");
        building2 = intent.getStringExtra("building2");
        room2 = intent.getStringExtra("room2");
        course3 = intent.getStringExtra("course3");
        building3 = intent.getStringExtra("building3");
        room3 = intent.getStringExtra("room3");
        course4 = intent.getStringExtra("course4");
        building4 = intent.getStringExtra("building4");
        room4 = intent.getStringExtra("room4");
        course5 = intent.getStringExtra("course5");
        building5 = intent.getStringExtra("building5");
        room5 = intent.getStringExtra("room5");
        course6 = intent.getStringExtra("course6");
        building6 = intent.getStringExtra("building6");
        room6 = intent.getStringExtra("room6");
        course7 = intent.getStringExtra("course7");
        building7 = intent.getStringExtra("building7");
        room7 = intent.getStringExtra("room7");
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
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_LOCATION_REQUEST_CODE);
        }

        // testing to see if the map will mark where the user wants (hard-coding)
        if (!course1.equals("")){
            LatLng courseOne = determineBuilding(building1);
            mMap.addMarker(new MarkerOptions().position(courseOne).title(course1 + " Room:" + room1));
        }
        if (!course2.equals("")){
            LatLng courseOne = determineBuilding(building2);
            mMap.addMarker(new MarkerOptions().position(courseOne).title(course2 + " Room:" + room2));
        }
        if (!course3.equals("")){
            LatLng courseOne = determineBuilding(building3);
            mMap.addMarker(new MarkerOptions().position(courseOne).title(course3 + " Room:" + room3));
        }
        if (!course4.equals("")){
            LatLng courseOne = determineBuilding(building4);
            mMap.addMarker(new MarkerOptions().position(courseOne).title(course4 + " Room:" + room4));
        }
        if (!course5.equals("")){
            LatLng courseOne = determineBuilding(building5);
            mMap.addMarker(new MarkerOptions().position(courseOne).title(course5 + " Room:" + room5));
        }
        if (!course6.equals("")){
            LatLng courseOne = determineBuilding(building6);
            mMap.addMarker(new MarkerOptions().position(courseOne).title(course6 + " Room:" + room6));
        }
        if (!course7.equals("")){
            LatLng courseOne = determineBuilding(building7);
            mMap.addMarker(new MarkerOptions().position(courseOne).title(course7 + " Room:" + room7));
        }

        LatLng gonzaga = new LatLng(47.6664, -117.4015);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(gonzaga, 15.3f));
    }

    /**
     * The Alert Dialog that asks for user's permission
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_LOCATION_REQUEST_CODE) {
            if (permissions.length == 1 &&
                    permissions[0].equals(Manifest.permission.ACCESS_FINE_LOCATION) &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
            }
        }
    }

    public LatLng determineBuilding(String building) {
        double lat =0.0, lng = 0.0;

        if (building.equals("College Hall")) {
            lat = 47.668148;
            lng = -117.402357;
        } else if (building.equals("Coughlin")) {
            lat = 47.664871;
            lng = -117.396923;
        } else if (building.equals("Crosby")) {
            lat = 47.666783;
            lng = -117.402173;
        } else if (building.equals("Hemmingson")) {
            lat = 47.667108;
            lng = -117.398625;
        } else if (building.equals("Herek")) {
            lat = 47.666752;
            lng = -117.402116;
        } else if (building.equals("Hughes")) {
            lat = 47.667134;
            lng = -117.403190;
        } else if (building.equals("Jepson")) {
            lat = 47.667134;
            lng = -117.405254;
        } else if (building.equals("Jundt")) {
            lat = 47.666349;
            lng = -117.406907;
        } else if (building.equals("Paccar")) {
            lat = 47.666372;
            lng = -117.401954;
        } else if (building.equals("Rosar")) {
            lat = 47.668138;
            lng = -117.399131;
        } else { // tilford
            lat = 47.667639;
            lng = -117.408705;
        }
        return new LatLng(lat, lng);
    }
}
