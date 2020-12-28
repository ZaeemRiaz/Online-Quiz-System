package com.se.onlinequizsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class StudentAttemptQuiz extends AppCompatActivity {

    int quizid;
    private ArrayList<Question> questionsList;
    int Qno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Quiz quiz = (Quiz) intent.getSerializableExtra("quizViewIntent");
        questionsList = quiz.listOfQuestions;
        Qno=0;

        setContentView(R.layout.activity_student_attempt_quiz);
    }


    public void StudentAttemptQuizStartButton(View view) {


        Question q= questionsList.get(Qno);
        TextView QuestionTxt;
        TextView QuesNo;
        q.qType=2;

        if(q.qType==1)// MCQ single
        {
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

            // add question
            QuestionTxt= findViewById(R.id.MCQ_single_question);
            QuestionTxt.setText(q.qText);

            //add qno
            QuesNo= findViewById(R.id.AttemptQuizMCQ_single_qno);
            QuesNo.setText(String.valueOf(Qno+1));

            //add options
            RadioButton RB1;
            RadioButton RB2;
            RadioButton RB3;

            RB1= findViewById(R.id.MCQ_single_sound);
            RB1.setText(q.qAnsPossible.get(0));

            RB2= findViewById(R.id.MCQ_single_vibration);
            RB2.setText(q.qAnsPossible.get(1));

            RB3= findViewById(R.id.MCQ_single_silent);
            RB3.setText(q.qAnsPossible.get(2));

        }

        else if(q.qType==2) //MCQ multiple
        {
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



            // add question
            QuestionTxt= findViewById(R.id.MCQ_mul_question);
            QuestionTxt.setText(q.qText);

            //add qno
            QuesNo= findViewById(R.id.AttemptQuizMCQ_mul_qno);
            QuesNo.setText(String.valueOf(Qno+1));

            //add options
            CheckBox RB1;
            CheckBox RB2;
            CheckBox RB3;

            RB1= findViewById(R.id.MCQ_mul_checkBox);
            RB1.setText(q.qAnsPossible.get(0));

            RB2= findViewById(R.id.MCQ_mul_checkBox2);
            RB2.setText(q.qAnsPossible.get(1));

            RB3= findViewById(R.id.MCQ_mul_checkBox3);
            RB3.setText(q.qAnsPossible.get(2));


        }

        else if(q.qType==3) //true false
        {
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

        else {
            //setContentView(R.layout.attempt_quiz_subejective_multple_words);

            //get the spinner from the xml.
            //Spinner dropdown = findViewById(R.id.spinner3);
            //create a list of items for the spinner.
            //String[] items = new String[]{"1", "2", "3"};
            //create an adapter to describe how the items are displayed, adapters are used in several places in android.
            //There are multiple variations of this, but this is the basic variant.
            //ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
            //set the spinners adapter to the previously created one.
            //dropdown.setAdapter(adapter);

        }
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
        Intent intent = new Intent(this, StudentQuizListActivity.class);
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