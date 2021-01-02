package com.se.onlinequizsystem;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class StudentAttemptQuiz extends AppCompatActivity {


    private ArrayList<Question> questionsList;
    int Qno;

    Stopwatch sw[];
    Boolean Attempted=true; //false is equal to not attempted
    int count=0;
    long Time_perQuestion[]; //time for each question stored here
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Quiz quiz = (Quiz) intent.getSerializableExtra("quizViewIntent");
        questionsList = quiz.listOfQuestions;
        Qno=-1;
        int i=0;
        sw= new Stopwatch[questionsList.size()];
        for( i=0; i<questionsList.size();i++ )
        {
            sw[i]= new Stopwatch();
        }

        Time_perQuestion= new long[questionsList.size()];

        for(i=0; i<questionsList.size(); i++)
        {
            Time_perQuestion[i]=0;
        }

        setContentView(R.layout.activity_student_attempt_quiz);
    }



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void StudentAttemptNextQuestion(View view) {
        Qno++; //next question
       // Context context = getApplicationContext();
        //CharSequence text = "Lenght !"+ String.valueOf(questionsList.size());
        //int duration = Toast.LENGTH_SHORT;
        //Toast toast = Toast.makeText(context, text, duration);
        //toast.show();


        ////////////////////////////////////////////////////////////////////
        if(Qno>=questionsList.size())
        {
            setContentView(R.layout.attempt_submit);

            ////////////////////////////set all paused timers to stop before submitting////////////////
            int i=0;
            for (i=0; i<sw.length; i++)
            {
                if(sw[i].paused==true)
                {
                    sw[i].stop();
                    Time_perQuestion[i]= 0; //not attempted
                }
            }


            Context context = getApplicationContext();
            CharSequence text = "Time taken for q1 ="+ String.valueOf(Time_perQuestion[0]);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            ////////////////////////////////////////////////////////////////////////////

        }
        else {
            Question q = questionsList.get(Qno);
            TextView QuestionTxt;
            TextView QuesNo;



            ///////////////////////////// time the question (try: next, next back)///////////////////////
            if(Time_perQuestion[Qno]==0) {  //if time has already not been calculated
                if (count % 2 == 0) // meaning question started
                {
                    if(sw[Qno].paused==false)
                    {sw[Qno].start();}
                    else{sw[Qno].resume();}
                    if (count > 1) {
                        //if question attempted, then store time
                        if(sw[Qno-1].running==true) {
                            if (Attempted == true) {
                                long timetaken = sw[Qno - 1].getElapsedTimeSecs();
                                Time_perQuestion[Qno - 1] = timetaken;
                                sw[Qno - 1].stop();
                            } else {
                                sw[Qno - 1].pause(); // pause prev question if not attempted
                            }
                        }

                    }
                } else //meaning question ended wiht next pressed
                {

                    if(sw[Qno].paused==false)
                    {sw[Qno].start();}
                    else{sw[Qno].resume();}

                    //TODO: if previous question is attempted, then store ellapsed time, else store
                    if(sw[Qno-1].running==true) {
                        if (Attempted == true) {
                            long timetaken = sw[Qno - 1].getElapsedTimeSecs();
                            Time_perQuestion[Qno - 1] = timetaken;
                            sw[Qno - 1].stop();
                        } else {
                            sw[Qno - 1].pause(); // pause prev question
                        }
                    }

                }
                count++;
            }
            /////////////////////////////////////////////////////////////////////


            //////////////////////////////////////////////////////////////////
            if (q.qType == 1)// MCQ single
            {
                setContentView(R.layout.attempt_quiz_mcq_single_ans);
                //get the spinner from the xml.
                Spinner dropdown = findViewById(R.id.spinner2);
                //create a list of items for the spinner.
                String[] items = new String[]{"0","1", "2", "3"};
                //create an adapter to describe how the items are displayed, adapters are used in several places in android.
                //There are multiple variations of this, but this is the basic variant.
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
                //set the spinners adapter to the previously created one.
                dropdown.setAdapter(adapter);

                ////////////////////////////////////////////////////////////////////////////
                //dropdown.setOnItemClickListener();

                dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                      @Override
                                                      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                          String textSelected = dropdown.getSelectedItem().toString();
                                                          int Q=Integer.parseInt(textSelected);
                                                          Context context = getApplicationContext();
                                                          CharSequence text = "Spinner seeleteced= "+ String.valueOf(Q);
                                                          int duration = Toast.LENGTH_SHORT;
                                                          Toast toast = Toast.makeText(context, text, duration);
                                                          toast.show();

                                                      }

                                                      @Override
                                                      public void onNothingSelected(AdapterView<?> parent) {

                                                      }
                                                  });


                // add question
                QuestionTxt = findViewById(R.id.MCQ_single_question);
                QuestionTxt.setText(q.qText);

                //add qno
                QuesNo = findViewById(R.id.AttemptQuizMCQ_single_qno);
                QuesNo.setText(String.valueOf(Qno + 1));

                //add options
                RadioButton RB1;
                RadioButton RB2;
                RadioButton RB3;

                RB1 = findViewById(R.id.MCQ_single_sound);
                RB1.setText(q.qAnsPossible.get(0));

                RB2 = findViewById(R.id.MCQ_single_vibration);
                RB2.setText(q.qAnsPossible.get(1));

                RB3 = findViewById(R.id.MCQ_single_silent);
                RB3.setText(q.qAnsPossible.get(2));

                //////////////////////////////////////////////////////////

            } else if (q.qType == 2) //MCQ multiple
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
                QuestionTxt = findViewById(R.id.MCQ_mul_question);
                QuestionTxt.setText(q.qText);

                //add qno
                QuesNo = findViewById(R.id.AttemptQuizMCQ_mul_qno);
                QuesNo.setText(String.valueOf(Qno + 1));

                //add options
                CheckBox RB1;
                CheckBox RB2;
                CheckBox RB3;

                RB1 = findViewById(R.id.MCQ_mul_checkBox);
                RB1.setText(q.qAnsPossible.get(0));

                RB2 = findViewById(R.id.MCQ_mul_checkBox2);
                RB2.setText(q.qAnsPossible.get(1));

                RB3 = findViewById(R.id.MCQ_mul_checkBox3);
                RB3.setText(q.qAnsPossible.get(2));


            } else if (q.qType == 3) //true false
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


                // add question
                QuestionTxt = findViewById(R.id.mcq_tf_question);
                QuestionTxt.setText(q.qText);

                //add qno
                QuesNo = findViewById(R.id.AttemptQuizMCQ_tf_qno);
                QuesNo.setText(String.valueOf(Qno + 1));




            }

            ///////////////////////////////////////////////////////////////

            else if(q.qType==4) //ow question
            {
                //sub_ow_question
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

                // add question
                QuestionTxt = findViewById(R.id.sub_ow_question);
                QuestionTxt.setText(q.qText);

                //add qno
                QuesNo = findViewById(R.id.AttemptQuizSubjective_ow_qno);
                QuesNo.setText(String.valueOf(Qno + 1));

            }

            else if(q.qType==5) //mw question
            {
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


                // add question
                QuestionTxt = findViewById(R.id.sub_words_question);
                QuestionTxt.setText(q.qText);

                //add qno
                QuesNo = findViewById(R.id.AttemptQuizsubjective_mul_qno);
                QuesNo.setText(String.valueOf(Qno + 1));


            }

            else {
                //setContentView(R.layout.attempt_quiz_mcq_multiple_ans);
                //get the spinner from the xml.
                //Spinner dropdown = findViewById(R.id.spinner1);
                //create a list of items for the spinner.
                //String[] items = new String[]{"1", "2", "3"};
                //create an adapter to describe how the items are displayed, adapters are used in several places in android.
                //There are multiple variations of this, but this is the basic variant.
                //ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
                //set the spinners adapter to the previously created one.
                //dropdown.setAdapter(adapter);
            }
        }

    }

    public void StudentAttemptPrevQuestion(View view) {

        Qno--; //previous question


        Context c= getApplicationContext();
        if(Qno<0)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("Close Quiz?");
            builder.setMessage("");
            builder.setPositiveButton("Confirm",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(c, StudentQuizListActivity.class);
                            startActivity(intent);

                        }
                    });
            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

        }
        else {


            Question q = questionsList.get(Qno);
            TextView QuestionTxt;
            TextView QuesNo;

            /////////////////////////// timer per question ///////////////////////////////////////
           if(sw[Qno+1]!=null) {
               if (sw[Qno + 1].running == true) //question from which back button was pressed-> Qno+1
               {
                   sw[Qno + 1].pause();
                   //TODO: if question is attempted,then store time
                   if (Attempted == true) {
                       long timetaken = sw[Qno + 1].getElapsedTimeSecs();
                       Time_perQuestion[Qno + 1] = timetaken;
                       sw[Qno + 1].stop();
                   }
               }
               if (sw[Qno].paused == true) //question to which back button took->Qno
               {
                   sw[Qno].resume();
               }
           }
            //////////////////////////////////////////////////////////////////////////////////////
            if (q.qType == 1)// MCQ single
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
                QuestionTxt = findViewById(R.id.MCQ_single_question);
                QuestionTxt.setText(q.qText);

                //add qno
                QuesNo = findViewById(R.id.AttemptQuizMCQ_single_qno);
                QuesNo.setText(String.valueOf(Qno+1));

                //add options
                RadioButton RB1;
                RadioButton RB2;
                RadioButton RB3;

                RB1 = findViewById(R.id.MCQ_single_sound);
                RB1.setText(q.qAnsPossible.get(0));

                RB2 = findViewById(R.id.MCQ_single_vibration);
                RB2.setText(q.qAnsPossible.get(1));

                RB3 = findViewById(R.id.MCQ_single_silent);
                RB3.setText(q.qAnsPossible.get(2));

            } else if (q.qType == 2) //MCQ multiple
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
                QuestionTxt = findViewById(R.id.MCQ_mul_question);
                QuestionTxt.setText(q.qText);

                //add qno
                QuesNo = findViewById(R.id.AttemptQuizMCQ_mul_qno);
                QuesNo.setText(String.valueOf(Qno+1));

                //add options
                CheckBox RB1;
                CheckBox RB2;
                CheckBox RB3;

                RB1 = findViewById(R.id.MCQ_mul_checkBox);
                RB1.setText(q.qAnsPossible.get(0));

                RB2 = findViewById(R.id.MCQ_mul_checkBox2);
                RB2.setText(q.qAnsPossible.get(1));

                RB3 = findViewById(R.id.MCQ_mul_checkBox3);
                RB3.setText(q.qAnsPossible.get(2));


            } else if (q.qType == 3) //true false
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


                // add question
                QuestionTxt = findViewById(R.id.mcq_tf_question);
                QuestionTxt.setText(q.qText);

                //add qno
                QuesNo = findViewById(R.id.AttemptQuizMCQ_tf_qno);
                QuesNo.setText(String.valueOf(Qno+1));


            }

            ///////////////////////////////////////////////////////////////
            else if(q.qType==4) //ow question
            {
                //sub_ow_question
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

                // add question
                QuestionTxt = findViewById(R.id.sub_ow_question);
                QuestionTxt.setText(q.qText);

                //add qno
                QuesNo = findViewById(R.id.AttemptQuizSubjective_ow_qno);
                QuesNo.setText(String.valueOf(Qno+1));

            }

            else if(q.qType==5) //mw question
            {
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


                // add question
                QuestionTxt = findViewById(R.id.sub_words_question);
                QuestionTxt.setText(q.qText);

                //add qno
                QuesNo = findViewById(R.id.AttemptQuizsubjective_mul_qno);
                QuesNo.setText(String.valueOf(Qno+1));


            }
            else {
                //setContentView(R.layout.attempt_quiz_mcq_multiple_ans);
                //get the spinner from the xml.
                //Spinner dropdown = findViewById(R.id.spinner1);
                //create a list of items for the spinner.
                //String[] items = new String[]{"1", "2", "3"};
                //create an adapter to describe how the items are displayed, adapters are used in several places in android.
                //There are multiple variations of this, but this is the basic variant.
                //ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
                //set the spinners adapter to the previously created one.
                //dropdown.setAdapter(adapter);
            }
        }
    }

    public void StudentAttemptGotToFirstQues(View view) {

        Qno=0; //question no 1
        Context c= getApplicationContext();
        if(Qno<0)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("Close Quiz?");
            builder.setMessage("");
            builder.setPositiveButton("Confirm",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(c, StudentQuizListActivity.class);
                            startActivity(intent);

                        }
                    });
            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

        }
        else {


            Question q = questionsList.get(Qno);
            TextView QuestionTxt;
            TextView QuesNo;

            //////////////////////////////////////////////////////////////////

            if (q.qType == 1)// MCQ single
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

                //String text = dropdown.getSelectedItem().toString();


                // add question
                QuestionTxt = findViewById(R.id.MCQ_single_question);
                QuestionTxt.setText(q.qText);

                //add qno
                QuesNo = findViewById(R.id.AttemptQuizMCQ_single_qno);
                QuesNo.setText(String.valueOf(Qno + 1));

                //add options
                RadioButton RB1;
                RadioButton RB2;
                RadioButton RB3;

                RB1 = findViewById(R.id.MCQ_single_sound);
                RB1.setText(q.qAnsPossible.get(0));

                RB2 = findViewById(R.id.MCQ_single_vibration);
                RB2.setText(q.qAnsPossible.get(1));

                RB3 = findViewById(R.id.MCQ_single_silent);
                RB3.setText(q.qAnsPossible.get(2));

            } else if (q.qType == 2) //MCQ multiple
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
                QuestionTxt = findViewById(R.id.MCQ_mul_question);
                QuestionTxt.setText(q.qText);

                //add qno
                QuesNo = findViewById(R.id.AttemptQuizMCQ_mul_qno);
                QuesNo.setText(String.valueOf(Qno + 1));

                //add options
                CheckBox RB1;
                CheckBox RB2;
                CheckBox RB3;

                RB1 = findViewById(R.id.MCQ_mul_checkBox);
                RB1.setText(q.qAnsPossible.get(0));

                RB2 = findViewById(R.id.MCQ_mul_checkBox2);
                RB2.setText(q.qAnsPossible.get(1));

                RB3 = findViewById(R.id.MCQ_mul_checkBox3);
                RB3.setText(q.qAnsPossible.get(2));


            } else if (q.qType == 3) //true false
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


                // add question
                QuestionTxt = findViewById(R.id.mcq_tf_question);
                QuestionTxt.setText(q.qText);

                //add qno
                QuesNo = findViewById(R.id.AttemptQuizMCQ_tf_qno);
                QuesNo.setText(String.valueOf(Qno + 1));


            }

            ///////////////////////////////////////////////////////////////
            else if(q.qType==4) //ow question
            {
                //sub_ow_question
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

                // add question
                QuestionTxt = findViewById(R.id.sub_ow_question);
                QuestionTxt.setText(q.qText);

                //add qno
                QuesNo = findViewById(R.id.AttemptQuizSubjective_ow_qno);
                QuesNo.setText(String.valueOf(Qno + 1));

            }

            else if(q.qType==5) //mw question
            {
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


                // add question
                QuestionTxt = findViewById(R.id.sub_words_question);
                QuestionTxt.setText(q.qText);

                //add qno
                QuesNo = findViewById(R.id.AttemptQuizsubjective_mul_qno);
                QuesNo.setText(String.valueOf(Qno + 1));


            }
            else {
                //setContentView(R.layout.attempt_quiz_mcq_multiple_ans);
                //get the spinner from the xml.
                //Spinner dropdown = findViewById(R.id.spinner1);
                //create a list of items for the spinner.
                //String[] items = new String[]{"1", "2", "3"};
                //create an adapter to describe how the items are displayed, adapters are used in several places in android.
                //There are multiple variations of this, but this is the basic variant.
                //ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
                //set the spinners adapter to the previously created one.
                //dropdown.setAdapter(adapter);
            }
        }
    }

    public void StudentAttemptGotToLastQues(View view) {

        Qno=questionsList.size()-1 ; //last question question
        Context c= getApplicationContext();
        if(Qno<0)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("Close Quiz?");
            builder.setMessage("");
            builder.setPositiveButton("Confirm",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(c, StudentQuizListActivity.class);
                            startActivity(intent);

                        }
                    });
            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

        }
        else {


            Question q = questionsList.get(Qno);
            TextView QuestionTxt;
            TextView QuesNo;

            //////////////////////////////////////////////////////////////////

            if (q.qType == 1)// MCQ single
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
                QuestionTxt = findViewById(R.id.MCQ_single_question);
                QuestionTxt.setText(q.qText);

                //add qno
                QuesNo = findViewById(R.id.AttemptQuizMCQ_single_qno);
                QuesNo.setText(String.valueOf(Qno + 1));

                //add options
                RadioButton RB1;
                RadioButton RB2;
                RadioButton RB3;

                RB1 = findViewById(R.id.MCQ_single_sound);
                RB1.setText(q.qAnsPossible.get(0));

                RB2 = findViewById(R.id.MCQ_single_vibration);
                RB2.setText(q.qAnsPossible.get(1));

                RB3 = findViewById(R.id.MCQ_single_silent);
                RB3.setText(q.qAnsPossible.get(2));

            } else if (q.qType == 2) //MCQ multiple
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
                QuestionTxt = findViewById(R.id.MCQ_mul_question);
                QuestionTxt.setText(q.qText);

                //add qno
                QuesNo = findViewById(R.id.AttemptQuizMCQ_mul_qno);
                QuesNo.setText(String.valueOf(Qno + 1));

                //add options
                CheckBox RB1;
                CheckBox RB2;
                CheckBox RB3;

                RB1 = findViewById(R.id.MCQ_mul_checkBox);
                RB1.setText(q.qAnsPossible.get(0));

                RB2 = findViewById(R.id.MCQ_mul_checkBox2);
                RB2.setText(q.qAnsPossible.get(1));

                RB3 = findViewById(R.id.MCQ_mul_checkBox3);
                RB3.setText(q.qAnsPossible.get(2));


            } else if (q.qType == 3) //true false
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


                // add question
                QuestionTxt = findViewById(R.id.mcq_tf_question);
                QuestionTxt.setText(q.qText);

                //add qno
                QuesNo = findViewById(R.id.AttemptQuizMCQ_tf_qno);
                QuesNo.setText(String.valueOf(Qno + 1));


            }

            ///////////////////////////////////////////////////////////////
            else if(q.qType==4) //ow question
            {
                //sub_ow_question
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

                // add question
                QuestionTxt = findViewById(R.id.sub_ow_question);
                QuestionTxt.setText(q.qText);

                //add qno
                QuesNo = findViewById(R.id.AttemptQuizSubjective_ow_qno);
                QuesNo.setText(String.valueOf(Qno + 1));

            }

            else if(q.qType==5) //mw question
            {
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


                // add question
                QuestionTxt = findViewById(R.id.sub_words_question);
                QuestionTxt.setText(q.qText);

                //add qno
                QuesNo = findViewById(R.id.AttemptQuizsubjective_mul_qno);
                QuesNo.setText(String.valueOf(Qno + 1));


            }
            else {
                //setContentView(R.layout.attempt_quiz_mcq_multiple_ans);
                //get the spinner from the xml.
                //Spinner dropdown = findViewById(R.id.spinner1);
                //create a list of items for the spinner.
                //String[] items = new String[]{"1", "2", "3"};
                //create an adapter to describe how the items are displayed, adapters are used in several places in android.
                //There are multiple variations of this, but this is the basic variant.
                //ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
                //set the spinners adapter to the previously created one.
                //dropdown.setAdapter(adapter);
            }
        }


    }

    public void QuizSubmitButton(View view) {
        //update Status of quiz to submitted
        Intent intent = new Intent(this, StudentQuizListActivity.class);
        startActivity(intent);
        Context context = getApplicationContext();
        CharSequence text = "Quiz Submitted";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();


    }
}

