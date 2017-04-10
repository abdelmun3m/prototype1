package com.abdelmun3m.prototype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class distance_popup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance_popup);
        DisplayMetrics dm=new DisplayMetrics();
       getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width=dm.widthPixels;
        int height=dm.heightPixels;

       getWindow().setLayout((int)(width*.9),(int)(height*.5));

        final EditText tv=(EditText)findViewById(R.id.editText);
        TextView ok=(TextView) findViewById(R.id.tvOk);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("");
            }
        });
    }
}
