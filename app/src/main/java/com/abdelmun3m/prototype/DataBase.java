package com.abdelmun3m.prototype;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Abdelmunem on 11/22/2016.
 */

public class DataBase {



    private FirebaseDatabase DBinsatnce;
    private DatabaseReference DBreference;


    public  DataBase(){
        DBinsatnce = FirebaseDatabase.getInstance(); // get an instance from the database .
        DBreference = DBinsatnce.getReference() ; // getting the reference to the root of the data base
    }

    public DataBase(DatabaseReference R){
        this.DBreference = R ;
    }

    public DatabaseReference  getDBreference(){
        return DBreference;
    }


    public void Add(String Child , Object o){
        DBreference.child(Child).push().setValue(o);
    }



}
