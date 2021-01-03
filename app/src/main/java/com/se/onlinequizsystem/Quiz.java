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
            for (Quiz quiz : listOfQuiz) {
                Log.d(TAG, "getAllQuiz: get questions for quiz: " + String.valueOf(quiz.quizID));
                String query = "select * from quizQuestions where quizID = " + quiz.quizID;
                Cursor cursor = db.rawQuery(query, null);
                while (cursor.moveToNext()) {
                    questionID = Integer.parseInt(cursor.getString(cursor.getColumnIndex("questionID")));
                    quiz.listOfQuestions.add(new Question(questionID));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
        for (Quiz quiz : listOfQuiz) {
            Log.d(TAG, "getAllQuiz: get details for quiz: " + String.valueOf(quiz.quizID));
            for (Question question : quiz.listOfQuestions) {
                Log.d(TAG, "getAllQuiz: get details for question: " + String.valueOf(question.qId));
                question.updateQuestion(context);

            }
        }

        Log.d(TAG, "getAllQuiz: quiz list generated ");
        return listOfQuiz;
    }

    static void insertQuestionAttempt(Context context, int userID, int quizID, int questionID, int timeTaken, String choices) {
        QuizDbHelper dbHelper = new QuizDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            String query = "insert into studentAttempt values(?,?,?,?,?,?)";
            Cursor cursor = db.rawQuery(query, new String[]{
                    String.valueOf(userID),
                    String.valueOf(quizID),
                    String.valueOf(questionID),
                    String.valueOf(choices),
                    String.valueOf(timeTaken),
                    String.valueOf(0)});

        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
    }

    static void updateQuestionAttempt(Context context, int userID, int quizID, int questionID, int timeTaken, String choices) {
        QuizDbHelper dbHelper = new QuizDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            String query = "UPDATE studentAttempt SET enteredAns=?, timeTaken=?, marksScored=? WHERE userID=? and QuizID=? and QuestionID=?";
            Cursor cursor = db.rawQuery(query, new String[]{
                    String.valueOf(choices),
                    String.valueOf(timeTaken),
                    String.valueOf(0),
                    String.valueOf(userID),
                    String.valueOf(quizID),
                    String.valueOf(questionID)});

        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
    }

    static void getQuestionAttempt(Context context, int userID, int quizID, int questionID) {
        QuizDbHelper dbHelper = new QuizDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            String query = "SELECT enteredAns, timeTaken, marksScored FROM studentAttempt WHERE userID=? and QuizID=? and QuestionID=?";
            Cursor cursor = db.rawQuery(query, new String[]{
                    String.valueOf(userID),
                    String.valueOf(quizID),
                    String.valueOf(questionID)});
            if (cursor != null && cursor.moveToFirst()) {
                String choices = cursor.getString(cursor.getColumnIndex("enteredAns"));
                int timeTaken = Integer.parseInt(cursor.getString(cursor.getColumnIndex("timeTaken")));
                int marks = Integer.parseInt(cursor.getString(cursor.getColumnIndex("marksScored")));
            }
            } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
    }

    // TODO: 03-Jan-21 getQuestionAttempt()
}
