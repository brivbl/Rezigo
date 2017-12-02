package com.rezigo.rezigo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Map;

public class ActivityProfil extends AppCompatActivity {

    private Button modifierProfil;
    private Button logoutBtn;
    private EditText nameField;
    private EditText passField;
    private EditText modelField;
    private EditText brandField;
    private Firebase mProfileRef;
    private TextView mUsername;
    private TextView mPass;
    private TextView mModel;
    private TextView mBrand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        Firebase.setAndroidContext(this);
        logoutBtn = (Button) findViewById(R.id.logoutBtn);

        mUsername = (TextView) findViewById(R.id.username);
        mPass = (TextView) findViewById(R.id.password);
        mModel = (TextView) findViewById(R.id.mBolide);
        mBrand = (TextView) findViewById(R.id.marqueBolide);
        mProfileRef = new Firebase("https://rezigo-f51a7.firebaseio.com/MyProfile");
        modifierProfil = (Button) findViewById(R.id.modifProfil);
        nameField = (EditText)  findViewById(R.id.username);
        passField = (EditText)  findViewById(R.id.password);
        modelField = (EditText)  findViewById(R.id.mBolide);
        brandField = (EditText)  findViewById(R.id.marqueBolide);

        modifierProfil.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String Username = nameField.getText().toString();
                String Password = passField.getText().toString();
                String Model = modelField.getText().toString();
                String Brand = brandField.getText().toString();

                Firebase childName = mProfileRef.child("Name");
                Firebase childPass = mProfileRef.child("Password");
                Firebase childModel = mProfileRef.child("Car Model");
                Firebase childBrand = mProfileRef.child("Car Brand");
                childName.setValue(Username);
                childPass.setValue(Password);
                childModel.setValue(Model);
                childBrand.setValue(Brand);
            }
        });
        logoutBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.signOut();
                finish();
                startActivityLogin();
            }
        });
        mProfileRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    Map<String, String> map = dataSnapshot.getValue(Map.class);
                    String username = map.get("Name");
                    String password = map.get("Password");
                    String brand = map.get("Car Brand");
                    String model = map.get("Car Model");
                    mUsername.setText(username);
                    mPass.setText(password);
                    mModel.setText(model);
                    mBrand.setText(brand);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
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
            case R.id.logoutBtn :
                finish();
                startActivityLogin();
                break;
        }
    }
    private void startActivityLeave() {
        Intent intent = new Intent(ActivityProfil.this, ActivityLeave.class);
        startActivity(intent);
    }
    private void startActivityInformation() {
        Intent intent = new Intent(ActivityProfil.this, ActivityInformation.class);
        startActivity(intent);
    }
    private void startActivityMaps() {
        Intent intent = new Intent(ActivityProfil.this, ActivityMaps.class);
        startActivity(intent);
    }

    private void startHomeActivity() {
        Intent intent = new Intent(ActivityProfil.this, HomeActivity.class);
        startActivity(intent);
    }
    private void startActivityLogin() {
        Intent intent = new Intent(ActivityProfil.this, ActivityLogin.class);
        startActivity(intent);
    }
}
