package com.abdelmun3m.prototype;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.CheckBox;
import android.widget.CompoundButton;

/**
 * Created by 3M on 19/11/2016.
 */
public class guipop extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gui_popup);

        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width=dm.widthPixels;
        int height=dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.35));

        CheckBox gui=(CheckBox)findViewById(R.id.guichk);
        CheckBox flash=(CheckBox)findViewById(R.id.flashChk);

    }
}
