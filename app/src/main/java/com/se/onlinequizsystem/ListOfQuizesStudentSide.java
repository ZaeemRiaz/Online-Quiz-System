package com.se.onlinequizsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListOfQuizesStudentSide extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_quizes_student_side);
        RecyclerView list = findViewById(R.id.recyclerViewActiveQuizzes);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(new StudentQuizListdapter(this));
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        // Not calling super, disables back button in current screen.
    }

    public void AttemptListener(View view) {
        Intent intent = new Intent(this, Attempt_Quiz_2.class);
        startActivity(intent);
    }

    public void ViewQuiztListener(View view) {
        Intent intent = new Intent(this, View_Quiz_Mcq_single_ans.class);
        startActivity(intent);
    }

    public void FeedbackQuiztListener(View view) {
        Intent intent = new Intent(this, Student_Feedback.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_student_quiz_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle item selection
        Intent intent;
        switch (item.getItemId()) {
            case R.id.student_ql_log_out:
                intent = new Intent(this, Login.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}