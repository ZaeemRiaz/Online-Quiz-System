package com.se.onlinequizsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button B= findViewById(R.id.Loginbutton);


    }

    public void LoginBtnClick(View view) {
        Intent intent = new Intent(this, ListOfQuizesStudentSide.class);
        startActivity(intent);
    }


}