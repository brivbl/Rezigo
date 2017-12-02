package com.rezigo.rezigo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

public class ActivityProposer extends AppCompatActivity {
    private Button proposeBtn;
    private EditText destinationField;
    private EditText hourField;
    private EditText dayField;
    private Firebase mTrajetsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposer);
        Firebase.setAndroidContext(this);

        mTrajetsRef = new Firebase("https://rezigo-f51a7.firebaseio.com/Trajets");
        proposeBtn = (Button) findViewById(R.id.propTraj);
        destinationField = (EditText)  findViewById(R.id.destination);
        hourField = (EditText)  findViewById(R.id.heuredepart);
        dayField = (EditText)  findViewById(R.id.date);
        proposeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                String Dest = destinationField.getText().toString();
                String Hour = hourField.getText().toString();
                String Day = dayField.getText().toString();

                String mTrajetId = mTrajetsRef.push().getKey();
                Firebase childDest = mTrajetsRef.child(mTrajetId).child("Destination");
                Firebase childHour = mTrajetsRef.child(mTrajetId).child("Heure");
                Firebase childDay = mTrajetsRef.child(mTrajetId).child("jour");

                childDest.setValue(Dest);
                childHour.setValue(Hour);
                childDay.setValue(Day);


            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_info :
                startActivityInformation();
                break;
            case R.id.button_maps:
                startActivityMaps();
                break;
            case R.id.button_leave:
                startActivityLeave();
                break;
            case R.id.button_home :
                finish();
                break;
        }
    }
    private void startActivityMaps() {
        Intent intent = new Intent(ActivityProposer.this, ActivityMaps.class);
        startActivity(intent);
    }

    private void startActivityLeave() {
        Intent intent = new Intent(ActivityProposer.this, ActivityLeave.class);
        startActivity(intent);
    }
    private void startActivityInformation() {
        Intent intent = new  Intent(ActivityProposer.this, ActivityInformation.class);
        startActivity(intent);
    }

}
