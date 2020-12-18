package com.se.onlinequizsystem;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InstructorQuizBankListActivity extends AppCompatActivity {
    private static final String TAG = "=== InstructorQuizBankListA ===";
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: init");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_quiz_bank_list);

    }
}