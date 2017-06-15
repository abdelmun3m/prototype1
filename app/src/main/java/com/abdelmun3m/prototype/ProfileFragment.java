package com.abdelmun3m.prototype;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;


public class ProfileFragment extends Fragment {
    TextView username,Email,country,city;
    ImageView imageViewEdite,imageViewProfile;
    User CurrentUser;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_profile, container,false);

        try {
            imageViewEdite=(ImageView) view.findViewById(R.id.edit);
            imageViewProfile = (ImageView)view.findViewById(R.id.imageview_profile);
            username=(TextView)view.findViewById(R.id.firest_name);
            Email=(TextView)view.findViewById(R.id.emaill_name);
            city=(TextView)view.findViewById(R.id.city_num);
            country=(TextView)view.findViewById(R.id.countryname); // ????
        }catch (Exception e){
            Toast.makeText(this.getContext(), "Error to define layout :: Message :"+e.getMessage().toString(), Toast.LENGTH_LONG).show();
        }


        updateView();

        imageViewEdite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_profile_Fragment edit = new edit_profile_Fragment();
                edit.CurrentUser = ProfileFragment.this.CurrentUser;
                Fragment editProfile=edit;
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container,editProfile);
                fragmentTransaction.commit();
            }
        });

        return view;
    }

    public void updateView(){
        try {

            if(CurrentUser.getProfile_pic() != null ){
                Glide.with(imageViewProfile.getContext())
                        .load(CurrentUser.getProfile_pic())
                        .into(imageViewProfile);
            }else{

                Glide.with(imageViewProfile.getContext())
                        .load(R.drawable.profile)
                        .into(imageViewProfile);
            }

        }catch (Exception e){
            Toast.makeText(this.getContext(), "Error load Image :: Message : "+e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
        if(CurrentUser.getName() != null){username.setText(CurrentUser.getName());}else{username.setText("Empty");}
        if(CurrentUser.getE_mail() != null){Email.setText(CurrentUser.getE_mail());}else{Email.setText("empty");}
        if(CurrentUser.getCity() != null){city.setText(CurrentUser.getCity());}else{city.setText("empty");}
    }
}
