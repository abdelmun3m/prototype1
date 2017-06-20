package com.abdelmun3m.prototype;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;


public class SettingsFragment extends Fragment {
    int distance = 100;
    int alarmTime = 15;
    String notifyType;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        final TextView distTime = (TextView) view.findViewById(R.id.distanceTime);
        final TextView AlarmTime = (TextView) view.findViewById(R.id.AlarmTime);
        final SeekBar distanceSeekbar = (SeekBar) view.findViewById(R.id.seekbar);
        distanceSeekbar.setMax(199);
        distanceSeekbar.setProgress(distance);
        distanceSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress += 1;
                distTime.setText(progress + "Km before obstacle");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        SeekBar AlarmBar = (SeekBar) view.findViewById(R.id.AlarmSeekbar);
        AlarmBar.setMax(29);
        AlarmBar.setProgress(alarmTime);
        AlarmBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress += 1;
                AlarmTime.setText(progress + "minutes");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        CheckBox voice = (CheckBox) view.findViewById(R.id.voiceChk);
        CheckBox guiFlash = (CheckBox) view.findViewById(R.id.GuifflashChk);
        CheckBox guiWithoutFlash = (CheckBox) view.findViewById(R.id.GuiChk);
        if (voice.isChecked())
            notifyType = "voice";
        else if (guiFlash.isChecked())
            notifyType = "Gui With flash";
        else if (guiWithoutFlash.isChecked())
            notifyType = "Gui without Flash";

        return view;


    }
}
