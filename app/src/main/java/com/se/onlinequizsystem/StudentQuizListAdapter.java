package com.se.onlinequizsystem;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

class AttemptRow
{
    String course;
    String totaltime;
    String status;
    String marks;
    String remaining_time;
    String ButtonTxt;



    public AttemptRow(String x, String y, String z, String a, String b) {

        course=x;
        totaltime=y;
        status=z;
        marks=a;
        remaining_time=b;
    }
}
public class StudentQuizListAdapter extends RecyclerView.Adapter<StudentQuizListAdapter.AttemptQuizHolder> {
    protected ArrayList<AttemptRow> quizList;
    private AttemptQuizHolder holder;
    private int position;

    public StudentQuizListAdapter(Context c) {
        this.quizList = new ArrayList<AttemptRow>();
        Resources res=c.getResources();
        String[]coursess=res.getStringArray(R.array.subjects);
        String[]totaltimes=res.getStringArray(R.array.durations);
        String[]statuss=res.getStringArray(R.array.Status);
        String[]markss=res.getStringArray(R.array.marks);
        String[]Remaining_time=res.getStringArray(R.array.timeleft);

        for(int i=0;i<coursess.length;i++)
        {
            quizList.add(new AttemptRow(coursess[i], totaltimes[i], statuss[i], markss[i], Remaining_time[i]));
        }
    }
    @NonNull
    @Override
    public StudentQuizListAdapter.AttemptQuizHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.student_quiz_list_item, parent,false);
        return new StudentQuizListAdapter.AttemptQuizHolder(view);

    }



    @Override
    public void onBindViewHolder(@NonNull StudentQuizListAdapter.AttemptQuizHolder holder, int position) {

        holder.t1.setText(quizList.get(position).course);
        holder.t2.setText(quizList.get(position).totaltime);
        holder.t3.setText(quizList.get(position).status);
        holder.t4.setText(quizList.get(position).marks);
        holder.t5.setText(quizList.get(position).remaining_time);


    }

    @Override
    public int getItemCount() {
        return quizList.size();
    }

    public class AttemptQuizHolder extends RecyclerView.ViewHolder
    {

        TextView t1;
        TextView t2;
        TextView t3;
        TextView t4;
        TextView t5;

        public AttemptQuizHolder (@NonNull View itemView) {

            super(itemView);

            t1=itemView.findViewById(R.id.subject);

            t2=itemView.findViewById(R.id.duration);
            t3=itemView.findViewById(R.id.time);
            t4=itemView.findViewById(R.id.marks);
            t5=itemView.findViewById(R.id.time_left);

        }
    }
}

