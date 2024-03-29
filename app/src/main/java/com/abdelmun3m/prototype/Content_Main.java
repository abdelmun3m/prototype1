package com.abdelmun3m.prototype;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class Content_Main extends Fragment {
    ImageButton imgActive;
    ImageView Warningimgv;
    boolean isPressed = false;
    boolean active = false;
    ProgressBar mprogressBar;
    private int mProgressStatus = 100;
    private TextView distance;
    private Handler mHandler = new Handler();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_content__main, container, false);

        imgActive = (ImageButton) view.findViewById(R.id.imgvactive);
        Warningimgv=(ImageView)view.findViewById(R.id.warningimgv);
        distance = (TextView) view.findViewById(R.id.distnum);
        imgActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPressed) {
                    imgActive.setImageResource(R.drawable.actv1);

                } else {
                    imgActive.setImageResource(R.drawable.actv3);
                    active=true;
                    UpdatedistNum();
                }
                isPressed = !isPressed;

            }
        });
        mprogressBar = (ProgressBar) view.findViewById(R.id.circular_progress_bar);

        return view;
    }
    public void UpdatedistNum() {

        ObjectAnimator anim = ObjectAnimator.ofInt(mprogressBar, "progress", 100, 0);
        anim.setDuration(15000);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.start();
        mprogressBar.setVisibility(View.VISIBLE);
        distance.setVisibility(View.VISIBLE);
        Warningimgv.setVisibility(View.INVISIBLE);
        new Thread(new Runnable() {
            public void run() {
                while (mProgressStatus > 0 ) {
                    if (isPressed) {
                        mProgressStatus -= 1;
                        // Update the progress bar
                        mHandler.post(new Runnable() {
                            public void run() {
                                if ( mProgressStatus>40&&mProgressStatus<=70)
                                    mprogressBar.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);

                                else if ( mProgressStatus>0 && mProgressStatus<=40)
                                    mprogressBar.getProgressDrawable().setColorFilter(Color.RED,PorterDuff.Mode.SRC_IN);

                                mprogressBar.setProgress(mProgressStatus);
                                distance.setText("" + mProgressStatus + "m");
                                if (mProgressStatus==0) {
                                    mprogressBar.setVisibility(View.INVISIBLE);
                                    Warningimgv.setVisibility(View.VISIBLE);
                                    distance.setVisibility(View.INVISIBLE);
                                    mProgressStatus=100;

                                }


                            }
                        });
                        try {

                            Thread.sleep(120);


                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
 }

