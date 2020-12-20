package com.se.onlinequizsystem;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

class AttemptRow
{
    String subject;
    String duration;
    String total_time;
    String marks;
    String time_left;
    String ButtonTxt;



    public AttemptRow(String r, String o, String or, String b, String t) {

        subject=r;
        duration=o;
        total_time=or;
        marks=b;
        time_left=t;
    }
}
public class StudentQuizListdapter extends RecyclerView.Adapter<StudentQuizListdapter.AttemptQuizHolder> {
    protected ArrayList<AttemptRow> quizzes;
    private AttemptQuizHolder holder;
    private int position;

    public StudentQuizListdapter(Context c) {
        this.quizzes= new ArrayList<AttemptRow>();
        Resources res=c.getResources();
        String[]subjects=res.getStringArray(R.array.subjects);
        String[]durations=res.getStringArray(R.array.durations);
        String[]times=res.getStringArray(R.array.total_times); //totaltime->status
        String[]markss=res.getStringArray(R.array.marks);
        String[]time_lefts=res.getStringArray(R.array.timeleft);

        for(int i=0;i<subjects.length;i++)
        {
            quizzes.add(new AttemptRow(subjects[i], durations[i], times[i], markss[i], time_lefts[i]));
        }
    }
    @NonNull
    @Override
    public StudentQuizListdapter.AttemptQuizHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.stduent_quiz_list_item, parent,false);
        return new StudentQuizListdapter.AttemptQuizHolder(view);

    }



    @Override
    public void onBindViewHolder(@NonNull StudentQuizListdapter.AttemptQuizHolder holder, int position) {

        holder.txt1.setText(quizzes.get(position).subject);
        holder.txt2.setText(quizzes.get(position).duration);
        holder.txt3.setText(quizzes.get(position).total_time);
        holder.txt4.setText(quizzes.get(position).marks);
        holder.txt5.setText(quizzes.get(position).time_left);


    }

    @Override
    public int getItemCount() {
        return quizzes.size();
    }

    public class AttemptQuizHolder extends RecyclerView.ViewHolder
    {

        TextView txt1;
        TextView txt2;
        TextView txt3;
        TextView txt4;
        TextView txt5;

        public AttemptQuizHolder (@NonNull View itemView) {

            super(itemView);

            txt1=itemView.findViewById(R.id.subject);

            txt2=itemView.findViewById(R.id.duration);
            txt3=itemView.findViewById(R.id.time);
            txt4=itemView.findViewById(R.id.marks);
            txt5=itemView.findViewById(R.id.time_left);

        }
    }
}

