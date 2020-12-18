package com.se.onlinequizsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

public class InstructorAddEditQuestion extends AppCompatActivity {

    private static final String TAG = "=== InstructorAddEditQuestion ===";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_add_edit_question);
    }

    public void InstructorAddEditQuestionNext(View view) {
    }

    public void InstructorAddEditQuestionSubjectiveBack(View view) {
    }

    public void InstructorAddEditQuestionSubjectiveSave(View view) {
    }
}