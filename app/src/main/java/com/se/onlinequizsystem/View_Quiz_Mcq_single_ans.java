package com.se.onlinequizsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class View_Quiz_Mcq_single_ans extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__quiz__mcq_single_ans);


        Button B= findViewById(R.id.MCQ_single_Next_button56);
        String Qtype= "MCQ2";

        B.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(Qtype=="MCQ1")
                {

                }
                else if(Qtype=="MCQ2")
                {
                    setContentView(R.layout.view_quiz_mcq_mul_ans);
                }
                else if(Qtype=="MCQ3")
                {
                    setContentView(R.layout.view_quiz_mcq_tf);
                }
                else if(Qtype=="S1")
                {
                    setContentView(R.layout.view_quiz_subjective_ow);
                }
                else if(Qtype=="S2")
                {
                    setContentView(R.layout.view_quiz_subjective_multiple_words);
                }
            }
        });

    }


}