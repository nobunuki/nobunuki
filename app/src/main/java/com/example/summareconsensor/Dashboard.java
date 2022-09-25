package com.example.summareconsensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Dashboard extends AppCompatActivity {
ImageButton soilmoisturebtn;
ImageButton airflowbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        soilmoisturebtn = (ImageButton) findViewById(R.id.btnsoil);
        soilmoisturebtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), sensorsoilmostuire.class);
                startActivity(intent);
            }
        });

        airflowbtn = (ImageButton) findViewById(R.id.btnairquality);
        airflowbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), airflow.class);
                startActivity(intent);
            }
        });


    }
}