package com.rezigo.rezigo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ActivityProfil extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

    }
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_info :
                finish();
                startActivityInformation();
                break;
            case R.id.button_home :
                // startHomeActivity();
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
}

