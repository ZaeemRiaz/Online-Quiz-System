package com.se.onlinequizsystem;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InstructorQuizFeedbackActivity extends AppCompatActivity {

    private static final String TAG = "=== InstructorQuizFeedback ===";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_quiz_feedback);

        TextView averageTimeTakenTextView = findViewById(R.id.instructor_quiz_feedback_average_time_taken);
        TextView quizzesAttemptedTextView = findViewById(R.id.instructor_quiz_feedback_quizzes_attempted);
        TextView totalStudentsTextView = findViewById(R.id.instructor_quiz_feedback_total_students);

        QuizStats quizStats = new QuizStats(this, 1);

        averageTimeTakenTextView.setText(String.valueOf(quizStats.averageTime));
        quizzesAttemptedTextView.setText(String.valueOf(quizStats.quizAttempted));
        totalStudentsTextView.setText(String.valueOf(quizStats.totalStudents));

        // TODO: 02-Jan-21 adapter for list view or something

    }
}