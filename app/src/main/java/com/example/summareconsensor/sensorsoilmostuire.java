package com.example.summareconsensor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
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
    private TextView progress_text_soil, info_text_soil, temperature_soil;
    private ProgressBar progress_bar;
    private Firebase myRef;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensorsoilmostuire);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("Notification Soil", "Notification Soil", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        progress_bar = findViewById(R.id.progress_bar);
        progress_text_soil = findViewById(R.id.text_view_progresssoil);
        info_text_soil = findViewById(R.id.text_view_infosoil);
        temperature_soil = findViewById(R.id.txttemperaturesoil);
        Firebase.setAndroidContext(this);
        myRef = new Firebase("https://summareconsensor-default-rtdb.firebaseio.com/sensor");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String sensorsoil = dataSnapshot.child("soilmoisture").getValue(String.class);
                String sensortemperaturesoil = dataSnapshot.child("soiltemperature").getValue(String.class);
                int soil = Integer.parseInt(sensorsoil.toString());
                progress_text_soil.setText(sensorsoil);
                temperature_soil.setText(sensortemperaturesoil + "°C");

                if(soil<=40){
                    info_text_soil.setText("Kering");
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(sensorsoilmostuire.this,"Notification Soil");
                    builder.setContentTitle("Kering");
                    builder.setContentText("Tanah Kering : Tolong Disiram");
                    builder.setSmallIcon(R.drawable.soilmoisture);
                    builder.setAutoCancel(true);
                    NotificationManagerCompat managerCompat = NotificationManagerCompat.from(sensorsoilmostuire.this);
                    managerCompat.notify(1,builder.build());
                }else if(soil > 40 && soil <= 70){
                    info_text_soil.setText("Normal");
                }else{
                    info_text_soil.setText("Basah");
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(sensorsoilmostuire.this,"Notification Soil");
                    builder.setContentTitle("Basah");
                    builder.setContentText("Tanah Basah : Stop Siram");
                    builder.setSmallIcon(R.drawable.soilmoisture);
                    builder.setAutoCancel(true);
                    NotificationManagerCompat managerCompat = NotificationManagerCompat.from(sensorsoilmostuire.this);
                    managerCompat.notify(1,builder.build());
                //}else{
                    //Toast.makeText(getApplicationContext(), "Error, Please Contact our Administrator!",
                            //Toast.LENGTH_LONG).show();
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