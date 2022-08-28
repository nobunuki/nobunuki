package com.example.summareconsensor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";

    private EditText editTextUsername, editTextPassword;
    public Button signIn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Text Input
        editTextUsername = (EditText) findViewById(R.id.txt_username);
        editTextPassword = (EditText) findViewById(R.id.txt_password);
        //Signin Button
        signIn = (Button) findViewById(R.id.button_login);
        signIn.setOnClickListener(this);

        //Auth declare
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_login:
                userLogin();
                break;
        }
    }

    private void userLogin() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(username.isEmpty()) {
            editTextUsername.setError("Masukkan email");
            editTextUsername.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
            editTextUsername.setError("Format email tidak sesuai");
            editTextUsername.requestFocus();
            return;
        }

        if(password.isEmpty()) {
            editTextPassword.setError("Masukkan password");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length() < 6) {
            editTextPassword.setError("Minimal 6 karakter");
            editTextPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()) {
                    startActivity(new Intent(Login.this, Dashboard.class));
                }else {
                    Toast.makeText(Login.this, "Gagal login, silahkan coba lagi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}