package com.abdelmun3m.prototype;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DataBase DBObject ;
    Fragment CurrentFragment;
    User CurrentUser;
    FirebaseAuth myAuth;
    Toolbar toolbar;
    Stack<String> History = new Stack<String>();
    RelativeLayout pb ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("test 25 ","in main ");
        pb = (RelativeLayout)findViewById(R.id.MainprogressBar);
        //-------------------DB_S--------------------------------
        DBObject = new DataBase();
        myAuth = FirebaseAuth.getInstance();
        if (myAuth.getCurrentUser() == null){
            Toast.makeText(this, "Faild to Get Current User Please LogIN Again.", Toast.LENGTH_SHORT).show();
            Intent j = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(j);
            this.finish();
        }else {
                getCurrentUser();
        }
        //--------------------DB_E--------------------------------
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else{
            super.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

       if (id == R.id.nav_PublicService) {
           try {
               Intent i = new Intent(MainActivity.this,LocationServices.class);
               startActivity(i);
           }catch (Exception e){
               Toast.makeText(this, "An Error accured while loading Services : "
                       +e.getMessage().toString(), Toast.LENGTH_LONG).show();
           }
        } else if (id == R.id.nav_TrafficJam) {
           // startActivity(new Intent(MainActivity.this,ProfileScreenXMLUIDesign.class));
        } else if (id == R.id.nav_Profile) {
           ChangeCurrentFragment("Profile",true);
        }  else if (id == R.id.nav_settings) {
           ChangeCurrentFragment("settings",true);
        }else if (id == R.id.nav_Logout) {
           logOut();
       }else if(id == R.id.about){
           ChangeCurrentFragment("About",true);
       }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;

    }
    public  void ChangeCurrentFragment(String name , boolean h){

        if(name.equals("Main")){
            CurrentFragment = new Content_Main();
        }else if (name.equals("Profile")){
            ProfileFragment profile = new ProfileFragment();
            CurrentFragment=profile;
            profile.CurrentUser = this.CurrentUser;
        }else if(name.equals("settings")){
            CurrentFragment= new SettingsFragment();
        }else if(name.equals("About")){
          CurrentFragment=new about();
        }
        try {

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            if(h){
                fragmentTransaction.replace(R.id.container,CurrentFragment).addToBackStack(name).commit();
            }else{
                fragmentTransaction.replace(R.id.container,CurrentFragment).commit();
            }
        }catch (Exception e){
            Toast.makeText(this, "an Error Accured While Loading "+name+"Page : "
                    +e.getMessage().toString(), Toast.LENGTH_LONG).show();
        }
    }
    public void getCurrentUser(){
        DBObject.ValueListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Toast.makeText(MainActivity.this, "data changed", Toast.LENGTH_SHORT).show();
                userDB u = dataSnapshot.getValue(userDB.class);
                CurrentUser = new User(u , myAuth.getCurrentUser().getUid());
                ChangeCurrentFragment("Main",true);
                updateView(true);
                if(u != null){updateView(true);}
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {updateView(false);}

        };
        try{
            DBObject.DBreference.child("user").child(myAuth.getCurrentUser().getUid()).
                    addValueEventListener(DBObject.ValueListener);
        }
        catch (Exception e){Toast.makeText(getApplicationContext(),"Message : "
                +e.getMessage().toString(),Toast.LENGTH_LONG).show();}

    }
    public void updateView(boolean user){
        if(user){
            if(CurrentUser.getName() != null){
                toolbar.setTitle(CurrentUser.getName());
                pb.setVisibility(View.GONE);
            }else{
                toolbar.setTitle("Anonymous");
            }
        }
        else{
            logOut();
        }
    }

    public void logOut(){
        DBObject.DBreference.child("user").child(myAuth.getCurrentUser().getUid())
                .removeEventListener(DBObject.ValueListener);
        FirebaseAuth.getInstance().signOut();
        this.CurrentUser = null;
        this.myAuth = null;
        this.DBObject = null;
        if (FirebaseAuth.getInstance().getCurrentUser() == null){
            Intent j = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(j);
            this.finish();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(DBObject != null){
            if(DBObject.ValueListener != null){
                DBObject.DBreference.child("user").child(myAuth.getCurrentUser().getUid()).
                        removeEventListener(DBObject.ValueListener);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(DBObject.ValueListener != null){
            DBObject.DBreference.child("user").child(myAuth.getCurrentUser().getUid()).addValueEventListener(DBObject.ValueListener);
        }
    }
}
