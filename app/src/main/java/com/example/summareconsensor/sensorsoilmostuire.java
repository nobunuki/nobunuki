package com.example.summareconsensor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class sensorsoilmostuire extends AppCompatActivity {
    private TextView dataair;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensorsoilmostuire);

        DocumentReference data1out = db.collection("SummareconSensor2").document("Soil");
        dataair = findViewById(R.id.data1number);


        data1out.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {

                Double dataA = snapshot.getDouble("a");
                String txtDataA = String.valueOf(dataA);
                dataair.setText(txtDataA);

            }
        });




    }
}