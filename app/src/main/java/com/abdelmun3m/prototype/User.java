package com.abdelmun3m.prototype;

import com.google.firebase.database.DatabaseReference;

/**
 * Created by Abdelmunem on 11/4/2016.
 */

public class User {

    public String name ;
    public String sname;

    public User(){

    }
    public User(String name , String s ){
        this.name=name;
        this.sname=s;
    }

    public void addNewuser(DatabaseReference r){

        r.child("users").push().setValue(this);

    }
}
