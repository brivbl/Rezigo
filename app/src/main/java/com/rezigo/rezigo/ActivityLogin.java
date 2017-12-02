package com.rezigo.rezigo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityLogin extends AppCompatActivity {

    private EditText emailField;
    private EditText passwordField;


    private Button loginBtn;

    private FirebaseAuth auth;

    private FirebaseAuth.AuthStateListener authListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Firebase.setAndroidContext(this);

        auth = FirebaseAuth.getInstance();

        emailField = (EditText) findViewById(R.id.loginEmail);
        passwordField = (EditText) findViewById(R.id.loginPassword);

        loginBtn = (Button) findViewById(R.id.loginBtn);

        authListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){

                if(firebaseAuth.getCurrentUser()!=null){

                    startActivity(new Intent(ActivityLogin.this,ActivityProfil.class ));

                }

            }


        };



        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startSignIn();
            }
        });


    }

    protected void onStart(){
        super.onStart();
        auth.addAuthStateListener(authListener);



    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_info :
                finish();
                startActivityInformation();
                break;
            case R.id.button_home :
                startHomeActivity();
                finish();
                break;
            case R.id.button_maps:
                finish();
                startActivityMaps();
                break;
            case R.id.button_leave :
                finish();
                startActivityLeave();
                break;

        }
    }
    private void startActivityLeave() {
        Intent intent = new Intent(ActivityLogin.this, ActivityLeave.class);
        startActivity(intent);
    }
    private void startActivityInformation() {
        Intent intent = new Intent(ActivityLogin.this, ActivityInformation.class);
        startActivity(intent);
    }
    private void startActivityMaps() {
        Intent intent = new Intent(ActivityLogin.this, ActivityMaps.class);
        startActivity(intent);
    }

    private void startHomeActivity() {
        Intent intent = new Intent(ActivityLogin.this, HomeActivity.class);
        startActivity(intent);
    }


    private void startSignIn(){

        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        if(TextUtils.isEmpty(email)|| TextUtils.isEmpty(password)) {

            Toast.makeText(ActivityLogin.this, "Un ou plusieurs champs vide(s)", Toast.LENGTH_LONG).show();

        } else {


            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (!task.isSuccessful()) {
                        Toast.makeText(ActivityLogin.this, "E-mail ou mot de passe incorrect(s)", Toast.LENGTH_LONG).show();

                    }

                }
            });

        }

    }
}
