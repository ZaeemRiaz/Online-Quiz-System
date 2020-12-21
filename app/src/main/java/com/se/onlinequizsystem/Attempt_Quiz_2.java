package com.se.onlinequizsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class Attempt_Quiz_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attempt__quiz_2);

        Button B= findViewById(R.id.start_button2);
        final String  Qtype= "MCQ2";

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


        //final LayoutInflater inflater=  getLayoutInflater();
        //View view = inflater.inflate(R.layout.attempt_quiz_mcq_multiple_ans, null);



    }


}