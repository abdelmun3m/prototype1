package com.abdelmun3m.prototype;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import static android.app.Activity.RESULT_OK;

public class edit_profile_Fragment extends Fragment {
    ImageView imageView;
    ImageView imageView2;
    private static final int PICK_IMAGE=100;
    Uri imageUri;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_edit_profile_, container,false);
        imageView=(ImageView) view.findViewById(R.id.imageview_profile);
        imageView2=(ImageView) view.findViewById(R.id.change);

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        return view;
    }
    private void openGallery()
    {
        Intent gallery =new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery,PICK_IMAGE);
        
    }

    @Override

    public void onActivityResult(int requestCode,int resultCode ,Intent data)
    {
        super.onActivityResult(requestCode ,resultCode,data);
        if (requestCode==RESULT_OK || requestCode==PICK_IMAGE)
        {
            imageUri=data.getData();
            imageView.setImageURI(imageUri);
        }
    }



    }

