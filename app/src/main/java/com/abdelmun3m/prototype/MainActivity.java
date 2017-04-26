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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DataBase DBObject ;
    Fragment CurrentFragment;
    User CurrentUser ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //-------------------DB_S--------------------------------
        DBObject = new DataBase();
        DBObject.User = FirebaseAuth.getInstance().getCurrentUser();
        if (DBObject.User == null){
            Intent j = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(j);
            this.finish();
        }else {
            if(CurrentUser == null){
                getCurrentUser();
            }
        }
        //--------------------DB_E--------------------------------


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Bumpo");
        setSupportActionBar(toolbar);

        ChangeCurrentFragment("Main");

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
        } else {
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

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }
*/
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

       if (id == R.id.nav_PublicService) {
            //startActivity(new Intent(MainActivity.this,distpop.class));
        } else if (id == R.id.nav_TrafficJam) {
           // startActivity(new Intent(MainActivity.this,ProfileScreenXMLUIDesign.class));
        } else if (id == R.id.nav_Profile) {
           ChangeCurrentFragment("Profile");
        }  else if (id == R.id.nav_settings) {
           ChangeCurrentFragment("settings");
        } else if (id == R.id.nav_Feedback) {
           // startActivity(new Intent(MainActivity.this,guipop.class));
        }else if (id == R.id.nav_Logout) {
           startActivity(new Intent(MainActivity.this,LoginActivity.class));
       }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;

    }
    public  void ChangeCurrentFragment(String name){

        if(name.equals("Main")){
            CurrentFragment = new Content_Main();
        }else if (name.equals("Profile")){
            ProfileFragment profile = new ProfileFragment();
            CurrentFragment=profile;
            profile.CurrentUser = this.CurrentUser;
        }else if(name.equals("settings")){
            CurrentFragment= new SettingsFragment();
        }else if(name.equals("About")){
          //  CurrentFragment=new About();

        }else if(name.equals("ContactUs")){
           // CurrentFragment=new ContactUS();
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container,CurrentFragment);
        fragmentTransaction.commit();
    }

    public void getCurrentUser(){
        DBObject.ValueListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userDB u = dataSnapshot.getValue(userDB.class);
                CurrentUser = new User(u , DBObject.User.getUid());
                if(u != null){updateView(true);}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                updateView(false);
            }
        };
        DBObject.DBreference.child("user").child(DBObject.User.getUid()).addValueEventListener(DBObject.ValueListener);
    }
    public void updateView(boolean user){
        if(user){
            Toast.makeText(getApplicationContext(),"Welcome : " + CurrentUser.getE_mail(),Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"No User Found ",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(DBObject.ValueListener != null){
            DBObject.DBreference.child("user").child(DBObject.User.getUid()).removeEventListener(DBObject.ValueListener);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(DBObject.ValueListener != null){
            DBObject.DBreference.child("user").child(DBObject.User.getUid()).addValueEventListener(DBObject.ValueListener);
        }
    }
}
