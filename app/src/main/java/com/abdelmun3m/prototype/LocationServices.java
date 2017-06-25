package com.abdelmun3m.prototype;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class LocationServices extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Button btnfind;
    TextView place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_services);


        Log.i("MainActivity", "msg4");
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

                googlePlacesUrl.append("location=" + 31.037519 + "%20" + 31.361164);
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
}

