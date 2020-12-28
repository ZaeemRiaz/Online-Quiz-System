package com.se.onlinequizsystem;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

public class Quiz implements Serializable {
    private static final String TAG = "=== Quiz ===";
    int quizID;
    String quizName;
    int difficulty;
    long openTime;
    long closeTime;
    int totalTime;
    int totalQuestions;
    int totalMarks;
    String instructions;
    ArrayList<Question> listOfQuestions;

    public Quiz(int quizID_, String quizName_, int difficulty_, Long openTime_, Long closeTime_, int totalTime_,
                int totalQuestions_, int totalMarks_, String instructions_, ArrayList<Question> listOfQuestions_) {
        quizID = quizID_;
        quizName = quizName_;
        difficulty = difficulty_;
        openTime = openTime_;
        closeTime = closeTime_;
        totalTime = totalTime_;
        totalQuestions = totalQuestions_;
        totalMarks = totalMarks_;
        instructions = instructions_;
        listOfQuestions = listOfQuestions_;
    }

    public static ArrayList<Quiz> getAllQuiz(Context context) {
        int quizID;
        String quizName;
        int difficulty;
        long openTime_epoch;
        long closeTime_epoch;
        int TotalTime;
        int totalQuestions;
        int totalMarks;
        int questionID;
        String instructions;
        ArrayList<Quiz> listOfQuiz = new ArrayList<Quiz>();

        QuizDbHelper dbHelper = new QuizDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            Log.d(TAG, "getAllQuiz: select all from quiz");
            String query = "select * from quiz";
            Cursor cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                quizID = Integer.parseInt(cursor.getString(cursor.getColumnIndex("quizID")));
                quizName = cursor.getString(cursor.getColumnIndex("quizName"));
                difficulty = Integer.parseInt(cursor.getString(cursor.getColumnIndex("difficulty")));
                openTime_epoch = Long.parseLong(cursor.getString(cursor.getColumnIndex("openTime")));
                closeTime_epoch = Long.parseLong(cursor.getString(cursor.getColumnIndex("closeTime")));
                TotalTime = Integer.parseInt(cursor.getString(cursor.getColumnIndex("totalTimeInSeconds")));
                totalQuestions = Integer.parseInt(cursor.getString(cursor.getColumnIndex("totalQuestions")));
                totalMarks = Integer.parseInt(cursor.getString(cursor.getColumnIndex("totalMarks")));
                instructions = cursor.getString(cursor.getColumnIndex("instructions"));

                Log.d(TAG, "getAllQuiz: quizId: " + quizID);
                ArrayList<Question> listOfQuestions = new ArrayList<Question>();

                listOfQuiz.add(new Quiz(quizID, quizName, difficulty, openTime_epoch, closeTime_epoch, TotalTime,
                        totalQuestions, totalMarks, instructions, listOfQuestions));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Log.d(TAG, "getAllQuiz: get questions for quiz");
            for (Quiz quiz : listOfQuiz) {
                String query = "select * from quizQuestions where quizID = " + quiz.quizID;
                Cursor cursor = db.rawQuery(query, null);
                if (cursor != null && cursor.moveToFirst()) {// Always one row returned.
                    while (cursor.moveToNext()) {
                        questionID = Integer.parseInt(cursor.getString(cursor.getColumnIndex("questionID")));
                        quiz.listOfQuestions.add(new Question(questionID));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
        Log.d(TAG, "getAllQuiz: get details for question");
        for (Quiz quiz : listOfQuiz) {
            for (Question question : quiz.listOfQuestions) {
                question.updateQuestion(context);
            }
        }

        Log.d(TAG, "getAllQuiz: quiz list generated ");
        return listOfQuiz;
    }
}
