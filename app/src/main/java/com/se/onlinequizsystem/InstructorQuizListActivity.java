package com.se.onlinequizsystem;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class InstructorQuizListActivity extends AppCompatActivity {

    private static final String TAG = "=== InstructorQuizListActivity ===";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: init");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_quiz_list);

    }
}
