package com.abdelmun3m.prototype;

import android.content.Intent;
import android.support.annotation.NonNull;
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


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signUpActivity extends AppCompatActivity {

    private static final String TAG = "myTESTSignUP";
    EditText name,email,cpass,pass;
    Button signUp;
    Spinner covernorate;
    FirebaseAuth myAuth;

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


        myAuth = FirebaseAuth.getInstance();
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!name.getText().toString().equals("")){
                    if(!email.getText().toString().equals("")){
                        if (pass.getText().toString().equals(cpass.getText().toString()) && !pass.getText().toString().equals("")){
                            Pass_Mail_SignUP(pass.getText().toString(),email.getText().toString());
                        }
                    }
                }
            }
        });

    }

    private void Pass_Mail_SignUP(final String pass , final String mail){
        myAuth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(signUpActivity.this, "faild to sign up : "+
                            task.getException().getMessage().toString(), Toast.LENGTH_LONG).show();
                }
                if(task.isSuccessful() && task.isComplete()){
                    String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    User newUser = new User(id,name.getText().toString(),pass,mail,"DEk","man",100,1,0,"");
                    newUser.addNewUser();
                    Intent j = new Intent(signUpActivity.this, MainActivity.class);
                    startActivity(j);
                    signUpActivity.this.finish();
                }
            }
        });
    }
}
