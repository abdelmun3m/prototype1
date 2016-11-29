package com.abdelmun3m.prototype;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.PropertyName;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Abdelmunem on 11/4/2016.
 */

public class User {

//-----------------------------------------Properties-----------------------------------------------
    private String name;
    private String password;
    private String e_mail;
    private String governorate;
    private String city;
    private String id;
    private float distance_N;
    private float moving_Speed;
    private boolean authentication;
    private boolean notification_Mode;
    private boolean isMoving;
    private Location location;
    //----------------------------------------------------------------------------------------------


    //-------------------------------------Methods--------------------------------------------------
    public User(String name, String password, String e_mail,
                String governorate, String city, String id, float distance_N,
                float moving_Speed, boolean authentication, boolean notification_Mode,
                boolean isMoving, Location location) {
        this.name = name;
        this.password = password;
        this.e_mail = e_mail;
        this.governorate = governorate;
        this.city = city;
        this.id = id;
        this.distance_N = distance_N;
        this.moving_Speed = moving_Speed;
        this.authentication = authentication;
        this.notification_Mode = notification_Mode;
        this.isMoving = isMoving;
        this.location = location;
    }
    public User(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public String getGovernorate() {
        return governorate;
    }

    public void setGovernorate(String governorate) {
        this.governorate = governorate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getDistance_N() {
        return distance_N;
    }

    public void setDistance_N(float distance_N) {
        this.distance_N = distance_N;
    }

    public float getMoving_Speed() {
        return moving_Speed;
    }

    public void setMoving_Speed(float moving_Speed) {
        this.moving_Speed = moving_Speed;
    }

    public boolean isAuthentication() {
        return authentication;
    }

    public void setAuthentication(boolean authentication) {
        this.authentication = authentication;
    }

    public boolean isNotification_Mode() {
        return notification_Mode;
    }

    public void setNotification_Mode(boolean notification_Mode) {
        this.notification_Mode = notification_Mode;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


    public void update_location(Location location){}

    public float calculate_speed(){return (float) .2;}

    public void get_user_data(String id){}
    //----------------------------------------------------------------------------------------------



    //----------------DataBase Interaction Methods--------------------------------------------------
    public void addNewUser(DatabaseReference R, User u){
        String id = R.child("user").push().getKey();
        R.child("user").push().setValue(this);
        this.id = id;
    }
    public ValueEventListener getUser(){

        ValueEventListener UserListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User u = dataSnapshot.getValue(User.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("loadPost:onCancelled", ""+databaseError.toException());
            }
        };
        return  UserListener;
    }
    //----------------------------------------------------------------------------------------------
}
