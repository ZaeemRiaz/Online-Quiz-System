package com.se.onlinequizsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class InstructorAddEditQuestion extends AppCompatActivity {

    private static final String TAG = "=== InstructorAddEditQuestion ===";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_add_edit_question);
    }
}