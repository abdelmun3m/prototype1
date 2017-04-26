package com.abdelmun3m.prototype;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URI;
import java.util.concurrent.Executor;

import static android.app.Activity.RESULT_OK;

public class edit_profile_Fragment extends Fragment {

    ImageView imageView;
    ImageView imageView2;
    private static final int PICK_IMAGE=100;
    Uri imageUri;
    View view;
    EditText username,Email,country,city;
    Button save;

    private FirebaseStorage myStorage ;
    private StorageReference myStorageRef;
    User CurrentUser ;
    private static  final String TAG = "myTEST";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_edit_profile_, container,false);
        imageView=(ImageView) view.findViewById(R.id.imageview_profile);
        imageView2=(ImageView) view.findViewById(R.id.change);
        username=(EditText)view.findViewById(R.id.username);
        Email=(EditText)view.findViewById(R.id.emaill_name);
        city=(EditText)view.findViewById(R.id.city_num);
        country=(EditText)view.findViewById(R.id.country);
        save=(Button)view.findViewById(R.id.btnsave);

        updateView();

        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /**
                 * unCompliteDB Update
                 * **/
                updateView();
            }
        });
        //----------------------DB_S-----------------------------
        myStorage = FirebaseStorage.getInstance();
        myStorageRef=myStorage.getReference().child("Profile_Picture");
        //----------------------DB_E-----------------------------

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
            StorageReference photoref = myStorageRef.child(imageUri.getLastPathSegment());
            UploadTask upload = photoref.putFile(imageUri);
            upload.addOnSuccessListener( getActivity(), new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    CurrentUser.setProfile_Pic(downloadUrl.toString());
                    /**
                     * UnComplite DB Update For picture
                     * **/
                    updateView();

                }
            });
            upload.addOnFailureListener( getActivity(), new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(view.getContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public void updateView(){
        Glide.with(imageView.getContext())
                .load(CurrentUser.getProfile_pic())
                .into(imageView);
        if(CurrentUser.getName() != null){username.setText(CurrentUser.getName());}
        if(CurrentUser.getE_mail() != null){Email.setText(CurrentUser.getE_mail());}
        if(CurrentUser.getCity() != null){city.setText(CurrentUser.getCity());}
    }

}

