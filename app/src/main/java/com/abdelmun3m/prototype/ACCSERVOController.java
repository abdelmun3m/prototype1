package com.abdelmun3m.prototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ACCSERVOController extends AppCompatActivity implements View.OnClickListener {

    Button btnStartService,btnStopService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accservocontroller);

        btnStartService=(Button)findViewById(R.id.btnStartService);
        btnStopService=(Button)findViewById(R.id.btnStopService);
        btnStartService.setOnClickListener(this);
        btnStopService.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnStartService:
                startService(new Intent(getBaseContext(),ACCSERVO.class));
                break;
            case R.id.btnStopService:
                stopService(new Intent(getBaseContext(),ACCSERVO.class));
                break;
        }
    }
}
