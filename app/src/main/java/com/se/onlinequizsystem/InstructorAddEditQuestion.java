package com.se.onlinequizsystem;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class InstructorAddEditQuestion extends AppCompatActivity {

    private static final String TAG = "=== InstructorAddEditQuestion ===";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_add_edit_question);
    }

    public void InstructorAddEditQuestionNext(View view) {
        RadioButton radioButtonObjective = findViewById(R.id.radio_button_question_type_objective);
        RadioButton radioButtonSubjective = findViewById(R.id.radio_button_question_type_subjective);
        if (radioButtonObjective.isChecked()) {
            setContentView(R.layout.activity_instructor_add_edit_question_objective);
        }
        if (radioButtonSubjective.isChecked()) {
            setContentView(R.layout.activity_instructor_add_edit_question_subjective);
        }
    }

    public void InstructorAddEditQuestionSubjectiveBack(View view) {
        setContentView(R.layout.activity_instructor_add_edit_question);
    }

    public void InstructorAddEditQuestionSubjectiveSave(View view) {
    }

    public void InstructorAddEditQuestionObjectiveNext(View view) {
        RadioButton radioButtonOw = findViewById(R.id.radio_button_objective_type_ow);
        RadioButton radioButtonTf = findViewById(R.id.radio_button_objective_type_tf);
        RadioButton radioButtonMultiple = findViewById(R.id.radio_button_objective_type_multiple);
        RadioButton radioButtonSingle = findViewById(R.id.radio_button_objective_type_single);
        if (radioButtonOw.isChecked()) {
            setContentView(R.layout.activity_instructor_add_edit_question_ow);
        }
        if (radioButtonTf.isChecked()) {
            setContentView(R.layout.activity_instructor_add_edit_question_tf);
        }
        if (radioButtonSingle.isChecked()) {
        }
        if (radioButtonMultiple.isChecked()) {
        }
    }

    public void InstructorAddEditQuestionObjectiveBack(View view) {
        setContentView(R.layout.activity_instructor_add_edit_question);
    }

    public void InstructorAddEditQuestionTfSave(View view) {
    }

    public void InstructorAddEditQuestionTfBack(View view) {
        setContentView(R.layout.activity_instructor_add_edit_question_objective);
    }

    public void InstructorAddEditQuestionOwSave(View view) {
    }

    public void InstructorAddEditQuestionOwBack(View view) {
        setContentView(R.layout.activity_instructor_add_edit_question_objective);
    }
}