package com.abdelmun3m.prototype;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Abdelmunem on 11/4/2016.
 */

public class Obstacle {

    //-----------------------------------Properties-------------------------------------------------
    private float danger_Rate;
    private float existing_Rate;
    private String id;
    //private int location_id;
    private int type;
    //----------------------------------------------------------------------------------------------


    //-------------------------Methods--------------------------------------------------------------
    public Obstacle(float danger_Rate, float existing_Rate, String id, int type) {
        this.danger_Rate = danger_Rate;
        this.existing_Rate = existing_Rate;
        this.id = id;
        this.type = type;
    }

    public Obstacle(){

    }


    public void decrease_Dangerous(){}

    public void get_Obstacle_Data(String id){}

    public void get_Obstacle_Id(Location location){}
    //----------------------------------------------------------------------------------------------


    //--------------------------DataBase Interaction Methods ---------------------------------------
    public void addNewObstacle(DatabaseReference R, Obstacle o){
        String id = R.child("obstacle").push().getKey();
        R.child("obstacle").push().setValue(this);
        this.id = id;
    }
    public ValueEventListener getObstacle(){

        ValueEventListener UserListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Obstacle o = dataSnapshot.getValue(Obstacle.class);
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
