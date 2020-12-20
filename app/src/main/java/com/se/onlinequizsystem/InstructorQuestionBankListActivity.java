package com.se.onlinequizsystem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InstructorQuestionBankListActivity extends AppCompatActivity {
    private static final String TAG = "=== InstructorQuestionBankListA ===";
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: init");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_question_bank_list);

    }

    public void InstructorQuestionBankAddButton(View view) {
        Intent intent = new Intent(this, InstructorAddEditQuestionActivity.class);
        startActivity(intent);
    }

    public void InstructorQuestionBankEditButton(View view) {
        Intent intent = new Intent(this, InstructorAddEditQuestionActivity.class);
        startActivity(intent);
    }
}