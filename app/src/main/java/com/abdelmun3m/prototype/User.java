package com.abdelmun3m.prototype;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.PropertyName;
import com.google.firebase.database.ValueEventListener;


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
    private int authentication;
    private int notification_Mode;
    private boolean isMoving;
    private Location location;
    private userDB UDB;
    private DatabaseReference userDBReference = FirebaseDatabase.getInstance().getReference().child("user");
    public ValueEventListener UserListener ;
    //----------------------------------------------------------------------------------------------


    //-------------------------------------Methods--------------------------------------------------
    public User(String name, String password, String e_mail,
                String governorate, String city, float distance_N,
                int authentication, int notification_Mode
                ){
      this.UDB = new userDB(name , password , e_mail , governorate , city ,distance_N , authentication , notification_Mode);
    }

    public User(String Id){
        this.UDB = getUser(Id);
        this.name = this.UDB.name;
        this.password = this.UDB.password;
        this.e_mail = this.UDB.e_mail;
        this.governorate = this.UDB.governorate;
        this.city = this.UDB.city;
        this.distance_N = this.UDB.distance_N;
        this.authentication = this.UDB.authentication;
        this.notification_Mode = this.UDB.notification_Mode;
    }

    public User(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.UDB.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        this.UDB.password = password;

    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;}

    public String getGovernorate() {
        return governorate;}

    public void setGovernorate(String governorate) {this.governorate = governorate;}

    public String getCity() {return city;}

    public void setCity(String city) {this.city = city;}

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

    public int isAuthentication() {
        return authentication;
    }

    public void setAuthentication(int authentication) {
        this.authentication = authentication;
    }

    public int isNotification_Mode() {
        return notification_Mode;
    }

    public void setNotification_Mode(int notification_Mode) {
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
  /*  public void addNewUser(DatabaseReference R, User u){
        String id = R.child("user").push().getKey();
        R.child("user").push().setValue(this);
        this.id = id;
    }*/

      public void addNewUser(){
        String id = this.userDBReference.push().getKey();
          this.userDBReference.push().setValue(this.UDB);
        this.id = id;
    }


    public userDB getUser(final String id){

        /**Event Listener to read the data in the user listener reference only once
         * Using addListenerForSingleValueEvent() it will  it triggers once and then does not trigger again
         * */
        ValueEventListener UserListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 User.this.UDB = dataSnapshot.child("user").child(id).getValue(userDB.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        this.UserListener = UserListener;

        this.userDBReference.addListenerForSingleValueEvent(UserListener);
        return  this.UDB;
    }
    public void UpdateRecord(String record , String value){
        this.userDBReference.child(this.id).child(record).setValue(value);
    }
    public void RemoveRecord(String record){
        this.userDBReference.child(this.id).child(record).removeValue();
    }

    //----------------------------------------------------------------------------------------------
}
