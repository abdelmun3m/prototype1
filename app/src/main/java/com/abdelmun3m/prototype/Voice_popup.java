package com.abdelmun3m.prototype;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.CheckBox;

/**
 * Created by 3M on 21/11/2016.
 */
public class Voice_popup extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voice_popup);

        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width=dm.widthPixels;
        int height=dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.35));

        CheckBox voice=(CheckBox)findViewById(R.id.voiceChk);



    }
}
