package com.example.summareconsensor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class sensorsoilmostuire extends AppCompatActivity {
    private TextView dataair, progress_text, info_text;
    private ProgressBar progress_bar;
    private Firebase myRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensorsoilmostuire);

        dataair = findViewById(R.id.data1);
        progress_bar = findViewById(R.id.progress_bar);
        progress_text = findViewById(R.id.text_view_progress);
        info_text = findViewById(R.id.text_view_info);
        Firebase.setAndroidContext(this);
        myRef = new Firebase("https://summareconsensor-default-rtdb.firebaseio.com/sensor");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String sensor = dataSnapshot.child("soilmoisture").getValue(String.class);
                int soil = Integer.parseInt(sensor.toString());
                progress_text.setText(sensor);

                if(soil<40){
                    info_text.setText("Kurang Air");
                }else if(soil<80){
                    info_text.setText("Cukup Baik!");
                }else if (soil >100){
                    info_text.setText("Bagus!");
                }else{
                    Toast.makeText(getApplicationContext(), "Error, Please Contact our Administrator!",
                            Toast.LENGTH_LONG).show();
                }

                progress_bar.setMax(100);
                progress_bar.setProgress(soil);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Toast.makeText(getApplicationContext(), "Error, Please Contact our Administrator!",
                        Toast.LENGTH_LONG).show();
            }
        });





    }
}