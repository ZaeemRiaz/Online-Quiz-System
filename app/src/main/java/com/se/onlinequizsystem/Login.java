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
    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        // Not calling super, disables back button in current screen.
    }
    public void LoginButton(View view) {
        EditText emailAddress = findViewById(R.id.input_email);
        EditText password = findViewById(R.id.input_password);
        if(emailAddress.getText().toString().equals("ins")&&password.getText().toString().equals("123")){
            Intent intent = new Intent(this, InstructorQuizListActivity.class);
            startActivity(intent);
        }
        if(emailAddress.getText().toString().equals("stu")&&password.getText().toString().equals("123")){
        }
    }
    public void LoginButtontest(View view) {
        Intent intent = new Intent(this, View_Quiz_Mcq_single_ans.class);
        startActivity(intent);
    }

}