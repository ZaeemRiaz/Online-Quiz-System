package com.se.onlinequizsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StudentViewQuiz extends AppCompatActivity {
    private static final String TAG = "View_Quiz_Mcq_single_an";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_view_quiz);


    }

    public void ViewQuizSANext(View view) {
        setContentView(R.layout.view_quiz_mcq_tf);
    }

    public void ViewQuizSABack(View view) {
    }

    public void ViewQuizTFNext(View view) {
        setContentView(R.layout.view_quiz_mcq_mul_ans);
    }

    public void ViewQuizTFBack(View view) {
        setContentView(R.layout.activity_student_view_quiz);
    }

    public void ViewQuizMANext(View view) {
        setContentView(R.layout.view_quiz_subjective_multiple_words);
    }

    public void ViewQuizMABack(View view) {
        setContentView(R.layout.view_quiz_mcq_tf);
    }

    public void ViewQuizMWNext(View view) {
        setContentView(R.layout.view_quiz_subjective_ow);
    }

    public void ViewQuizMWBack(View view) {
        setContentView(R.layout.view_quiz_mcq_mul_ans);
    }

    public void ViewQuizOWCloseButton(View view) {
        Intent intent = new Intent(this, StudentQuizListActivity.class);
        startActivity(intent);
    }

    public void ViewQuizOWBack(View view) {
        setContentView(R.layout.view_quiz_subjective_multiple_words);
    }






}