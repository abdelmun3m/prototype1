package com.abdelmun3m.prototype;

import android.app.Application;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DataBase {



    private static final String TAG = "DataBaseClass";

    public FirebaseDatabase DBinsatnce;
    public DatabaseReference DBreference;
    public ValueEventListener ValueListener;
    public FirebaseAuth myAuth;
    public FirebaseAuth.AuthStateListener myAuthListener;
    public FirebaseUser User ;

    public  DataBase(){
        DBinsatnce = FirebaseDatabase.getInstance(); // get an instance from the database .
        DBreference = DBinsatnce.getReference() ; // getting the reference to the root of the data base
        myAuth = FirebaseAuth.getInstance();
    }

}
