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
            String query = "select COUNT(userID) as cnt from users where uType = 0;";
            Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(quizID)});
            while (cursor.moveToNext()) {
                this.totalStudents = Integer.parseInt(cursor.getString(cursor.getColumnIndex("cnt")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        db.close();

        this.quizAttempted = idx;
//        Connection c = null;
//        try {
//            Class.forName("org.sqlite.JDBC");
//            c = DriverManager.getConnection("jdbc:sqlite:test.db");
//            c.setAutoCommit(false);
//            PreparedStatement prep = c.prepareStatement(
//                    "select quizID, questionID, AVG(timeTaken) as avgTimeQuestion, AVG(marksScored) as avgMarks from studentAttempt where quizID = ? GROUP by quizID, questionID;");
//            prep.setInt(1, 1);
//            ResultSet res = prep.executeQuery();
//
//            while (res.next()) {
//                idx += 1;
//                this.averageTime += res.getDouble("avgTimeQuestion");
//                this.averageQuestionTime.add(res.getDouble("avgTimeQuestion"));
//            }
//            prep.close();
//            c.commit();
//            c.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            Class.forName("org.sqlite.JDBC");
//            c = DriverManager.getConnection("jdbc:sqlite:test.db");
//            c.setAutoCommit(false);
//            PreparedStatement prep = c.prepareStatement("select COUNT(userID) as cnt from users where uType = 0;");
//            ResultSet res = prep.executeQuery();
//            while (res.next()) {
//                this.totalStudents = res.getInt("cnt");
//            }
//            prep.close();
//            c.commit();
//            c.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    }
}
