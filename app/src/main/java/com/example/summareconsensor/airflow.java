package com.example.summareconsensor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class airflow extends AppCompatActivity {
    private TextView txtprogresss, txtinfo ;
    private Firebase myRef ;
    private ProgressBar progress_bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airflow);
        txtprogresss = (TextView)findViewById(R.id.text_view_progress);
        txtinfo = (TextView)findViewById(R.id.text_view_info); 

        progress_bar = findViewById(R.id.progress_bar);
        Firebase.setAndroidContext(this);
        myRef = new Firebase("https://summareconsensor-default-rtdb.firebaseio.com/sensor");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String sensor = dataSnapshot.child("airhumidity").getValue(String.class);
                int air = Integer.parseInt(sensor.toString());
                txtprogresss.setText(sensor);
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