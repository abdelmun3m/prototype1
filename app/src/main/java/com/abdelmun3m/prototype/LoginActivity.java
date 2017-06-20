package com.abdelmun3m.prototype;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "myTESTLogin";
    private String username, password;
    private EditText emailEditText;
    private EditText passEditText;
    private TextView SignUpTv;
    private Button ok;
    private CheckBox saveLoginCheckBox;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;
    FirebaseAuth myAuth ;
    ProgressBar pb ;
    RelativeLayout layout ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //------------------------DB_S---------------------------------
        myAuth = FirebaseAuth.getInstance();
       /* if (myAuth.getCurrentUser() != null){
            Intent j = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(j);
            this.finish();
        }
       */ //------------------------DB_E------------------------------------
        // Address the email and password field
        ok = (Button) findViewById(R.id.btnLogin);
        ok.setOnClickListener(this);
        emailEditText = (EditText) findViewById(R.id.username);
        passEditText = (EditText) findViewById(R.id.password);
        saveLoginCheckBox = (CheckBox) findViewById(R.id.saveLoginCheckBox);
        SignUpTv = (TextView) findViewById(R.id.tvSignUp);
        pb = (ProgressBar)findViewById(R.id.LoginprogressBar);
        layout = (RelativeLayout)findViewById(R.id.Loginlayout);
        SignUpTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, signUpActivity.class);
                startActivity(i);
            }
        });

        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            emailEditText.setText(loginPreferences.getString("username", ""));
            passEditText.setText(loginPreferences.getString("password", ""));
            saveLoginCheckBox.setChecked(true);
        }
    }

    public void onClick(View view) {
        if (view == ok) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(emailEditText.getWindowToken(), 0);

            username = emailEditText.getText().toString();
            password = passEditText.getText().toString();

            if (saveLoginCheckBox.isChecked()) {
                loginPrefsEditor.putBoolean("saveLogin", true);
                loginPrefsEditor.putString("username", username);
                loginPrefsEditor.putString("password", password);
                loginPrefsEditor.commit();
            } else {
                loginPrefsEditor.clear();
                loginPrefsEditor.commit();
            }
            doSomethingElse();
        }
    }

    public void doSomethingElse() {
        final String email = emailEditText.getText().toString();
        if (!isValidEmail(email)) {
            //Set error message for email field
            emailEditText.setError("Invalid Email");
        }

        final String pass = passEditText.getText().toString();
        if (!isValidPassword(pass)) {
            //Set error message for password field
            passEditText.setError("Password cannot be empty");
        }

        if (isValidEmail(email) && isValidPassword(pass)) {
            // Validation Completed
            Pass_Mail_Login(pass,email);

        }

    }

    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 4) {
            return true;
        }
        return false;
    }

    private void Pass_Mail_Login(String pass , String mail){

        pb.setVisibility(View.VISIBLE);
        pb.bringToFront();
        myAuth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    pb.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "faild to SignIN"+
                            task.getException().getMessage().toString(), Toast.LENGTH_LONG).show();
                }
                if(task.isSuccessful() &&  task.isComplete()){
                    Intent j = new Intent(LoginActivity.this, MainActivity.class);
                    pb.setVisibility(View.GONE);
                    startActivity(j);
                    LoginActivity.this.finish();
                }
            }
        });

    }

}

