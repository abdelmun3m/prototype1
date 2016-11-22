package com.abdelmun3m.prototype;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by 3M on 21/11/2016.
 */
public class alarm_time_popup extends Activity {
    SeekBar seekbar;
    int progress=30;
    TextView progress_num;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_time);
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int) (width * 0.85), (int) (height * .45));

      seekbar=(SeekBar)findViewById(R.id.seekBar);
        progress_num=(TextView)findViewById(R.id.prog_num);
        progress_num.setText("" + progress + "minutes");


        seekbar.setMax(120);
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
