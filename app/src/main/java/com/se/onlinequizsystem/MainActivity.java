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

    public void InstructorQuizListButton(View view) {
        Intent intent = new Intent(this, InstructorQuizListActivity.class);
        startActivity(intent);
    }

    public void InstructorQuizBankListButton(View view) {
        Intent intent = new Intent(this, InstructorQuizBankListActivity.class);
        startActivity(intent);
    }

    public void InstructorFeedbackButton(View view) {
        Intent intent = new Intent(this, InstructorQuizFeedbackActivity.class);
        startActivity(intent);
    }

    public void InstructorAddEditQuestionButton(View view) {
        Intent intent = new Intent(this, InstructorAddEditQuestion.class);
        startActivity(intent);
    }

    public void LoginButton(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void InstructorQuizGeneration(View view) {
        Intent intent = new Intent(this, InstructorQuizGeneration.class);
        startActivity(intent);
    }
}