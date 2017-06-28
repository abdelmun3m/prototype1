package com.abdelmun3m.prototype;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class Location {

    //--------------------------------------Properties----------------------------------------------
    private String id;
    private double longtiude;
    private double latitude;
    private String address;
    private double distance;
    private double speed;
    //----------------------------------------------------------------------------------------------

    //---------------------------------------Methods------------------------------------------------
    public double getLongtiude() {
        return longtiude;
    }

    public void setLongtiude(double longtiude) {
        this.longtiude = longtiude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
    //----------------------------------------------------------------------------------------------



    //--------------------------------DataBase Interaction Methods ---------------------------------
    public void addNewLocation(DatabaseReference R, Location l){
        String id = R.child("location").push().getKey();
        R.child("location").push().setValue(this);
        this.id = id;
    }

    public ValueEventListener getLocation(){
        ValueEventListener UserListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Location  l = dataSnapshot.getValue(Location.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error : ", ""+databaseError.toException());
            }
        };
        return  UserListener;
    }
    //----------------------------------------------------------------------------------------------
}
