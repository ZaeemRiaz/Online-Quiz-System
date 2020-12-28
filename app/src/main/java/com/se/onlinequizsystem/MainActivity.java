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

    public void InstructorQuestionBankListButton(View view) {
        Intent intent = new Intent(this, InstructorQuestionBankListActivity.class);
        startActivity(intent);
    }

    public void InstructorFeedbackButton(View view) {
        Intent intent = new Intent(this, InstructorQuizFeedbackActivity.class);
        startActivity(intent);
    }

    public void InstructorAddEditQuestionButton(View view) {
        Intent intent = new Intent(this, InstructorAddEditQuestionActivity.class);
        startActivity(intent);
    }

    public void LoginButton(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void InstructorQuizGeneration(View view) {
        Intent intent = new Intent(this, InstructorQuizGenerationActivity.class);
        startActivity(intent);
    }

    public void InstructorQuizGrade(View view) {
        Intent intent = new Intent(this, InstructorQuizGradeActivity.class);
        startActivity(intent);

    }
}