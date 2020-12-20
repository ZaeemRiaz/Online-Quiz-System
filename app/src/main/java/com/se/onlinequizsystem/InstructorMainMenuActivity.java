package com.se.onlinequizsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class InstructorMainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_main_menu);
    }

    public void InstructorQuizListButton(View view) {
        Intent intent = new Intent(this, InstructorQuizListActivity.class);
        startActivity(intent);
    }

    public void InstructorQuestionBankListButton(View view) {
        Intent intent = new Intent(this, InstructorQuestionBankListActivity.class);
        startActivity(intent);
    }

    public void InstructorQuizGenerationButton(View view) {
        Intent intent = new Intent(this, InstructorQuizGenerationActivity.class);
        startActivity(intent);
    }
}