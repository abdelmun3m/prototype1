package com.abdelmun3m.prototype;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;


public class ProfileFragment extends Fragment {
    TextView username,Email,country,city;
    ImageView imageViewEdite,imageViewProfile;
    User CurrentUser;
    ProgressBar pb ;
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
            pb =(ProgressBar) view.findViewById(R.id.ProfileprogressBar);

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
            if(!CurrentUser.getProfile_pic().equals("") ){

                  Glide.with(imageViewProfile.getContext())
                        .load(CurrentUser.getProfile_pic())
                         .listener(new RequestListener<String, GlideDrawable>() {
                             @Override
                             public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                 pb.setVisibility(View.GONE);
                                 //Toast.makeText(getContext(), "E0: "+e.getMessage().toString()+" : "+CurrentUser.getProfile_pic(), Toast.LENGTH_LONG).show();
                                 return false;
                             }

                             @Override
                             public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                 pb.setVisibility(View.GONE);
                                 //Toast.makeText(getContext(), "R0 :"+resource.toString(), Toast.LENGTH_LONG).show();
                                 return false;

                             }
                         })
                        .into(imageViewProfile);

            }else{

                Glide.with(imageViewProfile.getContext())
                        .load(R.drawable.profile)
                        .listener(new RequestListener<Integer, GlideDrawable>() {
                            @Override
                            public boolean onException(Exception e, Integer model, Target<GlideDrawable> target, boolean isFirstResource) {
                             //   Toast.makeText(getContext(), "E1 : "+e.getMessage().toString(), Toast.LENGTH_LONG).show();
                             pb.setVisibility(View.GONE);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(GlideDrawable resource, Integer model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                               // Toast.makeText(getContext(), "R1 "+resource.toString(), Toast.LENGTH_LONG).show();
                                pb.setVisibility(View.GONE);
                                return false;
                            }
                        })
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
