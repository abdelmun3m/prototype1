package com.abdelmun3m.prototype;


import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public class PlacesDisplayTask extends AsyncTask<Object, Integer, List<HashMap<String, String>>> {

    JSONObject googlePlacesJson;
    GoogleMap googleMap;

    @Override
    protected List<HashMap<String, String>> doInBackground(Object... inputObj) {

        List<HashMap<String, String>> googlePlacesList = null;
        Places placeJsonParser = new Places();

        try {
            googleMap = (GoogleMap) inputObj[0];
            googlePlacesJson = new JSONObject((String) inputObj[1]);
            googlePlacesList = placeJsonParser.parse(googlePlacesJson);
        } catch (Exception e) {
            Log.d("Exception", e.toString());
        }
        return googlePlacesList;
    }

    @Override
    protected void onPostExecute(List<HashMap<String, String>> list) {
        googleMap.clear();
        Log.i("MainActivity","msg15");
        LatLng mylocat = new LatLng(31.037519, 31.361164);
        googleMap.addMarker(new MarkerOptions().position(mylocat).title("my location"));

        // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mylocat,200));

        for (int i = 0; i < list.size(); i++) {
            Log.i("MainActivity","msg16");
            MarkerOptions markerOptions = new MarkerOptions();
            HashMap<String, String> googlePlace = list.get(i);
            double lat = Double.parseDouble(googlePlace.get("lat"));
            double lng = Double.parseDouble(googlePlace.get("lng"));
           // String placeName = googlePlace.get("place_name");
           // String vicinity = googlePlace.get("vicinity");
            LatLng latLng = new LatLng(lat, lng);
            markerOptions.position(latLng);
           // markerOptions.title(placeName + " : " + vicinity);
            googleMap.addMarker(markerOptions);


        }
    }
}