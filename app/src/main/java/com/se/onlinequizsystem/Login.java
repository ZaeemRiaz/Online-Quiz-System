package com.se.onlinequizsystem;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login {
    private static final String TAG = "=== Login ===";

    static int loginUser(Context context, String id, String password) {
        int type = -1;

        QuizDbHelper dbHelper = new QuizDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            String query = "select * from users where userName=? and uPassword=?";
            Cursor cursor = db.rawQuery(query, new String[]{id, password});

            if (cursor != null && cursor.moveToFirst()) {                   // Always one row returned.
                type = Integer.parseInt(cursor.getString(cursor.getColumnIndex("uType")));
                Log.d(TAG, "loginUser: login successful");
            } else {
                Log.d(TAG, "loginUser: login unsuccessful   nothing returned");
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "loginUser: login unsuccessful");
        }
        db.close();
        return type;
    }

    static Question fetchMCQ(Context context, Integer id) {
        Question q = new Question();

        QuizDbHelper dbHelper = new QuizDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            String query = "select * from Question where qID = ?";
            Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});

            if (cursor != null && cursor.moveToFirst()) {                   // Always one row returned.
                q.qId = Integer.parseInt(cursor.getString(cursor.getColumnIndex("qID")));
                q.qType = Integer.parseInt(cursor.getString(cursor.getColumnIndex("qType")));
                q.qText = cursor.getString(cursor.getColumnIndex("qText"));
                q.qMarks = Integer.parseInt(cursor.getString(cursor.getColumnIndex("qMarks")));
                q.qAnsPossible.add(cursor.getString(cursor.getColumnIndex("posAns1")));
                q.qAnsPossible.add(cursor.getString(cursor.getColumnIndex("posAns2")));
                q.qAnsPossible.add(cursor.getString(cursor.getColumnIndex("posAns3")));
                q.qAnsPossible.add(cursor.getString(cursor.getColumnIndex("posAns4")));
                q.trueAnswers.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("valAns1"))));
                q.trueAnswers.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("valAns2"))));
                q.trueAnswers.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("valAns3"))));
                q.trueAnswers.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("valAns4"))));
            } else {
                Log.d(TAG, "fetchMCQ: 0 rows read");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();


//        Connection c = null;
//        PreparedStatement stmt = null;
//        String sql;
//        try {
//            Class.forName("org.sqlite.JDBC");
//            c = DriverManager.getConnection("jdbc:sqlite:test.db");
//            c.setAutoCommit(false);
////         System.out.println("Opened database successfully");
//
//            sql = "select * from Question where qID=?";
//            stmt = c.prepareStatement(sql);
//            // Parameters
//            stmt.setInt(1, id);
//            // Execute
//            ResultSet res = stmt.executeQuery();
//
//            if (res.next()) {
//                q.qId = res.getInt("qID");
//                q.qType = res.getInt("qType");
//                q.qText = res.getString("qText");
//                q.qMarks = res.getInt("qMarks");
//                q.qAnsPossible.add(res.getString("posAns1"));
//                q.qAnsPossible.add(res.getString("posAns2"));
//                q.qAnsPossible.add(res.getString("posAns3"));
//                q.qAnsPossible.add(res.getString("posAns4"));
//                q.trueAnswers.add(res.getInt("valAns1"));
//                q.trueAnswers.add(res.getInt("valAns2"));
//                q.trueAnswers.add(res.getInt("valAns3"));
//                q.trueAnswers.add(res.getInt("valAns4"));
//
//            }
//            stmt.close();
//            c.commit();
//            c.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return q;
    }

    static boolean checkSingularMCQ(Context context, Integer qId, Integer choice) // options 1, 2, 3, 4 selected corresond to 0, 1, 2, 3
    {
        boolean check = false;

        QuizDbHelper dbHelper = new QuizDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            String query = "select * from Question where qID = ?";
            Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(qId)});

            if (cursor != null && cursor.moveToFirst()) {                   // Always one row returned.
                if (choice.equals(1) && Integer.parseInt(cursor.getString(cursor.getColumnIndex("valAns1"))) == 1) {
                    check = true;
                }
                if (choice.equals(2) && Integer.parseInt(cursor.getString(cursor.getColumnIndex("valAns2"))) == 1) {
                    check = true;
                }
                if (choice.equals(3) && Integer.parseInt(cursor.getString(cursor.getColumnIndex("valAns3"))) == 1) {
                    check = true;
                }
                if (choice.equals(4) && Integer.parseInt(cursor.getString(cursor.getColumnIndex("valAns4"))) == 1) {
                    check = true;
                }
            } else {
                Log.d(TAG, "checkSingularMCQ: 0 rows read");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();


//        Connection c = null;
//        PreparedStatement stmt = null;
//        String sql;
//        try {
//            Class.forName("org.sqlite.JDBC");
//            c = DriverManager.getConnection("jdbc:sqlite:test.db");
//            c.setAutoCommit(false);
////         System.out.println("Opened database successfully");
//
//            sql = "select * from Question where qID=?";
//            stmt = c.prepareStatement(sql);
//            // Parameters
//            stmt.setInt(1, qId);
//            // Execute
//            ResultSet res = stmt.executeQuery();
//
//            if (res.next()) // Question fetched
//            {
//                if (choice.equals(1) && res.getInt("valAns1") == 1) {
//                    check = true;
//                }
//                if (choice.equals(2) && res.getInt("valAns2") == 1) {
//                    check = true;
//                }
//                if (choice.equals(3) && res.getInt("valAns3") == 1) {
//                    check = true;
//                }
//                if (choice.equals(4) && res.getInt("valAns4") == 1) {
//                    check = true;
//                }
//            }
//            stmt.close();
//            c.commit();
//            c.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return check;
    }

    static boolean checkObjective(Context context, Integer qId, int[] choices) // options 1, 2, 3, 4 selected corresond to 0, 1, 2, 3
    {
        boolean check = true;

        QuizDbHelper dbHelper = new QuizDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            String query = "select * from Question where qID = ?";
            Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(qId)});

            if (cursor != null && cursor.moveToFirst()) {                   // Always one row returnd
                int totalAns = choices.length;
                for (int i = 0; i < totalAns; i++) {
                    // TODO: 30-Dec-20 choices 1,2,3,4????
                    if (choices[i] == 1 && Integer.parseInt(cursor.getString(cursor.getColumnIndex("valAns1"))) != 1) {
                        check = false;
                    } else if (choices[i] == 2 && Integer.parseInt(cursor.getString(cursor.getColumnIndex("valAns2"))) != 1) {
                        check = false;
                    } else if (choices[i] == 3 && Integer.parseInt(cursor.getString(cursor.getColumnIndex("valAns3"))) != 1) {
                        check = false;
                    } else if (choices[i] == 4 && Integer.parseInt(cursor.getString(cursor.getColumnIndex("valAns4"))) != 1) {
                        check = false;
                    }
                }
            } else {
                Log.d(TAG, "checkObjective: 0 rows read");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();

//        Connection c = null;
//        PreparedStatement stmt = null;
//        String sql;
//        try {
//            Class.forName("org.sqlite.JDBC");
//            c = DriverManager.getConnection("jdbc:sqlite:test.db");
//            c.setAutoCommit(false);
////         System.out.println("Opened database successfully");
//
//            sql = "select * from Question where qID=?";
//            stmt = c.prepareStatement(sql);
//            // Parameters
//            stmt.setInt(1, qId);
//            // Execute
//            ResultSet res = stmt.executeQuery();
//
//            if (res.next()) // Question fetched
//            {
//                int totalAns = choices.length;
//                for (int i = 0; i < totalAns; i++) {
//                    if (choices[i] == 1 && res.getInt("valAns1") != 1) {
//                        check = false;
//                    } else if (choices[i] == 2 && res.getInt("valAns2") != 1) {
//                        check = false;
//                    } else if (choices[i] == 3 && res.getInt("valAns3") != 1) {
//                        check = false;
//                    } else if (choices[i] == 4 && res.getInt("valAns4") != 1) {
//                        check = false;
//                    }
//                }
//            }
//            stmt.close();
//            c.commit();
//            c.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return check;
    }

    static void addQScore(Context context, Integer userID, Integer quizID, Integer questionID, Integer TimeTaken, Integer marksScored) {

        QuizDbHelper dbHelper = new QuizDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            String query = "insert into studentAttempt values(?,?,?,?,?)";
            Cursor cursor = db.rawQuery(query, new String[]{
                    String.valueOf(userID),
                    String.valueOf(quizID),
                    String.valueOf(questionID),
                    String.valueOf(TimeTaken),
                    String.valueOf(marksScored)});

        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();

//        Connection c = null;
//        Statement stmt = null;
//        String sql = "insert into studentAttempt values(?,?,?,?,?);";
//        try {
//            Class.forName("org.sqlite.JDBC");
//            c = DriverManager.getConnection("jdbc:sqlite:test.db");
//            c.setAutoCommit(false);
//
//            stmt = c.createStatement();
//
//            PreparedStatement prep = c.prepareStatement(sql);
//
//            prep.setInt(1, userID);
//            prep.setInt(2, quizID);
//            prep.setInt(3, questionID);
//            prep.setInt(4, TimeTaken);
//            prep.setInt(5, marksScored);
//            prep.execute();
//
//            stmt.close();
//            c.commit();
//            c.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

}