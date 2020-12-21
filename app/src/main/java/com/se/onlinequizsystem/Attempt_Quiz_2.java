package com.se.onlinequizsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Attempt_Quiz_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attempt__quiz_2);
//
//        Button B= findViewById(R.id.start_button2);
//        final String  Qtype= "MCQ2";
//
//        B.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                if(Qtype=="MCQ1")
//                {
//                    setContentView(R.layout.attempt_quiz_mcq_single_ans);
//                }
//                else if(Qtype=="MCQ2")
//                {
//                    setContentView(R.layout.attempt_quiz_mcq_multiple_ans);
//                }
//                else if(Qtype=="MCQ3")
//                {
//                    setContentView(R.layout.attempt_quiz_truefalse);
//                }
//                else if(Qtype=="S1")
//                {
//                    setContentView(R.layout.attempt_quiz_subjective_ow);
//                }
//                else if(Qtype=="S2")
//                {
//                    setContentView(R.layout.attempt_quiz_subejective_multple_words);
//                }
//            }
//        });


        //final LayoutInflater inflater=  getLayoutInflater();
        //View view = inflater.inflate(R.layout.attempt_quiz_mcq_multiple_ans, null);

    }


    public void StudentAttemptQuizStartButton(View view) {
        setContentView(R.layout.attempt_quiz_subejective_multple_words);

//get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinner3);
//create a list of items for the spinner.
        String[] items = new String[]{"1", "2", "3"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

    }

    public void StudentAttemptQuizSubjectiveNextButton(View view) {
        setContentView(R.layout.attempt_quiz_subjective_ow);

//get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinner4);
//create a list of items for the spinner.
        String[] items = new String[]{"1", "2", "3"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

    }

    public void StudentAttemptQuizObjectiveOWNextButton(View view) {
        setContentView(R.layout.attempt_quiz_mcq_multiple_ans);

//get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinner1);
//create a list of items for the spinner.
        String[] items = new String[]{"1", "2", "3"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

    }

    public void StudentAttemptQuizObjectiveOWBackButton(View view) {
        setContentView(R.layout.attempt_quiz_subejective_multple_words);

//get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinner3);
//create a list of items for the spinner.
        String[] items = new String[]{"1", "2", "3"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

    }

    public void StudentAttemptQuizObjectiveMANextButton(View view) {
        setContentView(R.layout.attempt_quiz_mcq_single_ans);

//get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinner2);
//create a list of items for the spinner.
        String[] items = new String[]{"1", "2", "3"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

    }

    public void StudentAttemptQuizObjectiveMABackButton(View view) {
        setContentView(R.layout.attempt_quiz_subjective_ow);
        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinner4);
//create a list of items for the spinner.
        String[] items = new String[]{"1", "2", "3"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

    }

    public void StudentAttemptQuizObjectiveSANextButton(View view) {
        setContentView(R.layout.attempt_quiz_truefalse);

//get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinner5);
//create a list of items for the spinner.
        String[] items = new String[]{"1", "2", "3"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
    }

    public void StudentAttemptQuizObjectiveSABackButton(View view) {
        setContentView(R.layout.attempt_quiz_mcq_multiple_ans);

//get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinner1);
//create a list of items for the spinner.
        String[] items = new String[]{"1", "2", "3"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
    }

    public void StudentAttemptQuizObjectiveTFSubmitButton(View view) {
        Intent intent = new Intent(this, ListOfQuizesStudentSide.class);
        startActivity(intent);
    }

    public void StudentAttemptQuizObjectiveTFBackButton(View view) {
        setContentView(R.layout.attempt_quiz_mcq_single_ans);

//get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinner2);
//create a list of items for the spinner.
        String[] items = new String[]{"1", "2", "3"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
    }
}