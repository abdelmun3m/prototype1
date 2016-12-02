package com.abdelmun3m.prototype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class databaseTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_test);

        Toast.makeText(this, "Error : "+databaseError.toException(), Toast.LENGTH_LONG).show();
    }
}
