package com.abdelmun3m.prototype;

import android.content.pm.PackageManager;
import android.location.*;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class LocationServices extends FragmentActivity implements OnMapReadyCallback,GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private GoogleMap mMap;
    Button btnfind;
    TextView place;

    private FusedLocationProviderApi locationprovider = com.google.android.gms.location.LocationServices.FusedLocationApi;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private  double mylatitude,mylongitude,mySpeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_services);


        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(com.google.android.gms.location.LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        locationRequest = new LocationRequest();
        locationRequest.setInterval(60000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Log.i("MainActivity", "msg5");
        place = (TextView) findViewById(R.id.txtplace);
        btnfind = (Button) findViewById(R.id.btnFindPath);
        btnfind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String type = place.getText().toString();
                //  StringBuilder googlePlacesUrl = new StringBuilder
                // ("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=31.037519,
                // 31.361164&radius=800&type=restaurant&keyword=cruise&key=AIzaSyDDfPTAeQB2gDcwU6nIUkjIaKrbBkHR4ug");

                StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/" +
                        "place/radarsearch/json?");//location=31.037519,%2031.361164&radius=500&type=hospital&key=AIzaSyDDfPTAeQB2gDcwU6nIUkjIaKrbBkHR4ug");

                googlePlacesUrl.append("location=" + mylatitude + "%20" + mylongitude);
                googlePlacesUrl.append("&radius=" + 500);
                googlePlacesUrl.append("&type=" + type);
                //googlePlacesUrl.append("&sensor=true");
                googlePlacesUrl.append("&key=" + GOOGLE_API_KEY);
                Log.i("MainActivity", "msg1");

                GooglePlacesReadTask googlePlacesReadTask = new GooglePlacesReadTask();
                Object[] toPass = new Object[2];
                toPass[0] = mMap;
                toPass[1] = googlePlacesUrl.toString();
                googlePlacesReadTask.execute(toPass);
            }
        });
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

    private static final String GOOGLE_API_KEY = "AIzaSyDDfPTAeQB2gDcwU6nIUkjIaKrbBkHR4ug";

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        Log.i("MainActivity", "msg3");
        // Add a marker in Sydney and move the camera
        LatLng mylocat = new LatLng(31.037519, 31.361164);
        mMap.addMarker(new MarkerOptions().position(mylocat).title("my location"));
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mylocat, 300));
        // mMap.moveCamera(CameraUpdateFactory.zoomIn());
        //  mMap.animateCamera(CameraUpdateFactory.zoomTo(12));
        Log.i("MainActivity", "msg2");

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        requestLocationUpdate();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

        mylatitude=location.getLatitude();
        mylongitude=location.getLongitude();
    }

    private void requestLocationUpdate() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        com.google.android.gms.location.LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        googleApiClient.connect();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (googleApiClient.isConnected()){
            requestLocationUpdate();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        com.google.android.gms.location.LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient,this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        googleApiClient.disconnect();
    }
}
