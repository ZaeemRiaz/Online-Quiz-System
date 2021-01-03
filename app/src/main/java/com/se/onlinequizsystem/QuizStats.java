package com.se.onlinequizsystem;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class QuizStats {
    private static final String TAG = "=== QuizStats ===";
    int totalStudents;
    int quizAttempted;
    Double averageTime;
    ArrayList<Double> averageQuestionTime;

    public QuizStats(Context context, int quizID) {
        int idx = 0;
        this.averageQuestionTime = new ArrayList<Double>();


        QuizDbHelper dbHelper = new QuizDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            Log.d(TAG, "QuizStats: read quiz stats");

            String query = "select quizID, questionID, " +
                    "AVG(timeTaken) as avgTimeQuestion, " +
                    "AVG(marksScored) as avgMarks " +
                    "from studentAttempt " +
                    "where quizID = ? " +
                    " GROUP by quizID, questionID;";
            Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(quizID)});

            while (cursor.moveToNext()) {
                idx += 1;
                this.averageTime += Double.parseDouble(cursor.getString(cursor.getColumnIndex("avgTimeQuestion")));
                this.averageQuestionTime.add(Double.parseDouble(cursor.getString(cursor.getColumnIndex("avgTimeQuestion"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Log.d(TAG, "QuizStats: read total students");
            String query = "select COUNT(userID) as cnt from users where uType = 0;";
            Cursor cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                this.totalStudents = Integer.parseInt(cursor.getString(cursor.getColumnIndex("cnt")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d(TAG, "QuizStats: stats read");
        db.close();

        this.quizAttempted = idx;
    }
}
