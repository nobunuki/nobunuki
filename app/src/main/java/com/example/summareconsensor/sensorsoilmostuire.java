package com.example.summareconsensor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class sensorsoilmostuire extends AppCompatActivity {
    private TextView dataair, progress_text;
    private ProgressBar progress_bar;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensorsoilmostuire);

        DocumentReference data1out = db.collection("SummareconSensor2").document("Soil");
        dataair = findViewById(R.id.data1number);
        progress_bar = findViewById(R.id.progress_bar);
        progress_text = findViewById(R.id.text_view_progress);



        data1out.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {

                Double dataA = snapshot.getDouble("a");
                int i = Integer.valueOf(dataA.intValue());
                progress_bar.setProgress(i);
                progress_bar.setMax(12);


                String txtDataA = String.valueOf(dataA);
                progress_text.setText(txtDataA);

            }
        });




    }

    private void updateProgressBar() {

    }
}