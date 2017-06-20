package com.abdelmun3m.prototype;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;

public class loadingActivity extends AppCompatActivity {
    private final int WAIT_TIME = 2500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int) (width * 0.9), (int) (height * .3));
        findViewById(R.id.mainSpinner1).setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                //Simulating a long running task
              //  Thread.sleep(1000);
                System.out.println("Going to Profile Data");
	  /* Create an Intent that will start the ProfileData-Activity. */
                Intent mainIntent = new Intent(loadingActivity.this,MainActivity.class);
                loadingActivity.this.startActivity(mainIntent);
                loadingActivity.this.finish();
            }
        }, WAIT_TIME);
    }
    }

