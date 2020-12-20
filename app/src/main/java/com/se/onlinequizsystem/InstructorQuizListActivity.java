package com.se.onlinequizsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class InstructorQuizListActivity extends AppCompatActivity {

    private static final String TAG = "=== InstructorQuizListActivity ===";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: init");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_quiz_list);
    }


    public void InstructorQuizItem1Button(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Quiz1")
                .setMessage("SE-E\n07/12/2020 5:00PM")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton("Show Feedback", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Context context = getBaseContext();
                        Intent intent = new Intent(context, InstructorQuizFeedbackActivity.class);
                        startActivity(intent);
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton("Grade Quiz", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Context context = getBaseContext();
                        Intent intent = new Intent(context, InstructorQuizGradeActivity.class);
                        startActivity(intent);
                    }
                })
                .show();
    }

    public void InstructorQuizListAddButton(View view) {
        Intent intent = new Intent(this, InstructorQuizGenerationActivity.class);
        startActivity(intent);
    }
}