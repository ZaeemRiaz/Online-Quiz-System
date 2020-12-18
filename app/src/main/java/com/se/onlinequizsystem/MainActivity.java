package com.se.onlinequizsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void instructorQuizListButton(View view) {
        Intent intent = new Intent(this, InstructorQuizListActivity.class);
        startActivity(intent);
    }

    public void QuizBankListButton(View view) {
        Intent intent = new Intent(this, QuizBankListActivity.class);
        startActivity(intent);
    }
}