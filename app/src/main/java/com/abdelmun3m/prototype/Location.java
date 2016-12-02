package com.abdelmun3m.prototype;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class Location {

    //--------------------------------------Properties----------------------------------------------
    private String id;
    //----------------------------------------------------------------------------------------------

    //---------------------------------------Methods------------------------------------------------
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
