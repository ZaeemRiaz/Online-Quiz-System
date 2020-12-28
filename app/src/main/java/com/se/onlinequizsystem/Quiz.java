package com.se.onlinequizsystem;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
        Connection c = null;
        PreparedStatement stmt = null;
        String sql;
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
            if (cursor != null && cursor.moveToFirst()) {// Always one row returned.
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

//
//        try {
//            Class.forName("org.sqlite.JDBC");
//            c = DriverManager.getConnection("jdbc:sqlite:testq.db");
//            c.setAutoCommit(false);
//            System.out.println("Opened database successfully");
//            sql = "select * from quiz";
//            stmt = c.prepareStatement(sql);
//
//            ResultSet res = stmt.executeQuery();
//            while (res.next()) {
//                quizID = res.getInt("quizID");
//                quizName = res.getString("quizName");
//                difficulty = res.getInt("difficulty");
//                openTime_epoch = res.getLong("openTime");
//                closeTime_epoch = res.getLong("closeTime");
//                TotalTime = res.getInt("totalTimeInSeconds");
//                totalQuestions = res.getInt("totalQuestions");
//                totalMarks = res.getInt("totalMarks");
//                instructions = res.getString("instructions");
//
//
//                //get all questions
//                PreparedStatement prep = c.prepareStatement("select * from quizQuestions where quizID = ?;");
//                prep.setInt(1, quizID);
//                ResultSet quizQuestionIDs = prep.executeQuery();
//                ArrayList<Question> listOfQuestions = new ArrayList<Question>();
//
//                while (quizQuestionIDs.next()) {
//                    questionID = quizQuestionIDs.getInt("questionID");
//                    listOfQuestions.add(new Question(questionID));
//                }
//
//                listOfQuiz.add(new Quiz(quizID, quizName, difficulty, openTime_epoch, closeTime_epoch, TotalTime,
//                        totalQuestions, totalMarks, instructions, listOfQuestions));
//
//
//            }
//            stmt.close();
//            c.commit();
//            c.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        return listOfQuiz;
    }
}
