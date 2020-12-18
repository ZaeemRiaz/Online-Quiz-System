package com.se.onlinequizsystem;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class QuizBankListActivity extends AppCompatActivity {

    private static final String TAG = "=== QuizBankListActivity ===";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: init");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_bank_list);

    }
}
