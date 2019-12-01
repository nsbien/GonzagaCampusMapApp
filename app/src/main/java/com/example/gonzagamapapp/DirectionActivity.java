package com.example.gonzagamapapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class DirectionActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationClickListener {

    static final int MY_LOCATION_REQUEST_CODE = 1;
    private GoogleMap mMap;
    private static final String TAG = DirectionActivity.class.getSimpleName();
    private CameraPosition mCameraPosition;

    /**
     Allows the user to input the classes that they are in with the associated building
     and room number
     *
     * @return n/a
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Button navigateButton = (Button) findViewById(R.id.navigateButton);
        navigateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DirectionActivity.this, "pushed me!", Toast.LENGTH_SHORT).show();
            }
        });

//        mGeoDataClient = Places.getGeoDataClient(this, null);

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
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        addGonzagaMarker();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            mMap.setOnMyLocationClickListener(this);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_LOCATION_REQUEST_CODE);
        }

//        LatLng gonzaga = new LatLng(47.647536, -117.408985); // coordinates to Spokane not gonzaga but close enough right?
//        mMap.addMarker(new MarkerOptions().position(gonzaga).title("Here is Gonzaga!"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(gonzaga));
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
                mMap.setOnMyLocationClickListener(this);
            }
        }
    }

    /**
     * Adds Marker at Gonzaga University through the use of GeoCoder so the data is dynamically sourced
     * Moves camera so that it is at City View
     */
    private void addGonzagaMarker() {
        String gonzagaStr = "Gonzaga University";
        LatLng gonzagaLatLng = getLatLngUsingGeocoding(gonzagaStr);
        MarkerOptions gonzagaMarkerOptions = new MarkerOptions();
        gonzagaMarkerOptions.title(gonzagaStr);
        gonzagaMarkerOptions.snippet("Go zags!");
        gonzagaMarkerOptions.position(gonzagaLatLng);
        mMap.addMarker(gonzagaMarkerOptions);

        CameraUpdate gonzagaCameraUpdate = CameraUpdateFactory.newLatLngZoom(gonzagaLatLng, 15.0f);
        mMap.moveCamera(gonzagaCameraUpdate);
    }

    /**
     * Retrieves the Latitude and Longitude using Geocoding of Gonzaga University
     * @return Lat and Lang of GU campus
     */
    private LatLng getLatLngUsingGeocoding(String gonzagaStr) {
        LatLng latLng = null;
        Geocoder geocoder = new Geocoder(this);
        try {
            List<Address> addressList = geocoder.getFromLocationName(gonzagaStr, 1);
            if (addressList != null && addressList.size() > 0) {
                Address addressResult = addressList.get(0);
                latLng = new LatLng(addressResult.getLatitude(), addressResult.getLongitude());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return latLng;
    }

//    private void getLocationPermission() {
//        /*
//         * Request location permission, so that we can get the location of the
//         * device. The result of the permission request is handled by a callback,
//         * onRequestPermissionsResult.
//         */
//        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
//                android.Manifest.permission.ACCESS_FINE_LOCATION)
//                == PackageManager.PERMISSION_GRANTED) {
//            mLocationPermissionGranted = true;
//        } else {
//            ActivityCompat.requestPermissions(this,
//                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
//                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
//        }
//    }

    /**
     * Gets the user's location in latitude and longitude
     */
    @Override
    public void onMyLocationClick(@NonNull Location location) {
        // executes when user clicks on their blue dot
        Toast.makeText(this, "You're at: " + location.getLatitude() + ", " + location.getLongitude(),
                Toast.LENGTH_SHORT).show();
    }
}