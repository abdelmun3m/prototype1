package com.abdelmun3m.prototype;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 3M on 04/12/2016.
 */

public class LvqXml extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lvq_train);

       final LvqInput b1=new LvqInput(1.8,2.7,3.6,4.8,5.7,6.7,7.8,9.5,11.7,1);
       final LvqInput b2=new LvqInput(1.6,2.5,3.1,4.1,5.1,6.1,7.1,9.1,11.1,2);
       final LvqInput b3=new LvqInput(1.4,2.1,2.7,3.8,4.7,5.7,6.8,8.5,10.7,1);
        final LvqInput b4=new LvqInput(.4,.1,.7,.8,.7,.7,.8,.5,.7,2);
       final ArrayList<LvqInput>x=new ArrayList<LvqInput>();
        x.add(b1);
        x.add(b2);
        x.add(b3);
        x.add(b4);
       final LvQTrain n=new LvQTrain(b1);
        Button b=(Button)findViewById(R.id.btnLvq);
       final TextView tv=(TextView)findViewById(R.id.tv1);
        final TextView tv2=(TextView)findViewById(R.id.tv2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             n.defineType(x);
                n.Train(x);
                LvqInput w1=n.getW1();
                LvqInput w2=n.getW2();
                    tv.setText("w1.skwenessX= "+w1.sekwenessX+" , w1.kurtosisX= "+w1.KurtosisX+" , w1.sumoflagsX= "+w1.sumOflagsX+
                            "w1.skwenessY= "+w1.sekwenessY+" , w1.kurtosisY= "+w1.KurtosisY+" , w1.sumoflagsY= "+w1.sumOflagsY+
                            "w1.skwenessZ= "+w1.sekwenessZ+" , w1.kurtosisZ= "+w1.KurtosisZ+" , w1.sumoflagsX= "+w1.sumOflagsZ);
                    tv2.setText("w2.skwenessX= "+w2.sekwenessX+" , w2.kurtosisX= "+w2.KurtosisX+" , w2.sumoflagsX= "+w2.sumOflagsX+
                            "w2.skwenessY= "+w2.sekwenessY+" , w2.kurtosisY= "+w2.KurtosisY+" , w2.sumoflagsY= "+w2.sumOflagsY+
                            "w2.skwenessZ= "+w2.sekwenessZ+" , w2.kurtosisZ= "+w2.KurtosisZ+" , w2.sumoflagsX= "+w2.sumOflagsZ);

            }
        });
    }
}
