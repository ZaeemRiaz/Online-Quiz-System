package com.se.onlinequizsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AttemptQuiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attempt_quiz);

        Button B= findViewById(R.id.start_button);
        String Qtype= "MCQ1";

        B.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(Qtype=="MCQ1")
                {
                    setContentView(R.layout.attempt_quiz_mcq_single_ans);
                }
                else if(Qtype=="MCQ2")
                {
                    setContentView(R.layout.attempt_quiz_mcq_multiple_ans);
                }
                else if(Qtype=="MCQ3")
                {
                    setContentView(R.layout.attempt_quiz_truefalse);
                }
                else if(Qtype=="S1")
                {
                    setContentView(R.layout.attempt_quiz_subjective_ow);
                }
                else if(Qtype=="S2")
                {
                    setContentView(R.layout.attempt_quiz_subejective_multple_words);
                }
            }
        });

    }
}