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
    private obstacleDB ODB;
   public ValueEventListener ObstacleListener ;
    //----------------------------------------------------------------------------------------------


    //-------------------------Methods--------------------------------------------------------------
    public Obstacle(float danger_Rate, float existing_Rate, int type) {
        this.ODB = new obstacleDB(danger_Rate , existing_Rate,type);
    }
    public  Obstacle(String id){
        this.ODB = getObstacle(id);
    }

    public Obstacle(){

    }


    public void decrease_Dangerous(){}

    public void get_Obstacle_Data(String id){}

    public void get_Obstacle_Id(Location location){}
    //----------------------------------------------------------------------------------------------


    //--------------------------DataBase Interaction Methods ---------------------------------------
    public void addNewObstacle(DatabaseReference R){
        String id = R.child("obstacle").push().getKey();
        R.child("obstacle").push().setValue(this.ODB);
        this.id = id;
    }
    public obstacleDB getObstacle(final String id){

        ValueEventListener ObstacleListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               Obstacle.this.ODB = dataSnapshot.child("obstacle").child(id).getValue(obstacleDB.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error : ", ""+databaseError.toException());
            }
        };
        this.ObstacleListener = ObstacleListener ;
        return  this.ODB;
    }
    //----------------------------------------------------------------------------------------------
}
