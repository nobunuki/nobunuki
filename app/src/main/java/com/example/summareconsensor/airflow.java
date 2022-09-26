package com.example.summareconsensor;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class airflow extends AppCompatActivity {
    private TextView progress_text_air, info_text_air, temperature_air;
    private Firebase myRef ;
    private ProgressBar progress_bar;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airflow);
        progress_text_air = findViewById(R.id.text_view_progressair);
        info_text_air = findViewById(R.id.text_view_infoair);
        temperature_air = findViewById(R.id.txttemperatureair);

        progress_bar = findViewById(R.id.progress_bar);
        Firebase.setAndroidContext(this);
        myRef = new Firebase("https://summareconsensor-default-rtdb.firebaseio.com/sensor");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String sensorair = dataSnapshot.child("airhumidity").getValue(String.class);
                String sensortemperatureair = dataSnapshot.child("airtemperature").getValue(String.class);
                int air = Integer.parseInt(sensorair.toString());
                temperature_air.setText(sensortemperatureair + "°C");
                progress_text_air.setText(sensorair);
                progress_bar.setProgress(air);
                if(air<=50){
                    info_text_air.setText("Dry");
                }else{
                    info_text_air.setText("Humid");
                }
                progress_bar.setMax(100);
                progress_bar.setProgress(air);
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Toast.makeText(getApplicationContext(), "Error, Please Contact our Administrator!",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}