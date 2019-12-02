package com.example.gonzagamapapp;
// https://developers.google.com/maps/documentation/urls/android-intents

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;

public class DirectionActivity extends AppCompatActivity implements
        OnMapReadyCallback,
        GoogleMap.OnMyLocationClickListener{

    static final int MY_LOCATION_REQUEST_CODE = 1;
    private GoogleMap mMap;
    private static final String TAG = DirectionActivity.class.getSimpleName();
    private CameraPosition mCameraPosition;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    static final int LOCATION_REQUEST_CODE = 1;

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
                Uri intentUri = Uri.parse("google.navigation:q = 47.6664,-117.4015");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, intentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

//        mGeoDataClient = Places.getGeoDataClient(this, null);
        mFusedLocationProviderClient =
                LocationServices.getFusedLocationProviderClient(this);

        setupLastKnownLocation();
        setupUserLocationUpdates();
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
    }


    private void setupLastKnownLocation() {
        // implementing approach (1.)
        // starting with api level 23, at runtime we have to request/make sure
        // we have permission to access the users location

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            // we don't have permission to access the user's location
            // we need to request it
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_REQUEST_CODE);
            // shows a dialog for the user to select allow or deny for location permission
            // we need to override a callback that executes once the user makes their choice
        } else {
            // we have permission!!! to access the user's location
            Task<Location> locationTask = mFusedLocationProviderClient.getLastLocation();
            // add a complete/successful/failure listener so we know when the task is done
            locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    // at this point the parameter location should store
                    // the users last known location
                    // location could be null if the device does not have a last known location
                    if (location != null) {
                        // we have it finally!
                        Log.d(TAG, "onSuccess: " + location.getLatitude() + ", " + location.getLongitude());
                    }
                }
            });
        }
    }



    private void setupUserLocationUpdates() {
        final LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000); // request an update every 10 seconds
        locationRequest.setFastestInterval(5000); // handle at most updates every 5 seconds
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY); // most precise

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(locationRequest);
        SettingsClient client = LocationServices.getSettingsClient(this);
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());

        task.addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                // All location settings are satisfied. The client can initialize
                // location requests here.
                LocationCallback locationCallback = new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        Log.d(TAG, "onLocationResult: ");
                        for (Location location : locationResult.getLocations()) {
                            Log.d(TAG, "onSuccess: " + location.getLatitude() + ", " + location.getLongitude());
                        }
                    }

                };
                if (ActivityCompat.checkSelfPermission(DirectionActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(DirectionActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            LOCATION_REQUEST_CODE);
                } else {
                    Log.d(TAG, "onSuccess: We have the user's permission");
                    mFusedLocationProviderClient.requestLocationUpdates(locationRequest,
                            locationCallback, null);
                }
            }
        });
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
        // this method executes when the user responds to the permissions dialog
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // we have the user's permission!!
                setupLastKnownLocation();
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