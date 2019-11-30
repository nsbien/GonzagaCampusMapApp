package com.example.gonzagamapapp;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class FindMyClassesMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    static final String TAG = "FindMyClass";
    private GoogleMap mMap;

    private double coor1 = 0;
    private double coor2 = 0;
    private String course1;

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
        String building1 = intent.getStringExtra("building1");
        String room1 = intent.getStringExtra("room1");
//        Log.d(TAG, "Course: " + course1 + "  Building: " + building1 + "  Room: " + room1);
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

        // testing to see if the map will mark where the user wants (hard-coding)
        if(course1.equals("cpsc 223")){
            coor1 = 47.666394;
            coor2 = -117.402070;
        }

        // Add a marker where the first class is
        LatLng sydney = new LatLng(coor1, coor2);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Pacaar 106"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
