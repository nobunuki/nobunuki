package com.example.summareconsensor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Dashboard extends AppCompatActivity {
//RecyclerView recyclerviewer;
//String s1[];
//int images[] = {R.drawable.airquality,R.drawable.soilmoisture};
ImageButton soilmoisturebtn;
ImageButton airflowbtn;
Button notifbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        //recyclerviewer = findViewById(R.id.rcyviewer);
       // s1 = getResources().getStringArray(R.array.dashboardbutton);
        // adapter adapter = new adapter(this, s1, images);
       //recyclerviewer.setAdapter(adapter);
        //recyclerviewer.setLayoutManager(new LinearLayoutManager(this));
        soilmoisturebtn = (ImageButton) findViewById(R.id.btnsoil);
        soilmoisturebtn.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getApplicationContext(), sensorsoilmostuire.class);
               startActivity(intent);
            }
        });

        airflowbtn = (ImageButton) findViewById(R.id.btnair);
        airflowbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), airflow.class);
                startActivity(intent);
            }
        });
        notifbtn = (Button) findViewById(R.id.btnnotif);
        notifbtn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), notif.class);
                startActivity(intent);
            }
        }));


    }
}