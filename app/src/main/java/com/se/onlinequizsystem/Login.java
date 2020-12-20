package com.se.onlinequizsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    private static final String TAG = "=== Login ===";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void LoginButton(View view) {
        EditText emailAddress = findViewById(R.id.editTextTextEmailAddress);
        EditText password = findViewById(R.id.editTextTextPassword);
        if(emailAddress.getText().toString().equals("Ins")&&password.getText().toString().equals("123")){
            Log.d(TAG, "LoginButton: Login Match");
            Intent intent = new Intent(this, InstructorQuizListActivity.class);
            startActivity(intent);
        }
        Log.d(TAG, "LoginButton: Login Func");
    }
}