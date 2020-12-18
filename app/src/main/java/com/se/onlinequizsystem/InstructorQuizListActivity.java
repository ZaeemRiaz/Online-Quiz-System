package com.se.onlinequizsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class InstructorQuizListActivity extends AppCompatActivity {

    private static final String TAG = "=== InstructorQuizListActivity ===";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: init");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_quiz_list);
    }
}