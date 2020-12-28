package com.se.onlinequizsystem;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//class AttemptRow {
//    String course;
//    String totaltime;
//    String status;
//    String marks;
//    String remaining_time;
//    String ButtonTxt;
//
//
//    public AttemptRow(String x, String y, String z, String a, String b) {
//
//        course = x;
//        totaltime = y;
//        status = z;
//        marks = a;
//        remaining_time = b;
//    }
//}

public class StudentQuizListAdapter extends RecyclerView.Adapter<StudentQuizListAdapter.QuizViewHolder> {
    //    protected ArrayList<AttemptRow> quizList;
    protected ArrayList<Quiz> quizList;
    Context context;
    private QuizViewHolder holder;
    private int position;

    public StudentQuizListAdapter(Context context) {
//        this.quizList = new ArrayList<AttemptRow>();
        this.quizList = Quiz.getAllQuiz(context);
        this.context = context;

//        Resources res = c.getResources();
//        String[] coursess = res.getStringArray(R.array.subjects);
//        String[] totaltimes = res.getStringArray(R.array.durations);
//        String[] statuss = res.getStringArray(R.array.Status);
//        String[] markss = res.getStringArray(R.array.marks);
//        String[] Remaining_time = res.getStringArray(R.array.timeleft);
//
//        for (int i = 0; i < coursess.length; i++) {
//            quizList.add(new AttemptRow(coursess[i], totaltimes[i], statuss[i], markss[i], Remaining_time[i]));
//        }
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.student_quiz_list_item, parent, false);
        return new QuizViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder quizViewHolder, int position) {
        quizViewHolder.quizName.setText(quizList.get(position).quizName);
        quizViewHolder.duration.setText(quizList.get(position).totalTime);
        quizViewHolder.status.setText("TODO");
        // TODO: 28-Dec-20 check status
        quizViewHolder.marks.setText(quizList.get(position).totalMarks);
        quizViewHolder.timeLeft.setText(quizList.get(position).totalTime);
        quizViewHolder.attemptQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StudentAttemptQuiz.class);
                intent.putExtra("quizViewIntent", quizList.get(position));
                context.startActivity(intent);
            }
        });
        quizViewHolder.viewQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StudentViewQuiz.class);
                intent.putExtra("quizViewIntent", quizList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return quizList.size();
    }

    public class QuizViewHolder extends RecyclerView.ViewHolder {
        TextView quizName;
        TextView duration;
        TextView status;
        TextView marks;
        TextView timeLeft;
        Button attemptQuizButton;
        Button viewQuizButton;

        public QuizViewHolder(@NonNull View itemView) {
            super(itemView);
            quizName = itemView.findViewById(R.id.subject);
            duration = itemView.findViewById(R.id.duration);
            status = itemView.findViewById(R.id.status);
            marks = itemView.findViewById(R.id.marks);
            timeLeft = itemView.findViewById(R.id.time_left);
            attemptQuizButton = itemView.findViewById(R.id.student_attempt_quiz_button);
            viewQuizButton = itemView.findViewById(R.id.student_view_quiz_button);
        }
    }
}

