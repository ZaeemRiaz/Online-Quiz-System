package com.se.onlinequizsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ListOfQuizesStudentSide extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_quizes_student_side);
        RecyclerView list = findViewById(R.id.recyclerViewActiveQuizzes);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(new StudentQuizListdapter(this));
    }

    public void AttemptListener(View view)
    {
        Intent intent = new Intent(this, Attempt_Quiz_2.class);
        startActivity(intent);
    }
    public void ViewQuiztListener(View view)
    {
        Intent intent = new Intent(this, View_Quiz_Mcq_single_ans.class);
        startActivity(intent);
    }

    public void FeedbackQuiztListener(View view)
    {
        Intent intent = new Intent(this, Student_Feedback.class);
        startActivity(intent);
    }


}