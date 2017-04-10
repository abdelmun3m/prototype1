package com.abdelmun3m.prototype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.SeekBar;
import android.widget.TextView;

public class alarm_popup extends AppCompatActivity {

    SeekBar seekbar;
    int progress=15;
    TextView progress_num;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_popup);
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int) (width * 0.9), (int) (height * .5));

        seekbar=(SeekBar)findViewById(R.id.seekbar);
        progress_num=(TextView)findViewById(R.id.time);
        progress_num.setText("" + progress + "minutes");


        seekbar.setMax(30);
        seekbar.setProgress(progress);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress+=1;
                progress_num.setText(""+progress+"minutes");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
