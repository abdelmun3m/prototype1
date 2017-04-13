package com.abdelmun3m.prototype;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signUpActivity extends AppCompatActivity {


    EditText name,email,cpass,pass;
    //TextInputLayout pass;
    Button signUp;
    Spinner covernorate;
    User newUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);
        cpass = (EditText) findViewById(R.id.confirmpass);
        signUp = (Button) findViewById(R.id.btnSignUP);
        covernorate = (Spinner) findViewById(R.id.covernorateSpanner);



        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(signUpActivity.this, "asss",Toast.LENGTH_SHORT).show();
                if(!name.getText().toString().equals("")){
                    //newUser.setName(name.getText().toString());
                    if(!email.getText().toString().equals("")){

                       // newUser.setE_mail(email.getText().toString())
 //                     if (pass.getText().toString().equals(cpass.getText().toString()) && !pass.getText().toString().equals("")){
                                newUser = new User(name.getText().toString(),pass.getText().toString(),email.getText().toString()
                                ,"De","ma",100,0,0);
                            newUser.addNewUser();
                        //newUser.setPassword(pass.getText().toString());

//                          }
                    }
                }



            }
        });

    }


}
